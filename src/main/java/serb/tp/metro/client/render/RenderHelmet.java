package serb.tp.metro.client.render;

import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import serb.tp.metro.client.render.armor.RenderEquipArmor;
import serb.tp.metro.items.armor.ItemHelmet;

public class RenderHelmet {

	public RenderHelmet() {

	}

	public void render(EntityPlayer player, RenderPlayer renderModel) {
		ItemStack equipStack = player.getCurrentArmor(3);
		Item equip = equipStack.getItem();

		if(equip instanceof ItemHelmet) RenderEquipArmor.renderHelmet(renderModel, equip, equipStack);	
	}

}