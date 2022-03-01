package serb.tp.metro.network.general;

import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import serb.tp.metro.common.clans.ClanHandler;
import serb.tp.metro.network.AbstractMessage;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.client.SyncCHMessage;

public class AddClanMessage extends AbstractMessage<AddClanMessage> {

	private String name;

	public AddClanMessage() {

	}

	public AddClanMessage(String name) {
		this.name = name;
	}

	@Override
	protected void read(PacketBuffer buffer) {
		try {
			name = buffer.readStringFromBuffer(30);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void write(PacketBuffer buffer) {
		try {
			buffer.writeStringToBuffer(name);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void process(EntityPlayer player, Side side) 
	{
		World world = MinecraftServer.getServer().getEntityWorld();

		ClanHandler handler = ClanHandler.get(world);
		handler.createClan(name, player.getUniqueID());
		handler.markDirty();
		NBTTagCompound tag = new NBTTagCompound();
		handler.writeToNBT(tag);
		PacketDispatcher.sendToAll(new SyncCHMessage(tag));
		
	}

}