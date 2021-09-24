package serb.tp.metro.items.guns;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import serb.tp.metro.client.Type;
import serb.tp.metro.containers.InventoryItemStorage;
import serb.tp.metro.items.ItemChestrig;
import serb.tp.metro.items.guns.modules.ItemMag;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.server.LoadAmmoMessage;
import serb.tp.metro.network.server.LoadGunMagMessage;
import serb.tp.metro.network.server.UnloadGunMagMessage;


public class ItemFirearmMagGun extends ItemGun{
	protected final ItemMag[] mags;
	public ItemFirearmMagGun(String name, float weight, int rateOfFire, double powerMod, double jummingMod, int loadTime, int unloadTime, double recoilVert, double recoilHoriz, double accuracy, boolean autoFire, ItemMag[] mags) {
		super(name, weight, rateOfFire, powerMod, jummingMod, loadTime, unloadTime, recoilVert, accuracy, recoilHoriz, autoFire);
		this.mags = mags;
	}
	
	
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean isAdv) {
    	super.addInformation(itemStack, entityPlayer, list, isAdv);
        if (!itemStack.hasTagCompound()) return;
        NBTTagCompound nbt = itemStack.getTagCompound();
    	if (nbt.getInteger("mag")!=-1)
    		list.add(Type.getTranslate("characteristic.guns.magplaced"));
    	else
    		list.add(Type.getTranslate("characteristic.guns.magnotplaced"));
    }
	
    @Override
    public void getSubItems(Item item, CreativeTabs ct, List list)
    {
    	ItemStack itemStack = new ItemStack(item);
		itemStack.setTagCompound(new NBTTagCompound());
		list.add(itemStack);
		itemStack.getTagCompound().setFloat("weight", weight);
		itemStack.getTagCompound().setDouble("rateOfFire", rateOfFire);
		itemStack.getTagCompound().setDouble("powerMod", powerMod);
		itemStack.getTagCompound().setDouble("jummingMod", jummingMod);
		itemStack.getTagCompound().setInteger("loadTime", loadTime);
		itemStack.getTagCompound().setInteger("unloadTime", unloadTime);
		itemStack.getTagCompound().setBoolean("safetyMod", true);
		itemStack.getTagCompound().setBoolean("autoFire", autoFire);
		itemStack.getTagCompound().setBoolean("fireMod", false);
		itemStack.getTagCompound().setBoolean("jumming", false);
        itemStack.getTagCompound().setLong("notfire", new Date().getTime());
        itemStack.getTagCompound().setLong("notreload", new Date().getTime());
        itemStack.getTagCompound().setInteger("mag", -1);
        itemStack.getTagCompound().setIntArray("bullets", new int[]{});
    }
    
    @Override
    public void load(ItemStack itemStack, EntityPlayer player) 
    {
    	InventoryItemStorage inv;
    	int type = 0;
    	int ammo = -1;
    	int index = -1;
    	if (player.inventory.getStackInSlot(19)!=null && player.inventory.getStackInSlot(19).getItem() instanceof ItemChestrig && player.inventory.getStackInSlot(19).hasTagCompound()) 
    	{
    		inv = new InventoryItemStorage(player.inventory.getStackInSlot(19));
    		inv.openInventory();
    		for (int i = 0; i < player.inventory.getStackInSlot(19).getTagCompound().getByte("size"); i++) 
    		{
    			if(inv.getStackInSlot(i)!=null && inv.getStackInSlot(i).getItem() instanceof ItemMag && Arrays.asList(mags).contains(inv.getStackInSlot(i).getItem()) && inv.getStackInSlot(i).hasTagCompound() && inv.getStackInSlot(i).getTagCompound().getIntArray("bullets").length>ammo) 
    			{
    				type = 1;
    				ammo = inv.getStackInSlot(i).getTagCompound().getIntArray("bullets").length;
    				index = i;
    			}
    		}
    		inv.save();
    		inv.closeInventory();
			
    	}
    	for (int i = 3; i < 16; i++) 
    	{
    		if(player.inventory.getStackInSlot(i)!=null && player.inventory.getStackInSlot(i).getItem() instanceof ItemMag && Arrays.asList(mags).contains(player.inventory.getStackInSlot(i).getItem()) && player.inventory.getStackInSlot(i).hasTagCompound() && player.inventory.getStackInSlot(i).getTagCompound().getIntArray("bullets").length>ammo) 
    		{
    			type = 2;
    			ammo = player.inventory.getStackInSlot(i).getTagCompound().getIntArray("bullets").length;
    			index = i;
    		}
    	}
    	if (type>0) 
    	{
    		PacketDispatcher.sendToServer(new LoadGunMagMessage(type, index));
    	}
    }

    @Override
    public void unload(ItemStack itemStack, EntityPlayer player) 
    {
    	InventoryItemStorage inv;
    	if (player.inventory.getStackInSlot(19)!=null && player.inventory.getStackInSlot(19).getItem() instanceof ItemChestrig && player.inventory.getStackInSlot(19).hasTagCompound()) 
    	{
			inv = new InventoryItemStorage(player.inventory.getStackInSlot(19));
			inv.openInventory();
			for (int i = 0; i < player.inventory.getStackInSlot(19).getTagCompound().getByte("size"); i++) 
			{
				if (inv.getStackInSlot(i)==null) {
					PacketDispatcher.sendToServer(new UnloadGunMagMessage(1, i));
					inv.save();
					inv.closeInventory();
					return;
				}
			}
			inv.save();
			inv.closeInventory();
    	}
		for (int i = 3; i < 16; i++) 
		{
			if(player.inventory.getStackInSlot(i)==null) 
			{
				PacketDispatcher.sendToServer(new UnloadGunMagMessage(2, i));
				return;
			}
		}
		PacketDispatcher.sendToServer(new UnloadGunMagMessage(-1, -1));
    }
}
