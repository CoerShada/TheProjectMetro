package serb.tp.metro.network.client;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import serb.tp.metro.containers.CustomSlots;
import serb.tp.metro.entities.player.ExtendedPlayer;
import serb.tp.metro.items.armor.LoadItemArmor;
import serb.tp.metro.network.AbstractMessage.AbstractClientMessage;

public class SyncEquipOuterwearMessage extends AbstractClientMessage<SyncEquipOuterwearMessage> {

	private int playerID;
	private int equipID;

	public SyncEquipOuterwearMessage() {

	}

	public SyncEquipOuterwearMessage(int playerID, int equipID) {
		this.playerID = playerID;
		this.equipID = equipID;
	}

	@Override
	protected void read(PacketBuffer buffer) {
		playerID = buffer.readInt();
		equipID = buffer.readInt();
	}

	@Override
	protected void write(PacketBuffer buffer) {
		buffer.writeInt(playerID);
		buffer.writeInt(equipID);
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		if(Minecraft.getMinecraft().thePlayer.getEntityId() == playerID) {
			return;
		}

		Entity playerToUpd = player.worldObj.getEntityByID(playerID);
		if(playerToUpd == null || !(playerToUpd instanceof EntityPlayer)) {
			return;
		}

		ItemStack itemStack = null;
		if (equipID == 1) itemStack = new ItemStack(LoadItemArmor.bracers);
		else itemStack = null;
		ExtendedPlayer.get((EntityPlayer) playerToUpd).inventory.inventory[CustomSlots.OUTERWEAR.ordinal()] = itemStack;
	}

}