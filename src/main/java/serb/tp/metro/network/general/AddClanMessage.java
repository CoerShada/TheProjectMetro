package serb.tp.metro.network.general;

import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import serb.tp.metro.Main;
import serb.tp.metro.common.handlers.ClanHandler;
import serb.tp.metro.common.ieep.ExtendedPlayer;
import serb.tp.metro.network.AbstractMessage;
import serb.tp.metro.network.PacketDispatcher;

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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void write(PacketBuffer buffer) {
		try {
			buffer.writeStringToBuffer(name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void process(EntityPlayer player, Side side) 
	{
		World world = side == Side.SERVER? MinecraftServer.getServer().getEntityWorld(): player.worldObj;

		ClanHandler handler = ClanHandler.get(world);
		handler.createClan(name, player);
		handler.markDirty();

		PacketDispatcher.sendToServer(new UpdateClanMessage());
		
		/*if (side.isServer()) {
			PacketDispatcher.sendToServer(new UpdateClanMessage());
			PacketDispatcher.sendTo(new AddClanMessage(name), (EntityPlayerMP) player);
		}*/

		
	}

}