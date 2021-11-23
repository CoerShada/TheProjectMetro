package serb.tp.metro.network.server;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import serb.tp.metro.Main;
import serb.tp.metro.common.ieep.WeaponSystem;
import serb.tp.metro.items.weapons.ItemWeapon;
import serb.tp.metro.network.AbstractMessage.AbstractServerMessage;

public class ChangeSafetyModMessage extends AbstractServerMessage<ChangeSafetyModMessage>{
	public ChangeSafetyModMessage(){

	}

	@Override
	protected void read(PacketBuffer buffer){
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void write(PacketBuffer buffer){
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		ItemStack hold = player.inventory.getCurrentItem();
		ItemWeapon weapon = (ItemWeapon) hold.getItem();
		weapon.safetyModChange(hold, player.worldObj, player);
	}

}
