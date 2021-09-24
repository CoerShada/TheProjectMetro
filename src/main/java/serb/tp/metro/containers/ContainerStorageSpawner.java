package serb.tp.metro.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import serb.tp.metro.blocks.tiles.storages.TileEntityStorage;
import serb.tp.metro.blocks.tiles.storages.spawners.TileEntityStorageSpawner;
import serb.tp.metro.containers.slots.SlotArmor;
import serb.tp.metro.containers.slots.SlotBackpack;
import serb.tp.metro.containers.slots.SlotChestrig;
import serb.tp.metro.containers.slots.SlotGun;
import serb.tp.metro.containers.slots.SlotHotbar;
import serb.tp.metro.containers.slots.SlotInBackpack;
import serb.tp.metro.containers.slots.SlotInChestrig;
import serb.tp.metro.containers.slots.SlotKnife;
import serb.tp.metro.containers.slots.SlotMask;
import serb.tp.metro.containers.slots.SlotOuterwear;
import serb.tp.metro.containers.slots.SlotPants;
import serb.tp.metro.containers.slots.SlotPistol;
import serb.tp.metro.items.ItemBackpack;
import serb.tp.metro.items.ItemChestrig;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.server.SaveContainerServerMessage;

public class ContainerStorageSpawner extends Container {

	private final TileEntityStorageSpawner tile;
	private int numRows;
	
	Slot[] slotsRig;
	Slot[] slotsBackpack;
	Slot[] slotsSpawning;
	Slot slotRig;
	Slot slotBackpack;
	
	private int numColumns;
	
	protected final EntityPlayer player;
	

	public ContainerStorageSpawner(EntityPlayer player, TileEntityStorageSpawner tile) {
		this.tile = tile;
		this.player = player;
		InventoryPlayer inventoryPlayer = player.inventory;
		if (tile.spawningIventory == null) return;
		tile.spawningIventory.openInventory();
		
		
		for (int i = 0; i < 9; i++) 
		{
			addSlotToContainer(new Slot(inventoryPlayer, i, i * 18 - 42, 65));
		}
		
		//инвентарь	все 3 ряда
		for (int i = 0; i < 3; ++i) 
		{
			for (int j = 0; j < 9; ++j) 
			{
				addSlotToContainer(new Slot(inventoryPlayer, i * 9 + j + 9, j * 18 - 42, 7 + i * 18));
			}
		}
		
		slotsSpawning = new Slot[tile.spawningIventory.getSizeInventory()];
		for (int i = 0; i<tile.spawningIventory.getSizeInventory(); i++) {
			slotsSpawning[i] = addSlotToContainer(new Slot(tile.spawningIventory, i, 143, 26+i*25));

		}

		
		

	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		if (tile == null) return false;
		if (tile.spawningIventory == null) return false;
		return tile.spawningIventory.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot_i) {
		ItemStack is = null;
		Slot slot = (Slot)inventorySlots.get(slot_i);

		if (slot != null && slot.getHasStack()) {
			ItemStack is1 = slot.getStack();
			is = is1.copy();

			if (slot_i < tile.inventory.getSizeInventory()) {
				
				if (!mergeItemStack(is1, tile.spawningIventory.getSizeInventory(), inventorySlots.size(), true)) return null;
			} else if (!mergeItemStack(is1, 0, tile.spawningIventory.getSizeInventory(), false))
				return null;

			if (is1.stackSize == 0) slot.putStack((ItemStack)null);
			else slot.onSlotChanged();
			
			
			
		}

		return is;
	}
	
	@Override
	public ItemStack slotClick(int slot, int button, int modifier, EntityPlayer player) {
		if (modifier==4)
			modifier = 0;
		ItemStack itemStack = super.slotClick(slot, button, modifier, player);
		return itemStack;
	}
	
	
	public void shiftSlots(int pixels) {
		for (int i = 0; i<tile.spawningIventory.getSizeInventory(); i++) {
			slotsSpawning[i].yDisplayPosition=26+i*25+pixels;
			if (slotsSpawning[i].yDisplayPosition<7 || slotsSpawning[i].yDisplayPosition>7+25*6) {
				slotsSpawning[i].xDisplayPosition = 90000;
			}
			else {
				slotsSpawning[i].xDisplayPosition=143;
			}
		}
		
	}

	
	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);
		tile.getDescriptionPacket();
		player.inventory.closeInventory();
		tile.inventory.closeInventory();
		tile.getDescriptionPacket();
		PacketDispatcher.sendToServer(new SaveContainerServerMessage(tile.xCoord, tile.yCoord, tile.zCoord));
		
	}

}

