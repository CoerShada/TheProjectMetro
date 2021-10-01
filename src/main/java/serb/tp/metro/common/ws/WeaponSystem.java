package serb.tp.metro.common.ws;


import org.lwjgl.input.Mouse;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import serb.tp.metro.Main;
import serb.tp.metro.items.weapons.ItemWeapon;

public class WeaponSystem implements IExtendedEntityProperties {
	
	public final static String TAG = Main.modid + ":WeaponSystem";
	protected EntityPlayer player;
	protected ItemStack currentItemStack;
	protected Item currentItem;
	protected int currentSlot;
	
	public void onUpdate() {
		onItemChanged(player);
		
	}
	
	protected boolean onItemChanged(EntityPlayer player) {
		InventoryPlayer inv = player.inventory;
		boolean changed = false;
		if (currentSlot!=inv.currentItem || !currentItem.equals(inv.getCurrentItem().getItem())) {
			currentItemStack = inv.getStackInSlot(inv.currentItem);
			if (inv.getStackInSlot(inv.currentItem)==null)
				currentItem = null;
			else
				currentItem = inv.getStackInSlot(inv.currentItem).getItem();
			currentSlot = inv.currentItem;
			changed = true;
		}
		return changed;
	}
	
	public boolean mouseLeftButtonPressed() {
		return Mouse.isButtonDown(0);
	}
	
	public boolean mouseRightButtonPressed() {
		return Mouse.isButtonDown(1);
	}
	

	
	public void reg(EntityPlayer player) {
		player.registerExtendedProperties(TAG, new WeaponSystem());
	}
	
	public WeaponSystem get(EntityPlayer player) {
		return player != null ? (WeaponSystem)player.getExtendedProperties(TAG) : null;
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(Entity entity, World world) {
		if (entity instanceof EntityPlayer) 
			this.player = (EntityPlayer) entity;
		
	}

	

	

}