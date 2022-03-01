package serb.tp.metro.network.client;

import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import serb.tp.metro.DebugMessage;
import serb.tp.metro.Main;
import serb.tp.metro.blocks.BlockCreator;
import serb.tp.metro.blocks.tiles.storages.creators.TileEntityCreator;
import serb.tp.metro.client.ClientProxy;
import serb.tp.metro.common.CommonProxy;
import serb.tp.metro.common.clans.ClanHandler;
import serb.tp.metro.network.AbstractMessage;
import serb.tp.metro.network.AbstractMessage.AbstractClientMessage;

public class WriterNBTDataToClientBufferMessage extends AbstractMessage<WriterNBTDataToClientBufferMessage> {
	
	NBTTagCompound tag;

	public WriterNBTDataToClientBufferMessage() {
	}
	
	public WriterNBTDataToClientBufferMessage(NBTTagCompound tag) {
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
		ClientProxy.buffer = tag;
	}

}
