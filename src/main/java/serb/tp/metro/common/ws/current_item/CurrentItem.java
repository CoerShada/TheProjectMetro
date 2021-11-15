package serb.tp.metro.common.ws.current_item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import serb.tp.metro.containers.InventoryItemStorage;
import serb.tp.metro.items.Item3D;
import serb.tp.metro.items.ItemChestrig;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.server.LoadAmmoMessage;


public abstract class CurrentItem {
	protected EntityPlayer player;
	protected World world;
	protected ItemStack is;
	protected String name;
	protected ArrayList<Integer> ammo;
	protected float weight;
	protected final int slotId;
	protected float[] pos = new float[3];
	protected float[] rotation = new float[3];
	
	
	public CurrentItem(EntityPlayer player, World world) {
		this(player, world, -1);
	}
	
	public CurrentItem(EntityPlayer player, World world, long counter) {
		this.player = player;
		this.world = world;
		this.is = player.inventory.getCurrentItem();
		this.slotId = player.inventory.currentItem;
		this.name = player.inventory.getCurrentItem().getUnlocalizedName();
		this.weight = player.inventory.getCurrentItem().getTagCompound().getFloat("weight");
	}
	
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void onUpdate() {
		
	}
	
	public int getSlotId() {
		return slotId;
	}
	
	public ItemStack getCurrentItemStack() {
		return is;
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
	
	protected enum State{
		LOAD,
		UNLOAD
	}
}
