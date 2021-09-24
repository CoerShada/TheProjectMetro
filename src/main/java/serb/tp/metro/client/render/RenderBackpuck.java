package serb.tp.metro.client.render;

import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import serb.tp.metro.client.render.armor.RenderEquipArmor;
import serb.tp.metro.containers.CustomSlots;
import serb.tp.metro.entities.player.ExtendedPlayer;
import serb.tp.metro.items.ItemBackpack;
import serb.tp.metro.items.armor.ArmorType.armorTypeBackpack;

public class RenderBackpuck {

	public RenderBackpuck() {

	}

	public void render(EntityPlayer player, RenderPlayer renderModel)
	{
		ItemStack equipStack = player.inventory.getStackInSlot(18);
		Item equip = equipStack.getItem();

		if(equip instanceof ItemBackpack) RenderEquipArmor.renderBackpack(renderModel, equip);
	}

}