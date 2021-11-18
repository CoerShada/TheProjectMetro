package serb.tp.metro.items.modules;

import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import serb.tp.metro.creativetabs.LoadTabs;
import serb.tp.metro.customization.AbstractCustomizableSlot;
import serb.tp.metro.customization.ICustomizable;
import serb.tp.metro.database.CustomizationSlotsReader;
import serb.tp.metro.items.Item3D;
import serb.tp.metro.utils.DefenceVariables;

public abstract class ItemModule extends Item3D implements ICustomizable{

	ArrayList<AbstractCustomizableSlot> slotsCustomization = new ArrayList<AbstractCustomizableSlot>();


	public ItemModule(String name, String description, float weight, String model, float[] sizeModel, float[] pos,
			float[] rotation, float[] onInventoryPos, float[] rightHandPos, float[] rightHandRotation) {
		super(name, description, weight, model, sizeModel, pos, rotation, onInventoryPos, rightHandPos, rightHandRotation) ;
		this.setCreativeTab(LoadTabs.modules);
		this.maxStackSize = 1;
	}

	

    @Override
    public boolean onLeftClickEntity(ItemStack is, EntityPlayer player, Entity entity) {
    	return true;
    }
    
    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        return true;
    }
    
	public void addSlot(AbstractCustomizableSlot slotCustomization) {
		slotsCustomization.add(slotCustomization);
		
	}
	
	public ArrayList<AbstractCustomizableSlot> getSlotsCustomization() {
		return this.slotsCustomization;
	}
	
	public int getIndexNewSlot() {
		return this.slotsCustomization.size();
	}
	
	public boolean isCustomizable() {
		return slotsCustomization.size()>0;
	}
	
	@SideOnly(Side.CLIENT)
	public float[] getModsCoordsForRendere(ItemRenderType type) {
		switch (type) {
			case INVENTORY:
				return this.onInventoryPos;
			case EQUIPPED:
				return this.pos;
			case EQUIPPED_FIRST_PERSON:
				return this.pos;
			default:
				return new float[] {0, 0, 0};
		
		}
	}
	
	@Override
	public final AbstractCustomizableSlot getSlotFromIndex(int index) {
		AbstractCustomizableSlot slot = null;
		for (AbstractCustomizableSlot tempslot: slotsCustomization) {
			if (index==tempslot.indexSlot) {
				slot = tempslot;
				break;
			}
		}
		return slot;
	}
	
	@Override
	public void replaceSlotFromIndex(int index, AbstractCustomizableSlot slot) {
		AbstractCustomizableSlot preSlot = this.getSlotFromIndex(index);
		slotsCustomization.set(slotsCustomization.indexOf(preSlot), slot);
	}
}
