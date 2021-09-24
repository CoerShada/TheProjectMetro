package serb.tp.metro.common.handlers.equip;

import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.client.SyncEquipBackpuckMessage;

public class PlayerUpdateEquipBackpuck {

	private static HashMap<Integer, Integer> equip0Map = new HashMap<Integer, Integer>();

	@SubscribeEvent
	public void onPlayerUpdateEvent(LivingUpdateEvent event) 
	{
		if(event.entity.worldObj.isRemote || !(event.entityLiving instanceof EntityPlayer)) {
			return;
		}

		EntityPlayer player = (EntityPlayer) event.entityLiving;
		int prevEquip = equip0Map.containsKey(player.getEntityId()) ? equip0Map.get(player.getEntityId()) : -1;

		if(prevEquip == -1) {
			for(Map.Entry<Integer, Integer> entry0 : equip0Map.entrySet()) {
				Entity target = event.entity.worldObj.getEntityByID(entry0.getKey());
				
				if(target != null && target.dimension == event.entity.dimension && target instanceof EntityPlayer) 
				{
					EntityPlayerMP pl = (EntityPlayerMP)target;
					ItemStack itemStack = pl.inventory.getStackInSlot(18);
					if(itemStack != null) 
					{
						PacketDispatcher.sendTo(new SyncEquipBackpuckMessage(entry0.getKey(), Item.getIdFromItem(itemStack.getItem())), (EntityPlayerMP) player);
					}
					else {
						PacketDispatcher.sendTo(new SyncEquipBackpuckMessage(entry0.getKey(), 0), (EntityPlayerMP) player);
					}
				}
			}
		}

		if(MinecraftServer.getServer().getTickCounter() % 20 == 0) {
			ItemStack itemStack = player.inventory.getStackInSlot(18);

			if(itemStack != null) 
			{
					PacketDispatcher.sendToDimension(new SyncEquipBackpuckMessage(player.getEntityId(), Item.getIdFromItem(itemStack.getItem())), player.dimension);
			}
			else {
				PacketDispatcher.sendToDimension(new SyncEquipBackpuckMessage(player.getEntityId(), 0), player.dimension);
			}
		}
			

		if(MinecraftServer.getServer().getTickCounter() % 12000 == 0) {
			equip0Map.clear();
		}
	}
}