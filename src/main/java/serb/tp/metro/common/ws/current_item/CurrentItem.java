package serb.tp.metro.common.ws.current_item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;


public abstract class CurrentItem {
	protected EntityPlayer player;
	protected World world;
	protected String name;
	protected ArrayList<Integer> ammo;
	protected long counter;
	protected float[] pos = new float[3];
	protected float[] rotation = new float[3];
	
	public CurrentItem(EntityPlayer player, World world) {
		this.player = player;
		this.world = world;
		this.counter = world.getTotalWorldTime();
	}
	
	public void onUpdate() {
		
	}
	
	public long getCounter() {
		return counter;
	}
	
	public void setAmmo(int[] ammo) {
		this.ammo = new ArrayList<Integer>();
		Collections.addAll(this.ammo, Arrays.stream(ammo).boxed().toArray(Integer[]::new));
	}
	
	public int[] getAmmo() {
		return ammo.stream().mapToInt(i -> i).toArray();
	}
	
	public void setPlayer(EntityPlayer player) {
		this.player = player;
	}
	
	public EntityPlayer getPlayer() {
		return player;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
