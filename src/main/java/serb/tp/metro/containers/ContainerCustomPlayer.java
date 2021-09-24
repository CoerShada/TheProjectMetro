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
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.ChatComponentText;
import serb.tp.metro.containers.slots.SlotArmor;
import serb.tp.metro.containers.slots.SlotBackpack;
import serb.tp.metro.containers.slots.SlotChestrig;
import serb.tp.metro.containers.slots.SlotGun;
import serb.tp.metro.containers.slots.SlotHotbar;
import serb.tp.metro.containers.slots.SlotInChestrig;
import serb.tp.metro.containers.slots.SlotKnife;
import serb.tp.metro.containers.slots.SlotMask;
import serb.tp.metro.containers.slots.SlotOuterwear;
import serb.tp.metro.containers.slots.SlotPants;
import serb.tp.metro.containers.slots.SlotPistol;
import serb.tp.metro.entities.player.ExtendedPlayer;
import serb.tp.metro.entities.player.handlers.WeightHandler;
import serb.tp.metro.items.ItemChestrig;
import serb.tp.metro.items.armor.ArmorType.armorTypeBackpack;
import serb.tp.metro.items.armor.ArmorType.armorTypeBody;
import serb.tp.metro.items.armor.ArmorType.armorTypeBracers;
import serb.tp.metro.items.armor.ArmorType.armorTypeGloves;
import serb.tp.metro.items.armor.ArmorType.armorTypeHelmet;
import serb.tp.metro.items.armor.ArmorType.armorTypeShoulders;
import serb.tp.metro.items.armor.ArmorType.customArmor;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.server.ChangeItemWeight;

public class ContainerCustomPlayer extends Container {
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

	public ContainerCustomPlayer(EntityPlayer player) {
		this.player = player;
		int i;
		int j;
		if (player.inventory.getStackInSlot(19)!=null)
			inv = new InventoryItemStorage(player.inventory.getStackInSlot(19));
		else
			inv = null;
		InventoryPlayer inventoryPlayer = player.inventory;
		

		
		//хотбар
		
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
		
		addSlotToContainer(new SlotBackpack(inventoryPlayer, 18, 102, 8));        
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
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}	

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		Slot slot = (Slot)inventorySlots.get(index);
		if(slot != null && slot.getHasStack()) {
			ItemStack itemStack1 = slot.getStack();
			if(index > slotCraftEnd) 
			{
				if(index == slotCraftResult) 
				{
					itemCraftToInventoryOrHotbar(slot, itemStack1);
				} 
				else 
				{
					if (!this.mergeItemStack(itemStack1, slotHotbarStart, slotInventoryEnd + 1, true)) 
					{
						return null;
					}

					changeSlot(slot, itemStack1);
				}
			} 
			else if(itemStack1.getItem() instanceof customArmor) 
			{
				itemToArmorSlot(slot, itemStack1);		
			} 
			else if((slotHotbarStart <= slot.getSlotIndex() && slot.getSlotIndex() <= slotInventoryEnd)) 
			{
				itemToInventoryOrHotbar(slot, itemStack1);
			} 
			else 
			{
				player.addChatMessage(new ChatComponentText("Ошибка! Отправьте отчет на форум!")); 
			}
		}

		return null;
	}

	public void itemToInventoryOrHotbar(Slot slot, ItemStack itemStack) {
		ItemStack itemStack1 = itemStack.copy();
		
		if(slot instanceof SlotArmor) 
		{
			if (!this.mergeItemStack(itemStack1, slotHotbarStart, slotInventoryEnd + 1, false)) 
			{
				return;
			}
			

			changeSlot(slot, itemStack);
		}

		if(slotHotbarStart <= slot.getSlotIndex() && slot.getSlotIndex() <= slotHotbarEnd) {
			if (!this.mergeItemStack(itemStack1, slotInventoryStart, slotInventoryEnd + 1, false)) 
			{
				return;
			}
			

			changeSlot(slot, itemStack);
		}
		
		if(slotInventoryStart <= slot.getSlotIndex() && slot.getSlotIndex() <= slotInventoryEnd) {
			if (!this.mergeItemStack(itemStack1, slotHotbarStart+3, slotHotbarEnd + 1, false)) 
			{
				return;
			}
			

			changeSlot(slot, itemStack);
		}

	}		

	public void itemCraftToInventoryOrHotbar(Slot slot, ItemStack itemStack) {
		ItemStack itemStack1 = itemStack.copy();
		if (!this.mergeItemStack(itemStack1, slotHotbarStart, slotInventoryEnd, true)) 
		{
			return;
		}

		for(int i = slotCraftStart; i <= slotCraftEnd; i++) {
			Slot slotCraft = (Slot)inventorySlots.get(i);
			if(slotCraft != null && slotCraft.getHasStack()) {
				slotCraft.getStack().stackSize--;
				if(slotCraft.getStack().stackSize <= 0) {
					slotCraft.putStack((ItemStack)null);
				}
			}
		}
	}

	public void changeSlot(Slot slot, ItemStack itemStack) {
		if(itemStack.stackSize <= 0) {
			slot.putStack((ItemStack)null);
		} else {
			slot.onSlotChanged();
			slot.putStack((ItemStack)null);
			
		}
		
	}

	public void itemToArmorSlot(Slot slot, ItemStack itemStack) {
		if(itemStack.getItem() instanceof armorTypeShoulders) {
			if(ExtendedPlayer.get(player).inventory.getStackInSlot(CustomSlots.MASK.ordinal()) == null) 
			{		
				ExtendedPlayer.get(player).inventory.setInventorySlotContents(CustomSlots.MASK.ordinal(), itemStack.copy());
				slot.putStack((ItemStack)null);
			} 
			else 
			{
				itemToInventoryOrHotbar(slot, itemStack);
			}
		}

		if(itemStack.getItem() instanceof armorTypeBracers) {
			if(ExtendedPlayer.get(player).inventory.getStackInSlot(CustomSlots.OUTERWEAR.ordinal()) == null) 
			{
				ExtendedPlayer.get(player).inventory.setInventorySlotContents(CustomSlots.OUTERWEAR.ordinal(), itemStack.copy());
				slot.putStack((ItemStack)null);
			} 
			else 
			{
				itemToInventoryOrHotbar(slot, itemStack);
			}
		}

		if(itemStack.getItem() instanceof armorTypeGloves) {
			if(ExtendedPlayer.get(player).inventory.getStackInSlot(CustomSlots.PANTS.ordinal()) == null) {
				ExtendedPlayer.get(player).inventory.setInventorySlotContents(CustomSlots.PANTS.ordinal(), itemStack.copy());
				slot.putStack((ItemStack)null);
			} else {
				itemToInventoryOrHotbar(slot, itemStack);
			}
		}

		if(itemStack.getItem() instanceof armorTypeBackpack) {
			if(ExtendedPlayer.get(player).inventory.getStackInSlot(CustomSlots.BACKPACK.ordinal()) == null) 
			{
				ExtendedPlayer.get(player).inventory.setInventorySlotContents(CustomSlots.BACKPACK.ordinal(), itemStack.copy());
				slot.putStack((ItemStack)null);
			} 
			else 
			{
				itemToInventoryOrHotbar(slot, itemStack);
			}
		}

		if(itemStack.getItem() instanceof armorTypeHelmet) {
			if(player.getCurrentArmor(3) == null) 
			{
				player.setCurrentItemOrArmor(4, itemStack.copy());
				slot.putStack((ItemStack)null);
			} 
			else 
			{
				itemToInventoryOrHotbar(slot, itemStack);
			}
		}

		if(itemStack.getItem() instanceof armorTypeBody) {
			if(player.getCurrentArmor(2) == null) {
				player.setCurrentItemOrArmor(3, itemStack.copy());
				slot.putStack((ItemStack)null);
			} else {
				itemToInventoryOrHotbar(slot, itemStack);
			}
		}

	}


	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);
		
		if (inv!=null) 
		{
			inv.save();
			inv.closeInventory();//Типа инициализируем закрытия инвентаря
		}
	}
	

	@Override
	public ItemStack slotClick(int slot, int button, int modifier, EntityPlayer player) {
		if (modifier == 2) return null;
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
		float weight = 0;
		for (int i = 0; i<20; i++) 
		{
			if (player.inventory.getStackInSlot(i)!=null && player.inventory.getStackInSlot(i).hasTagCompound()) 
			{
				try 
				{
					weight+=player.inventory.getStackInSlot(i).getTagCompound().getFloat("weight") * player.inventory.getStackInSlot(i).stackSize;
				}
				finally {}
			}
		}
		WeightHandler.setWeight(player, weight);
		return itemStack;
	

	}	
}