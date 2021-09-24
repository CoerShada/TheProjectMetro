package serb.tp.metro.blocks.tiles.storages.creators;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;
import serb.tp.metro.blocks.BlockCreator;
import serb.tp.metro.blocks.tiles.storages.TileEntityStorage;
import serb.tp.metro.containers.ContainerCreator;
import serb.tp.metro.containers.InventoryItemStorage;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.client.SyncCraftTimeMessage;
import serb.tp.metro.recipes.Recipe;

public abstract class TileEntityCreator extends TileEntityStorage{
	public long craftEndTime;
	public InventoryBasic result = new InventoryBasic("result", false, 2);
	
	
	public TileEntityCreator() {
		
	}
	
	public TileEntityCreator(int width, int height) {
		super(width, height);
		this.craftEndTime = 0;
		
	}
	
	public boolean newCraft(EntityPlayer player, Recipe recipe) {

		
		for (int i = 0; i<recipe.receivedItems.length; i++) {
			
			ItemStack is = new ItemStack(recipe.receivedItems[i]);
			is.stackSize = recipe.quantityReceivedItems[i];
			result.setInventorySlotContents(i, is);
		}
		

		
		CompletableFuture.runAsync(() -> {
			try {
				
				Thread.sleep(recipe.craftTime);
				
				endCraft();
				
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		});
		
		this.craftEndTime = new Date().getTime() + recipe.craftTime;
		this.getDescriptionPacket();
		return true;
	}
	
	
	public long getTimeLeft() {
		if (this.craftEndTime==0) return 0;
		else return (this.craftEndTime - new Date().getTime())/1000;
	}
	
	public boolean removalOfIngredients(EntityPlayer player, Recipe recipe) {
		
		InventoryItemStorage inventoryBackpack = ((ContainerCreator) player.openContainer).inventoryBackpack;

		
		InventoryItemStorage inv = ((ContainerCreator) player.openContainer).inv;
	
		for (int i = 0; i< recipe.neededItems.length; i++) {
			int count = recipe.quantityNeededItems[i];
			if (inventoryBackpack!=null) {
				for (int j = 0; j<inventoryBackpack.getSizeInventory(); j++) {
					if (inventoryBackpack.getStackInSlot(j)==null || !inventoryBackpack.getStackInSlot(j).getItem().equals(recipe.neededItems[i])) continue;
					
					if (count<inventoryBackpack.getStackInSlot(j).stackSize){
						count = 0;
						break;
					}
					else {
						count-=inventoryBackpack.getStackInSlot(j).stackSize;
					}
				}
				if (count==0) break;
			}
			
			for (int j = 3; j<16; j++) {
				if (player.inventory.getStackInSlot(j)==null || !player.inventory.getStackInSlot(j).getItem().equals(recipe.neededItems[i])) continue;
				
				if (count<player.inventory.getStackInSlot(j).stackSize){
					count = 0;
					break;
				}
				else {
					count-=player.inventory.getStackInSlot(j).stackSize;
				}
			}
			
			if (inv!=null) {
				if (count==0) break;
				for (int j = 0; j<inv.getSizeInventory(); j++) {
					if (inv.getStackInSlot(j)==null || !inv.getStackInSlot(j).getItem().equals(recipe.neededItems[i])) continue;
					
					if (count<inv.getStackInSlot(j).stackSize){
						count = 0;
						break;
					}
					else {
						count-=inv.getStackInSlot(j).stackSize;
					}
				}
				
			}
			
			if (count>0) {
				if (inv != null)
					inv.closeInventory();
				if (inventoryBackpack!=null)
					inventoryBackpack.closeInventory();
				return false;
			}
		}
		
		for (int i = 0; i< recipe.neededItems.length; i++) {
			int count = recipe.quantityNeededItems[i];
			
			if (inventoryBackpack!=null) {
				
				for (int j = 0; j<inventoryBackpack.getSizeInventory(); j++) {
					if (inventoryBackpack.getStackInSlot(j)==null || !inventoryBackpack.getStackInSlot(j).getItem().equals(recipe.neededItems[i])) continue;
					
					if (count<inventoryBackpack.getStackInSlot(j).stackSize){
						inventoryBackpack.getStackInSlot(j).splitStack(count);
						count = 0;
						break;
					}
					else {
						count-=inventoryBackpack.getStackInSlot(j).stackSize;
						inventoryBackpack.setInventorySlotContents(j, null);
						
					}
				}
				
				if (count==0) {
					inventoryBackpack.save();
					break;
				}
			}
			
			
			for (int j = 3; j<16; j++) {
				if (player.inventory.getStackInSlot(j)==null || !player.inventory.getStackInSlot(j).getItem().equals(recipe.neededItems[i])) continue;
				
				if (count<player.inventory.getStackInSlot(j).stackSize){
					player.inventory.getStackInSlot(j).stackSize -= count;
					count = 0;
					break;
				}
				else {
					count-=player.inventory.getStackInSlot(j).stackSize;
					player.inventory.setInventorySlotContents(j, null);
					
				}
			}
			
			if (inv!=null) {
				
				for (int j = 0; j<inventoryBackpack.getSizeInventory(); j++) {
					if (inventoryBackpack.getStackInSlot(j)==null || !inventoryBackpack.getStackInSlot(j).getItem().equals(recipe.neededItems[i])) continue;
					
					if (count<inventoryBackpack.getStackInSlot(j).stackSize){
						inventoryBackpack.getStackInSlot(j).splitStack(count);
						count = 0;
						break;
					}
					else {
						count-=inventoryBackpack.getStackInSlot(j).stackSize;
						inventoryBackpack.setInventorySlotContents(j, null);
						
					}
				}
				
				if (count==0) {
					inv.save();
					break;
				}
			}
		
		}

		return true;
	}
	
	
	public void endCraft() {

		this.craftEndTime =0;
		if (Minecraft.getMinecraft().theWorld!=null)
		PacketDispatcher.sendToAll(new SyncCraftTimeMessage(0, this.xCoord, this.yCoord, this.zCoord));
		for (int i = 0; i<result.getSizeInventory(); i++) {
			boolean placed = false;
			if (result.getStackInSlot(i)==null) continue;
			for (int j = 0; j<inventory.getSizeInventory(); j++) {
				if (inventory.getStackInSlot(j)==null) {
					inventory.setInventorySlotContents(j, result.getStackInSlot(i));
					placed = true;
					break;
				}
				else if (inventory.getStackInSlot(j).isItemEqual(result.getStackInSlot(i))){
					if (inventory.getStackInSlot(j).getMaxStackSize()<=inventory.getStackInSlot(j).stackSize+inventory.getStackInSlot(j).stackSize) {
						int stackSize = inventory.getStackInSlot(j).getMaxStackSize()- inventory.getStackInSlot(j).stackSize;
						inventory.getStackInSlot(j).stackSize+=result.getStackInSlot(i).stackSize - stackSize;
						result.getStackInSlot(i).stackSize-=stackSize;
						
					}
					else {
						inventory.getStackInSlot(j).stackSize+=result.getStackInSlot(i).stackSize;
						result.getStackInSlot(i).stackSize = 0;
					}
					
					if (result.getStackInSlot(i).stackSize==0) {
						placed = true;
						break;
					}
					
				}
			}
			if (!placed) {
				int x = this.xCoord;
				int z = this.zCoord;
				if (this.getBlockMetadata()==0) {
					x--;
				}
				else if (this.getBlockMetadata()==0) {
					z--;
				}
				else if (this.getBlockMetadata()==0) {
					x+=((BlockCreator) this.blockType).getSize()[0] +1;
				}
				else if (this.getBlockMetadata()==0) {
					z+=((BlockCreator) this.blockType).getSize()[3] +1;
				}
				EntityItem entityToSpawn = new EntityItem(this.worldObj, x, this.yCoord, z, result.getStackInSlot(i));
				this.worldObj.spawnEntityInWorld(entityToSpawn);
			}
		}

		
	}
	
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		if (result==null) return;
		nbt.setLong("craftEndTime", this.craftEndTime);	
		NBTTagList list = new NBTTagList();
		for (int i = 0; i < result.getSizeInventory(); ++i) {
			if (result.getStackInSlot(i) == null) continue;
			NBTTagCompound slot_nbt = new NBTTagCompound();
			result.getStackInSlot(i).writeToNBT(slot_nbt);
			slot_nbt.setByte("Slot", (byte)i);
			list.appendTag(slot_nbt);
		}
		nbt.setTag("Result", list);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.craftEndTime = nbt.getLong("craftEndTime");
		this.result = new InventoryBasic("result", false, 2);
		boolean craft = false;
		if (nbt.hasKey("Result", NBT.TAG_LIST)) {
			NBTTagList list = nbt.getTagList("Result", NBT.TAG_COMPOUND);
			for (int i = 0; i < list.tagCount(); ++i) {
				NBTTagCompound slot_nbt = list.getCompoundTagAt(i);
				this.result.setInventorySlotContents(slot_nbt.getByte("Slot") & 255, ItemStack.loadItemStackFromNBT(slot_nbt));
				if (result.getStackInSlot(i)!=null) {
					craft = true;
				}
			}	
		}
		
		/*inventory = new InventoryBasic("result", false, 2);
		if (nbt.hasKey("Inventory", NBT.TAG_LIST)) {
			NBTTagList list = nbt.getTagList("Inventory", NBT.TAG_COMPOUND);
			for (int i = 0; i < list.tagCount(); ++i) {
				NBTTagCompound slot_nbt = list.getCompoundTagAt(i);
				inventory.setInventorySlotContents((slot_nbt.getByte("Slot") & 255), ItemStack.loadItemStackFromNBT(slot_nbt));
				
			}
			
		}*/
		
		if (craft && this.craftEndTime<=new Date().getTime()) {
			this.endCraft();
		}
		else if (craft) {
			CompletableFuture.runAsync(() -> {
				try {
					
					Thread.sleep(this.craftEndTime-new Date().getTime());
					endCraft();
					
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			});
		}
	}
	
}
