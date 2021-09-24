package serb.tp.metro.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import serb.tp.metro.containers.slots.SlotArmor;
import serb.tp.metro.containers.slots.SlotBackpackFromBackpack;
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
import serb.tp.metro.network.server.ChangeItemWeight;

public class ContainerBackpackStorage extends Container {
	Slot[] slotsRig;
	Slot slotRig;

	protected final EntityPlayer player;
	public InventoryItemStorage inv;
	
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

	private InventoryItemStorage inventoryBackpack;
	private int numColumns;

	public ContainerBackpackStorage(EntityPlayer player, InventoryItemStorage inventoryItemStorage) {
		this.player = player;
		inventoryBackpack = inventoryItemStorage;
		inventoryBackpack.openInventory();//Типа инициализируем открытия инвентаря
		InventoryPlayer inventoryPlayer = player.inventory;
		if (player.inventory.getStackInSlot(19)!=null && player.inventory.getStackInSlot(19).getItem() instanceof ItemChestrig)
			inv = new InventoryItemStorage(player.inventory.getStackInSlot(19));
		else
			inv = null;
		numColumns = inventoryBackpack.getSizeInventory() / 3;
		int i;
		int j;
		int k;


		addSlotToContainer(new SlotKnife(inventoryPlayer, 0, 66, 64)); 
		addSlotToContainer(new SlotPistol(inventoryPlayer, 1, 84, 64)); 
		addSlotToContainer(new SlotGun(inventoryPlayer, 2, 102, 64)); 
		
		for (i = 0; i < 3; i++) 
		{
			addSlotToContainer(new SlotHotbar(inventoryPlayer, 3 + i, 127 + i * 18, 64));
		}
		
		//инвентарь	все 3 ряда
		for (i = 0; i < 3; ++i) 
		{
			for (j = 0; j < 3; ++j) 
			{
				addSlotToContainer(new Slot(inventoryPlayer, 6 + j + i * 3, 127 + j * 18, 8 + i * 18));
			}
		}

		//броня
		for (i = 0; i < 2; ++i) 
		{
			addSlotToContainer(new SlotArmor(player, inventoryPlayer, inventoryPlayer.getSizeInventory() - 1 - i, 84, 8 + i * 18, i));
		}
		
		addSlotToContainer(new SlotMask(player, inventoryPlayer, 15, 66, 8)); 
		addSlotToContainer(new SlotOuterwear(inventoryPlayer, 16, 66, 26)); 
		addSlotToContainer(new SlotPants(inventoryPlayer, 17, 66, 44)); 
		
		addSlotToContainer(new SlotBackpackFromBackpack(inventoryPlayer, 18, 102, 8));        
		slotRig = addSlotToContainer(new SlotChestrig(inventoryPlayer, 19, 102, 26)); 
		
		
		

		
		if (inv!=null) 
		{
			
			inv.openInventory();
			slotsRig = new Slot[inv.getSizeInventory()];
			int columns = 2;
			if (inv.getSizeInventory()%2!=0) 
			{
				columns = 3;
			}
			for (i = 0; i < inv.getSizeInventory()/columns; ++i) {
				for (j = 0; j < columns; ++j) 
				{
					slotsRig[columns * i + j] = addSlotToContainer(new SlotInChestrig(inv, columns * i + j, 200 + j * 18, 18 * i + 8));
				}
			}	
		}
		
		for (i = 0; i < 3; ++i) {
			for (j = 0; j < numColumns; ++j)
				addSlotToContainer(new SlotInBackpack(inventoryBackpack, numColumns * i + j, 89 - 9 * (numColumns - 3) + 18 * j, 18 *i + 85));
		}

	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {

		return inventoryBackpack.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot_i) {
		ItemStack is = null;
		Slot slot = (Slot)inventorySlots.get(slot_i);

		if (slot != null && slot.getHasStack()) {
			ItemStack is1 = slot.getStack();
			is = is1.copy();

			//Или иначе по шифт-клику можно было засунуть в наш рюкзак ещё один рюкзак.
			if (is1.getItem() instanceof ItemBackpack) return null;

			if (slot_i < inventoryBackpack.getSizeInventory()) {
				if (!mergeItemStack(is1, inventoryBackpack.getSizeInventory(), inventorySlots.size(), true)) return null;
			} else if (!mergeItemStack(is1, 0, inventoryBackpack.getSizeInventory(), false))
				return null;

			if (is1.stackSize == 0) slot.putStack((ItemStack)null);
			else slot.onSlotChanged();
			
		}

		return is;
	}

	@Override
	public ItemStack slotClick(int slot, int button, int modifier, EntityPlayer player) {
		
		if (slot == 18) return null;
		if (modifier == 2) return null;//Блокируем возможность использование игроком цифровых кнопок, чтобы не было попытки подмены
		if (slot>19 && modifier==4)
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
					slotsRig[columns * i + j] = addSlotToContainer(new SlotInChestrig(inv, columns * i + j, 200 + j * 18, 18 * i + 8));
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
						slotsRig[columns * i + j] = addSlotToContainer(new SlotInChestrig(inv, columns * i + j, 200 + j * 18, 18 * i + 8));
					}
				}	
			}
		}
		
		if (slotsRig!=null && slotRig.getStack()!=null && slotRig.getStack().getItem() instanceof ItemChestrig && inv!=null) 
		{
			ItemStack is;
			float weight = 0F;
			for (int i=0; i<inv.getSizeInventory(); i++) {
				is = inv.getStackInSlot(i);
				if (is!=null && is.hasTagCompound()) 
				{
					weight+=is.getTagCompound().getFloat("weight")*is.stackSize;
				}
			PacketDispatcher.sendToServer(new ChangeItemWeight(19, weight));
			}
		}
		
		if (slotsRig!=null && slotRig.getStack()!=null && slotRig.getStack().getItem() instanceof ItemChestrig && inv!=null) 
		{
			ItemStack is;
			float weight = 0F;
			for (int i=0; i<inventoryBackpack.getSizeInventory(); i++) {
				is = inventoryBackpack.getStackInSlot(i);
				if (is!=null && is.hasTagCompound()) 
				{
					weight+=is.getTagCompound().getFloat("weight")*is.stackSize;
				}
			PacketDispatcher.sendToServer(new ChangeItemWeight(18, weight));
			}
		}
		
		return itemStack;
	}

	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);
		if (inv!=null) {
			inv.save();
			inv.closeInventory();
		}
		inventoryBackpack.save();
		inventoryBackpack.closeInventory();//Типа инициализируем закрытия инвентаря
	}

	public void update(EntityPlayer player) {
		if (inventoryBackpack != null) inventoryBackpack.update(player);
	}

}