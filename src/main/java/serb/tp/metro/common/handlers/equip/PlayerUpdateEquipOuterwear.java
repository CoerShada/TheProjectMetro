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
import serb.tp.metro.containers.CustomSlots;
import serb.tp.metro.entities.player.ExtendedPlayer;
import serb.tp.metro.items.armor.LoadItemArmor;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.client.SyncEquipOuterwearMessage;

public class PlayerUpdateEquipOuterwear {

	private static HashMap<Integer, Integer> equip0Map = new HashMap<Integer, Integer>();

	@SubscribeEvent
	public void onPlayerUpdateEvent(LivingUpdateEvent event) {
		if(event.entity.worldObj.isRemote || !(event.entityLiving instanceof EntityPlayer)) {
			return;
		}

		/*EntityPlayer player = (EntityPlayer) event.entityLiving;
		int prevEquip = equip0Map.containsKey(player.getEntityId()) ? equip0Map.get(player.getEntityId()) : -1;
		int currentEquip = 0;

		if(prevEquip == -1) {
			for(Map.Entry<Integer, Integer> entry0 : equip0Map.entrySet()) {
				Entity target = event.entity.worldObj.getEntityByID(entry0.getKey());

				if(target != null && target.dimension == event.entity.dimension) {
					PacketDispatcher.sendTo(new SyncEquipOuterwearMessage(entry0.getKey(), entry0.getValue()), (EntityPlayerMP) player);
				}
			}
		}

		if(MinecraftServer.getServer().getTickCounter() % 20 == 0) {
			ItemStack itemStack = ExtendedPlayer.get(player).inventory.getStackInSlot(CustomSlots.OUTERWEAR.ordinal());

			if(itemStack != null) {
				Item equip = itemStack.getItem();
				if (equip == LoadItemArmor.bracers) currentEquip = 1;
			}

			if(prevEquip != currentEquip) {
				equip0Map.put(player.getEntityId(), currentEquip);
				if(prevEquip + currentEquip >= 0) {
					PacketDispatcher.sendToDimension(new SyncEquipOuterwearMessage(player.getEntityId(), currentEquip), player.dimension);
				}
			}

			if(MinecraftServer.getServer().getTickCounter() % 12000 == 0) {
				equip0Map.clear();
			}
		}*/
	}

}