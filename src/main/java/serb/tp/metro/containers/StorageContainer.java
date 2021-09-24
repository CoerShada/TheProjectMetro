package serb.tp.metro.containers;

import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import serb.tp.metro.blocks.tiles.storages.TileEntityStorage;
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

public class StorageContainer extends Container {

	private final TileEntityStorage tile;
	private int numRows;
	
	Slot[] slotsRig;
	Slot[] slotsBackpack;
	Slot slotRig;
	Slot slotBackpack;
	
	private int numColumns;
	
	protected final EntityPlayer player;
	public InventoryItemStorage inv;
	public InventoryItemStorage inventoryBackpack;
	
	protected static int slotHotbarStart = 0;
	protected static int slotHotbarEnd = slotHotbarStart + 5; 

	protected static int slotInventoryStart = slotHotbarEnd + 1;
	protected static int slotInventoryEnd = slotInventoryStart + 8; //43

	public InventoryCrafting craftMatrix = new InventoryCrafting(this, 2, 2);
	public IInventory craftResult = new InventoryCraftResult();
	protected static int slotCraftStart = slotInventoryEnd + ArmorSlots.values().length + 6; //17; //12
	protected static int slotCraftEnd = slotCraftStart + 3; //15
	protected static int slotCraftResult = slotCraftEnd + 1; //16  

	//к индексам кастомного инвентаря не относится
	protected static int vanillaSlotHotbarStart = 0;
	protected static int vanillaSlotHotbarEnd = 8;	
	protected static int vanillaSlotInventoryStart = 9;
	protected static int vanillaSlotInventoryEnd = 35;

	public StorageContainer(EntityPlayer player, TileEntityStorage tile, int width, int height) {
		this.tile = tile;
		this.player = player;
		InventoryPlayer inventoryPlayer = player.inventory;
		if (tile.inventory == null) return;
		tile.inventory.openInventory();
		
		int leftOffset = 125 - (width+1)*9;
		for (int i = 0; i < height; ++i) 
			for (int j=0; j<width; ++j) 
				addSlotToContainer(new Slot(tile.inventory, width * i + j , leftOffset + j * 18,  (i) * 18 + 5));
				
					
		if (player.inventory.getStackInSlot(19)!=null && player.inventory.getStackInSlot(19).getItem() instanceof ItemChestrig)
			inv = new InventoryItemStorage(player.inventory.getStackInSlot(19));
		else
			inv = null;
		
		if (player.inventory.getStackInSlot(18)!=null && player.inventory.getStackInSlot(18).getItem() instanceof ItemBackpack)
			inventoryBackpack = new InventoryItemStorage(player.inventory.getStackInSlot(18));
		else
			inventoryBackpack = null;



		addSlotToContainer(new SlotKnife(inventoryPlayer, 0, 66, 124)); 
		addSlotToContainer(new SlotPistol(inventoryPlayer, 1, 84, 124)); 
		addSlotToContainer(new SlotGun(inventoryPlayer, 2, 102, 124)); 
		
		for (int i = 0; i < 3; i++) 
		{
			addSlotToContainer(new SlotHotbar(inventoryPlayer, 3 + i, 127 + i * 18, 124));
		}
		
		//инвентарь	все 3 ряда
		for (int i = 0; i < 3; ++i) 
		{
			for (int j = 0; j < 3; ++j) 
			{
				addSlotToContainer(new Slot(inventoryPlayer, 6 + j + i * 3, 127 + j * 18, 68 + i * 18));
			}
		}

		//броня
		for (int i = 0; i < 2; ++i) 
		{
			addSlotToContainer(new SlotArmor(player, inventoryPlayer, inventoryPlayer.getSizeInventory() - 1 - i, 84, 68 + i * 18, i));
		}
		
		addSlotToContainer(new SlotMask(player, inventoryPlayer, 15, 66, 68)); 
		addSlotToContainer(new SlotOuterwear(inventoryPlayer, 16, 66, 86)); 
		addSlotToContainer(new SlotPants(inventoryPlayer, 17, 66, 104)); 
		
		slotBackpack = addSlotToContainer(new SlotBackpack(inventoryPlayer, 18, 102, 68));        
		slotRig = addSlotToContainer(new SlotChestrig(inventoryPlayer, 19, 102, 86)); 

		if (inv!=null) 
		{
			
			inv.openInventory();
			slotsRig = new Slot[inv.getSizeInventory()];
			int columns = 2;
			if (inv.getSizeInventory()%2!=0) 
			{
				columns = 3;
			}
			for (int i = 0; i < inv.getSizeInventory()/columns; ++i) {
				for (int j = 0; j < columns; ++j) 
				{
					slotsRig[columns * i + j] = addSlotToContainer(new SlotInChestrig(inv, columns * i + j, 200 + j * 18, 18 * i + 68));
				}
			}	
		}
		if(inventoryBackpack!=null) {
			inventoryBackpack.openInventory();
			slotsBackpack = new Slot[inventoryBackpack.getSizeInventory()];
			numColumns = inventoryBackpack.getSizeInventory() / 3;
			for (int i = 0; i < 3; ++i) {
				for (int j = 0; j < numColumns; ++j)
					slotsBackpack[numColumns * i + j] = addSlotToContainer(new SlotInBackpack(inventoryBackpack, numColumns * i + j, 89 - 9 * (numColumns - 3) + 18 * j, 18 *i + 145));
			}
		}

	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		if (tile == null) return false;
		if (tile.inventory == null) return false;
		return tile.inventory.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot_i) {
		ItemStack is = null;
		Slot slot = (Slot)inventorySlots.get(slot_i);

		if (slot != null && slot.getHasStack()) {
			ItemStack is1 = slot.getStack();
			is = is1.copy();

			if (slot_i < tile.inventory.getSizeInventory()) {
				
				if (!mergeItemStack(is1, tile.inventory.getSizeInventory(), inventorySlots.size(), true)) return null;
			} else if (!mergeItemStack(is1, 0, tile.inventory.getSizeInventory(), false))
				return null;

			if (is1.stackSize == 0) slot.putStack((ItemStack)null);
			else slot.onSlotChanged();
			
			
			
		}
		return is;
	}
	
	@Override
	public ItemStack slotClick(int slot, int button, int modifier, EntityPlayer player) {
		if (modifier == 2) return null;//Блокируем возможность использование игроком цифровых кнопок, чтобы не было попытки подмены
		if (slot>0 && modifier==4)
			modifier = 0;
		
		ItemStack itemStack = super.slotClick(slot, button, modifier, player);
		if (itemStack!= null && itemStack.getItem() instanceof ItemChestrig && slot==slotRig.slotNumber && slotRig.getStack()!=null && slotRig.getStack()!=itemStack) {
			for (int i = 0; i<inv.getSizeInventory(); i++) 
			{
				this.inventorySlots.remove(slotsRig[i]);
			}
			inv.save();
			inv.closeInventory();
			inv = new InventoryItemStorage(player.inventory.getStackInSlot(19));;
			slotsRig = new Slot[inv.getSizeInventory()];
			inv.openInventory();
			int columns = 2;
			if (inv.getSizeInventory()%2!=0) 
			{
				columns = 3;
			}
			for (int i = 0; i < inv.getSizeInventory()/columns; ++i) {
				for (int j = 0; j < columns; ++j) 
				{
					slotsRig[columns * i + j] = addSlotToContainer(new SlotInChestrig(inv, columns * i + j, 200 + j * 18, 18 * i + 68));
				}
			}	
			
		}	
		else {	
			if (inv!=null && itemStack!=null && itemStack.getItem() instanceof ItemChestrig && slot == this.slotRig.slotNumber && this.slotRig.getStack()==null) {
				for (int i = 0; i<inv.getSizeInventory(); i++) 
				{
					this.inventorySlots.remove(slotsRig[i]);
				}
				inv.save();
				inv.closeInventory();
				inv = null;
				slotsRig = null;
			}
			if (inv==null && itemStack==null && player.inventory.getStackInSlot(19)!=null && player.inventory.getStackInSlot(19).getItem() instanceof ItemChestrig) 
			{
				inv = new InventoryItemStorage(player.inventory.getStackInSlot(19));
				inv.openInventory();
				slotsRig = new Slot[inv.getSizeInventory()];
				int columns = 2;
				if (inv.getSizeInventory()%2!=0) 
				{
					columns = 3;
				}
				for (int i = 0; i < inv.getSizeInventory()/columns; ++i) {
					for (int j = 0; j < columns; ++j) 
					{
						slotsRig[columns * i + j] = addSlotToContainer(new SlotInChestrig(inv, columns * i + j, 200 + j * 18, 18 * i + 68));
					}
				}	
			}
		}

		if(itemStack!= null && itemStack.getItem() instanceof ItemBackpack && slot==slotBackpack.slotNumber && slotBackpack.getStack()!=null && slotBackpack.getStack()!=itemStack) {
			for (int i = 0; i<inventoryBackpack.getSizeInventory(); i++) 
			{
				this.inventorySlots.remove(slotsBackpack[i]);
			}
			inventoryBackpack.save();
			inventoryBackpack.closeInventory();
			inventoryBackpack = new InventoryItemStorage(player.inventory.getStackInSlot(18));
			slotsBackpack = new Slot[inventoryBackpack.getSizeInventory()];
			inventoryBackpack.openInventory();
			
			numColumns = inventoryBackpack.getSizeInventory() / 3;
			for (int i = 0; i < 3; ++i) {
				for (int j = 0; j < numColumns; ++j)
					slotsBackpack[numColumns * i + j] = addSlotToContainer(new SlotInBackpack(inventoryBackpack, numColumns * i + j, 89 - 9 * (numColumns - 3) + 18 * j, 18 *i + 145));
			}
			
		}
		else {
			if (inventoryBackpack!=null && slot == this.slotBackpack.slotNumber && itemStack!=null && itemStack.getItem() instanceof ItemBackpack && this.slotBackpack.getStack()==null) {
				for (int i = 0; i<inventoryBackpack.getSizeInventory(); i++) 
				{
					this.inventorySlots.remove(slotsBackpack[i]);
				}
				inventoryBackpack.save();
				inventoryBackpack.closeInventory();
				inventoryBackpack = null;
				slotsBackpack = null;
			}
			if (inventoryBackpack==null && itemStack==null && player.inventory.getStackInSlot(18)!=null && player.inventory.getStackInSlot(18).getItem() instanceof ItemBackpack) 
			{
				inventoryBackpack = new InventoryItemStorage(player.inventory.getStackInSlot(18));
				inventoryBackpack.openInventory();
				slotsBackpack = new Slot[inventoryBackpack.getSizeInventory()];
			
				numColumns = inventoryBackpack.getSizeInventory() / 3;
				for (int i = 0; i < 3; ++i) {
					for (int j = 0; j < numColumns; ++j)
						slotsBackpack[numColumns * i + j] = addSlotToContainer(new SlotInBackpack(inventoryBackpack, numColumns * i + j, 89 - 9 * (numColumns - 3) + 18 * j, 18 *i + 145));
				}
			}
		}
		return itemStack;
	}
	


	
	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);
		if (tile.inventory == null) return;
		if (inv!=null) {
			inv.save();
			inv.closeInventory();
		}
		if (inventoryBackpack!=null) {
			inventoryBackpack.save();
			inventoryBackpack.closeInventory();
		}
		player.inventory.closeInventory();
		tile.inventory.closeInventory();
		tile.getDescriptionPacket();
		PacketDispatcher.sendToServer(new SaveContainerServerMessage(tile.xCoord, tile.yCoord, tile.zCoord));
	}

}
