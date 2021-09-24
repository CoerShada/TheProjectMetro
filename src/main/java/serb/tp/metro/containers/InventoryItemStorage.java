package serb.tp.metro.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants.NBT;

public class InventoryItemStorage implements IInventory {

	final ItemStack current;//Предмет в котором будет хранить вещи
	public ItemStack[] inventory;//Массив слотов инвентаря в виде ItemStack
	boolean isSave = false;

	public InventoryItemStorage(ItemStack is) {
		current = is;
		if (!current.hasTagCompound()) current.setTagCompound(new NBTTagCompound());
		load(current);
		//openInventory();
	}

	@Override
	public int getSizeInventory() {
		return inventory.length;//Количество слотов инвентаря
	}

	@Override
	public ItemStack getStackInSlot(int slot_id) {
		return slot_id >= 0 && slot_id < inventory.length ? inventory[slot_id] : null;
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if (inventory[slot] != null) {
			ItemStack is;

			if (inventory[slot].stackSize <= amount) {
				is = inventory[slot];
				inventory[slot] = null;
				markDirty();
				return is;
			} else {
				is = inventory[slot].splitStack(amount);
				if (inventory[slot].stackSize == 0) inventory[slot] = null;

				markDirty();
				return is;
			}
		} else return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot_id) {
		if (inventory[slot_id] != null) {
			ItemStack itemstack = inventory[slot_id];
			inventory[slot_id] = null;
			return itemstack;
		} else return null;
	}

	@Override
	public void setInventorySlotContents(int slot_id, ItemStack is) {
		inventory[slot_id] = is;

		if (is != null && is.stackSize > getInventoryStackLimit())
			is.stackSize = getInventoryStackLimit();

		markDirty();
	}

	@Override
	public String getInventoryName() {
		return current.getDisplayName();//Своё имя
	}

	@Override
	public boolean hasCustomInventoryName() {
		return current.hasDisplayName();//Есть-ли своё имя
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;//Лимит размер стака в слоте
	}

	@Override
	public void markDirty() {
		if (!isSave) isSave = true;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		//Может-ли игрок использовать этот инвентарь
		return true;
	}

	//Открытие инвентаря
	@Override
	public void openInventory() {
		//load();
	}

	//Закрытие инвентаря
	@Override
	public void closeInventory() {
		//save();
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack is) {
		//Можно-ли взаимодействовать со слотом(-ами).
		return true;
	}

	//Методы "load" должны из предмета брать нужные теги для простой работы с инвентарём.
	public void load() {
		load(current);
	}

	public void load(ItemStack is) {
		load(is.getTagCompound());
	}

	public void load(NBTTagCompound nbt) {
		if (nbt != null) {
			NBTTagList nbttaglist = nbt.getTagList("Items", NBT.TAG_COMPOUND);

			//Мне так показалось проще сделать, чем брать значение из "nbttagcompound1.getByte("Slot")"
			//Наш NBT тег из которого будем брать максимальное количества слотов для нашего инвентаря Item Storage
			//Желательно, чтобы размеры инвентаря были [9 * n]
			if (nbt.hasKey("size", NBT.TAG_BYTE))
				inventory = new ItemStack[nbt.getByte("size") & 255];
			//Если нету нужно тега, то замер инвентаря Item Storage будет в 27 слотов.
			else inventory = new ItemStack[(9 * 3)];

			for (int i = 0; i < nbttaglist.tagCount(); ++i) {
				NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
				int j = nbttagcompound1.getByte("Slot") & 255;

				if (j >= 0 && j < inventory.length)
					inventory[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}

		}
	}

	//Методы "save" должны в предмет сохранять инвентарь Item Storage в теги.
	public void save() {
		/*current = */save(current);
	}

	public void save(ItemStack is) {
		save(is.getTagCompound());
	}

	public void save(NBTTagCompound nbt) {
		NBTTagList nbttaglist = new NBTTagList();

		nbt.setByte("CustomSize", (byte)getSizeInventory());

		for (int i = 0; i < inventory.length; ++i) {
			if (inventory[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				inventory[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		nbt.setTag("Items", nbttaglist);
	}

	public void update(EntityPlayer player) {
		if (isSave) {
			save();
			player.inventory.setInventorySlotContents(player.inventory.currentItem, current);
			isSave = false;
		}
	}

}
