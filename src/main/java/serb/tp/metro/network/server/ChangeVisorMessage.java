package serb.tp.metro.network.server;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import serb.tp.metro.network.AbstractMessage.AbstractServerMessage;

public class ChangeVisorMessage extends AbstractServerMessage<ChangeVisorMessage> {


	public ChangeVisorMessage() {

	}


	@Override
	protected void read(PacketBuffer buffer) {
		
	}

	@Override
	protected void write(PacketBuffer buffer) {
		
	}

	@Override
	public void process(EntityPlayer player, Side side) 
	{
		ItemStack equipped = player.inventory.armorItemInSlot(3);
		if(equipped.stackTagCompound.getString("visor").equalsIgnoreCase("open"))
			equipped.stackTagCompound.setString("visor", "close");
		else if (equipped.stackTagCompound.getString("visor").equalsIgnoreCase("close"))
			equipped.stackTagCompound.setString("visor", "open");
		player.inventoryContainer.detectAndSendChanges();
	}

}