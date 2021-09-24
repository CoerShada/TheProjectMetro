package serb.tp.metro.client.handlers;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.GuiOpenEvent;
import serb.tp.metro.common.CommonProxy;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.server.OpenGuiMessage;

public class OpenInventory {

	@SubscribeEvent
	public void onOpenGui(GuiOpenEvent event) {
		if(event.gui instanceof GuiInventory) 
		{
			EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
			if (!player.capabilities.isCreativeMode) {
				event.setCanceled(true);
				PacketDispatcher.sendToServer(new OpenGuiMessage(CommonProxy.GUI_CUSTOM_INV));
			}
		}
	}

}