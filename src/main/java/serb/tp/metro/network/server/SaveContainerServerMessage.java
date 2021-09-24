package serb.tp.metro.network.server;

import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import serb.tp.metro.blocks.tiles.storages.TileEntityStorage;
import serb.tp.metro.network.AbstractMessage.AbstractClientMessage;
import serb.tp.metro.network.AbstractMessage.AbstractServerMessage;

public class SaveContainerServerMessage extends AbstractServerMessage<SaveContainerServerMessage> {

	private int x;
	private int y;
	private int z;
	
	public SaveContainerServerMessage() {
		
	}
	
	public SaveContainerServerMessage(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		x = buffer.readInt();
		y = buffer.readInt();
		z = buffer.readInt();
	
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		MinecraftServer server = MinecraftServer.getServer();
		World world = server.getEntityWorld();
		if (world.getTileEntity(x, y, z)!=null && world.getTileEntity(x, y, z) instanceof TileEntityStorage) {
			TileEntityStorage te = (TileEntityStorage) world.getTileEntity(x, y, z);
			te.getDescriptionPacket();
		}
			
	}
}
