package serb.tp.metro.common.ws.current_item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CurrentMag extends CurrentItem{
	
	private int loadTime;
	private int unloadTime;

	public CurrentMag(EntityPlayer player, World world) {
		super(player, world);
		
	}
	
	@Override
	public void onUpdate() {
		
	}
	
	public void loadAmmo(int bulletId) {
		ammo.add(bulletId, 0);
		counter = world.getTotalWorldTime() + loadTime;
	}

	public int unloadAmmo() {
		int bulletId;
		if (ammo.size()==0 || counter+unloadTime <world.getTotalWorldTime())
			return -1;
		bulletId = ammo.get(0);
		ammo.remove(0);
		counter = world.getTotalWorldTime() + unloadTime;
		return bulletId;
	}
}
