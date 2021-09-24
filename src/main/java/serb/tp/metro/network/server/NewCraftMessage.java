package serb.tp.metro.network.server;

import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import serb.tp.metro.blocks.BlockCreator;
import serb.tp.metro.blocks.tiles.storages.creators.TileEntityCreator;
import serb.tp.metro.containers.ContainerCreator;
import serb.tp.metro.containers.InventoryItemStorage;
import serb.tp.metro.network.AbstractMessage.AbstractServerMessage;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.client.SyncCraftTimeMessage;

public class NewCraftMessage extends AbstractServerMessage<NewCraftMessage>{
	
	int index;
	int x;
	int y;
	int z;
	
	public NewCraftMessage() {
	}
	
	public NewCraftMessage(int index, int x, int y, int z) {
		this.index = index;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		index = buffer.readInt();
		x = buffer.readInt();
		y = buffer.readInt();
		z = buffer.readInt();
		
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		buffer.writeInt(index);
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
		
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		
		MinecraftServer server = MinecraftServer.getServer();
		if (server.getEntityWorld().getTileEntity(x, y, z)!=null && server.getEntityWorld().getTileEntity(x, y, z) instanceof TileEntityCreator) {
			
			TileEntityCreator tile = (TileEntityCreator) server.getEntityWorld().getTileEntity(x, y, z);
			BlockCreator block = (BlockCreator) tile.getBlockType();
			boolean craft = tile.removalOfIngredients(player, block.recipes[index]);
			
			if (craft)
				tile.newCraft(player, block.recipes[index]);
				
			tile.getDescriptionPacket();
			PacketDispatcher.sendToAll(new SyncCraftTimeMessage(tile.craftEndTime, x, y, z));

		}
		
	}

}
