package serb.tp.metro.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import serb.tp.metro.client.Type;

public class ItemFilter extends Item3D {

	private final int filterTime;
	
	public ItemFilter(String name, String description, float weight, String model, float[] sizeModel, float[] pos,
			float[] rotation, float[] onInventoryPos, float[] rightHandPos, float[] rightHandRotation, int filterTime) {
		super(name, description, weight, model, sizeModel, pos, rotation, onInventoryPos, rightHandPos, rightHandRotation);
		this.filterTime = filterTime;
		this.maxStackSize = 1;
	}
	
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean isAdv) {
        if (!itemStack.hasTagCompound()) return;
        NBTTagCompound nbt = itemStack.getTagCompound();
        list.add(Type.getTranslate("characteristic.masks.filtertime") +": " + (int)(itemStack.getTagCompound().getInteger("filterTime")/20) + " "+Type.getTranslate("characteristic.all.time.seconds"));
        list.add(Type.getTranslate("characteristic.all.weight") + ": " + String.format("%.2f", weight/1000) + Type.getTranslate("characteristic.all.weight.kg"));
    }
    
    
    @Override
    public void getSubItems(Item item, CreativeTabs ct, List list)
    {
    	ItemStack itemStack = new ItemStack(item);
		itemStack.setTagCompound(new NBTTagCompound());
		itemStack.getTagCompound().setLong("filterTime", filterTime);
		itemStack.getTagCompound().setFloat("weight", weight);
		list.add(itemStack);
		
    }

}
