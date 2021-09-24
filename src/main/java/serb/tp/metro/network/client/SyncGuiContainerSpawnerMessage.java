package serb.tp.metro.network.client;

import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import serb.tp.metro.blocks.tiles.storages.spawners.TileEntityStorageSpawner;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.AbstractMessage.AbstractClientMessage;
import serb.tp.metro.network.server.ChangeSpawnProbabilityMessage;

public class SyncGuiContainerSpawnerMessage extends AbstractClientMessage<SyncGuiContainerSpawnerMessage> {
	
	int index;
	float value;
	int x;
	int y;
	int z;
	
	
	public SyncGuiContainerSpawnerMessage() {
	}
	
	public SyncGuiContainerSpawnerMessage( int index, float value, int x, int y, int z) {
		this.index = index;
		this.value = value;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		index = buffer.readInt();
		value = buffer.readFloat();
		x = buffer.readInt();
		y = buffer.readInt();
		z = buffer.readInt();
		
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		buffer.writeInt(index);
		buffer.writeFloat(value);
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
		
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		if (player.getEntityWorld().getTileEntity(x, y, z)!=null && player.getEntityWorld().getTileEntity(x, y, z) instanceof TileEntityStorageSpawner) {
			TileEntityStorageSpawner tile = (TileEntityStorageSpawner) player.getEntityWorld().getTileEntity(x, y, z);
			tile.spawnProbability[index] = value;
			tile.getDescriptionPacket();
		}
		
	}

}
