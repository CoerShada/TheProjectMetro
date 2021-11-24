package serb.tp.metro.network.server;

import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import serb.tp.metro.Main;
import serb.tp.metro.common.handlers.ClanHandler;
import serb.tp.metro.network.AbstractMessage.AbstractServerMessage;

public class AddClanMessage extends AbstractServerMessage<AddClanMessage> {

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
		
		ClanHandler handler = ClanHandler.get(player.worldObj);
		handler.createClan(name, player);
		handler.markDirty();
		Main.proxy.clanIEEP.get(player).setClan();
		
	}

}