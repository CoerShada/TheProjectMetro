package serb.tp.metro.blocks;


import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import serb.tp.metro.Main;
import serb.tp.metro.entities.player.ExtendedPlayer;
import serb.tp.metro.entities.player.handlers.RadiationHandler;
import serb.tp.metro.items.ItemMask;

public class BlockRadiation extends Block
{
	
    public final float radiation;

    protected BlockRadiation(float radiation, String name) 
    {
	super(Material.redstoneLight);
	this.radiation = radiation;
	this.setBlockUnbreakable();
	this.setBlockName(name);
	this.setLightOpacity(0);
	this.setCreativeTab(CreativeTabs.tabBlock);
	this.setBlockTextureName(Main.modid + ":rads_low");
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public boolean isOpaqueCube()
    {
	return false;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) 
    {
	return null;
    }
    

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
	if (!(entity instanceof EntityPlayer)) return;
	EntityPlayer player = (EntityPlayer) entity;
	
	float value = radiation;
	
	if(player.inventory.getStackInSlot(15)!=null && player.inventory.getStackInSlot(15).getItem() instanceof ItemMask && player.inventory.getStackInSlot(15).hasTagCompound() && player.inventory.getStackInSlot(15).getTagCompound().getInteger("filterTime")>0) 
	{
	    value = (float) (radiation * (1 - player.inventory.getStackInSlot(15).getTagCompound().getDouble("radResistance")));
	}
	
	RadiationHandler.increaseRadiation(player, (float) (0.1 * value));
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random rnd) 
    {
	EntityPlayer player = Minecraft.getMinecraft().thePlayer;
	if (player.capabilities.isCreativeMode) {
	    System.out.println(this.getTextureName());
	    
	    
	}
	    
    }
}
