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
import serb.tp.metro.utils.StringHelper;

public abstract class Item3D extends Item implements IItemLeftClickable{
	protected String[] description;
	protected float weight;
	protected ResourceLocation model;
	public float[] sizeModel;
	public float[] pos;
	public float[] rotation;
	public float[] onInventoryPos;
	public float[] rightHandPos;
	public float[] rightHandRotation;
	protected ItemStack baseItemStack;
	

	public Item3D(String name, String description, float weight, String model, float[] sizeModel, float[] pos, float[] rotation, float[] onInventoryPos,float[] rightHandPos, float[] rightHandRotation) {
		this.setUnlocalizedName(name);
		this.setDescription(description);

		this.weight = weight;
		this.sizeModel = sizeModel;
		this.pos = pos;
		this.rotation = rotation;
		this.onInventoryPos = onInventoryPos;
		this.setModel(model);
	    this.rightHandPos = rightHandPos;
	    this.rightHandRotation = rightHandRotation;
		GameRegistry.registerItem(this, this.getUnlocalizedName());
		this.baseItemStack = new ItemStack(this);
		this.setFull3D();
		
	}
	
	public final void setModel(String model) {
		this.model = new ResourceLocation(Main.modid, model);
	}

	public final ResourceLocation getModel() {
		return this.model;
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
	
	public final void setDescription(String description) {

		this.description = StringHelper.floatSplit(Type.getTranslate("characteristic.all.description") + ": " + description, 45);
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
	

    public ItemStack onItemLeftClick(ItemStack is, World world, EntityPlayer player) {
		return is;
    	
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
