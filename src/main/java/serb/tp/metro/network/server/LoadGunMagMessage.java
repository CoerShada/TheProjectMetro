package serb.tp.metro.network.server;

import java.util.Date;

import org.apache.commons.lang3.ArrayUtils;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import serb.tp.metro.Main;
import serb.tp.metro.containers.InventoryItemStorage;
import serb.tp.metro.network.AbstractMessage.AbstractServerMessage;

public class LoadGunMagMessage extends AbstractServerMessage<LoadGunMagMessage> {

	private int type;
	private int index;

	public LoadGunMagMessage() {

	}

	public LoadGunMagMessage(int type, int index) {
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
		ItemStack mag = null;
		if (type==1) {
			InventoryItemStorage inv = new InventoryItemStorage(player.inventory.getStackInSlot(19));
			mag=inv.getStackInSlot(index);
			ItemStack rig = player.inventory.getStackInSlot(19);
			rig.getTagCompound().setFloat("weight", rig.getTagCompound().getFloat("weight")-mag.getTagCompound().getFloat("weight"));
			inv.decrStackSize(index, 1);
			inv.save();
			inv.closeInventory();
		}
		else 
		{
			mag = player.inventory.getStackInSlot(index);
			player.inventory.decrStackSize(index, 1);
		}
		hold.stackTagCompound.setFloat("weight", hold.stackTagCompound.getFloat("weight") + mag.stackTagCompound.getFloat("weight"));
		if (hold.stackTagCompound.getIntArray("bullets").length==0) {
			hold.stackTagCompound.setIntArray("bullets", mag.getTagCompound().getIntArray("bullets"));
		}
		else {
			hold.stackTagCompound.setIntArray("bullets", ArrayUtils.addAll(hold.getTagCompound().getIntArray("bullets"), mag.getTagCompound().getIntArray("bullets")));
		}
		NBTTagCompound magTag = new NBTTagCompound();
		magTag.setString("name", mag.getUnlocalizedName());
		magTag.setTag("nbt", mag.getTagCompound());
		hold.stackTagCompound.setTag("mag", magTag);
		hold.stackTagCompound.setLong("notreload", new Date().getTime());
		hold.stackTagCompound.setLong("notfire", new Date().getTime());
		hold.stackTagCompound.setBoolean("jumming", false);
		
		player.worldObj.playSoundEffect(player.posX, player.posY, player.posZ, Main.modid +":"+hold.getUnlocalizedName()+"_load", 1.0f, 1.0f);
		player.inventoryContainer.detectAndSendChanges();
		
	}

}