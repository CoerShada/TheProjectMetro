package serb.tp.metro.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import serb.tp.metro.client.Type;
import serb.tp.metro.creativetabs.LoadTabs;

public class ItemBackpack extends Item3D{
	private final int size;
	public ItemBackpack(String name, String description, float weight, String model, float[] sizeModel, float[] pos,
			float[] rotation, float[] onInventoryPos, float[] rightHandPos, float[] rightHandRotation, int size) {
		super(name, description, weight, model, sizeModel, pos, rotation, onInventoryPos, rightHandPos, rightHandRotation);
		this.setCreativeTab(LoadTabs.itemsComponents);
		this.maxStackSize = 1;
		this.size = size;
	}

	



    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean isAdv) {
        if (!itemStack.hasTagCompound()) return;
        super.addInformation(itemStack, entityPlayer, list, isAdv);
        list.add(Type.getTranslate("characteristic.all.weightcontent")+": " + String.format("%.2f", (itemStack.getTagCompound().getFloat("weight")-weight)/1000) + Type.getTranslate("characteristic.all.weight.kg"));
    }
	
    @Override
    public void getSubItems(Item item, CreativeTabs ct, List list)
    {
    	ItemStack itemStack = new ItemStack(item);
		itemStack.setTagCompound(new NBTTagCompound());
		itemStack.getTagCompound().setFloat("weight", weight);
		itemStack.getTagCompound().setFloat("weightStandart", weight);
		itemStack.getTagCompound().setByte("size", (byte) size);
		list.add(itemStack);
		
    }
    
    

}
