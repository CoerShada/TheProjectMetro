package serb.tp.metro.common.handlers;

import java.util.Iterator;
import java.util.Set;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import serb.tp.metro.DebugMessage;
import serb.tp.metro.Main;
import serb.tp.metro.common.ieep.ExtendedPlayer;
import serb.tp.metro.common.ieep.WeaponSystem;
import serb.tp.metro.containers.CustomSlots;
import serb.tp.metro.customization.ICustomizable;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.general.UpdateClanMessage;

public class WeaponSystemHandler {

	public static EntityPlayer player = null;
	
	@SubscribeEvent
	public void addEntityConstructing(EntityEvent.EntityConstructing event) {
		
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.entity;
			if (Main.proxy.ws.get(player) == null) {
				Main.proxy.ws.reg(player);
				
			}
			
			if (Main.proxy.bs.get(player) == null) {
				Main.proxy.bs.reg(player);
				
			}
			
			if (Main.proxy.clanIEEP.get(player)==null) {
				Main.proxy.clanIEEP.reg(player);
		
			}

		}
	}
	
	
	@SubscribeEvent
	public void onPlayerLoadFromFile(PlayerEvent.PlayerLoggedInEvent event) {
		EntityPlayer player = event.player;
		System.out.println("player " + player);
		
		/*if (!player.worldObj.isRemote)
			PacketDispatcher.sendToAll(new UpdateClanMessage());
		else
			PacketDispatcher.sendToServer(new UpdateClanMessage());*/
		


	}
	
	@SubscribeEvent
	public void UpdateItems(EntityJoinWorldEvent event) {
		if (event.entity instanceof EntityPlayer) {	
			EntityPlayer player = (EntityPlayer) event.entity;		
			//Пересчет характеристик кастомизируемых предметов
			for (int i = 0; i<CustomSlots.BACKPACK.getIndex(); i++) {
				ItemStack is = player.inventory.getStackInSlot(i);
				if (is==null || !(is.getItem() instanceof ICustomizable)) continue;
				ICustomizable customizable = (ICustomizable) is.getItem();
				NBTTagCompound tag = customizable.updateCharacteristics(is);
				Set keys = tag.func_150296_c();
				Iterator<String> it = keys.iterator();
				while(it.hasNext()) {
					String key = it.next();
					if (!is.getTagCompound().hasKey(key)) continue;
					is.getTagCompound().setFloat(key, tag.getFloat(key));
				}
				
					
			}
			

			//PacketDispatcher.sendToServer(new UpdateClanMessage());
		}
		


	}

	
	
	@SubscribeEvent
	public void onLivingEvent(LivingEvent.LivingUpdateEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.entityLiving;
			WeaponSystem ws = Main.proxy.ws.get(player);
			if (ws == null) return;
				ws.onUpdate();
			if (player.worldObj.isRemote)
				ws.onClientUpdate();
			
		}
	}

}
