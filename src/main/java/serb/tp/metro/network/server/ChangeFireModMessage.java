package serb.tp.metro.network.server;

import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import serb.tp.metro.network.AbstractMessage.AbstractServerMessage;

public class ChangeFireModMessage extends AbstractServerMessage<ChangeFireModMessage>{
	

	String fireMod;
	
	public ChangeFireModMessage() {

	}
	
	public ChangeFireModMessage(String fireMod) {
		this.fireMod = fireMod;
	}

	@Override
	protected void read(PacketBuffer buffer){
		try {
			fireMod = buffer.readStringFromBuffer(10);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void write(PacketBuffer buffer){
		try {
			buffer.writeStringToBuffer(fireMod);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		ItemStack hold = player.inventory.getCurrentItem();
		hold.getTagCompound().setString("fireMod", fireMod);
		
	}

}
