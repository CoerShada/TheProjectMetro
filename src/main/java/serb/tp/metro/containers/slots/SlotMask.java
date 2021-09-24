package serb.tp.metro.containers.slots;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import serb.tp.metro.containers.slots.SlotType.customArmorSlot;
import serb.tp.metro.items.ItemMask;
import serb.tp.metro.items.armor.ItemHelmet;

public class SlotMask extends Slot implements customArmorSlot {
	EntityPlayer player;
	
	public SlotMask(EntityPlayer player, IInventory inventory, int slotIndex, int x, int y) {
		super(inventory, slotIndex, x, y);
		this.player = player;
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		if(stack!=null && stack.getItem() instanceof ItemMask) 
		{
			if (player.inventory.armorItemInSlot(3)!=null && player.inventory.armorItemInSlot(3).hasTagCompound() && player.inventory.armorItemInSlot(3).getTagCompound().getString("visor").equalsIgnoreCase("close")) 
				player.inventory.armorItemInSlot(3).getTagCompound().setString("visor", "open");
			
			return true;
		}
		else return false;
	}

}