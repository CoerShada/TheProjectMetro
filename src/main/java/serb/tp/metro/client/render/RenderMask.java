package serb.tp.metro.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import serb.tp.metro.client.render.armor.RenderEquipArmor;
import serb.tp.metro.containers.CustomSlots;
import serb.tp.metro.entities.player.ExtendedPlayer;
import serb.tp.metro.items.ItemMask;

public class RenderMask {

	public RenderMask() {

	}

	public void render(EntityPlayer player, RenderPlayer renderModel) {
		ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
		ItemStack equipStack = player.inventory.getStackInSlot(15);
		Item equip = equipStack.getItem();

		if(equip instanceof ItemMask) 
		{
			
			RenderEquipArmor.renderMask(renderModel, equip, equipStack);
		}
	}
}