package serb.tp.metro.items.guns;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import serb.tp.metro.client.Type;
import serb.tp.metro.items.Item3D;

public class ItemBullet extends Item3D {
	private final String name;
	public final int damage;
	public final int penetration;
	public final double jamming;
	public final int parts;
	public final float armorDamage;
	public final float fragmentationChance;
	
	public ItemBullet(String name, float weight, int damage, int penetration, double jamming, double armorDamage, double fragmentationChance ,int parts){
		super(name, weight);
		this.name = name;
		this.damage = damage;
		this.penetration = penetration;
		this.jamming = jamming;
		this.parts = parts;
		this.armorDamage = (float) armorDamage;
		this.fragmentationChance = (float) fragmentationChance;
		this.setCreativeTab(CreativeTabs.tabCombat);
	}
	
	
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean isAdv) {
        list.add(Type.getTranslate("characteristic.all.weight") +": " + String.format("%.2f", weight/1000) + Type.getTranslate("characteristic.all.weight.kg"));
    }
	
    @Override
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        ItemStack hold = new ItemStack(par1);
        hold.setTagCompound(new NBTTagCompound());
        hold.getTagCompound().setFloat("weight", weight);
        par3List.add(hold);
    }
   
}
