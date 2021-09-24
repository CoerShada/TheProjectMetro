package serb.tp.metro.network.server;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import serb.tp.metro.Main;
import serb.tp.metro.containers.CustomSlots;
import serb.tp.metro.entities.player.ExtendedPlayer;
import serb.tp.metro.network.AbstractMessage.AbstractServerMessage;

public class ResperatingMessage extends AbstractServerMessage<ResperatingMessage> {


	public ResperatingMessage() {

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
		if(player.inventory.getStackInSlot(15)!=null && player.inventory.getStackInSlot(15).getTagCompound().getInteger("filterTime")>0) 
		{
			ItemStack itemStack = player.inventory.getStackInSlot(15);
			int time = itemStack.getTagCompound().getInteger("filterTime");
			itemStack.getTagCompound().setInteger("filterTime", time - 1);
			if (time % 80==0)
			    player.playSound(Main.modid +":resperating_mask", 1, 1);
			//Minecraft.getMinecraft().theWorld.playSound(player.posX, player.posY, player.posZ, Adtime.modid +":resperating_mask", 10, 10, true);
			player.inventoryContainer.detectAndSendChanges();
		}
	}

}