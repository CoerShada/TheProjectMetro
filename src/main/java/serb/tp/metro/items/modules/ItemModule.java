package serb.tp.metro.items.modules;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import serb.tp.metro.items.Item3D;

public abstract class ItemModule extends Item3D{



	public ItemModule(String name, String description, float weight, String model, float[] sizeModel, float[] pos,
			float[] rotation, float[] onInventoryPos, float[] rightHandPos, float[] rightHandRotation) {
		super(name, description, weight, model, sizeModel, pos, rotation, onInventoryPos, rightHandPos, rightHandRotation);
	}

	protected SlotInstalledModules[] potentialInstalledModules;
	

	
	public void addSlotInstalledModules(SlotInstalledModules[] potentialInstalledModules) {
		this.potentialInstalledModules = potentialInstalledModules;
	}

    @Override
    public boolean onLeftClickEntity(ItemStack is, EntityPlayer player, Entity entity) {
    	return true;
    }
    
    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        return true;
    }
}
