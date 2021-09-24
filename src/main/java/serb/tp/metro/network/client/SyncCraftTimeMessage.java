package serb.tp.metro.network.client;

import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import serb.tp.metro.blocks.BlockCreator;
import serb.tp.metro.blocks.tiles.storages.creators.TileEntityCreator;
import serb.tp.metro.blocks.tiles.storages.spawners.TileEntityStorageSpawner;
import serb.tp.metro.containers.InventoryItemStorage;
import serb.tp.metro.network.AbstractMessage.AbstractClientMessage;

public class SyncCraftTimeMessage extends AbstractClientMessage<SyncCraftTimeMessage> {
	
	long value;
	int x;
	int y;
	int z;
	
	
	public SyncCraftTimeMessage() {
	}
	
	public SyncCraftTimeMessage(long value, int x, int y, int z) {
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
		if (player.getEntityWorld().getTileEntity(x, y, z)!=null && player.getEntityWorld().getTileEntity(x, y, z) instanceof TileEntityCreator) {
			TileEntityCreator tile = (TileEntityCreator) player.getEntityWorld().getTileEntity(x, y, z);
			BlockCreator block = (BlockCreator) tile.getBlockType();
			tile.craftEndTime = value;
			tile.getDescriptionPacket();
		}
		
	}

}
