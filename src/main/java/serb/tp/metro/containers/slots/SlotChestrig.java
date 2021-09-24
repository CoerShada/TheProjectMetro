package serb.tp.metro.containers.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import serb.tp.metro.containers.slots.SlotType.customArmorSlot;
import serb.tp.metro.items.ItemChestrig;

public class SlotChestrig extends Slot implements customArmorSlot {

	public SlotChestrig(IInventory inventory, int slotIndex, int x, int y) {
		super(inventory, slotIndex, x, y);
	}

	public boolean isItemValid(ItemStack stack) {
		if(stack.getItem() instanceof ItemChestrig) return true;
		else return false;
	}

}