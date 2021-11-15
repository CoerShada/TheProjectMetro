package serb.tp.metro.common.ws.current_item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import serb.tp.metro.items.modules.ItemMag;
import serb.tp.metro.items.weapons.ItemBullet;

public class CurrentMag extends CurrentItem{
	
	private int loadTime;
	private int unloadTime;
	private ItemBullet getBullet;

	public CurrentMag(EntityPlayer player, World world) {
		this(player, world, -1);
		
	}
	
	public CurrentMag(EntityPlayer player, World world, long counter) {
		super(player, world, counter);
		ItemMag mag = (ItemMag) player.inventory.getCurrentItem().getItem();
		getBullet = mag.getBullet(is);
	}
	
	@Override
	public void onUpdate() {
		
	}
	
	public ItemBullet getBullet() {
		return getBullet;
	}
	
	public int getLoadTime() {
		return loadTime;
	}
	
	public int getUnloadTime() {
		return loadTime;
	}
	
	public boolean loadAmmo(int bulletId, long counter) {
		if (counter<world.getTotalWorldTime()) return false;
		ammo.add(bulletId, 0);
		//counter = world.getTotalWorldTime() + loadTime;
		return true;
	}

	
	public int unloadAmmo(long counter) {
		int bulletId;
		if (ammo.size()==0 || counter<world.getTotalWorldTime())
			return -1;
		bulletId = ammo.get(0);
		ammo.remove(0);
		//counter = world.getTotalWorldTime() + unloadTime;
		return bulletId;
	}
	

}
