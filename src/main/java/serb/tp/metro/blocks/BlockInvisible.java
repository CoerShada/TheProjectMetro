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
		if (!world.isRemote && Main.debug) {
			File file = new File("E://adtime", "fileConstructor.txt");
			if (!file.exists())
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
			
	        try(FileWriter writer = new FileWriter("E://adtime//fileConstructor.txt", true))
	        {
				String stringX = String.valueOf(0+x);
				if (0+x>0)
					stringX = "+" + stringX;
				else if (x==0) 
					stringX="";
				
				String stringY = String.valueOf(y-4);
				if (0+y>4)
					stringY = "+" + stringY;
				else if (y==4) 
					stringY="";
				
				String stringZ = String.valueOf(0+z);
				if (0+z>0)
					stringZ = "+" + stringZ;
				else if (z==0) 
					stringZ="";
				
	            String text = "setFrameBlock(world, x" + stringX + ", y"+stringY+", z" + stringZ + ", LoadBuildersBlocks."+this.getUnlocalizedName().substring(5) +");\n";
	            writer.write(text);

	            writer.flush();
	        }
	        catch(IOException ex){
	             
	            System.out.println(ex.getMessage());
	        } 

		}
		
	}
}
