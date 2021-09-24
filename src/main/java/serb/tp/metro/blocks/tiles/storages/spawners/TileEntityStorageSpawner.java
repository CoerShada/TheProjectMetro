package serb.tp.metro.blocks.tiles.storages.spawners;


import java.util.Date;
import java.util.Random;

import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.Constants.NBT;
import serb.tp.metro.blocks.tiles.storages.TileEntityStorage;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.server.ChangeSpawnProbabilityMessage;

public class TileEntityStorageSpawner extends TileEntityStorage{

	protected Random rnd = new Random();
	public InventoryBasic spawningIventory;
	public float[] spawnProbability;
	public long spawnTime;
	public long lastSpawnTime;
	public int maxQuantityLoot;
	
	public TileEntityStorageSpawner() {

	}

	public TileEntityStorageSpawner(int width, int height) {
		super(width, height);
		spawningIventory = new InventoryBasic("tileInventorySpawning", false, 30);
		spawnProbability = new float[30];
		lastSpawnTime = new Date().getTime();
		maxQuantityLoot = 0;
		spawnTime = 0;
	}
	
	public void spawnLoot() {
		int tmp = 0;
		for (int i = 0; i<spawningIventory.getSizeInventory(); i++)
			if (spawningIventory.getStackInSlot(i)!=null)
				tmp++;
		
		int quantityLoot;
		if (tmp==0)
			return;
		else {
			
			quantityLoot = 0;
			float k = 0F;
			while (rnd.nextFloat()<1-k+0.001 && quantityLoot<spawningIventory.getSizeInventory()) {
				quantityLoot++;
				k=((float)quantityLoot/maxQuantityLoot) /((float)maxQuantityLoot/quantityLoot);
				
			}
		}
		InventoryBasic spawningIventory = new InventoryBasic("buf", false, this.spawningIventory.getSizeInventory());
		for (int i = 0; i<spawningIventory.getSizeInventory(); i++)
			spawningIventory.setInventorySlotContents(i, this.spawningIventory.getStackInSlot(i));
		float[] spawnProbability = this.spawnProbability.clone();
		
		float buff;
		ItemStack buffIS;
        int left = 0;
        int right = spawnProbability.length - 1;
        do {
            for (int i = left; i < right; i++) {
                if (spawnProbability[i] < spawnProbability[i + 1]) {
                    buff = spawnProbability[i];
                    spawnProbability[i] = spawnProbability[i + 1];
                    spawnProbability[i + 1] = buff;
                    
                    buffIS = spawningIventory.getStackInSlot(i);
                    spawningIventory.setInventorySlotContents(i, spawningIventory.getStackInSlot(i+1));
                    spawningIventory.setInventorySlotContents(i+1, buffIS);
                }
            }
            right--;
            for (int i = right; i > left; i--) {
                if (spawnProbability[i] > spawnProbability[i - 1]) {
                    buff = spawnProbability[i];
                    spawnProbability[i] = spawnProbability[i - 1];
                    spawnProbability[i - 1] = buff;
                    
                    buffIS = spawningIventory.getStackInSlot(i);
                    spawningIventory.setInventorySlotContents(i, spawningIventory.getStackInSlot(i-1));
                    spawningIventory.setInventorySlotContents(i-1, buffIS);
                }
            }
            left++;
        } while (left < right);
		
		ItemStack[] bufinv = new ItemStack[quantityLoot];
		boolean[] spawning = new boolean[spawningIventory.getSizeInventory()];
		int spawningCounter = 0;
		while (spawningCounter<quantityLoot) {
			
			for (int i = 0; i<spawningIventory.getSizeInventory(); i++) {
				
				if(spawningIventory.getStackInSlot(i)==null) continue;
				float r = rnd.nextFloat();
				if((r<=spawnProbability[i] && !spawning[i]) || (r<=spawnProbability[i]/20 && spawning[i])) {
					spawning[i] = true;
					bufinv[spawningCounter] = spawningIventory.getStackInSlot(i).copy();
					if (spawningIventory.getStackInSlot(i).stackSize>1) 
					{
						int stackSize = 1;
						
						while (rnd.nextFloat()<=spawnProbability[i]*(spawningIventory.getStackInSlot(i).stackSize/stackSize) && stackSize<spawningIventory.getStackInSlot(i).stackSize) {
							stackSize++;
						}
						bufinv[spawningCounter].stackSize= stackSize;
					}
					spawningCounter++;	
					if (spawningCounter>=quantityLoot)
						break;
				}
			}
		}
		for (int i = 0; i<inventory.getSizeInventory(); i++) {
			inventory.setInventorySlotContents(i, null);
		}
		for (int i = 0; i<quantityLoot; i++) {
			int r;
			do {
				r = rnd.nextInt(this.inventory.getSizeInventory());
			}while (this.inventory.getStackInSlot(r)!=null);
			this.inventory.setInventorySlotContents(r, bufinv[i]);
		}
		lastSpawnTime = new Date().getTime();
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setLong("SpawnTime", spawnTime);
		nbt.setLong("LastSpawnTime", lastSpawnTime);
		nbt.setInteger("maxQuantityLoot", maxQuantityLoot);
		if (spawningIventory == null) return;
		NBTTagList list1 = new NBTTagList();
		for (int i = 0; i < spawningIventory.getSizeInventory(); ++i) {
			if (spawningIventory.getStackInSlot(i) == null) continue;
			NBTTagCompound slot_nbt = new NBTTagCompound();
			spawningIventory.getStackInSlot(i).writeToNBT(slot_nbt);
			slot_nbt.setByte("Slot", (byte)i);
			slot_nbt.setFloat("Probability", spawnProbability[i]);
			list1.appendTag(slot_nbt);
		}
		nbt.setTag("SP_inventory", list1);
		
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		spawnProbability = new float[30];
		spawningIventory = new InventoryBasic("tileInventorySpawning", false, 30);
		spawnTime = nbt.getLong("SpawnTime");
		lastSpawnTime = nbt.getLong("LastSpawnTime");
		maxQuantityLoot = nbt.getInteger("maxQuantityLoot");
		if (nbt.hasKey("SP_inventory", NBT.TAG_LIST)) {
			NBTTagList list1 = nbt.getTagList("SP_inventory", NBT.TAG_COMPOUND);
			for (int i = 0; i < list1.tagCount(); ++i) {
				NBTTagCompound slot_nbt = list1.getCompoundTagAt(i);
				spawnProbability[i] = slot_nbt.getFloat("Probability");
				spawningIventory.setInventorySlotContents((slot_nbt.getByte("Slot") & 255), ItemStack.loadItemStackFromNBT(slot_nbt));
			}
			
		}
		
	}
	
	
}
