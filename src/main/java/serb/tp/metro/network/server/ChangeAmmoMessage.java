package serb.tp.metro.network.server;

import java.util.Date;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import serb.tp.metro.network.AbstractMessage.AbstractServerMessage;

public class ChangeAmmoMessage extends AbstractServerMessage<ChangeAmmoMessage> {

	private int len;

	public ChangeAmmoMessage() {

	}

	public ChangeAmmoMessage(int len) {
		this.len = len;
	}

	@Override
	protected void read(PacketBuffer buffer) {
		len = buffer.readInt();
	}

	@Override
	protected void write(PacketBuffer buffer) {
		buffer.writeInt(len);
	}

	@Override
	public void process(EntityPlayer player, Side side) 
	{
		ItemStack hold = player.inventory.getCurrentItem();
		
		int typeAmmo = hold.stackTagCompound.getInteger("getBullet");
		if (typeAmmo == len - 1) 
		{
			hold.stackTagCompound.setInteger("getBullet", 0);
		}
		else
		{
			hold.stackTagCompound.setInteger("getBullet", typeAmmo + 1);
		}
		player.inventoryContainer.detectAndSendChanges();
		
	}

}
