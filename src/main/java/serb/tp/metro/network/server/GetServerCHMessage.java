package serb.tp.metro.network.server;

import java.io.IOException;
import java.util.UUID;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import serb.tp.metro.DebugMessage;
import serb.tp.metro.common.clans.Clan;
import serb.tp.metro.common.clans.ClanHandler;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.AbstractMessage.AbstractServerMessage;
import serb.tp.metro.network.client.SyncCHMessage;
import serb.tp.metro.network.client.WriterNBTDataToClientBufferMessage;

public class GetServerCHMessage extends AbstractServerMessage<GetServerCHMessage> {

	public GetServerCHMessage() {}
	
	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {

	}

	@Override
	public void process(EntityPlayer player, Side side) {
		ClanHandler handler = ClanHandler.get(MinecraftServer.getServer().getEntityWorld());
		NBTTagCompound tag = new NBTTagCompound();
		handler.writeToNBT(tag);
		PacketDispatcher.sendTo(new SyncCHMessage(tag), (EntityPlayerMP) player);
	}

	

}

