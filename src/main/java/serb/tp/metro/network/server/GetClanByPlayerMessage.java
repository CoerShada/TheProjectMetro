package serb.tp.metro.network.server;

import java.io.IOException;
import java.util.UUID;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import serb.tp.metro.DebugMessage;
import serb.tp.metro.common.clans.Clan;
import serb.tp.metro.common.clans.ClanHandler;
import serb.tp.metro.common.clans.INBTSyncronized;
import serb.tp.metro.items.weapons.ItemWeapon;
import serb.tp.metro.network.AbstractMessage.AbstractServerMessage;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.client.WriterNBTDataToClientBufferMessage;

public class GetClanByPlayerMessage extends AbstractServerMessage<GetClanByPlayerMessage> {

	
	String playerUUID;
	
	public GetClanByPlayerMessage() {}
	
	public GetClanByPlayerMessage(UUID playerUUID) {
		this.playerUUID = playerUUID.toString();
	}
	
	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		this.playerUUID = buffer.readStringFromBuffer(128);
		
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		buffer.writeStringToBuffer(playerUUID);

	}

	@Override
	public void process(EntityPlayer player, Side side) {
		
		World world = MinecraftServer.getServer().getEntityWorld();
		ClanHandler instance = ClanHandler.get(world);
		
		Clan clan = instance.getAPlayersClan(UUID.fromString(playerUUID));
		
		NBTTagCompound tag;
		if (clan!=null) {
			tag = clan.getNBT();
			tag.setString("type", "clan");
		}
		else 
		{
			tag = new NBTTagCompound();
		}
		DebugMessage.printMessage("uuid from server " + tag);
		PacketDispatcher.sendTo(new WriterNBTDataToClientBufferMessage(tag), (EntityPlayerMP) player);
	}

	

}
