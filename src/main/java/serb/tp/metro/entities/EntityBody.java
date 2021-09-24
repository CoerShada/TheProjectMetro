package serb.tp.metro.entities;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;

public class EntityBody extends EntityLivingBase{

	public InventoryBasic inventory;
	
	
	public EntityBody(World world) {
		super(world);
		this.setSize(10F,  10F);
	}
	
	/*@Override
	protected boolean interact(EntityPlayer player) {
		//if (worldObj.isRemote) {
		if (player instanceof EntityPlayerMP) {
			if (inventory == null) return true;
			//GuiHandler.openGui(0, player, this);
		}
		return true;
	}*/
	
	public void setDrops(ArrayList<EntityItem> drops) {
	    inventory = new InventoryBasic("InventoryEntityCorpse", false, drops.size());
	    for (int i = 0; i < drops.size(); ++i) {
	        EntityItem entity_item = drops.get(i);
	        if (entity_item.getEntityItem() != null) {
	            inventory.setInventorySlotContents(i, entity_item.getEntityItem());
	        }
	    }
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		//super.readEntityFromNBT(nbt);
		inventory = new InventoryBasic("InventoryEntityCorpse", false, 21);
		if (nbt.hasKey("Inventory", NBT.TAG_LIST)) {
			NBTTagList list = nbt.getTagList("Inventory", NBT.TAG_COMPOUND);
			for (int i = 0; i < list.tagCount(); ++i) {
				NBTTagCompound slot_nbt = list.getCompoundTagAt(i);
				inventory.setInventorySlotContents((slot_nbt.getByte("Slot") & 255), ItemStack.loadItemStackFromNBT(slot_nbt));
			}
		}
		
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		//super.writeEntityToNBT(nbt);
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
	protected void entityInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ItemStack getHeldItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack getEquipmentInSlot(int p_71124_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCurrentItemOrArmor(int p_70062_1_, ItemStack p_70062_2_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ItemStack[] getLastActiveItems() {
		// TODO Auto-generated method stub
		return null;
	}

}
