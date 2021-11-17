package serb.tp.metro.items;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import serb.tp.metro.Main;
import serb.tp.metro.client.Type;
import serb.tp.metro.database.Reader;
import serb.tp.metro.utils.DefenceVariables;

public abstract class Item3D extends Item{
	public String[] description;
	protected float weight;
	public ResourceLocation model;
	public float[] sizeModel;
	public float[] pos;
	public float[] rotation;
	public float[] onInventoryPos;
	public float[] rightHandPos;
	public float[] rightHandRotation;
	protected ItemStack baseItemStack;
	

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
		this.baseItemStack = new ItemStack(this);
		
	}
	

	@SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean isAdv) {
        if (!itemStack.hasTagCompound()) return;
        list.add(Type.getTranslate("characteristic.all.weight")+": " + String.format("%.2f", itemStack.getTagCompound().getFloat("weight")/1000) + Type.getTranslate("characteristic.all.weight.kg"));

        list.add(" ");
        
        
        for (String str : description)
        	list.add(str);
        

    }
    
    @Override
    public void getSubItems(Item item, CreativeTabs ct, List list)
    {
    	if(!this.baseItemStack.hasTagCompound()) {
    		this.baseItemStack.setTagCompound(new NBTTagCompound());
			
    	}
    	list.add(this.baseItemStack);
    	this.baseItemStack.getTagCompound().setFloat("weight", weight);
    }
	
    
    public ItemStack onItemRightClick(ItemStack hold, World world, EntityPlayer player) {
    	return hold;
    }
	
    public ItemStack onItemLeftClick(ItemStack hold, World world, EntityPlayer player) {
    	return hold;
    }
    

	public final float getWeight() {
		return weight;
	}


	public final void setWeight(float weight) {
		if (DefenceVariables.authorizedAccess(Reader.class)) {
			this.weight = weight;
		}
		
	}
    
}
