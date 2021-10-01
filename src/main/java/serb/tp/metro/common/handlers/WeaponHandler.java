package serb.tp.metro.common.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import serb.tp.metro.Main;
import serb.tp.metro.common.ws.WeaponSystem;

public class WeaponHandler {

	@SubscribeEvent
	public void addEntityConstructing(EntityEvent.EntityConstructing event) {
		if (event.entity instanceof EntityPlayer) {
			if (Main.proxy.ws.get((EntityPlayer)event.entity) == null)
				Main.proxy.ws.reg((EntityPlayer)event.entity);
		}
	}

	@SubscribeEvent
	public void onLivingEvent(LivingEvent.LivingUpdateEvent event) {
		if (event.entity instanceof EntityPlayer) {
			WeaponSystem ws = Main.proxy.ws.get((EntityPlayer)event.entityLiving);
			if (ws != null) ws.onUpdate();
		}
	}

}
