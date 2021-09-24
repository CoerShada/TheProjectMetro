package serb.tp.metro.containers.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import serb.tp.metro.containers.slots.SlotType.customGunSlot;
import serb.tp.metro.items.weapons.ItemWeapon;

public class SlotGun extends Slot implements customGunSlot {

	public SlotGun(IInventory inventory, int slotIndex, int x, int y) {
		super(inventory, slotIndex, x, y);
	}

	public boolean isItemValid(ItemStack stack) {
		if(stack!=null && stack.getItem() instanceof ItemWeapon) return true;
		else return false;
	}

}