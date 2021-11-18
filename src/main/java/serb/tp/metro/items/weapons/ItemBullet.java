package serb.tp.metro.items.weapons;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import serb.tp.metro.client.Type;
import serb.tp.metro.creativetabs.LoadTabs;
import serb.tp.metro.database.Reader;
import serb.tp.metro.items.Item3D;
import serb.tp.metro.utils.DefenceVariables;

public class ItemBullet extends Item3D {
;
	protected int damage;
	protected int penetration;
	protected float jamming;
	protected int parts;
	protected float armorDamage;
	protected float fragmentationChance;
	
	public ItemBullet(String name, String description, float weight, String model, float[] sizeModel, float[] pos, float[] rotation, float[] onInventoryPos,float[] rightHandPos, float[] rightHandRotation, int damage, int penetration, float jamming, float armorDamage, float fragmentationChance, int parts){
		super(name, description, weight, model, sizeModel, pos, rotation, onInventoryPos, rightHandPos, rightHandRotation);
		this.damage = damage;
		this.penetration = penetration;
		this.jamming = jamming;
		this.parts = parts;
		this.armorDamage = armorDamage;
		this.fragmentationChance = fragmentationChance;
		this.setCreativeTab(LoadTabs.bullets);
	}
	
	
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean isAdv) {
    	super.addInformation(itemStack, entityPlayer, list, isAdv);
        //list.add(Type.getTranslate("characteristic.all.weight") +": " + String.format("%.2f", weight/1000) + Type.getTranslate("characteristic.all.weight.kg"));
    }
	
    @Override
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        ItemStack hold = new ItemStack(par1);
        hold.setTagCompound(new NBTTagCompound());
        hold.getTagCompound().setFloat("weight", weight);
        par3List.add(hold);
    }


	public final int getDamage() {
		return damage;
	}


	public final void setDamage(int damage) {
		if (DefenceVariables.authorizedAccess(Reader.class)) {
			this.damage = damage;
		}
	}


	public final int getPenetration() {
		return penetration;
	}


	public final void setPenetration(int penetration) {
		if (DefenceVariables.authorizedAccess(Reader.class)) {
			this.penetration = penetration;
		}
	}


	public final float getJamming() {
		return jamming;
	}


	public final void setJamming(float jamming) {
		if (DefenceVariables.authorizedAccess(Reader.class)) {
			this.jamming = jamming;
		}
	}


	public final int getParts() {
		return parts;
	}


	public final void setParts(int parts) {
		if (DefenceVariables.authorizedAccess(Reader.class)) {
			this.parts = parts;
		}
	}


	public final float getArmorDamage() {
		return armorDamage;
	}


	public final void setArmorDamage(float armorDamage) {
		if (DefenceVariables.authorizedAccess(Reader.class)) {
			this.armorDamage = armorDamage;
		}
	}


	public final float getFragmentationChance() {
		return fragmentationChance;
	}


	public final void setFragmentationChance(float fragmentationChance) {
		if (DefenceVariables.authorizedAccess(Reader.class)) {
			this.fragmentationChance = fragmentationChance;
		}
	}
   
}
