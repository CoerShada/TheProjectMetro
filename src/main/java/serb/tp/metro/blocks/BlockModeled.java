package serb.tp.metro.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import serb.tp.metro.blocks.tiles.TileEntityChair;
import serb.tp.metro.creativetabs.LoadTabs;

public class BlockModeled extends Block implements ITileEntityProvider{

	Class<? extends TileEntity> te;
	
	protected BlockModeled(Material material, String name, Class<? extends TileEntity> te) {
		super(material);
		this.setBlockName(name);
		this.setCreativeTab(LoadTabs.furnitureBlocks);
		GameRegistry.registerBlock(this, name);
		GameRegistry.registerTileEntity(te, name+":TileEntity");
		this.te = te;
	}
	
	
    @Override
    public boolean isBlockNormalCube() {
        return false;
    }
    
    @Override
    public boolean renderAsNormalBlock() {
    	return false;
    }
    
    @Override
    public boolean isOpaqueCube() {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
    	return -1;
    }


	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		TileEntity tile = null;
		try {
			tile = this.te.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return tile;
	}
	
	public void setFrameBlock(World world, int x, int y, int z, Block block) {
		this.setFrameBlock(world, x, y, z, block, -1);
	}
	
	public void setFrameBlock(World world, int x, int y, int z, Block block, int metadata) {
		if(world.getBlock(x, y, z).isAir(world, x, y, z)) {
			world.setBlock(x, y, z, block);
			
			if (metadata>-1)
				world.setBlockMetadataWithNotify(x, y, z, metadata, 1);
			else
				world.setBlockMetadataWithNotify(x, y, z, 2, 1);
				
		}
	}
	
	public void removeFrameBlock(World world, int x, int y, int z, Block[] blocks) {
		for (int i=0; i<blocks.length; i++)
			if(world.getBlock(x, y, z) == blocks[i]) 
				world.setBlock(x, y, z, Blocks.air);
				
	}
	
	public void removeFrameBlock(World world, int x, int y, int z, Block block) {
		if(world.getBlock(x, y, z) == block) 
			world.setBlock(x, y, z, Blocks.air);
				
	}

}
