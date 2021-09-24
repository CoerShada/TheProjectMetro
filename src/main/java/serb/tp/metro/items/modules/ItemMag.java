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
import serb.tp.metro.Main;
import serb.tp.metro.client.Type;
import serb.tp.metro.containers.InventoryItemStorage;
import serb.tp.metro.creativetabs.LoadTabs;
import serb.tp.metro.items.Item3D;
import serb.tp.metro.items.ItemChestrig;
import serb.tp.metro.items.weapons.ItemBullet;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.server.ChangeAmmoMessage;
import serb.tp.metro.network.server.LoadAmmoMessage;
import serb.tp.metro.network.server.UnloadAmmoMessage;

public class ItemMag extends ItemWeaponModule
{

	public final int maxAmmo;
    public final int cooldownLoading;
    public final int cooldownUnloading;
    private final ItemBullet[] bullets;

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
        if (new ItemStack(bullets[nbt.getInteger("getBullet")])!=null)
        	list.add(Type.getTranslate("characteristic.guns.loadammo") +": " + (new ItemStack(bullets[nbt.getInteger("getBullet")]).getDisplayName()));
        else
        	System.out.println("NULL: " + nbt.getInteger("getBullet"));
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
    
    
    public void unloadAmmo(ItemStack hold, EntityPlayer player) 
    {
    	if (hold.hasTagCompound() && hold.getTagCompound().getIntArray("bullets").length>0 && hold.stackTagCompound.getLong("counter")+cooldownUnloading <= new Date().getTime())
    	{
        	if (player.inventory.getStackInSlot(19)!=null && player.inventory.getStackInSlot(19).getItem() instanceof ItemChestrig && player.inventory.getStackInSlot(19).hasTagCompound()) 
        	{
        		
        		ItemStack rig = player.inventory.getStackInSlot(19);
        		InventoryItemStorage inv = new InventoryItemStorage(player.inventory.getStackInSlot(19));
        		for (int i = 0; i<rig.getTagCompound().getByte("size"); i++) 
        		{
        			if(inv.getStackInSlot(i)!=null && Item.getIdFromItem(inv.getStackInSlot(i).getItem()) == hold.getTagCompound().getIntArray("bullets")[0] && inv.getStackInSlot(i).stackSize<64) {
        				
        				PacketDispatcher.sendToServer(new UnloadAmmoMessage(1, i));
        				inv.closeInventory();
        				return;
        			}
        		}
        		for (int i = 0; i<rig.getTagCompound().getByte("size"); i++) 
        		{
        			if(inv.getStackInSlot(i)==null) {
        				
        				PacketDispatcher.sendToServer(new UnloadAmmoMessage(1, i));
        				inv.closeInventory();
        				return;
        			}
        		}
        	}
    		for (int i = 3; i<16; i++) 
    		{
    			if (player.inventory.getStackInSlot(i)!=null && Item.getIdFromItem(player.inventory.getStackInSlot(i).getItem()) == hold.getTagCompound().getIntArray("bullets")[0] && player.inventory.getStackInSlot(i).stackSize<64) 
    			{
    				PacketDispatcher.sendToServer(new UnloadAmmoMessage(2, i));
    				return;
    			}
    		}

    		for (int i = 3; i<16; i++) 
    		{
    			if (player.inventory.getStackInSlot(i)==null) 
    			{
    				PacketDispatcher.sendToServer(new UnloadAmmoMessage(2, i));
    				return;
    			}
    		}

    		PacketDispatcher.sendToServer(new UnloadAmmoMessage(-1, -1));
    	}
    }
    
    public void loadAmmo(ItemStack hold, EntityPlayer player)
    {
    	
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
    					return;
    				}
    			}
    		}
    		for (int i = 3; i<16; i++) 
    		{
    			if (player.inventory.getStackInSlot(i)!=null && player.inventory.getStackInSlot(i).getItem() == (Item) bullets[hold.getTagCompound().getInteger("getBullet")]) 
    			{
    				PacketDispatcher.sendToServer(new LoadAmmoMessage(2, i ,this.maxAmmo));
    				return;
    			}
    		}
    	}
        	
        
    }
    
    public ItemStack chengeAmmo(ItemStack hold, World world ,EntityPlayer player) 
    {
        if (world.isRemote)
        	PacketDispatcher.sendToServer(new ChangeAmmoMessage(bullets.length));
            
        return hold;
    }
    
}
