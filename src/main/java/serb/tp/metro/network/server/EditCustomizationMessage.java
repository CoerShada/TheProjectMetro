package serb.tp.metro.network.server;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import serb.tp.metro.containers.ContainerCustomization;
import serb.tp.metro.network.AbstractMessage.AbstractServerMessage;

public class EditCustomizationMessage extends AbstractServerMessage<EditCustomizationMessage> {

	private int complexID;
	private int changeSlotID;

	public EditCustomizationMessage() {

	}

	public EditCustomizationMessage(int complexID, int changeSlotID) {
		this.complexID = complexID;
		this.changeSlotID = changeSlotID;
	}


	@Override
	protected void read(PacketBuffer buffer) {
		complexID = buffer.readInt();
		changeSlotID = buffer.readInt();
	}

	@Override
	protected void write(PacketBuffer buffer) {
		buffer.writeInt(complexID);
		buffer.writeInt(changeSlotID);
	}

	@Override
	public void process(EntityPlayer player, Side side) 
	{
		ContainerCustomization cont = (ContainerCustomization) player.openContainer;
		cont.changeItemInRecursionInventory(complexID, changeSlotID);
		
	}

}
