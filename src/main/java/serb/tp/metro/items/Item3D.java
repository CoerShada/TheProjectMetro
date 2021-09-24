package serb.tp.metro.items;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import serb.tp.metro.Main;
import serb.tp.metro.client.Type;

public abstract class Item3D extends Item{
	public final String[] description;
	public final float weight;
	public final ResourceLocation model;
	public final float[] sizeModel;
	public final float[] pos;
	public final float[] rotation;
	public final float[] onInventoryPos;
    public final float[] rightHandPos;
    public final float[] rightHandRotation;
	

	public Item3D(String name, String description, float weight, String model, float[] sizeModel, float[] pos, float[] rotation, float[] onInventoryPos,float[] rightHandPos, float[] rightHandRotation) {
		this.setUnlocalizedName(name);
		
		int beginIndex = 0;
		int lastIndex = 0;
		int index = 0;
		final int sizeString = 45;
		description = Type.getTranslate("characteristic.all.description") + ": " + description;
		while (index<description.length()) {
			if (description.substring(index, index+1).equals("@")) {
				beginIndex = index;
				lastIndex = index;
				
				
			}
			else if (description.substring(index, index+1).equals(" ")) {
				if (index-beginIndex<sizeString) {
					lastIndex = index;
				}
				else if (index-beginIndex>sizeString) {
					description = description.substring(0, lastIndex) + "@" + description.substring(lastIndex+1);
					beginIndex = lastIndex;
					lastIndex = index;
				}
				else {
					description = description.substring(0, index) + "@" + description.substring(index+1);
					beginIndex = index;
					lastIndex = index;
				}
			}
			index++;
		}
		
		this.description = description.split("@");
		this.weight = weight;
		this.sizeModel = sizeModel;
		this.pos = pos;
		this.rotation = rotation;
		this.onInventoryPos = onInventoryPos;
		this.model = new ResourceLocation(Main.modid, model);
	    this.rightHandPos = rightHandPos;
	    this.rightHandRotation = rightHandRotation;
		GameRegistry.registerItem(this, this.getUnlocalizedName());
	}
	
	
	/*public Item3D(String name, float weight) {
		this(name, weight, 1, 0, 0, 0);
	}*/
	
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean isAdv) {
        if (!itemStack.hasTagCompound()) return;
        list.add(Type.getTranslate("characteristic.all.weight")+": " + String.format("%.2f", itemStack.getTagCompound().getFloat("weight")/1000) + Type.getTranslate("characteristic.all.weight.kg"));

        list.add(" ");
        
        
        for (String str : description)
        	list.add(str);
        

    }
	
    
    
	
}
