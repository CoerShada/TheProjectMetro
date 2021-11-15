package serb.tp.metro.network.server;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import serb.tp.metro.items.Item3D;
import serb.tp.metro.network.AbstractMessage.AbstractServerMessage;

public class OnItemLeftClickMessage extends AbstractServerMessage<OnItemLeftClickMessage> {


		public OnItemLeftClickMessage() {

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
			ItemStack hold = player.inventory.getCurrentItem();
			Item3D holdItem = (Item3D) hold.getItem();
			holdItem.onItemLeftClick(hold, player.worldObj, player);
			
		}
}
