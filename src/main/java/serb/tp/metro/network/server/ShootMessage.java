package serb.tp.metro.network.server;

import java.util.Date;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.Maps;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import serb.tp.metro.Main;
import serb.tp.metro.entities.Bullet;
import serb.tp.metro.items.weapons.ItemBullet;
import serb.tp.metro.items.weapons.ItemWeapon;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.client.SoundsToPlayersMessage;
import serb.tp.metro.network.AbstractMessage.AbstractServerMessage;

public class ShootMessage extends AbstractServerMessage<ShootMessage> {

	public ShootMessage() {

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
		ItemWeapon weapon = (ItemWeapon) hold.getItem();
		weapon.shoot(hold, player.worldObj, player);
	}

}
