package serb.tp.metro.containers.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import serb.tp.metro.containers.slots.SlotType.CustomArmorSlot;
import serb.tp.metro.items.armor.ArmorType.armorTypeGloves;

public class SlotPants extends Slot implements CustomArmorSlot {

	public SlotPants(IInventory inventory, int slotIndex, int x, int y) {
		super(inventory, slotIndex, x, y);
	}

	public boolean isItemValid(ItemStack stack) {
		if(stack!=null && stack.getItem() instanceof armorTypeGloves) return true;
		else return false;
	}

}