package serb.tp.metro.blocks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import serb.tp.metro.Main;
import serb.tp.metro.creativetabs.LoadTabs;

public class BuildersBlock extends Block
{

	String texture;
    public BuildersBlock(Material material, String name, float light)
    {
    	super(material);
    	this.setBlockTextureName(Main.modid +":builders/"+name);
    	this.setBlockUnbreakable();
    	//this.setStepSound(sound);
    	this.setBlockName(name);
    	this.setResistance(6000000.0F);
    	this.setLightLevel(light);
    	this.setCreativeTab(LoadTabs.buildersBlocks);
    	GameRegistry.registerBlock(this, name);
    }
    
    @Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
		
	}
}
