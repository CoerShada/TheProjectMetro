package serb.tp.metro.network.server;

import java.io.IOException;
import java.util.Date;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import serb.tp.metro.containers.InventoryItemStorage;
import serb.tp.metro.items.modules.ItemMag;
import serb.tp.metro.items.weapons.ItemBullet;
import serb.tp.metro.network.AbstractMessage.AbstractServerMessage;

public class UnloadAmmoMessage extends AbstractServerMessage<UnloadAmmoMessage> {

	ItemStack is;

	public UnloadAmmoMessage() {

	}
	
	/*public UnloadAmmoMessage(ItemStack is) {
		this.is = is;
	}*/


	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		//is = buffer.readItemStackFromBuffer();
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		//buffer.writeItemStackToBuffer(is);
	}

	@Override
	public void process(EntityPlayer player, Side side) 
	{
		ItemMag mag = (ItemMag) player.inventory.getCurrentItem().getItem();
		ItemStack magIs = mag.unloadAmmo(player, player.worldObj);
		
	}

}