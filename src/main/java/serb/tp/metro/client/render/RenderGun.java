package serb.tp.metro.client.render;

import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import serb.tp.metro.client.render.armor.RenderEquipArmor;
import serb.tp.metro.items.weapons.ItemWeapon;

public class RenderGun {

	public RenderGun() {

	}

	public void render(EntityPlayer player, RenderPlayer renderModel)
	{
	    
	    ItemStack equipStack = player.inventory.getStackInSlot(2);
	    Item equip = equipStack.getItem();
	    //if(equip instanceof ItemWeapon && player.inventory.getCurrentItem() != equipStack) RenderEquipArmor.renderGun(renderModel, equip);
	}

}