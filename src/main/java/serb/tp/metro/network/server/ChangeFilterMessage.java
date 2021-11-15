package serb.tp.metro.network.server;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import serb.tp.metro.containers.CustomSlots;
import serb.tp.metro.entities.player.ExtendedPlayer;
import serb.tp.metro.items.ItemFilter;
import serb.tp.metro.network.AbstractMessage.AbstractServerMessage;

public class ChangeFilterMessage extends AbstractServerMessage<ChangeFilterMessage> {
	int slot;

	public ChangeFilterMessage(int slot) {
		this.slot = slot;
	}


	public ChangeFilterMessage() {

	}


	@Override
	protected void read(PacketBuffer buffer) {
		slot = buffer.readInt();
	}

	@Override
	protected void write(PacketBuffer buffer) {
		buffer.writeInt(slot);
	}

	@Override
	public void process(EntityPlayer player, Side side) 
	{
		ItemStack mask = player.inventory.getStackInSlot(CustomSlots.MASK.getIndex());
		if (slot>=0 && mask.getTagCompound().getInteger("filter")!=0) 
		{
			ItemStack filterOld = new ItemStack(Item.getItemById(mask.getTagCompound().getInteger("filter")));
			ItemFilter itemFilterOld = (ItemFilter)filterOld.getItem();
			ItemStack filterNew = player.inventory.getStackInSlot(slot);
			filterOld.setTagCompound(new NBTTagCompound());
			filterOld.getTagCompound().setLong("filterTime", mask.getTagCompound().getInteger("filterTime"));
			filterOld.getTagCompound().setFloat("weight", itemFilterOld.getWeight());
			mask.getTagCompound().setFloat("weight", mask.getTagCompound().getFloat("weight")- itemFilterOld.getWeight() + filterNew.getTagCompound().getFloat("wight"));
			mask.getTagCompound().setInteger("filter", Item.getIdFromItem(filterNew.getItem()));
			mask.getTagCompound().setLong("filterTime", filterNew.getTagCompound().getInteger("filterTime"));
			player.inventory.decrStackSize(slot, 1);
			for (int i = 3; i<16; i++) 
			{
				if (player.inventory.getStackInSlot(i)==null) {
					player.inventory.mainInventory[i] = filterOld;
					break;
				}
				if(i==15) 
				{
					player.entityDropItem(filterOld, 1F);
				}
			}
		}
		else if (slot>=0 && mask.getTagCompound().getInteger("filter")==0) {
			ItemStack filterNew = player.inventory.getStackInSlot(slot);
			mask.getTagCompound().setFloat("weight", mask.getTagCompound().getFloat("weight") + filterNew.getTagCompound().getFloat("weight"));
			mask.getTagCompound().setInteger("filter", Item.getIdFromItem(filterNew.getItem()));
			mask.getTagCompound().setLong("filterTime", filterNew.getTagCompound().getInteger("filterTime"));
			player.inventory.decrStackSize(slot, 1);
		}
		
		
		
		player.inventoryContainer.detectAndSendChanges();

	}

}
