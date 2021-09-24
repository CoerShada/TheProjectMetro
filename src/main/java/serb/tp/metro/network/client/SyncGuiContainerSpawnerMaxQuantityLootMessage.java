package serb.tp.metro.network.client;

import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import serb.tp.metro.blocks.tiles.storages.spawners.TileEntityStorageSpawner;
import serb.tp.metro.network.AbstractMessage.AbstractClientMessage;

public class SyncGuiContainerSpawnerMaxQuantityLootMessage extends AbstractClientMessage<SyncGuiContainerSpawnerMaxQuantityLootMessage> {
	
	int value;
	int x;
	int y;
	int z;
	
	
	public SyncGuiContainerSpawnerMaxQuantityLootMessage() {
	}
	
	public SyncGuiContainerSpawnerMaxQuantityLootMessage(int value, int x, int y, int z) {
		this.value = value;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		value = buffer.readInt();
		x = buffer.readInt();
		y = buffer.readInt();
		z = buffer.readInt();
		
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		buffer.writeInt(value);
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
		
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		if (player.getEntityWorld().getTileEntity(x, y, z)!=null && player.getEntityWorld().getTileEntity(x, y, z) instanceof TileEntityStorageSpawner) {
			TileEntityStorageSpawner tile = (TileEntityStorageSpawner) player.getEntityWorld().getTileEntity(x, y, z);
			tile.maxQuantityLoot = value;
			tile.getDescriptionPacket();
		}
		
	}

}
