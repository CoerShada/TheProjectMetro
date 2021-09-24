package serb.tp.metro.client.render;

import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import serb.tp.metro.client.render.armor.RenderEquipArmor;
import serb.tp.metro.containers.CustomSlots;
import serb.tp.metro.entities.player.ExtendedPlayer;
import serb.tp.metro.items.armor.ArmorType.armorTypeGloves;

public class RenderPants {

	public RenderPants() {

	}

	public void render(EntityPlayer player, RenderPlayer renderModel) {
		ItemStack equipStack = ExtendedPlayer.get(player).inventory.getStackInSlot(CustomSlots.PANTS.ordinal());
		Item equip = equipStack.getItem();

		if(equip instanceof armorTypeGloves) RenderEquipArmor.renderGloves(renderModel, equip);
	}

}