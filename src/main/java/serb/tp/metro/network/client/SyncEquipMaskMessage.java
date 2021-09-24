package serb.tp.metro.network.client;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import serb.tp.metro.client.render.RenderImages;
import serb.tp.metro.containers.CustomSlots;
import serb.tp.metro.entities.player.ExtendedPlayer;
import serb.tp.metro.items.armor.LoadItemArmor;
import serb.tp.metro.network.AbstractMessage.AbstractClientMessage;

public class SyncEquipMaskMessage extends AbstractClientMessage<SyncEquipMaskMessage> {

	private int playerID;
	private int itemID;
	private int filter;

	public SyncEquipMaskMessage() {

	}

	public SyncEquipMaskMessage(int playerID, int equipID, ItemStack mask) {
		this.playerID = playerID;
		this.itemID = equipID;
		if(mask!=null && mask.hasTagCompound())
			this.filter = mask.getTagCompound().getInteger("filter");
	}

	@Override
	protected void read(PacketBuffer buffer) {
		playerID = buffer.readInt();
		itemID = buffer.readInt();
		filter = buffer.readInt();
	}

	@Override
	protected void write(PacketBuffer buffer) {
		buffer.writeInt(playerID);
		buffer.writeInt(itemID);
		buffer.writeInt(filter);
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		if(Minecraft.getMinecraft().thePlayer.getEntityId() == playerID) {
			return;
		}

		EntityPlayer playerToUpd = (EntityPlayer) player.worldObj.getEntityByID(playerID);
		if(playerToUpd == null || !(playerToUpd instanceof EntityPlayer)) {
			return;
		}

		ItemStack itemStack = null;
		if (itemID != 0) 
		{
			itemStack = new ItemStack(Item.getItemById(itemID));
			if(!itemStack.hasTagCompound())
				itemStack.setTagCompound(new NBTTagCompound());
			itemStack.getTagCompound().setInteger("filter", filter);
		}
		else itemStack = null;
		playerToUpd.inventory.mainInventory[15] = itemStack;
		
	}

}