package serb.tp.metro.network.server;

import java.util.Date;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import serb.tp.metro.Main;
import serb.tp.metro.containers.InventoryItemStorage;
import serb.tp.metro.items.weapons.ItemBullet;
import serb.tp.metro.network.AbstractMessage.AbstractServerMessage;

public class LoadAmmoMessage extends AbstractServerMessage<LoadAmmoMessage> {

	private int type;
	private int index;
	private int maxAmmo;

	public LoadAmmoMessage() {

	}

	public LoadAmmoMessage(int type, int index, int maxAmmo) {
		this.type = type;
		this.index = index;
		this.maxAmmo = maxAmmo;
	}


	@Override
	protected void read(PacketBuffer buffer) {
		type = buffer.readInt();
		index = buffer.readInt();
		maxAmmo = buffer.readInt();

	}

	@Override
	protected void write(PacketBuffer buffer) {
		buffer.writeInt(type);
		buffer.writeInt(index);
		buffer.writeInt(maxAmmo);

	}

	@Override
	public void process(EntityPlayer player, Side side) 
	{
		if (player.inventory.getStackInSlot(index)==null) return;
		ItemStack hold = player.inventory.getCurrentItem();
		ItemBullet bullet = null;
		int [] bullets = hold.stackTagCompound.getIntArray("bullets");
	    int [] bulletsNew = new int[bullets.length + 1];
		if(type==1) 
		{
			InventoryItemStorage inv = new InventoryItemStorage(player.inventory.getStackInSlot(19));
			bullet=(ItemBullet) inv.getStackInSlot(index).getItem();
			ItemStack rig = player.inventory.getStackInSlot(19);
			rig.getTagCompound().setFloat("weight", rig.getTagCompound().getFloat("weight")-bullet.weight);
			inv.decrStackSize(index, 1);		
			inv.save();
			inv.closeInventory();
		}
		else 
		{
			bullet = (ItemBullet) player.inventory.getStackInSlot(index).getItem();
			player.inventory.decrStackSize(index, 1);
		}
		for(int i=0; i<bullets.length; i++) 
		{
			bulletsNew[i+1] = bullets[i];
		}
		bulletsNew[0] =  bullet.getIdFromItem(bullet);
		hold.stackTagCompound.setFloat("weight", hold.stackTagCompound.getFloat("weight")+bullet.weight);
		hold.stackTagCompound.setIntArray("bullets", bulletsNew);
		hold.stackTagCompound.setLong("counter", new Date().getTime());
		player.inventoryContainer.detectAndSendChanges();
		
	}

}
