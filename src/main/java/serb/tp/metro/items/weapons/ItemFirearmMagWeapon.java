package serb.tp.metro.items.weapons;

import java.util.Arrays;
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
import serb.tp.metro.Main;
import serb.tp.metro.client.Type;
import serb.tp.metro.containers.CustomSlots;
import serb.tp.metro.containers.InventoryItemStorage;
import serb.tp.metro.database.Reader;
import serb.tp.metro.items.ItemChestrig;
import serb.tp.metro.items.modules.ItemMag;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.server.LoadAmmoMessage;
import serb.tp.metro.network.server.LoadGunMagMessage;
import serb.tp.metro.network.server.UnloadGunMagMessage;
import serb.tp.metro.utils.DefenceVariables;


public class ItemFirearmMagWeapon extends ItemWeapon{
	
	protected ItemMag[] mags;
	protected int loadTime;
	protected int unloadTime;
	
	public ItemFirearmMagWeapon(String name, String description, String model, float weight, float[] sizeModel, float[] pos, 
    		float[] rotation, float[] onInventoryPos, float[] rightHandPos, float[] rightHandRotation, float[] leftHandPos, float[] leftHandRotation, 
    		float[] onAimingPos, float[] onAimingRotation,  float[] onAimingLeftHandPos, float[] onAimingLeftHandRotation, 
    		float[] onAimingRightHandPos, float[] onAimingRightHandRotation, int soundRadius, int rateOfFire, float penetrationMod, 
    		float jammingChance, int loadTime, int unloadTime, 
    		float recoilVert, float recoilHoriz, float accuracy, float convenience, FireMod[] fireMods, 
    		String[] mags) {
		super(name, description, weight, model, sizeModel, pos, rotation, onInventoryPos, rightHandPos, rightHandRotation,
				leftHandPos, leftHandRotation, onAimingPos, onAimingRotation, onAimingLeftHandPos, onAimingLeftHandRotation,
				onAimingRightHandPos, onAimingRightHandRotation, soundRadius, rateOfFire, penetrationMod, jammingChance,
				loadTime+unloadTime, recoilVert, recoilHoriz, accuracy, convenience, fireMods);
		this.loadTime = loadTime;
		this.unloadTime = unloadTime;
		this.setMags(mags);
		

	}
	
	
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean isAdv) {
        if (!itemStack.hasTagCompound()) return;
        NBTTagCompound nbt = itemStack.getTagCompound();
    	if (nbt.getTag("mag")!=null)
    		list.add(Type.getTranslate("characteristic.guns.magplaced"));
    	else
    		list.add(Type.getTranslate("characteristic.guns.magnotplaced"));
    	
    	super.addInformation(itemStack, entityPlayer, list, isAdv);
    }
	
    @Override
    public void getSubItems(Item item, CreativeTabs ct, List list)
    {
    	super.getSubItems(item, ct, list);
    }
    
    @Override
    public void load(ItemStack itemStack, EntityPlayer player) 
    {
    	InventoryItemStorage inv;
    	int type = 0;
    	int ammo = -1;
    	int index = -1;
    	int rigIndex = CustomSlots.CHESTRIG.getIndex();
    	if (player.inventory.getStackInSlot(rigIndex)!=null && player.inventory.getStackInSlot(rigIndex).getItem() instanceof ItemChestrig && player.inventory.getStackInSlot(rigIndex).hasTagCompound()) 
    	{
    		inv = new InventoryItemStorage(player.inventory.getStackInSlot(rigIndex));
    		inv.openInventory();
    		for (int i = 0; i < player.inventory.getStackInSlot(rigIndex).getTagCompound().getByte("size"); i++) 
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
    	int rigIndex = CustomSlots.CHESTRIG.getIndex();
    	if (player.inventory.getStackInSlot(rigIndex)!=null && player.inventory.getStackInSlot(rigIndex).getItem() instanceof ItemChestrig && player.inventory.getStackInSlot(rigIndex).hasTagCompound()) 
    	{
			inv = new InventoryItemStorage(player.inventory.getStackInSlot(rigIndex));
			inv.openInventory();
			for (int i = 0; i < player.inventory.getStackInSlot(rigIndex).getTagCompound().getByte("size"); i++) 
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


	public final ItemMag[] getMags() {
		return mags;
	}


	public final void setMags(String[] mags) {
		if (DefenceVariables.authorizedAccess(Reader.class, this.getClass())) {
			this.mags = new ItemMag[mags.length];
			for (int i = 0; i< mags.length; i++) {
				this.mags[i] = (ItemMag) GameRegistry.findItem(Main.modid, "item."+ mags[i]);
			}
		}
	}


	public final int getLoadTime() {
		return loadTime;
	}


	public final void setLoadTime(int loadTime) {
		if (DefenceVariables.authorizedAccess(Reader.class)) {
			this.loadTime = loadTime;
		}
	}


	public final int getUnloadTime() {
		return unloadTime;
	}


	public final void setUnloadTime(int unloadTime) {
		if (DefenceVariables.authorizedAccess(Reader.class)) {
			this.unloadTime = unloadTime;
		}
	}
}
