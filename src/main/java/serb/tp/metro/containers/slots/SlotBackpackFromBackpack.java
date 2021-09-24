package serb.tp.metro.containers.slots;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import serb.tp.metro.containers.slots.SlotType.customArmorSlot;
import serb.tp.metro.items.ItemBackpack;

public class SlotBackpackFromBackpack extends Slot implements customArmorSlot {

	public SlotBackpackFromBackpack(IInventory inventory, int slotIndex, int x, int y) {
		super(inventory, slotIndex, x, y);
	}

	public boolean isItemValid(ItemStack stack) {
		if(stack!=null && stack.getItem() instanceof ItemBackpack) return true;
		else return false;
	}
	
    public boolean canTakeStack(EntityPlayer p_82869_1_)
    {
        return false;
    }
}
