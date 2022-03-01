package serb.tp.metro.network.server;

import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import serb.tp.metro.common.clans.ClanHandler;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.client.SyncRelationMessage;
import serb.tp.metro.network.AbstractMessage.AbstractServerMessage;

public class ChangeRelationMessage extends AbstractServerMessage<ChangeRelationMessage>{

	private NBTTagCompound tag;
	
	public ChangeRelationMessage(){};
	
	public ChangeRelationMessage(NBTTagCompound tag) {
		this.tag = tag;
	}
	
	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		tag = buffer.readNBTTagCompoundFromBuffer();
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {	
		buffer.writeNBTTagCompoundToBuffer(tag);
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		World world = MinecraftServer.getServer().getEntityWorld();
		
		ClanHandler handler = ClanHandler.get(world);
		handler.changeRelationByNBT(tag);
		
		PacketDispatcher.sendToAll(new SyncRelationMessage(tag));
	}

}
