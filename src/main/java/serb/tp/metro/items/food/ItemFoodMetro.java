package serb.tp.metro.items.food;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemFoodMetro extends ItemFood
{
    private final int saturation;
    private final int hydration;
    private final float radiation;
    private final int durability;
    private final float weight;
    private final float chanceOfPoisoning;
	
	
    public ItemFoodMetro(int saturation, int hydration, int durability, float weight, float radiation, float chanceOfPoisoning) 
    {
	super(32, true);
	this.saturation = saturation;
	this.hydration = hydration;
	this.durability = durability;
	this.radiation = radiation;
	this.weight = weight;
	this.chanceOfPoisoning = chanceOfPoisoning;
    }
	
    @Override
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        ItemStack hold = new ItemStack(par1);
        hold.setTagCompound(new NBTTagCompound());
        hold.stackTagCompound.setInteger("saturation", saturation);
        hold.stackTagCompound.setInteger("hydration", hydration);
        hold.stackTagCompound.setInteger("durability", durability);
        hold.stackTagCompound.setFloat("radiation", radiation);
        hold.stackTagCompound.setFloat("chanceOfPoisoning", chanceOfPoisoning);
        hold.stackTagCompound.setFloat("weight", weight);
        par3List.add(hold);
    }

    
    public ItemStack onEaten(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_)
    {
        --p_77654_1_.stackSize;
        p_77654_3_.getFoodStats().func_151686_a(this, p_77654_1_);
        p_77654_2_.playSoundAtEntity(p_77654_3_, "random.burp", 0.5F, p_77654_2_.rand.nextFloat() * 0.1F + 0.9F);
        this.onFoodEaten(p_77654_1_, p_77654_2_, p_77654_3_);
        return p_77654_1_;
    }
	
    public void eat(ItemStack itemStack, EntityPlayer player) 
    {
	player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel() + itemStack.getTagCompound().getInteger("saturation"));
    }
}
