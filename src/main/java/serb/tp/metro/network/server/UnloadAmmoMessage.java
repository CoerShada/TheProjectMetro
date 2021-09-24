package serb.tp.metro.network.server;

import java.util.Date;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import serb.tp.metro.containers.InventoryItemStorage;
import serb.tp.metro.items.weapons.ItemBullet;
import serb.tp.metro.network.AbstractMessage.AbstractServerMessage;

public class UnloadAmmoMessage extends AbstractServerMessage<UnloadAmmoMessage> {

	private int type;
	private int index;

	public UnloadAmmoMessage() {

	}

	public UnloadAmmoMessage(int type, int index) {
		this.type = type;
		this.index = index;
	}

	@Override
	protected void read(PacketBuffer buffer) {
		type = buffer.readInt();
		index = buffer.readInt();
	}

	@Override
	protected void write(PacketBuffer buffer) {
		buffer.writeInt(type);
		buffer.writeInt(index);
	}

	@Override
	public void process(EntityPlayer player, Side side) 
	{
		ItemStack hold = player.inventory.getCurrentItem();
		int[] bullets = hold.getTagCompound().getIntArray("bullets");
		int[] bulletsNew = new int[bullets.length-1];
		ItemBullet bullet = (ItemBullet) Item.getItemById(bullets[0]);
		for (int i = 0; i<bullets.length-1; i++) {
			bulletsNew[i] = bullets[i+1];
		}
		
		hold.getTagCompound().setIntArray("bullets", bulletsNew);
		hold.getTagCompound().setDouble("weight", hold.getTagCompound().getDouble("weight") - bullet.weight);
		hold.stackTagCompound.setLong("counter", new Date().getTime());
		ItemStack itemStack = new ItemStack(bullet);
		itemStack.setTagCompound(new NBTTagCompound());
		itemStack.getTagCompound().setDouble("weight", bullet.weight);
		
		if (type==1) {
			InventoryItemStorage inv = new InventoryItemStorage(player.inventory.getStackInSlot(19));
			ItemStack rig = player.inventory.getStackInSlot(19);
			rig.getTagCompound().setDouble("weight", rig.getTagCompound().getDouble("weight") + bullet.weight);
			if (inv.getStackInSlot(index)==null)
			{
				inv.inventory[index] = itemStack;
			}
			else
			{
				inv.inventory[index].stackSize+=1;
			}
			inv.save();
			inv.closeInventory();
		}
		else if(type==2)
		{
			if(player.inventory.getStackInSlot(index)==null) 
			{
				player.inventory.mainInventory[index] = itemStack;
			}
			else 
			{
				player.inventory.mainInventory[index].stackSize+=1;
			}
			
		}
		else 
		{
			player.func_146097_a(itemStack, true, false);
		}
		player.inventoryContainer.detectAndSendChanges();
		
	}

}