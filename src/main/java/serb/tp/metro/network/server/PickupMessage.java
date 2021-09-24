package serb.tp.metro.network.server;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import serb.tp.metro.entities.player.handlers.WeightHandler;
import serb.tp.metro.items.ItemBackpack;
import serb.tp.metro.network.AbstractMessage.AbstractServerMessage;

public class PickupMessage extends AbstractServerMessage<PickupMessage> {

	private int entityID;
	private int slotIndex;
	private boolean stacked;

	public PickupMessage() {

	}

	public PickupMessage(int entityID, int slotIndex, boolean stacked) {
		this.entityID = entityID;
		this.slotIndex = slotIndex;
		this.stacked = stacked;
	}

	@Override
	protected void read(PacketBuffer buffer) {
		entityID = buffer.readInt();
		slotIndex = buffer.readInt();
		stacked = buffer.readBoolean();
	}

	@Override
	protected void write(PacketBuffer buffer) {
		buffer.writeInt(entityID);
		buffer.writeInt(slotIndex);
		buffer.writeBoolean(stacked);
		
	}

	@Override
	public void process(EntityPlayer player, Side side)
	{

		MinecraftServer server = MinecraftServer.getServer();
		EntityItem theItem = (EntityItem)server.getEntityWorld().getEntityByID(entityID);
		if(theItem!=null && theItem.getEntityItem().getItem()!=null && theItem.getEntityItem().getItem() instanceof ItemBackpack && player.inventory.getStackInSlot(18)==null) {
			player.inventory.mainInventory[18] = theItem.getEntityItem();
			player.worldObj.playSoundEffect(player.posX, player.posY, player.posZ, "random.pop", 1.0f, 1.0f);
			theItem.setDead();
		}
		else if (theItem!=null && theItem.getEntityItem().getItem()!=null && !(theItem.getEntityItem().getItem() instanceof ItemBackpack))
		{
			if (stacked)
				player.inventory.mainInventory[slotIndex].stackSize += theItem.getEntityItem().stackSize;
			else
				player.inventory.mainInventory[slotIndex] = theItem.getEntityItem();
			player.worldObj.playSoundEffect(player.posX, player.posY, player.posZ, "random.pop", 1.0f, 1.0f);
			theItem.setDead();
			
			
		}
		float weight = 0;
		for (int i = 0; i<20; i++) 
		{
			if (player.inventory.getStackInSlot(i)!=null && player.inventory.getStackInSlot(i).hasTagCompound()) 
			{
				try 
				{
					weight+=player.inventory.getStackInSlot(i).getTagCompound().getFloat("weight") * player.inventory.getStackInSlot(i).stackSize;
				}
				finally{}
			}
		}
		WeightHandler.setWeight(player, weight);
		player.inventoryContainer.detectAndSendChanges();
		
	}

}
