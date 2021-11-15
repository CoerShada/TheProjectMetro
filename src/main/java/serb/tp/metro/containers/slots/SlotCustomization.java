package serb.tp.metro.containers.slots;

import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import serb.tp.metro.items.Item3D;
import serb.tp.metro.items.modules.ItemModule;

public class SlotCustomization extends Slot{
	
	private ItemModule[] subItems;

	public SlotCustomization(IInventory inventory, int slotIndex, int x, int y, ItemModule[] subItems) {
		super(inventory, slotIndex, x, y);
		this.subItems = subItems;
	}

	public boolean isItemValid(ItemStack stack) {
		/*if(stack!=null && Arrays.stream(subItems).anyMatch(stack.getItem()::equals)) return true;
		else*/ return false;
	}
	
	public boolean isItemValidChanged(ItemStack is) {
		if (is==null || !(is.getItem() instanceof ItemModule)) return false;
		ItemModule module = (ItemModule) is.getItem();
		for (ItemModule item: subItems) {
			if (module.equals(item)) 
				return true;
		}
		return false;
	}
	
    public boolean canTakeStack(EntityPlayer player)
    {
        return false;
    }

}
