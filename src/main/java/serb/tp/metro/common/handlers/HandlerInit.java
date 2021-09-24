package serb.tp.metro.common.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import serb.tp.metro.containers.ContainerBackpackStorage;
import serb.tp.metro.items.ItemBackpack;
import serb.tp.metro.items.ItemChestrig;

public class HandlerInit {

	@SubscribeEvent
	public void onPlayerTickEvent(TickEvent.PlayerTickEvent event) {
		switch(event.phase) {
		case END:
			Container con = event.player.openContainer;
			if (con != null && con instanceof ContainerBackpackStorage) {
				ContainerBackpackStorage cis = (ContainerBackpackStorage)con;
				ItemStack current = event.player.getCurrentEquippedItem();
				//update
				if (event.side.isServer() && current != null) cis.update(event.player);
				//Закрытия окна, в случаи если предмета нет нужного нам предмета.
				if (current == null || !(current != null && current.getItem() instanceof ItemBackpack && current.getItem() instanceof ItemChestrig))
					event.player.closeScreen();
			}
			break;
		default:break;
		}
	}

}
