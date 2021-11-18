package serb.tp.metro.containers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import serb.tp.metro.containers.slots.SlotCustomization;
import serb.tp.metro.customization.AbstractCustomizableSlot;
import serb.tp.metro.customization.ICustomizable;
import serb.tp.metro.items.Item3D;
import serb.tp.metro.items.ItemBackpack;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.server.ChangeItemWeight;
import serb.tp.metro.utils.IntHelper;

public class ContainerCustomization extends Container{

	protected EntityPlayer player;
	public final int x;
	public final int y;
	public final ICustomizable parentItem;
	public final InventoryItemStorage customizationInventory;
	private ContainerCustomization[] subContainers;
	private ArrayList<SlotCustomization> slots = new ArrayList<SlotCustomization>();
	public final int inventoryID;
	
	
	public ContainerCustomization(int inventoryID, EntityPlayer player, ItemStack customizableItem, int x, int y) {
		this.inventoryID = inventoryID;
		this.player = player;
		this.parentItem = (ICustomizable) customizableItem.getItem();
		this.x = x;
		this.y = y;
		this.customizationInventory = new InventoryItemStorage(customizableItem);
		this.customizationInventory.openInventory();
		ArrayList<AbstractCustomizableSlot> parentSlots = parentItem.getSlotsCustomization();
		subContainers = new ContainerCustomization[parentSlots.size()];
		int counter = 0;
		
		for (AbstractCustomizableSlot slot: parentSlots) {
			
			SlotCustomization slotBuf = (SlotCustomization) addSlotToContainer(new SlotCustomization(customizationInventory, slot.indexSlot , this.x + slot.getX(), this.y + slot.getY(), slot.getSubItems()));
			slots.add(slotBuf);

			counter++;
		}
		setContentForSlots();
	}
	
	public ArrayList<SlotCustomization> getSlotsThisContainer(){
		return slots;
	}
	
	public ArrayList<ContainerCustomization> getSubContainers(){
		ArrayList<ContainerCustomization> subContainers = new ArrayList<ContainerCustomization>();
		for(ContainerCustomization cont: this.subContainers) {
			if (cont==null) continue;
			subContainers.addAll(0, cont.getSubContainers());
		}
		
		for(ContainerCustomization cont: this.subContainers) {
			if (cont==null) continue;
			subContainers.add(0, cont);
		}
		
		return subContainers;
	}
	
	private void setContentForSlots() {
		for (int i = 0; i<slots.size(); i++) {
			if (customizationInventory.getStackInSlot(i)==null) {
				subContainers[i] = null;
			}
			else {
				subContainers[i] = new ContainerCustomization(IntHelper.concat(this.inventoryID, i) ,player, customizationInventory.getStackInSlot(i), slots.get(i).xDisplayPosition, slots.get(i).yDisplayPosition);
			}
		}
	}
	
	
	
	public ArrayList<int[]> getSlotsCoords(){
		ArrayList<int[]> coords = new ArrayList<int[]>();
		for(ContainerCustomization container: subContainers) {
			if(container!=null)
				coords.addAll(container.getSlotsCoords());
		}
		for (SlotCustomization slot: slots)
			coords.add(0, new int[] {slot.xDisplayPosition, slot.yDisplayPosition});
		return coords;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return customizationInventory.isUseableByPlayer(player);
	}

	public ArrayList<Integer> getSlotsIds() {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for(ContainerCustomization container: subContainers) {
			
			if(container!=null)
				ids.addAll(container.getSlotsIds());
		}
		int counter = 0;
		for (SlotCustomization slot: slots) {
			String complexID = String.valueOf(slot.getSlotIndex());
			if (complexID.length()<2)
				complexID = "0" + complexID;
			complexID = String.valueOf(this.inventoryID) + complexID;
			ids.add(0, Integer.valueOf(complexID));
			counter++;
		}
		
		return ids;
	}
	
	
	
	public void changeItemInRecursionInventory(int complexID, int changedSlotID) {
		int changedSlotIDNoIndex = IntHelper.cutAfterSymbols(changedSlotID, 1);//Убрали модификатор инвентаря из id;
		
		//String inventoryIDStr = String.valueOf(complexID).substring(0, 1);
		int inventoryID = IntHelper.cutBeforeSymbols(changedSlotID, -2);
		if(inventoryID==this.inventoryID) {
			String slotIDStr = String.valueOf(complexID).substring(String.valueOf(complexID).length()-2);
			int slotID = Integer.valueOf(slotIDStr);
			int nextContID = IntHelper.cutBeforeSymbols(changedSlotID, -2);
			int nextSlotID = IntHelper.cutAfterSymbols(changedSlotID, -2);
			
			//Если id слота 99, т.е. нажата кнопка "убрать модуль"
			if (slotID==99) {
				
				ItemStack is;
				is = this.customizationInventory.getStackInSlot(changedSlotIDNoIndex);
				if (is==null) return;
				//Если есть рюкзак - идет поиск подходящих слотов по рюкзатку
				if (player.inventory.getStackInSlot(CustomSlots.BACKPACK.getIndex())!=null && player.inventory.getStackInSlot(CustomSlots.BACKPACK.getIndex()).getItem() instanceof ItemBackpack) {
					ItemBackpack backpack = (ItemBackpack) player.inventory.getStackInSlot(CustomSlots.BACKPACK.getIndex()).getItem();
					InventoryItemStorage inventoryBackpack = new InventoryItemStorage(player.inventory.getStackInSlot(CustomSlots.BACKPACK.getIndex()));
					inventoryBackpack.openInventory();
					

					//Поиск сначала слота для стака
					for(int i = 0; i<inventoryBackpack.getSizeInventory(); i++) {
						Item changedItem = inventoryBackpack.getStackInSlot(i).getItem();
						if(is!=null && is.getItem().equals(changedItem) && inventoryBackpack.getStackInSlot(i).stackSize + is.stackSize<=changedItem.getItemStackLimit()) {
							inventoryBackpack.getStackInSlot(i).stackSize+=is.stackSize;
							inventoryBackpack.save();
							this.customizationInventory.setInventorySlotContents(slotID, null);
							this.customizationInventory.save();
							subContainers[nextSlotID] = null;
							return;
						}
					}
					//Если не нашли - ищем пустой слот
					for(int i = 0; i<inventoryBackpack.getSizeInventory(); i++) {
						if(inventoryBackpack.getStackInSlot(i)==null) {
							inventoryBackpack.setInventorySlotContents(i, is);
							inventoryBackpack.save();
							this.customizationInventory.setInventorySlotContents(slotID, is);
							this.customizationInventory.save();
							return;
						}
					}
				}
				else {
					for(int i = CustomSlots.WEAPON.getIndex(); i<CustomSlots.MASK.getIndex(); i++) {
						if (player.inventory.getStackInSlot(i)==null) continue;
						Item changedItem = player.inventory.getStackInSlot(i).getItem();
						if(is!=null && is.getItem().equals(changedItem) && player.inventory.getStackInSlot(i).stackSize + is.stackSize<=changedItem.getItemStackLimit()) {
							player.inventory.getStackInSlot(i).stackSize+=is.stackSize;
							this.customizationInventory.setInventorySlotContents(changedSlotIDNoIndex, null);
							this.customizationInventory.save();
							subContainers[nextSlotID] = null;
							return;
						}
					}
					for(int i = CustomSlots.WEAPON.getIndex(); i<CustomSlots.MASK.getIndex(); i++) {
						if(player.inventory.getStackInSlot(i)==null) {
							player.inventory.setInventorySlotContents(i, is);
							this.customizationInventory.setInventorySlotContents(changedSlotIDNoIndex, null);
							this.customizationInventory.save();
							subContainers[nextSlotID] = null;
							return;
						}
					}
					
					
				}
			}
			else {
				
				ItemStack is;
				int isBackpack = Integer.valueOf(String.valueOf(changedSlotID).substring(0, 1));	//1 не в рюкзаке, 2 в рюкзаке
				slotID = Integer.valueOf(String.valueOf(slotID).substring(1));

				ItemStack isFromCustomization = this.customizationInventory.getStackInSlot(changedSlotIDNoIndex);
				
				if (isBackpack==1) {
					
					is = this.player.inventory.getStackInSlot(slotID);
					this.player.inventory.setInventorySlotContents(slotID, isFromCustomization);
					if (is!=null)
						subContainers[nextSlotID] = new ContainerCustomization(IntHelper.concat(nextContID, nextSlotID), player, is, this.slots.get(nextSlotID).xDisplayPosition, this.slots.get(nextSlotID).yDisplayPosition);
					else
						subContainers[nextSlotID] = null;
				}
				else {
					
					InventoryItemStorage inventoryBackpack = new InventoryItemStorage(player.inventory.getStackInSlot(CustomSlots.BACKPACK.getIndex()));
					is = inventoryBackpack.getStackInSlot(slotID);
					inventoryBackpack.setInventorySlotContents(slotID, isFromCustomization);
					subContainers[nextSlotID] = new ContainerCustomization(IntHelper.concat(nextContID, nextSlotID), player, is, this.slots.get(nextSlotID).xDisplayPosition, this.slots.get(nextSlotID).yDisplayPosition);
					this.inventorySlots.get(nextSlotID);
				}
				this.customizationInventory.setInventorySlotContents(changedSlotIDNoIndex, is);
				this.customizationInventory.save();
				
			}
			return;
		}
		else if (IntHelper.indexOf(inventoryID, this.inventoryID)==0) {
			
			for (ContainerCustomization cont: subContainers) {
				if (cont==null) continue;
				cont.changeItemInRecursionInventory(complexID, changedSlotID);
			}
		}
	}
	
	
	public Map<Integer, ItemStack> findPotentialSlots(int complexID) {
		Map<Integer, ItemStack> slotsAndContent = new HashMap<Integer, ItemStack>();
		int inventoryID = IntHelper.cutBeforeSymbols(complexID, -2);
		if(inventoryID==this.inventoryID) {
			//String slotIDStr = String.valueOf(complexID).substring(String.valueOf(complexID).length()-1);
			int slotID =IntHelper.cutAfterSymbols(complexID, -2);;
			SlotCustomization slot = (SlotCustomization) getSlot(slotID);
			ItemStack is = slot.getStack();
			String id = String.valueOf(this.inventoryID)+"99";
			slotsAndContent.put(Integer.valueOf(id), null);

			for (int i = CustomSlots.WEAPON.getIndex(); i<CustomSlots.MASK.getIndex(); i++) {

				if (player.inventory.getStackInSlot(i)==null) continue;
				boolean itemValid= slot.isItemValidChanged(player.inventory.getStackInSlot(i));
				if (itemValid) {
					int outputSlotID = Integer.valueOf("1"+String.valueOf(i));
					slotsAndContent.put(outputSlotID, player.inventory.getStackInSlot(i));
				}
			}
			
			if (player.inventory.getStackInSlot(CustomSlots.BACKPACK.getIndex())!=null && player.inventory.getStackInSlot(CustomSlots.BACKPACK.getIndex()).getItem() instanceof ItemBackpack) {
				InventoryItemStorage inventoryBackpack = new InventoryItemStorage(player.inventory.getStackInSlot(CustomSlots.BACKPACK.getIndex()));
				for (int i = CustomSlots.WEAPON.getIndex(); i<CustomSlots.MASK.getIndex(); i++) {
					if (inventoryBackpack.getStackInSlot(i)==null) continue;
					boolean itemValid= slot.isItemValidChanged(inventoryBackpack.getStackInSlot(i));
					if (itemValid) {
						int outputSlotID = Integer.valueOf("2"+String.valueOf(i));
						slotsAndContent.put(outputSlotID, inventoryBackpack.getStackInSlot(i));
					}
				}
			}

		}
		else if (IntHelper.indexOf(inventoryID, this.inventoryID)==0) {
			
			for(ContainerCustomization cont: this.subContainers) {
				if (cont==null) continue;
				Map<Integer, ItemStack> potentialSlots = cont.findPotentialSlots(complexID);
				//if (potentialSlots.isEmpty())
				slotsAndContent.putAll(potentialSlots);
			}
		}
		else {
			return new HashMap<Integer, ItemStack>();
		}
		
		return slotsAndContent;
	}
	
	private float getInventoryWeight() {
		float weight = 0;
		for (ContainerCustomization cont: this.subContainers) {
			if (cont==null) continue;
			weight+=cont.getInventoryWeight();
		}
		for (ItemStack is: this.customizationInventory.inventory) {
			if (is==null || !(is.getItem() instanceof Item3D)) continue;
			Item3D item3D= (Item3D) is.getItem();
			weight+=item3D.getWeight();
		}
		return weight;
			
	}
	
	@Override
    public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);
        float weight = getInventoryWeight();
        PacketDispatcher.sendToServer(new ChangeItemWeight(player.inventory.currentItem, weight));
    }
}
