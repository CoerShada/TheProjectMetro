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
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.server.ChangeFilterMessage;

public class ItemMask extends Item3D{

	public final float radResistance;
	
	public ItemMask(String name, String description, float weight, String model, float[] sizeModel, float[] pos,
			float[] rotation, float[] onInventoryPos, float[] rightHandPos, float[] rightHandRotation, float radResistance) {
		super(name, description, weight, model, sizeModel, pos, rotation, onInventoryPos, rightHandPos, rightHandRotation);
		this.radResistance = radResistance;
    	this.maxStackSize = 1;
	}

	
    @Override
    public void getSubItems(Item item, CreativeTabs ct, List list)
    {
    	ItemStack itemStack = new ItemStack(item);
		itemStack.setTagCompound(new NBTTagCompound());
		itemStack.getTagCompound().setInteger("filterTime", 0);
		itemStack.getTagCompound().setString("filter", null);
		itemStack.getTagCompound().setFloat("weight", weight);
		
		list.add(itemStack);
		
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean isAdv) {
        if (!itemStack.hasTagCompound()) return;
        NBTTagCompound nbt = itemStack.getTagCompound();
        if (itemStack.getTagCompound().getString("filter").equals(null)) {
        	list.add(Type.getTranslate("characteristic.masks.filter") + " " + Type.getTranslate("characteristic.status.notplaced_m"));
        }
        else {
        	list.add(Type.getTranslate("characteristic.masks.filter") + " " +Type.getTranslate("characteristic.status.placed_m"));
            list.add(Type.getTranslate("characteristic.masks.filtertime") +": " + (int)(itemStack.getTagCompound().getInteger("filterTime")/20) + " "+Type.getTranslate("characteristic.all.time.seconds"));
        }
        list.add(Type.getTranslate("characteristic.all.weight") + ": " + String.format("%.2f", weight/1000) + Type.getTranslate("characteristic.all.weight.kg"));
    }
    
    public void ChengeFilter(EntityPlayer player) 
    {
    	int k = -1;
    	long time = 0;
    	for (int i = 3; i < 15; i++) 
    	{
    		ItemStack itemStack = player.inventory.getStackInSlot(i);
    		if (itemStack!= null && itemStack.getItem() instanceof ItemFilter && itemStack.hasTagCompound() && itemStack.getTagCompound().getLong("filterTime")>time) 
    		{
    			time = itemStack.getTagCompound().getLong("filterTime");
    			k = i;
    		}
    	}
    	PacketDispatcher.sendToServer(new ChangeFilterMessage(k));
    }
    

    
}

