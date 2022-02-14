package serb.tp.metro.blocks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import serb.tp.metro.Main;
import serb.tp.metro.creativetabs.LoadTabs;

public class BlockInvisible extends Block{

	String texture;
	
	public BlockInvisible(String name) {
		super(Material.clay);
		this.setBlockTextureName(Main.modid+":blocksInvisible/blockInvisible");
		this.setCreativeTab(LoadTabs.blocksInvisible);
		this.setBlockName(name);
		this.setBlockUnbreakable();
		this.setResistance(6000000.0F);
		GameRegistry.registerBlock(this, name);
	}

	@Override
	public int getRenderType() {
		return 0;
	}
	
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
		if (Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode) {
			return 1;
		}
        return -1;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public float getAmbientOcclusionLightValue() {
		return 1.0F;
	}
	
    @SideOnly(Side.CLIENT)
    @Override
    public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
    {
        Block block = p_149646_1_.getBlock(p_149646_2_, p_149646_3_, p_149646_4_);

        if (this == LoadBuildersBlocks.blockInvisible)
        {
            if (p_149646_1_.getBlockMetadata(p_149646_2_, p_149646_3_, p_149646_4_) != p_149646_1_.getBlockMetadata(p_149646_2_ - Facing.offsetsXForSide[p_149646_5_], p_149646_3_ - Facing.offsetsYForSide[p_149646_5_], p_149646_4_ - Facing.offsetsZForSide[p_149646_5_]))
            {
                return true;
            }

            if (block == this)
            {
                return false;
            }
        }

        return true;
    }
    
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
		int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		l*=2;
		world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z)+l, 1);
		
	}
	
	@Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
    {	
		if (side != 0 && (side == 1 || (double)hitY <= 0.5D)){
			return 0;
		}
        return 1;
        
    }
}
