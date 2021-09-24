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
	protected Random rnd = new Random();
	private int[] bullets;
	private long notfire;
	private boolean jumming;
	private int modRecoil;

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
		MinecraftServer server = MinecraftServer.getServer();
		World world = server.getEntityWorld();
		ItemStack hold = player.inventory.getCurrentItem();
		if (hold==null) {
			return;
		}
		ItemWeapon holdItem = (ItemWeapon) hold.getItem();
		int[] bullets = hold.getTagCompound().getIntArray("bullets");
		hold.getTagCompound().setLong("notfire", new Date().getTime());
		if (bullets.length>0) {
			int[] bulletsNew = new int[bullets.length-1];
			ItemBullet bullet = (ItemBullet) Item.getItemById(bullets[0]);
			if (!hold.getTagCompound().getBoolean("jumming") && holdItem.getJummingChance()*bullet.jamming<rnd.nextInt(101)) 
			{
				
				for (int i = 0; i<bullets.length - 1; i++) 
				{
					bulletsNew[i] = bullets[i+1];
				}
				
				PacketDispatcher.sendToAllAround(new SoundsToPlayersMessage(player.getEntityId(), Item.getIdFromItem(holdItem)), player, holdItem.getSoundRadius(hold)*2);
				world.spawnEntityInWorld(new Bullet(world, player, hold.getTagCompound().getDouble("accuracy"), bullet.damage, bullet.penetration));
				hold.getTagCompound().setDouble("weight", hold.getTagCompound().getDouble("weight")- bullet.weight);
				hold.getTagCompound().setIntArray("bullets", bulletsNew);
				player.inventoryContainer.detectAndSendChanges();
				return;
			}
			else 
			{
				hold.getTagCompound().setBoolean("jumming", true);
			}
		}
		//Тут звук щелчка
	}

}
