package serb.tp.metro.items.jewels;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import serb.tp.metro.containers.CustomSlots;
import serb.tp.metro.entities.player.ExtendedPlayer;
import serb.tp.metro.items.jewels.JewelsType.jewelTypeGun;
import serb.tp.metro.items.jewels.JewelsType.jewelTypeKnife;
import serb.tp.metro.items.jewels.JewelsType.jewelTypePistol;

public class CustomItemJewel extends Item {

	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {   	

		if(itemStack.getItem() instanceof jewelTypeKnife && ExtendedPlayer.get(player).inventory.getStackInSlot(CustomSlots.KNIFE.ordinal()) == null) {
			ExtendedPlayer.get(player).inventory.setInventorySlotContents(CustomSlots.KNIFE.ordinal(), itemStack.copy());
			itemStack.stackSize = 0;
		}

		/*if(itemStack.getItem() instanceof jewelTypeRing) {
			if(ExtendedPlayer.get(player).inventory.getStackInSlot(CustomSlots.CHESTRIG.ordinal()) == null) {
				ExtendedPlayer.get(player).inventory.setInventorySlotContents(CustomSlots.CHESTRIG.ordinal(), itemStack.copy());
				itemStack.stackSize = 0;
			}
  	
		}*/
		
		if(itemStack.getItem() instanceof jewelTypePistol && ExtendedPlayer.get(player).inventory.getStackInSlot(CustomSlots.PISTOL.ordinal()) == null) {
			ExtendedPlayer.get(player).inventory.setInventorySlotContents(CustomSlots.PISTOL.ordinal(), itemStack.copy());
			itemStack.stackSize = 0;
		}    	

		if(itemStack.getItem() instanceof jewelTypeGun && ExtendedPlayer.get(player).inventory.getStackInSlot(CustomSlots.GUN.ordinal()) == null) {
			ExtendedPlayer.get(player).inventory.setInventorySlotContents(CustomSlots.GUN.ordinal(), itemStack.copy());
			itemStack.stackSize = 0;
		}    	

		return itemStack;
	}

	public static void onCustomArmorTick(World world, EntityPlayer player, ItemStack stack) {
		
	}

}