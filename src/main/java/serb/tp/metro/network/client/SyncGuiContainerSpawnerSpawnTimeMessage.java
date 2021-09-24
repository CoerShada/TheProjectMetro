package serb.tp.metro.network.client;

import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import serb.tp.metro.blocks.tiles.storages.spawners.TileEntityStorageSpawner;
import serb.tp.metro.network.AbstractMessage.AbstractClientMessage;

public class SyncGuiContainerSpawnerSpawnTimeMessage extends AbstractClientMessage<SyncGuiContainerSpawnerSpawnTimeMessage> {
	
	long value;
	int x;
	int y;
	int z;
	
	
	public SyncGuiContainerSpawnerSpawnTimeMessage() {
	}
	
	public SyncGuiContainerSpawnerSpawnTimeMessage(long value, int x, int y, int z) {
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
		if (player.getEntityWorld().getTileEntity(x, y, z)!=null && player.getEntityWorld().getTileEntity(x, y, z) instanceof TileEntityStorageSpawner) {
			TileEntityStorageSpawner tile = (TileEntityStorageSpawner) player.getEntityWorld().getTileEntity(x, y, z);
			tile.spawnTime = value;
			tile.getDescriptionPacket();
		}
		
	}

}