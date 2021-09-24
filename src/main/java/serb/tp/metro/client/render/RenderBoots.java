package serb.tp.metro.client.render;

import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import serb.tp.metro.client.render.armor.RenderEquipArmor;
import serb.tp.metro.items.armor.ArmorType.armorTypeBoots;

public class RenderBoots {

	public RenderBoots() {

	}

	public void render(EntityPlayer player, RenderPlayer renderModel) {
		ItemStack equipStack = player.getCurrentArmor(0);
		Item equip = equipStack.getItem();

		if(equip instanceof armorTypeBoots) RenderEquipArmor.renderBoots(renderModel, equip);		
	}

}