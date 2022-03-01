package serb.tp.metro.network.client;

import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import serb.tp.metro.common.clans.ClanHandler;
import serb.tp.metro.network.AbstractMessage.AbstractClientMessage;

public class SyncRelationMessage extends AbstractClientMessage<SyncRelationMessage>{

	NBTTagCompound tag;
	
	public SyncRelationMessage() {}
	
	public SyncRelationMessage(NBTTagCompound tag) {
		this.tag = tag;
	}
	
	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		tag = buffer.readNBTTagCompoundFromBuffer();
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {	
		buffer.writeNBTTagCompoundToBuffer(tag);
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		World world = Minecraft.getMinecraft().theWorld;
		
		ClanHandler handler = ClanHandler.get(world);
		handler.changeRelationByNBT(tag);
		
	}

}
