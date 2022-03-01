package serb.tp.metro.network.general;

import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import serb.tp.metro.Main;
import serb.tp.metro.common.clans.Clan;
import serb.tp.metro.common.clans.ClanHandler;
import serb.tp.metro.common.ieep.ExtendedPlayer;
import serb.tp.metro.network.AbstractMessage;
import serb.tp.metro.network.PacketDispatcher;

public class UpdateClanMessage extends AbstractMessage<UpdateClanMessage>{
	
	
	
	public UpdateClanMessage() {
		
	}

	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		/*if (side.isServer()){
			PacketDispatcher.sendToAll(new UpdateClanMessage());
		}*/
		World world = side.isServer()? MinecraftServer.getServer().getEntityWorld(): Minecraft.getMinecraft().theWorld;
		ExtendedPlayer clanIEEP = Main.proxyCommon.clanIEEP.get(player);
		

		ClanHandler handler = ClanHandler.get(world);
		/*Clan clan = handler.getAPlayersClan(player);
		clanIEEP.setClan(clan);*/


	}
	
	

}
