package serb.tp.metro.containers.slots;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import serb.tp.metro.containers.CustomSlots;
import serb.tp.metro.containers.slots.SlotType.CustomArmorSlot;
import serb.tp.metro.entities.player.ExtendedPlayer;
import serb.tp.metro.items.armor.ItemHelmet;
import serb.tp.metro.items.modules.ItemMag;

public class SlotArmor extends Slot implements CustomArmorSlot {

	final int armorType;

	final EntityPlayer player;

	public SlotArmor(EntityPlayer player, IInventory inventory, int slotIndex, int x, int y, int armorType) {
		super(inventory, slotIndex, x, y);
		this.player = player;
		this.armorType = armorType;
	}

	public int getSlotStackLimit() {
		return 1;
	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		Item item = (itemstack == null ? null : itemstack.getItem());
		if (item != null && item.isValidArmor(itemstack, armorType, player)) 
		{
			if (armorType==0 && ExtendedPlayer.get(player).inventory.getStackInSlot(CustomSlots.MASK.ordinal())!=null && itemstack.hasTagCompound() && itemstack.getTagCompound().getString("visor").equalsIgnoreCase("close")) 
				itemstack.getTagCompound().setString("visor", "open");
			return true;

		}
		else 
			return false;
		
	}

	@SideOnly(Side.CLIENT)
	public IIcon getBackgroundIconIndex() {
		return ItemArmor.func_94602_b(this.armorType);
	}

}