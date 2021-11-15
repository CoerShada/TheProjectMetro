package serb.tp.metro.containers.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import serb.tp.metro.containers.slots.SlotType.CustomWeaponSlot;
import serb.tp.metro.items.weapons.ItemWeapon;

public class SlotHotbar extends Slot implements CustomWeaponSlot {

	public SlotHotbar(IInventory inventory, int slotIndex, int x, int y) {
		super(inventory, slotIndex, x, y);
	}

	public boolean isItemValid(ItemStack stack) {
		if(stack!=null && !(stack.getItem() instanceof ItemWeapon)) return true;
		else return false;
	}

}
