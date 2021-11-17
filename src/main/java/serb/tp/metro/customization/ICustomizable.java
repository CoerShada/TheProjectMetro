package serb.tp.metro.customization;

import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

public interface ICustomizable {

	
	public void addSlot(AbstractCustomizableSlot slotCustomization);
	
	public ArrayList<AbstractCustomizableSlot> getSlotsCustomization();
	
	public int getIndexNewSlot() ;
	
	public boolean isCustomizable();
	
	public NBTTagCompound updateCharacteristics(ItemStack is);
	
	@SideOnly(Side.CLIENT)
	public float[] getModsCoordsForRendere(ItemRenderType type);
	
	public void clearSlots();
}
