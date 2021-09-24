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

public class RemovingFilterMessage extends AbstractServerMessage<RemovingFilterMessage> {


	public RemovingFilterMessage() {

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
		ItemStack mask = player.inventory.getStackInSlot(15);
		if (mask!=null && mask.getTagCompound().getInteger("filter")!=0) 
		{
			ItemStack filterOld = new ItemStack(Item.getItemById(mask.getTagCompound().getInteger("filter")));
			ItemFilter itemFilterOld = (ItemFilter)filterOld.getItem();
			filterOld.setTagCompound(new NBTTagCompound());	
			filterOld.getTagCompound().setLong("filterTime", mask.getTagCompound().getInteger("filterTime"));
			filterOld.getTagCompound().setFloat("weight", itemFilterOld.getWeight());
			mask.getTagCompound().setFloat("weight", mask.getTagCompound().getInteger("weight")- itemFilterOld.getWeight());
			mask.getTagCompound().setInteger("filter", 0);
			mask.getTagCompound().setLong("filterTime", 0);
			for (int i = 3; i<16; i++) {
				if (player.inventory.getStackInSlot(i)==null) {
					player.inventory.mainInventory[i] = filterOld;
					break;
				}
				if(i==15) {
					player.entityDropItem(filterOld, 10F);
				}
			}
			player.inventoryContainer.detectAndSendChanges();
		}
	}

}
