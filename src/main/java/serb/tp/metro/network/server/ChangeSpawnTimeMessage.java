package serb.tp.metro.network.server;

import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import serb.tp.metro.blocks.tiles.storages.spawners.TileEntityStorageSpawner;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.AbstractMessage.AbstractServerMessage;
import serb.tp.metro.network.client.SyncGuiContainerSpawnerSpawnTimeMessage;

public class ChangeSpawnTimeMessage extends AbstractServerMessage<ChangeSpawnTimeMessage>{
	
	long value;
	int x;
	int y;
	int z;
	
	public ChangeSpawnTimeMessage() {
	}
	
	public ChangeSpawnTimeMessage(long value, int x, int y, int z) {
		this.value = value;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		value = buffer.readLong();
		x = buffer.readInt();
		y = buffer.readInt();
		z = buffer.readInt();
		
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		buffer.writeLong(value);
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
		
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		MinecraftServer server = MinecraftServer.getServer();
		if (server.getEntityWorld().getTileEntity(x, y, z)!=null && server.getEntityWorld().getTileEntity(x, y, z) instanceof TileEntityStorageSpawner) {
			TileEntityStorageSpawner tile = (TileEntityStorageSpawner) server.getEntityWorld().getTileEntity(x, y, z);
			tile.spawnTime = value;
			tile.getDescriptionPacket();
			PacketDispatcher.sendToAll(new SyncGuiContainerSpawnerSpawnTimeMessage(value, x, y, z));
		}
		
	}

}
