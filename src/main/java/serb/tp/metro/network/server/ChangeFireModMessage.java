package serb.tp.metro.network.server;

import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import serb.tp.metro.Main;
import serb.tp.metro.items.weapons.ItemWeapon;
import serb.tp.metro.network.AbstractMessage.AbstractServerMessage;

public class ChangeFireModMessage extends AbstractServerMessage<ChangeFireModMessage>{
	

	
	public ChangeFireModMessage() {

	}
	

	@Override
	protected void read(PacketBuffer buffer){

		
	}

	@Override
	protected void write(PacketBuffer buffer){

		
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		ItemStack hold = player.inventory.getCurrentItem();
		ItemWeapon weapon = (ItemWeapon) hold.getItem();
		weapon.fireModChange(hold, player.worldObj, player);
		
		
	}

}
