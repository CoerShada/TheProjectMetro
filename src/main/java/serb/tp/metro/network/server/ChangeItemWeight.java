package serb.tp.metro.network.server;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import serb.tp.metro.network.AbstractMessage.AbstractServerMessage;

public class ChangeItemWeight extends AbstractServerMessage<ChangeItemWeight> {

	private int slotIndex;
	private float weight;

	public ChangeItemWeight() {

	}

	public ChangeItemWeight(int slotIndex, float weight) {
		this.slotIndex = slotIndex;
		this.weight = weight;
	}

	@Override
	protected void read(PacketBuffer buffer) {
		slotIndex = buffer.readInt();
		weight = buffer.readFloat();
	}

	@Override
	protected void write(PacketBuffer buffer) {
		buffer.writeInt(slotIndex);
		buffer.writeFloat(weight);
	}

	@Override
	public void process(EntityPlayer player, Side side) 
	{
		ItemStack itemStack = player.inventory.getStackInSlot(slotIndex);
		if (itemStack!=null && itemStack.hasTagCompound()) 
		{
			itemStack.getTagCompound().setFloat("weight", itemStack.getTagCompound().getFloat("weightStandart")+weight);
		}
		player.inventoryContainer.detectAndSendChanges();
		
	}

}
