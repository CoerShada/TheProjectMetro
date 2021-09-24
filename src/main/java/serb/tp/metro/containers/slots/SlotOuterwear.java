package serb.tp.metro.containers.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import serb.tp.metro.containers.slots.SlotType.customArmorSlot;
import serb.tp.metro.items.armor.ArmorType.armorTypeBracers;

public class SlotOuterwear extends Slot implements customArmorSlot {

	public SlotOuterwear(IInventory inventory, int slotIndex, int x, int y) {
		super(inventory, slotIndex, x, y);
	}

	public boolean isItemValid(ItemStack stack) {
		if(stack!=null && stack.getItem() instanceof armorTypeBracers) return true;
		else return false;
	}

}