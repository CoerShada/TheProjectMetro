package serb.tp.metro.items.modules;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import serb.tp.metro.containers.InventoryItemStorage;
import serb.tp.metro.customization.ICustomizable;
import serb.tp.metro.utils.MathHelper;

public class ItemWeaponModule extends ItemModule{
	

	private float verticalRecoilMod;
	private float horizontalRecoilMod;
	private float convenienceMod;
	private float accuracyMod;
	private float penetrationMod;
	private float jummingMod;
	
	public ItemWeaponModule(String name, String description, float weight, String model, float[] sizeModel, float[] pos,
			float[] rotation, float[] onInventoryPos, float[] rightHandPos, float[] rightHandRotation, float verticalRecoilMod, 
			float horizontalRecoilMod, float convenienceMod, float accuracyMod, float penetrationMod, float jummingMod) {
		super(name, description, weight, model, sizeModel, pos, rotation, onInventoryPos, rightHandPos, rightHandRotation);
		this.verticalRecoilMod = verticalRecoilMod;
		this.horizontalRecoilMod = horizontalRecoilMod;
		this.convenienceMod = convenienceMod;
		this.accuracyMod = accuracyMod;
		this.penetrationMod = penetrationMod;
		this.jummingMod = jummingMod;
	}
	
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean isAdv) {
        super.addInformation(itemStack, entityPlayer, list, isAdv);
        //list.add(jummingMod);

    }

	@Override
	public NBTTagCompound updateCharacteristics(ItemStack is) {
		if (is==null || !is.hasTagCompound()) return null;
		InventoryItemStorage inv = new InventoryItemStorage(is);
		inv.openInventory();
		NBTTagCompound tag = new NBTTagCompound();
		tag.setFloat("verticalRecoilMod", verticalRecoilMod);
		tag.setFloat("horizontalRecoilMod", horizontalRecoilMod);
		tag.setFloat("convenienceMod", convenienceMod);
		tag.setFloat("accuracyMod", accuracyMod);
		tag.setFloat("penetrationMod", penetrationMod);
		tag.setFloat("jummingMod", jummingMod);
		for (int i = 0; i<inv.getSizeInventory(); i++) {
			
			if (inv.getStackInSlot(i)==null || !(inv.getStackInSlot(i).getItem() instanceof ICustomizable)) continue;
			NBTTagCompound tempTag = new NBTTagCompound();
			ICustomizable customizable = (ICustomizable) inv.getStackInSlot(i).getItem();
			tempTag = customizable.updateCharacteristics(inv.getStackInSlot(i));
			tag.setFloat("verticalRecoilMod", 	MathHelper.sumPercent(tag.getFloat("verticalRecoilMod"), tempTag.getFloat("verticalRecoilMod")));
			tag.setFloat("horizontalRecoilMod", MathHelper.sumPercent(tag.getFloat("horizontalRecoilMod"), tempTag.getFloat("horizontalRecoilMod")));
			tag.setFloat("convenienceMod", 		MathHelper.sumPercent(tag.getFloat("convenienceMod"), tempTag.getFloat("convenienceMod")));
			tag.setFloat("accuracyMod",  		tag.getFloat("accuracyMod") *  tempTag.getFloat("accuracyMod"));
			tag.setFloat("penetrationMod", 		MathHelper.sumPercent(tag.getFloat("penetrationMod"), tempTag.getFloat("penetrationMod")));
			tag.setFloat("jummingMod", 			MathHelper.sumPercent(tag.getFloat("jummingMod"), tempTag.getFloat("jummingMod")));
		}

		return tag;
		
	}
	


}
