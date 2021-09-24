package serb.tp.metro.common.handlers;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import serb.tp.metro.client.render.RenderItemOnGround;

public class PickupHandler {
	
	@SubscribeEvent
	public void onItemPickup(EntityItemPickupEvent event) {
		if(event.entity.worldObj.isRemote || !(event.entityLiving instanceof EntityPlayer)) {
			return;
		}	
		event.setCanceled(true);		
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(receiveCanceled = true,	priority = EventPriority.HIGHEST)
	public void onInGameUI(RenderGameOverlayEvent e) {
		if(e.type == ElementType.ALL) {	  
			RenderHelper.disableStandardItemLighting();
			RenderItemOnGround.drawItems(e);
			RenderHelper.disableStandardItemLighting();
		}
	}
}
