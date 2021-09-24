package serb.tp.metro.blocks.tiles.storages;

import net.minecraft.client.Minecraft;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants.NBT;

public class TileEntityStorage extends TileEntity {

	public InventoryBasic inventory;
	public int width;
	public int height;
	
	public TileEntityStorage() {
		//inventory = new InventoryBasic("tileInventory", false, width * height);
	}

	public TileEntityStorage(int width, int height) {

		inventory = new InventoryBasic("tileInventory", false, width * height);
		this.width = width;
		this.height = height;
	}
	
	

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("width", this.width);
		nbt.setInteger("height", this.height);
		if (inventory == null) return;
		NBTTagList list = new NBTTagList();
		for (int i = 0; i < inventory.getSizeInventory(); ++i) {
			if (inventory.getStackInSlot(i) == null) continue;
			NBTTagCompound slot_nbt = new NBTTagCompound();
			inventory.getStackInSlot(i).writeToNBT(slot_nbt);
			slot_nbt.setByte("Slot", (byte)i);
			list.appendTag(slot_nbt);
		}
		nbt.setTag("Inventory", list);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.width = nbt.getInteger("width");
		this.height = nbt.getInteger("height");
		inventory = new InventoryBasic("tileInventory", false, width * height);
		if (nbt.hasKey("Inventory", NBT.TAG_LIST)) {
			NBTTagList list = nbt.getTagList("Inventory", NBT.TAG_COMPOUND);
			for (int i = 0; i < list.tagCount(); ++i) {
				NBTTagCompound slot_nbt = list.getCompoundTagAt(i);
				inventory.setInventorySlotContents((slot_nbt.getByte("Slot") & 255), ItemStack.loadItemStackFromNBT(slot_nbt));
				
			}
			
		}
	}
	
	 @Override
	 public Packet getDescriptionPacket() {
		 NBTTagCompound tag = new NBTTagCompound();
		 writeToNBT(tag);
		 return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
	 }
	 
	 @Override
	 public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		 readFromNBT(pkt.func_148857_g());
	 }

}
