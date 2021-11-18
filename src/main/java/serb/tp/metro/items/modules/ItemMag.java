package serb.tp.metro.items.modules;

import java.util.Date;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import serb.tp.metro.DebugMessage;
import serb.tp.metro.Main;
import serb.tp.metro.client.Type;
import serb.tp.metro.containers.InventoryItemStorage;
import serb.tp.metro.creativetabs.LoadTabs;
import serb.tp.metro.database.Reader;
import serb.tp.metro.items.Item3D;
import serb.tp.metro.items.ItemChestrig;
import serb.tp.metro.items.weapons.ItemBullet;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.server.ChangeAmmoMessage;
import serb.tp.metro.network.server.LoadAmmoMessage;
import serb.tp.metro.network.server.UnloadAmmoMessage;
import serb.tp.metro.utils.DefenceVariables;

public class ItemMag extends ItemWeaponModule
{

	private int maxAmmo;
	private int cooldownLoading;
	private int cooldownUnloading;
    private ItemBullet[] bullets;
    

    public ItemMag(String name, String description, float weight, String model, float[] sizeModel, float[] pos,
			float[] rotation, float[] onInventoryPos, float[] rightHandPos, float[] rightHandRotation,
			float verticalRecoilMod, float horizontalRecoilMod, float convenienceMod, float accuracyMod,
			float penetrationMod, float jummingMod, int maxAmmo ,String[] bullets, int cooldownLoading, int cooldownUnloading) {
		super(name, description, weight, model, sizeModel, pos, rotation, onInventoryPos, rightHandPos, rightHandRotation,
				verticalRecoilMod, horizontalRecoilMod, convenienceMod, accuracyMod, penetrationMod, jummingMod);
        this.maxAmmo = maxAmmo;
        this.cooldownLoading = cooldownLoading;
        this.cooldownUnloading = cooldownUnloading;
        this.setMaxStackSize(1);
        this.bullets = new ItemBullet[bullets.length];
        for (int i = 0; i<bullets.length; i++)
        	this.bullets[i] = (ItemBullet) GameRegistry.findItem(Main.modid, "item." + bullets[i].trim());
	}

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean isAdv) {
        if (!itemStack.hasTagCompound()) return;
        NBTTagCompound nbt = itemStack.getTagCompound();
        list.add(Type.getTranslate("characteristic.guns.loadammo") +": " + (new ItemStack(bullets[nbt.getInteger("getBullet")]).getDisplayName()));
        list.add(Type.getTranslate("characteristic.guns.ammunition") + ": " + nbt.getIntArray("bullets").length); 
        list.add(Type.getTranslate("characteristic.all.weight")+": " + String.format("%.2f", nbt.getFloat("weight")/1000) + Type.getTranslate("characteristic.all.weight.kg"));

    }
    
    @Override
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        ItemStack hold = new ItemStack(par1);
        hold.setTagCompound(new NBTTagCompound());
        hold.stackTagCompound.setIntArray("bullets", new int[]{});
        hold.stackTagCompound.setLong("counter", new Date().getTime());
        hold.stackTagCompound.setInteger("getBullet", 0);
        hold.stackTagCompound.setFloat("weight", weight);
        par3List.add(hold);
    }
    
    public ItemBullet getBullet(ItemStack itemStack) {
    	return bullets[itemStack.stackTagCompound.getInteger("getBullet")];
    }
    
    public ItemStack unloadAmmo(EntityPlayer player, World world) 
    {
    	
    	ItemStack bullet = null;
    	ItemStack hold = player.inventory.getCurrentItem();
    	
    	if (hold.hasTagCompound() && hold.getTagCompound().getIntArray("bullets").length>0 && hold.stackTagCompound.getLong("counter")+cooldownUnloading <= world.getTotalWorldTime())
    	{
        	if (player.inventory.getStackInSlot(19)!=null && player.inventory.getStackInSlot(19).getItem() instanceof ItemChestrig && player.inventory.getStackInSlot(19).hasTagCompound()) 
        	{
        		
        		ItemStack rig = player.inventory.getStackInSlot(19);
        		InventoryItemStorage inv = new InventoryItemStorage(player.inventory.getStackInSlot(19));
        		for (int i = 0; i<rig.getTagCompound().getByte("size"); i++) 
        		{
        			if(inv.getStackInSlot(i)!=null && Item.getIdFromItem(inv.getStackInSlot(i).getItem()) == hold.getTagCompound().getIntArray("bullets")[0] && inv.getStackInSlot(i).stackSize<64) {
        				
        				bullet = unload(player, world);
        				
        				inv.getStackInSlot(i).stackSize++;
        				inv.closeInventory();
        				return hold;
        			}
        		}
        		for (int i = 0; i<rig.getTagCompound().getByte("size"); i++) 
        		{
        			if(inv.getStackInSlot(i)==null) {
        				
        				bullet = unload(player, world);
        				inv.setInventorySlotContents(i, bullet);
        				inv.closeInventory();
        				return hold;
        			}
        		}
        	}
    		for (int i = 3; i<16; i++) 
    		{
    			if (player.inventory.getStackInSlot(i)!=null && Item.getIdFromItem(player.inventory.getStackInSlot(i).getItem()) == hold.getTagCompound().getIntArray("bullets")[0] && player.inventory.getStackInSlot(i).stackSize<64) 
    			{
    				bullet = unload(player, world);
    				if (!world.isRemote)
    					player.inventory.getStackInSlot(i).stackSize++;
    				
    				return hold;
    			}
    		}

    		for (int i = 3; i<16; i++) 
    		{
    			if (player.inventory.getStackInSlot(i)==null) 
    			{
    				bullet = unload(player, world);
    				if (!world.isRemote)
    					player.inventory.setInventorySlotContents(i, bullet);
    				return bullet;
    			}
    		}
    		bullet = unload(player, world);
    		player.func_146097_a(bullet, true, false);
    	}
    	return hold;
    }
    
    public ItemStack loadAmmo(ItemStack hold, EntityPlayer player)
    {
    	DebugMessage.printMessage("Load ammo");
    	if(hold.hasTagCompound() && hold.stackTagCompound.getLong("counter")+cooldownLoading <= new Date().getTime() && hold.getTagCompound().getIntArray("bullets").length<maxAmmo) 
    	{
    		if (player.inventory.getStackInSlot(19)!=null && player.inventory.getStackInSlot(19).getItem() instanceof ItemChestrig && player.inventory.getStackInSlot(19).hasTagCompound() ) 
    		{
    			ItemStack rig = player.inventory.getStackInSlot(19);
    			InventoryItemStorage inv = new InventoryItemStorage(player.inventory.getStackInSlot(19));
    			for (int i = 0; i<rig.getTagCompound().getByte("size"); i++) 
    			{
    				if (inv.getStackInSlot(i)!=null && inv.getStackInSlot(i).getItem() == (Item) bullets[hold.getTagCompound().getInteger("getBullet")]) 
    				{
    					PacketDispatcher.sendToServer(new LoadAmmoMessage(1, i ,this.maxAmmo));
    					return hold;
    				}
    			}
    		}
    		for (int i = 3; i<16; i++) 
    		{
    			if (player.inventory.getStackInSlot(i)!=null && player.inventory.getStackInSlot(i).getItem() == (Item) bullets[hold.getTagCompound().getInteger("getBullet")]) 
    			{
    				
    				//PacketDispatcher.sendToServer(new LoadAmmoMessage(2, i ,this.maxAmmo));
    				int [] bullets = hold.stackTagCompound.getIntArray("bullets");
    			    int [] bulletsNew = new int[bullets.length + 1];
    			    ItemBullet bullet = (ItemBullet) player.inventory.getStackInSlot(i).getItem();
    				player.inventory.decrStackSize(i, 1);
    				for(int j=0; j<bullets.length; j++) 
    				{
    					bulletsNew[j+1] = bullets[j];
    				}
    				bulletsNew[0] =  bullet.getIdFromItem(bullet);
    				hold.stackTagCompound.setFloat("weight", hold.stackTagCompound.getFloat("weight")+bullet.getWeight());
    				hold.stackTagCompound.setIntArray("bullets", bulletsNew);
    				//hold.stackTagCompound.setLong("counter", new Date().getTime());
    				player.inventoryContainer.detectAndSendChanges();
    				return hold;
    			}
    		}
    		
    	}
    	return hold;	
    }
    
    public ItemStack onItemLeftClick(ItemStack hold, World world, EntityPlayer player) {
    	return loadAmmo(hold, player);
    }
    
    
    public ItemStack chengeAmmo(ItemStack hold, World world ,EntityPlayer player) 
    {
        if (world.isRemote)
        	PacketDispatcher.sendToServer(new ChangeAmmoMessage(bullets.length));
            
        return hold;
    }
    
    
    private ItemStack unload(EntityPlayer player, World world) {
    	ItemStack hold = player.inventory.getCurrentItem();
		int[] bullets = hold.getTagCompound().getIntArray("bullets");
		int[] bulletsNew = new int[bullets.length-1];
		ItemBullet bullet = (ItemBullet) Item.getItemById(bullets[0]);
		ItemStack bulletStack = new ItemStack(bullet);
		for (int j = 0; j<bullets.length-1; j++) {
			bulletsNew[j] = bullets[j+1];
		}
		hold.getTagCompound().setIntArray("bullets", bulletsNew);
		hold.getTagCompound().setDouble("weight", hold.getTagCompound().getDouble("weight") - bullet.getWeight());
		hold.stackTagCompound.setLong("counter", world.getTotalWorldTime());
		bulletStack.setTagCompound(new NBTTagCompound());
		bulletStack.getTagCompound().setFloat("weight", bullet.getWeight());
		//player.inventoryContainer.detectAndSendChanges();
		return bulletStack;
    }

	public final int getMaxAmmo() {
		return maxAmmo;
	}

	public final void setMaxAmmo(int maxAmmo) {
		if (DefenceVariables.authorizedAccess(Reader.class)) {
			this.maxAmmo = maxAmmo;
		}
	}

	public final int getCooldownLoading() {
		return cooldownLoading;
	}

	public final void setCooldownLoading(int cooldownLoading) {
		if (DefenceVariables.authorizedAccess(Reader.class)) {
			this.cooldownLoading = cooldownLoading;
		}
	}

	public final int getCooldownUnloading() {
		return cooldownUnloading;
	}

	public final void setCooldownUnloading(int cooldownUnloading) {
		if (DefenceVariables.authorizedAccess(Reader.class)) {
			this.cooldownUnloading = cooldownUnloading;
		}
	}

	public final ItemBullet[] getBullets() {
		return bullets;
	}

	public final void setBullets(String[] bullets) {
		if (DefenceVariables.authorizedAccess(Reader.class)) {
	        this.bullets = new ItemBullet[bullets.length];
	        for (int i = 0; i<bullets.length; i++)
	        	this.bullets[i] = (ItemBullet) GameRegistry.findItem(Main.modid, "item." + bullets[i].trim());
		}
	}
}
