package serb.tp.metro.items.armor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import serb.tp.metro.Main;
import serb.tp.metro.containers.CustomSlots;
import serb.tp.metro.entities.player.ExtendedPlayer;
import serb.tp.metro.items.ItemMask;
import serb.tp.metro.items.armor.ArmorType.armorTypeBackpack;
import serb.tp.metro.items.armor.ArmorType.armorTypeBracers;
import serb.tp.metro.items.armor.ArmorType.armorTypeGloves;
import serb.tp.metro.items.weapons.ItemWeapon;

public class CustomItemArmor extends Item {

	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
	    
	    if(itemStack.getItem() instanceof ItemWeapon ) {
		player.inventory.setInventorySlotContents(2, itemStack.copy());
		itemStack.stackSize = 0;
	    }

	    if(itemStack.getItem() instanceof ItemMask && ExtendedPlayer.get(player).inventory.getStackInSlot(CustomSlots.MASK.ordinal()) == null) {
		ExtendedPlayer.get(player).inventory.setInventorySlotContents(CustomSlots.MASK.ordinal(), itemStack.copy());
		itemStack.stackSize = 0;
	    }
		

	    if(itemStack.getItem() instanceof armorTypeBracers && ExtendedPlayer.get(player).inventory.getStackInSlot(CustomSlots.OUTERWEAR.ordinal()) == null) {
		ExtendedPlayer.get(player).inventory.setInventorySlotContents(CustomSlots.OUTERWEAR.ordinal(), itemStack.copy());
		itemStack.stackSize = 0;
	    }

	    if(itemStack.getItem() instanceof armorTypeGloves && ExtendedPlayer.get(player).inventory.getStackInSlot(CustomSlots.PANTS.ordinal()) == null) {
		ExtendedPlayer.get(player).inventory.setInventorySlotContents(CustomSlots.PANTS.ordinal(), itemStack.copy());
		itemStack.stackSize = 0;
	    }

	    if(itemStack.getItem() instanceof armorTypeBackpack && ExtendedPlayer.get(player).inventory.getStackInSlot(CustomSlots.BACKPACK.ordinal()) == null) {
		ExtendedPlayer.get(player).inventory.setInventorySlotContents(CustomSlots.BACKPACK.ordinal(), itemStack.copy());
		itemStack.stackSize = 0;
	    }    

	    return itemStack;
	}

	public static void destroyItemArmor(ItemStack stack, EntityPlayer player, int slot) {
		if(stack.getItemDamage() >= stack.getMaxDamage()) {
			player.inventory.setInventorySlotContents(slot, null);
		}
	}

	public static void onCustomArmorTick(World world, EntityPlayer player, ItemStack stack) {

	}

}