package serb.tp.metro.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockTunnelLineExitRight extends BlockTunnelLine{

	protected BlockTunnelLineExitRight(Material material, String name, Class<? extends TileEntity> te) {
		super(material, name, te);
		
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack)
	{
		super.onBlockPlacedBy(world, x, y, z, entity, itemStack);
		int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
	    if (l == 0)
	    {
	    	removeFrameBlock(world, x-4, y+1, z+3, new Block[] {LoadBuildersBlocks.block_invisible_horizontal_slab});
	    	setFrameBlock(world, x-4, y+1, z+3, LoadBuildersBlocks.block_invisible_horizontal_slab, 0);
	    	removeFrameBlock(world, x-4, y+1, z+4, new Block[] {LoadBuildersBlocks.block_invisible_horizontal_slab});
	    	setFrameBlock(world, x-4, y+1, z+4, LoadBuildersBlocks.block_invisible_horizontal_slab, 0);
	    	removeFrameBlock(world, x-4, y+2, z+3, new Block[] {LoadBuildersBlocks.block_invisible_vertical_slab});
	    	removeFrameBlock(world, x-4, y+3, z+3, new Block[] {LoadBuildersBlocks.block_invisible_vertical_slab});
	    	removeFrameBlock(world, x-4, y+2, z+4, new Block[] {LoadBuildersBlocks.block_invisible_vertical_slab});
	    	removeFrameBlock(world, x-4, y+3, z+4, new Block[] {LoadBuildersBlocks.block_invisible_vertical_slab});
	    	removeFrameBlock(world, x-5, y+1, z+3, new Block[] {LoadBuildersBlocks.concrete_gray_6});
	    	removeFrameBlock(world, x-5, y+2, z+3, new Block[] {LoadBuildersBlocks.concrete_gray_6});
	    	removeFrameBlock(world, x-5, y+3, z+3, new Block[] {LoadBuildersBlocks.concrete_gray_6});
	    	removeFrameBlock(world, x-5, y+1, z+4, new Block[] {LoadBuildersBlocks.concrete_gray_6});
	    	removeFrameBlock(world, x-5, y+2, z+4, new Block[] {LoadBuildersBlocks.concrete_gray_6});
	    	removeFrameBlock(world, x-5, y+3, z+4, new Block[] {LoadBuildersBlocks.concrete_gray_6});
	    }
	    else if (l == 1)
	    {
	    	removeFrameBlock(world, x-3, y+1, z-4, new Block[] {LoadBuildersBlocks.block_invisible_horizontal_slab});
	    	setFrameBlock(world, x-3, y+1, z-4, LoadBuildersBlocks.block_invisible_horizontal_slab, 0);
	    	removeFrameBlock(world, x-4, y+1, z-4, new Block[] {LoadBuildersBlocks.block_invisible_horizontal_slab});
	    	setFrameBlock(world, x-4, y+1, z-4, LoadBuildersBlocks.block_invisible_horizontal_slab, 0);
	    	removeFrameBlock(world, x-3, y+2, z-4, new Block[] {LoadBuildersBlocks.block_invisible_vertical_slab});
	    	removeFrameBlock(world, x-3, y+3, z-4, new Block[] {LoadBuildersBlocks.block_invisible_vertical_slab});
	    	removeFrameBlock(world, x-4, y+2, z-4, new Block[] {LoadBuildersBlocks.block_invisible_vertical_slab});
	    	removeFrameBlock(world, x-4, y+3, z-4, new Block[] {LoadBuildersBlocks.block_invisible_vertical_slab});
	    	removeFrameBlock(world, x-3, y+1, z-5, new Block[] {LoadBuildersBlocks.concrete_gray_6});
	    	removeFrameBlock(world, x-3, y+2, z-5, new Block[] {LoadBuildersBlocks.concrete_gray_6});
	    	removeFrameBlock(world, x-3, y+3, z-5, new Block[] {LoadBuildersBlocks.concrete_gray_6});
	    	removeFrameBlock(world, x-4, y+1, z-5, new Block[] {LoadBuildersBlocks.concrete_gray_6});
	    	removeFrameBlock(world, x-4, y+2, z-5, new Block[] {LoadBuildersBlocks.concrete_gray_6});
	    	removeFrameBlock(world, x-4, y+3, z-5, new Block[] {LoadBuildersBlocks.concrete_gray_6});
	    }
	 
	    else if (l == 2)
	    {
	    	removeFrameBlock(world, x+4, y+1, z-3, new Block[] {LoadBuildersBlocks.block_invisible_horizontal_slab});
	    	setFrameBlock(world, x+4, y+1, z-3, LoadBuildersBlocks.block_invisible_horizontal_slab, 0);
	    	removeFrameBlock(world, x+4, y+1, z-4, new Block[] {LoadBuildersBlocks.block_invisible_horizontal_slab});
	    	setFrameBlock(world, x+4, y+1, z-4, LoadBuildersBlocks.block_invisible_horizontal_slab, 0);
	    	removeFrameBlock(world, x+4, y+2, z-3, new Block[] {LoadBuildersBlocks.block_invisible_vertical_slab});
	    	removeFrameBlock(world, x+4, y+3, z-3, new Block[] {LoadBuildersBlocks.block_invisible_vertical_slab});
	    	removeFrameBlock(world, x+4, y+2, z-4, new Block[] {LoadBuildersBlocks.block_invisible_vertical_slab});
	    	removeFrameBlock(world, x+4, y+3, z-4, new Block[] {LoadBuildersBlocks.block_invisible_vertical_slab});
	    	removeFrameBlock(world, x+5, y+1, z-3, new Block[] {LoadBuildersBlocks.concrete_gray_6});
	    	removeFrameBlock(world, x+5, y+2, z-3, new Block[] {LoadBuildersBlocks.concrete_gray_6});
	    	removeFrameBlock(world, x+5, y+3, z-3, new Block[] {LoadBuildersBlocks.concrete_gray_6});
	    	removeFrameBlock(world, x+5, y+1, z-4, new Block[] {LoadBuildersBlocks.concrete_gray_6});
	    	removeFrameBlock(world, x+5, y+2, z-4, new Block[] {LoadBuildersBlocks.concrete_gray_6});
	    	removeFrameBlock(world, x+5, y+3, z-4, new Block[] {LoadBuildersBlocks.concrete_gray_6});
	    }
	    
	    else
	    {
	    	removeFrameBlock(world, x+3, y+1, z+4, new Block[] {LoadBuildersBlocks.block_invisible_horizontal_slab});
	    	setFrameBlock(world, x+3, y+1, z+4, LoadBuildersBlocks.block_invisible_horizontal_slab, 0);
	    	removeFrameBlock(world, x+4, y+1, z+4, new Block[] {LoadBuildersBlocks.block_invisible_horizontal_slab});
	    	setFrameBlock(world, x+4, y+1, z+4, LoadBuildersBlocks.block_invisible_horizontal_slab, 0);
	    	removeFrameBlock(world, x+3, y+2, z+4, new Block[] {LoadBuildersBlocks.block_invisible_vertical_slab});
	    	removeFrameBlock(world, x+3, y+3, z+4, new Block[] {LoadBuildersBlocks.block_invisible_vertical_slab});
	    	removeFrameBlock(world, x+4, y+2, z+4, new Block[] {LoadBuildersBlocks.block_invisible_vertical_slab});
	    	removeFrameBlock(world, x+4, y+3, z+4, new Block[] {LoadBuildersBlocks.block_invisible_vertical_slab});
	    	removeFrameBlock(world, x+3, y+1, z+5, new Block[] {LoadBuildersBlocks.concrete_gray_6});
	    	removeFrameBlock(world, x+3, y+2, z+5, new Block[] {LoadBuildersBlocks.concrete_gray_6});
	    	removeFrameBlock(world, x+3, y+3, z+5, new Block[] {LoadBuildersBlocks.concrete_gray_6});
	    	removeFrameBlock(world, x+4, y+1, z+5, new Block[] {LoadBuildersBlocks.concrete_gray_6});
	    	removeFrameBlock(world, x+4, y+2, z+5, new Block[] {LoadBuildersBlocks.concrete_gray_6});
	    	removeFrameBlock(world, x+4, y+3, z+5, new Block[] {LoadBuildersBlocks.concrete_gray_6});
	    }
	 }
	


	@Override
	public void breakBlock(World world, int x, int y, int z, Block blockOld, int metadataOld) {
    	super.breakBlock(world, x, y, z, blockOld, metadataOld);
		int l = metadataOld-2;
	     if (l == 0)
	     {
	        	removeFrameBlock(world, x-4, y, z+3, new Block[] {LoadBuildersBlocks.blockInvisible});
	        	removeFrameBlock(world, x-5, y, z+3, new Block[] {LoadBuildersBlocks.blockInvisible});
	        	removeFrameBlock(world, x-4, y, z+4, new Block[] {LoadBuildersBlocks.blockInvisible});
	        	removeFrameBlock(world, x-5, y, z+4, new Block[] {LoadBuildersBlocks.blockInvisible});
	        	removeFrameBlock(world, x-5, y+1, z+2, new Block[] {LoadBuildersBlocks.blockInvisible});
	        	removeFrameBlock(world, x-5, y+1, z+5, new Block[] {LoadBuildersBlocks.blockInvisible});
	     }
	     else if(l==1) {
	        	removeFrameBlock(world, x-3, y, z+4, new Block[] {LoadBuildersBlocks.blockInvisible});
	        	removeFrameBlock(world, x-3, y, z+5, new Block[] {LoadBuildersBlocks.blockInvisible});
	        	removeFrameBlock(world, x-4, y, z+4, new Block[] {LoadBuildersBlocks.blockInvisible});
	        	removeFrameBlock(world, x-4, y, z+5, new Block[] {LoadBuildersBlocks.blockInvisible});
	        	removeFrameBlock(world, x-2, y+1, z+5, new Block[] {LoadBuildersBlocks.blockInvisible});
	        	removeFrameBlock(world, x-5, y+1, z+5, new Block[] {LoadBuildersBlocks.blockInvisible});
	     }
	     else if(l==2) {
	        	removeFrameBlock(world, x+4, y, z-3, new Block[] {LoadBuildersBlocks.blockInvisible});
	        	removeFrameBlock(world, x+5, y, z-3, new Block[] {LoadBuildersBlocks.blockInvisible});
	        	removeFrameBlock(world, x+4, y, z-4, new Block[] {LoadBuildersBlocks.blockInvisible});
	        	removeFrameBlock(world, x+5, y, z-4, new Block[] {LoadBuildersBlocks.blockInvisible});
	        	removeFrameBlock(world, x+5, y+1, z-2, new Block[] {LoadBuildersBlocks.blockInvisible});
	        	removeFrameBlock(world, x+5, y+1, z-5, new Block[] {LoadBuildersBlocks.blockInvisible});
	     }
	     else if(l==3) {
	        	removeFrameBlock(world, x+3, y, z+4, new Block[] {LoadBuildersBlocks.blockInvisible});
	        	removeFrameBlock(world, x+3, y, z+5, new Block[] {LoadBuildersBlocks.blockInvisible});
	        	removeFrameBlock(world, x+4, y, z+4, new Block[] {LoadBuildersBlocks.blockInvisible});
	        	removeFrameBlock(world, x+4, y, z+5, new Block[] {LoadBuildersBlocks.blockInvisible});
	        	removeFrameBlock(world, x+2, y+1, z+5, new Block[] {LoadBuildersBlocks.blockInvisible});
	        	removeFrameBlock(world, x+5, y+1, z+5, new Block[] {LoadBuildersBlocks.blockInvisible});
	     }
    }

}
