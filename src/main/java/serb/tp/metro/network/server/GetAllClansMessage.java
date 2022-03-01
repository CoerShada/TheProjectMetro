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
import serb.tp.metro.network.client.WriterNBTDataToClientBufferMessage;

public class GetAllClansMessage extends AbstractServerMessage<GetAllClansMessage> {


	public GetAllClansMessage() {}
	
	
	@Override
	protected void read(PacketBuffer buffer) throws IOException {

	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		

	}

	@Override
	public void process(EntityPlayer player, Side side) {
		
		World world = MinecraftServer.getServer().getEntityWorld();
		ClanHandler instance = ClanHandler.get(world);
		NBTTagCompound tag = new NBTTagCompound();
		//instance.writeClansTagCompound(tag);
		PacketDispatcher.sendTo(new WriterNBTDataToClientBufferMessage(tag), (EntityPlayerMP) player);
	}

	

}