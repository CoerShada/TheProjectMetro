package serb.tp.metro.containers.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import serb.tp.metro.items.ItemBackpack;
import serb.tp.metro.items.ItemChestrig;

public class SlotInBackpack extends Slot{
	
	public SlotInBackpack(IInventory inventory, int slotIndex, int x, int y) {
		super(inventory, slotIndex, x, y);
	}

	public boolean isItemValid(ItemStack stack) {
		if(stack!=null && !(stack.getItem() instanceof ItemBackpack) && !(stack.getItem() instanceof ItemChestrig)) return true;
		else return false;
	}

}
