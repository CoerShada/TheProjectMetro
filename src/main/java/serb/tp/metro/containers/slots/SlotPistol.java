package serb.tp.metro.containers.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import serb.tp.metro.containers.slots.SlotType.CustomWeaponSlot;

public class SlotPistol extends Slot implements CustomWeaponSlot {

	public SlotPistol(IInventory inventory, int slotIndex, int x, int y) {
		super(inventory, slotIndex, x, y);
	}

	public boolean isItemValid(ItemStack stack) {
		//if(stack!=null && stack.getItem() instanceof ItemPistol || stack.getItem() instanceof ItemRevolver) return true;
		//else return false;
		return true;
	}

}
