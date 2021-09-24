package serb.tp.metro.network.client;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import serb.tp.metro.Main;
import serb.tp.metro.client.sounds.SoundsManager;
import serb.tp.metro.items.weapons.ItemWeapon;
import serb.tp.metro.network.AbstractMessage.AbstractClientMessage;

public class SoundsToPlayersMessage extends AbstractClientMessage<SoundsToPlayersMessage> {

	private int itemID;
	private int playerID;

	public SoundsToPlayersMessage() {

	}

	public SoundsToPlayersMessage(int playerID, int itemID) {
		this.playerID = playerID;
		this.itemID = itemID;
	}

	@Override
	protected void read(PacketBuffer buffer) {
		playerID = buffer.readInt();
		itemID = buffer.readInt();
	}

	@Override
	protected void write(PacketBuffer buffer) {
		buffer.writeInt(playerID);
		buffer.writeInt(itemID);
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		Item item = Item.getItemById(itemID);
		if (item instanceof ItemWeapon) {
			EntityPlayer playerSoundSource = (EntityPlayer) player.worldObj.getEntityByID(playerID);
			ItemWeapon gun = (ItemWeapon) item;
			ResourceLocation path = new ResourceLocation(Main.modid,  gun.getUnlocalizedName().substring(5).toLowerCase()+"_shoot");
			SoundsManager.stopPositionSound();
			SoundsManager.playPositionSound(path, (int)playerSoundSource.posX, (int)playerSoundSource.posY, (int)playerSoundSource.posZ, playerID);
		}
	}

}