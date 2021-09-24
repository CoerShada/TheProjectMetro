package serb.tp.metro.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import serb.tp.metro.blocks.tiles.tunnels.TileEntityTunnelLine;
import serb.tp.metro.creativetabs.LoadTabs;

public class BlockTunnelLine extends BlockModeled{

	protected BlockTunnelLine(Material material, String name, Class<? extends TileEntity> te) {
		super(material, name, te);
		this.setCreativeTab(LoadTabs.tunnelsBlocks);
		this.setBlockUnbreakable();
		this.setResistance(6000000.0F);
	}
	
	
	
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack)
    {
        int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        System.out.println(l);
        if (l == 0)
        {
        	for(int i = 0; i<9; i++) {
        		setFrameBlock(world, x-4, y+7, z+i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-4, y+8, z+i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-3, y+8, z+i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+3, y+8, z+i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+4, y+8, z+i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+4, y+7, z+i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x, y, z+i, LoadBuildersBlocks.block_invisible_horizontal_slab, 0);
        		setFrameBlock(world, x-1, y, z+i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-2, y, z+i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-3, y, z+i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-4, y, z+i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-2, y+1, z+i, LoadBuildersBlocks.block_invisible_quater_horizontal, 1);
        		setFrameBlock(world, x-2, y+1, z+i, LoadBuildersBlocks.block_invisible_horizontal_slab, 0);
        		setFrameBlock(world, x-3, y+1, z+i, LoadBuildersBlocks.block_invisible_horizontal_slab, 0);
        		setFrameBlock(world, x-4, y+1, z+i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-4, y+2, z+i, LoadBuildersBlocks.block_invisible_vertical_slab, 1);
        		setFrameBlock(world, x-4, y+3, z+i, LoadBuildersBlocks.block_invisible_vertical_slab, 1);
        		setFrameBlock(world, x-4, y+4, z+i, LoadBuildersBlocks.block_invisible_vertical_slab, 1);
        		setFrameBlock(world, x-4, y+5, z+i, LoadBuildersBlocks.block_invisible_vertical_slab, 1);
        		setFrameBlock(world, x-4, y+6, z+i, LoadBuildersBlocks.blockInvisible_stairs, 5);
        		setFrameBlock(world, x-3, y+7, z+i, LoadBuildersBlocks.blockInvisible_stairs, 5);
        		setFrameBlock(world, x-2, y+8, z+i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-1, y+8, z+i, LoadBuildersBlocks.blockInvisible_stairs, 5);
        		setFrameBlock(world, x, y+8, z+i, LoadBuildersBlocks.block_invisible_horizontal_slab, 8);
        		setFrameBlock(world, x+1, y, z+i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+2, y, z+i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+3, y, z+i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+3, y+1, z+i, LoadBuildersBlocks.block_invisible_quater_horizontal, 3);
        		setFrameBlock(world, x+4, y, z+i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+4, y+1, z+i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+4, y+2, z+i, LoadBuildersBlocks.block_invisible_vertical_slab, 3);
        		setFrameBlock(world, x+4, y+3, z+i, LoadBuildersBlocks.block_invisible_vertical_slab, 3);
        		setFrameBlock(world, x+4, y+4, z+i, LoadBuildersBlocks.block_invisible_vertical_slab, 3);
        		setFrameBlock(world, x+4, y+5, z+i, LoadBuildersBlocks.block_invisible_vertical_slab, 3);
        		setFrameBlock(world, x+4, y+6, z+i, LoadBuildersBlocks.blockInvisible_stairs, 4);
        		setFrameBlock(world, x+3, y+7, z+i, LoadBuildersBlocks.blockInvisible_stairs, 4);
        		setFrameBlock(world, x+2, y+8, z+i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+1, y+8, z+i, LoadBuildersBlocks.blockInvisible_stairs, 4);
        		for (int j = 0; j<9; j++) {
        			setFrameBlock(world, x+j-4, y-1, z+i, LoadBuildersBlocks.concrete_gray_6);
        			setFrameBlock(world, x-5, y+j, z+i, LoadBuildersBlocks.concrete_gray_6);
        			setFrameBlock(world, x+5, y+j, z+i, LoadBuildersBlocks.concrete_gray_6);
        			setFrameBlock(world, x+j-4, y+9, z+i, LoadBuildersBlocks.concrete_gray_6);
        		}
        		
        	}
        	world.setBlockMetadataWithNotify(x, y, z, 2, 2);

        }

        if (l == 1)
        {
        	
        	for(int i = 0; i<9; i++) {
        		setFrameBlock(world, x-i, y, z, LoadBuildersBlocks.block_invisible_horizontal_slab, 0);
        		setFrameBlock(world, x-i, y, z-1, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-i, y, z-2, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-i, y, z-3, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-i, y, z-4, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-i, y+1, z-2, LoadBuildersBlocks.block_invisible_quater_horizontal, 2);
        		setFrameBlock(world, x-i, y+1, z-3, LoadBuildersBlocks.block_invisible_horizontal_slab, 0);
        		setFrameBlock(world, x-i, y+1, z-4, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-i, y+2, z-4, LoadBuildersBlocks.block_invisible_vertical_slab, 2);
        		setFrameBlock(world, x-i, y+3, z-4, LoadBuildersBlocks.block_invisible_vertical_slab, 2);
        		setFrameBlock(world, x-i, y+4, z-4, LoadBuildersBlocks.block_invisible_vertical_slab, 2);
        		setFrameBlock(world, x-i, y+5, z-4, LoadBuildersBlocks.block_invisible_vertical_slab, 2);
        		setFrameBlock(world, x-i, y+6, z-4, LoadBuildersBlocks.blockInvisible_stairs, 7);
        		setFrameBlock(world, x-i, y+7, z-4, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-i, y+8, z-4, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-i, y+8, z-3, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-i, y+7, z-3, LoadBuildersBlocks.blockInvisible_stairs, 7);
        		setFrameBlock(world, x-i, y+8, z-2, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-i, y+8, z-1, LoadBuildersBlocks.blockInvisible_stairs, 7);
        		setFrameBlock(world, x-i, y+8, z, LoadBuildersBlocks.block_invisible_horizontal_slab, 8);
        		setFrameBlock(world, x-i, y+8, z+1, LoadBuildersBlocks.blockInvisible_stairs, 6);
        		setFrameBlock(world, x-i, y+8, z+2, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-i, y+8, z+3, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-i, y+8, z+4, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-i, y+7, z+3, LoadBuildersBlocks.blockInvisible_stairs, 6);
        		setFrameBlock(world, x-i, y+7, z+4, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-i, y+6, z+4, LoadBuildersBlocks.blockInvisible_stairs, 6);
        		setFrameBlock(world, x-i, y+5, z+4, LoadBuildersBlocks.block_invisible_vertical_slab, 0);
        		setFrameBlock(world, x-i, y+4, z+4, LoadBuildersBlocks.block_invisible_vertical_slab, 0);
        		setFrameBlock(world, x-i, y+3, z+4, LoadBuildersBlocks.block_invisible_vertical_slab, 0);
        		setFrameBlock(world, x-i, y+2, z+4, LoadBuildersBlocks.block_invisible_vertical_slab, 0);
        		setFrameBlock(world, x-i, y+1, z+4, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-i, y+1, z+3, LoadBuildersBlocks.block_invisible_quater_horizontal, 0);
        		setFrameBlock(world, x-i, y, z+4, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-i, y, z+3, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-i, y, z+2, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-i, y, z+1, LoadBuildersBlocks.blockInvisible);
        		for (int j = 0; j<9; j++) {
        			setFrameBlock(world, x-i, y-1, z+j-4, LoadBuildersBlocks.concrete_gray_6);
        			setFrameBlock(world, x-i, y+j, z-5, LoadBuildersBlocks.concrete_gray_6);
        			setFrameBlock(world, x-i, y+j, z+5, LoadBuildersBlocks.concrete_gray_6);
        			setFrameBlock(world, x-i, y+9, z+j-4, LoadBuildersBlocks.concrete_gray_6);
        		}
        	}
        	world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if (l == 2)
        {
        	for(int i = 0; i<9; i++) {
        		setFrameBlock(world, x, y, z-i, LoadBuildersBlocks.block_invisible_horizontal_slab, 0);
        		setFrameBlock(world, x+1, y, z-i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+2, y, z-i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+3, y, z-i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+4, y, z-i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+2, y+1, z-i, LoadBuildersBlocks.block_invisible_quater_horizontal, 3);
        		setFrameBlock(world, x+3, y+1, z-i, LoadBuildersBlocks.block_invisible_horizontal_slab, 0);
        		setFrameBlock(world, x+4, y+1, z-i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+4, y+2, z-i, LoadBuildersBlocks.block_invisible_vertical_slab, 3);
        		setFrameBlock(world, x+4, y+3, z-i, LoadBuildersBlocks.block_invisible_vertical_slab, 3);
        		setFrameBlock(world, x+4, y+4, z-i, LoadBuildersBlocks.block_invisible_vertical_slab, 3);
        		setFrameBlock(world, x+4, y+5, z-i, LoadBuildersBlocks.block_invisible_vertical_slab, 3);
        		setFrameBlock(world, x+4, y+6, z-i, LoadBuildersBlocks.blockInvisible_stairs, 4);
        		setFrameBlock(world, x+4, y+7, z-i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+4, y+8, z-i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+3, y+7, z-i, LoadBuildersBlocks.blockInvisible_stairs, 4);
        		setFrameBlock(world, x+3, y+8, z-i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+2, y+8, z-i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+1, y+8, z-i, LoadBuildersBlocks.blockInvisible_stairs, 4);
        		setFrameBlock(world, x, y+8, z-i, LoadBuildersBlocks.block_invisible_horizontal_slab, 8);
        		setFrameBlock(world, x-1, y+8, z-i, LoadBuildersBlocks.blockInvisible_stairs, 5);
        		setFrameBlock(world, x-2, y+8, z-i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-3, y+8, z-i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-4, y+8, z-i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-3, y+7, z-i, LoadBuildersBlocks.blockInvisible_stairs, 5);
        		setFrameBlock(world, x-4, y+7, z-i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-4, y+6, z-i, LoadBuildersBlocks.blockInvisible_stairs, 5);
        		setFrameBlock(world, x-4, y+5, z-i, LoadBuildersBlocks.block_invisible_vertical_slab, 1);
        		setFrameBlock(world, x-4, y+4, z-i, LoadBuildersBlocks.block_invisible_vertical_slab, 1);
        		setFrameBlock(world, x-4, y+3, z-i, LoadBuildersBlocks.block_invisible_vertical_slab, 1);
        		setFrameBlock(world, x-4, y+2, z-i, LoadBuildersBlocks.block_invisible_vertical_slab, 1);
        		setFrameBlock(world, x-4, y+1, z-i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-4, y, z-i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-3, y+1, z-i, LoadBuildersBlocks.block_invisible_quater_horizontal, 1);
        		setFrameBlock(world, x-3, y, z-i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-2, y, z-i, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x-1, y, z-i, LoadBuildersBlocks.blockInvisible);
        		for (int j = 0; j<9; j++) {
        			setFrameBlock(world, x+j-4, y-1, z-i, LoadBuildersBlocks.concrete_gray_6);
        			setFrameBlock(world, x-5, y+j, z-i, LoadBuildersBlocks.concrete_gray_6);
        			setFrameBlock(world, x+5, y+j, z-i, LoadBuildersBlocks.concrete_gray_6);
        			setFrameBlock(world, x+j-4, y+9, z-i, LoadBuildersBlocks.concrete_gray_6);
        		}
        	}
        	world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        	
        }

        if (l == 3)
        {

        	for(int i = 0; i<9; i++) {

        		setFrameBlock(world, x+i, y, z, LoadBuildersBlocks.block_invisible_horizontal_slab, 0);
        		setFrameBlock(world, x+i, y, z+1, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+i, y, z+2, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+i, y, z+3, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+i, y, z+4, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+i, y+1, z+2, LoadBuildersBlocks.block_invisible_quater_horizontal, 0);
        		setFrameBlock(world, x+i, y+1, z+3, LoadBuildersBlocks.block_invisible_horizontal_slab, 0);
        		setFrameBlock(world, x+i, y+1, z+4, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+i, y+2, z+4, LoadBuildersBlocks.block_invisible_vertical_slab, 0);
        		setFrameBlock(world, x+i, y+3, z+4, LoadBuildersBlocks.block_invisible_vertical_slab, 0);
        		setFrameBlock(world, x+i, y+4, z+4, LoadBuildersBlocks.block_invisible_vertical_slab, 0);
        		setFrameBlock(world, x+i, y+5, z+4, LoadBuildersBlocks.block_invisible_vertical_slab, 0);
        		setFrameBlock(world, x+i, y+6, z+4, LoadBuildersBlocks.blockInvisible_stairs, 6);
        		setFrameBlock(world, x+i, y+7, z+4, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+i, y+8, z+4, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+i, y+7, z+3, LoadBuildersBlocks.blockInvisible_stairs, 6);
        		setFrameBlock(world, x+i, y+8, z+3, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+i, y+8, z+2, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+i, y+8, z+1, LoadBuildersBlocks.blockInvisible_stairs, 6);
        		setFrameBlock(world, x+i, y+8, z, LoadBuildersBlocks.block_invisible_horizontal_slab, 8);
        		setFrameBlock(world, x+i, y+8, z-1, LoadBuildersBlocks.blockInvisible_stairs, 7);
        		setFrameBlock(world, x+i, y+8, z-2, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+i, y+8, z-3, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+i, y+7, z-3, LoadBuildersBlocks.blockInvisible_stairs, 7);
        		setFrameBlock(world, x+i, y+8, z-4, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+i, y+7, z-4, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+i, y+6, z-4, LoadBuildersBlocks.blockInvisible_stairs, 7);
        		setFrameBlock(world, x+i, y+5, z-4, LoadBuildersBlocks.block_invisible_vertical_slab, 2);
        		setFrameBlock(world, x+i, y+4, z-4, LoadBuildersBlocks.block_invisible_vertical_slab, 2);
        		setFrameBlock(world, x+i, y+3, z-4, LoadBuildersBlocks.block_invisible_vertical_slab, 2);
        		setFrameBlock(world, x+i, y+2, z-4, LoadBuildersBlocks.block_invisible_vertical_slab, 2);
        		setFrameBlock(world, x+i, y+1, z-4, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+i, y, z-4, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+i, y+1, z-3, LoadBuildersBlocks.block_invisible_quater_horizontal, 2);
        		setFrameBlock(world, x+i, y, z-3, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+i, y, z-2, LoadBuildersBlocks.blockInvisible);
        		setFrameBlock(world, x+i, y, z-1, LoadBuildersBlocks.blockInvisible);
	       		for (int j = 0; j<9; j++) {
	        		setFrameBlock(world, x+i, y-1, z+j-4, LoadBuildersBlocks.concrete_gray_6);
	        		setFrameBlock(world, x+i, y+j, z-5, LoadBuildersBlocks.concrete_gray_6);
	        		setFrameBlock(world, x+i, y+j, z+5, LoadBuildersBlocks.concrete_gray_6);
	       			setFrameBlock(world, x+i, y+9, z+j-4, LoadBuildersBlocks.concrete_gray_6);
	       		}
	        }
	        world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }
        
        if (itemStack.hasDisplayName())
        {
            ((TileEntityFurnace)world.getTileEntity(x, y, z)).func_145951_a(itemStack.getDisplayName());
        }

    }

    @Override
	public void breakBlock(World world, int x, int y, int z, Block blockOld, int metadataOld) {
		int l = metadataOld-2;
		
		 if (l == 0)
	        {
	        	for(int i = 0; i<9; i++) {
	        		removeFrameBlock(world, x-4, y+7, z+i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-4, y+8, z+i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-3, y+8, z+i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+3, y+8, z+i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+4, y+8, z+i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+4, y+7, z+i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-3, y, z+i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-4, y, z+i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x, y, z+i, LoadBuildersBlocks.block_invisible_horizontal_slab);
	        		removeFrameBlock(world, x-1, y, z+i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-2, y, z+i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-2, y+1, z+i, LoadBuildersBlocks.block_invisible_quater_horizontal);
	        		removeFrameBlock(world, x-2, y+1, z+i, LoadBuildersBlocks.block_invisible_horizontal_slab);
	        		removeFrameBlock(world, x-3, y+1, z+i, LoadBuildersBlocks.block_invisible_horizontal_slab);
	        		removeFrameBlock(world, x-4, y+1, z+i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-4, y+2, z+i, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x-4, y+3, z+i, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x-4, y+4, z+i, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x-4, y+5, z+i, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x-4, y+6, z+i, LoadBuildersBlocks.blockInvisible_stairs);
	        		removeFrameBlock(world, x-3, y+7, z+i, LoadBuildersBlocks.blockInvisible_stairs);
	        		removeFrameBlock(world, x-2, y+8, z+i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-1, y+8, z+i, LoadBuildersBlocks.blockInvisible_stairs);
	        		removeFrameBlock(world, x, y+8, z+i, LoadBuildersBlocks.block_invisible_horizontal_slab);
	        		removeFrameBlock(world, x+1, y, z+i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+2, y, z+i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+3, y, z+i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+3, y+1, z+i, LoadBuildersBlocks.block_invisible_quater_horizontal);
	        		removeFrameBlock(world, x+4, y+1, z+i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+4, y, z+i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+4, y+2, z+i, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x+4, y+3, z+i, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x+4, y+4, z+i, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x+4, y+5, z+i, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x+4, y+6, z+i, LoadBuildersBlocks.blockInvisible_stairs);
	        		removeFrameBlock(world, x+3, y+7, z+i, LoadBuildersBlocks.blockInvisible_stairs);
	        		removeFrameBlock(world, x+2, y+8, z+i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+1, y+8, z+i, LoadBuildersBlocks.blockInvisible_stairs);
	        		for (int j = 0; j<9; j++) {
	        			removeFrameBlock(world, x+j-4, y-1, z+i, LoadBuildersBlocks.concrete_gray_6);
	        			removeFrameBlock(world, x-5, y+j, z+i, LoadBuildersBlocks.concrete_gray_6);
	        			removeFrameBlock(world, x+5, y+j, z+i, LoadBuildersBlocks.concrete_gray_6);
	        			removeFrameBlock(world, x+j-4, y+9, z+i, LoadBuildersBlocks.concrete_gray_6);
	        		}
	        		
	        	}

	        }

	        if (l == 1)
	        {
	        	
	        	for(int i = 0; i<9; i++) {
	        		removeFrameBlock(world, x-i, y, z, LoadBuildersBlocks.block_invisible_horizontal_slab);
	        		removeFrameBlock(world, x-i, y, z-1, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-i, y, z-2, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-i, y, z-3, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-i, y, z-4, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-i, y+1, z-2, LoadBuildersBlocks.block_invisible_quater_horizontal);
	        		removeFrameBlock(world, x-i, y+1, z-3, LoadBuildersBlocks.block_invisible_horizontal_slab);
	        		removeFrameBlock(world, x-i, y+1, z-4, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-i, y+2, z-4, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x-i, y+3, z-4, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x-i, y+4, z-4, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x-i, y+5, z-4, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x-i, y+6, z-4, LoadBuildersBlocks.blockInvisible_stairs);
	        		removeFrameBlock(world, x-i, y+7, z-4, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-i, y+8, z-4, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-i, y+8, z-3, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-i, y+7, z-3, LoadBuildersBlocks.blockInvisible_stairs);
	        		removeFrameBlock(world, x-i, y+8, z-2, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-i, y+8, z-1, LoadBuildersBlocks.blockInvisible_stairs);
	        		removeFrameBlock(world, x-i, y+8, z, LoadBuildersBlocks.block_invisible_horizontal_slab);
	        		removeFrameBlock(world, x-i, y+8, z+1, LoadBuildersBlocks.blockInvisible_stairs);
	        		removeFrameBlock(world, x-i, y+8, z+2, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-i, y+8, z+3, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-i, y+8, z+4, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-i, y+7, z+3, LoadBuildersBlocks.blockInvisible_stairs);
	        		removeFrameBlock(world, x-i, y+7, z+4, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-i, y+6, z+4, LoadBuildersBlocks.blockInvisible_stairs);
	        		removeFrameBlock(world, x-i, y+5, z+4, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x-i, y+4, z+4, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x-i, y+3, z+4, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x-i, y+2, z+4, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x-i, y+1, z+4, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-i, y+1, z+3, LoadBuildersBlocks.block_invisible_quater_horizontal);
	        		removeFrameBlock(world, x-i, y, z+4, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-i, y, z+3, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-i, y, z+2, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-i, y, z+1, LoadBuildersBlocks.blockInvisible);
	        		for (int j = 0; j<9; j++) {
	        			removeFrameBlock(world, x-i, y-1, z+j-4, LoadBuildersBlocks.concrete_gray_6);
	        			removeFrameBlock(world, x-i, y+j, z-5, LoadBuildersBlocks.concrete_gray_6);
	        			removeFrameBlock(world, x-i, y+j, z+5, LoadBuildersBlocks.concrete_gray_6);
	        			removeFrameBlock(world, x-i, y+9, z+j-4, LoadBuildersBlocks.concrete_gray_6);
	        		}
	        	}
	        }

	        if (l == 2)
	        {
	        	for(int i = 0; i<9; i++) {
	        		removeFrameBlock(world, x, y, z-i, LoadBuildersBlocks.block_invisible_horizontal_slab);
	        		removeFrameBlock(world, x+1, y, z-i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+2, y, z-i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+3, y, z-i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+4, y, z-i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+2, y+1, z-i, LoadBuildersBlocks.block_invisible_quater_horizontal);
	        		removeFrameBlock(world, x+3, y+1, z-i, LoadBuildersBlocks.block_invisible_horizontal_slab);
	        		removeFrameBlock(world, x+4, y+1, z-i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+4, y+2, z-i, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x+4, y+3, z-i, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x+4, y+4, z-i, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x+4, y+5, z-i, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x+4, y+6, z-i, LoadBuildersBlocks.blockInvisible_stairs);
	        		removeFrameBlock(world, x+4, y+7, z-i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+4, y+8, z-i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+3, y+7, z-i, LoadBuildersBlocks.blockInvisible_stairs);
	        		removeFrameBlock(world, x+3, y+8, z-i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+2, y+8, z-i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+1, y+8, z-i, LoadBuildersBlocks.blockInvisible_stairs);
	        		removeFrameBlock(world, x, y+8, z-i, LoadBuildersBlocks.block_invisible_horizontal_slab);
	        		removeFrameBlock(world, x-1, y+8, z-i, LoadBuildersBlocks.blockInvisible_stairs);
	        		removeFrameBlock(world, x-2, y+8, z-i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-3, y+8, z-i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-4, y+8, z-i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-3, y+7, z-i, LoadBuildersBlocks.blockInvisible_stairs);
	        		removeFrameBlock(world, x-4, y+7, z-i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-4, y+6, z-i, LoadBuildersBlocks.blockInvisible_stairs);
	        		removeFrameBlock(world, x-4, y+5, z-i, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x-4, y+4, z-i, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x-4, y+3, z-i, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x-4, y+2, z-i, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x-4, y+1, z-i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-4, y, z-i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-3, y+1, z-i, LoadBuildersBlocks.block_invisible_quater_horizontal);
	        		removeFrameBlock(world, x-3, y, z-i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-2, y, z-i, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x-1, y, z-i, LoadBuildersBlocks.blockInvisible);
	        		for (int j = 0; j<9; j++) {
	        			removeFrameBlock(world, x+j-4, y-1, z-i, LoadBuildersBlocks.concrete_gray_6);
	        			removeFrameBlock(world, x-5, y+j, z-i, LoadBuildersBlocks.concrete_gray_6);
	        			removeFrameBlock(world, x+5, y+j, z-i, LoadBuildersBlocks.concrete_gray_6);
	        			removeFrameBlock(world, x+j-4, y+9, z-i, LoadBuildersBlocks.concrete_gray_6);
	        		}
	        	} 	
	        }

	        if (l == 3)
	        {

	        	for(int i = 0; i<9; i++) {

	        		removeFrameBlock(world, x+i, y, z, LoadBuildersBlocks.block_invisible_horizontal_slab);
	        		removeFrameBlock(world, x+i, y, z+1, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+i, y, z+2, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+i, y, z+3, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+i, y, z+4, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+i, y+1, z+2, LoadBuildersBlocks.block_invisible_quater_horizontal);
	        		removeFrameBlock(world, x+i, y+1, z+3, LoadBuildersBlocks.block_invisible_horizontal_slab);
	        		removeFrameBlock(world, x+i, y+1, z+4, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+i, y+2, z+4, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x+i, y+3, z+4, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x+i, y+4, z+4, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x+i, y+5, z+4, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x+i, y+6, z+4, LoadBuildersBlocks.blockInvisible_stairs);
	        		removeFrameBlock(world, x+i, y+7, z+4, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+i, y+8, z+4, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+i, y+7, z+3, LoadBuildersBlocks.blockInvisible_stairs);
	        		removeFrameBlock(world, x+i, y+8, z+3, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+i, y+8, z+2, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+i, y+8, z+1, LoadBuildersBlocks.blockInvisible_stairs);
	        		removeFrameBlock(world, x+i, y+8, z, LoadBuildersBlocks.block_invisible_horizontal_slab);
	        		removeFrameBlock(world, x+i, y+8, z-1, LoadBuildersBlocks.blockInvisible_stairs);
	        		removeFrameBlock(world, x+i, y+8, z-2, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+i, y+8, z-3, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+i, y+7, z-3, LoadBuildersBlocks.blockInvisible_stairs);
	        		removeFrameBlock(world, x+i, y+8, z-4, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+i, y+7, z-4, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+i, y+6, z-4, LoadBuildersBlocks.blockInvisible_stairs);
	        		removeFrameBlock(world, x+i, y+5, z-4, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x+i, y+4, z-4, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x+i, y+3, z-4, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x+i, y+2, z-4, LoadBuildersBlocks.block_invisible_vertical_slab);
	        		removeFrameBlock(world, x+i, y+1, z-4, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+i, y, z-4, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+i, y+1, z-3, LoadBuildersBlocks.block_invisible_quater_horizontal);
	        		removeFrameBlock(world, x+i, y, z-3, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+i, y, z-2, LoadBuildersBlocks.blockInvisible);
	        		removeFrameBlock(world, x+i, y, z-1, LoadBuildersBlocks.blockInvisible);
		       		for (int j = 0; j<9; j++) {
		        		removeFrameBlock(world, x+i, y-1, z+j-4, LoadBuildersBlocks.concrete_gray_6);
		        		removeFrameBlock(world, x+i, y+j, z-5, LoadBuildersBlocks.concrete_gray_6);
		        		removeFrameBlock(world, x+i, y+j, z+5, LoadBuildersBlocks.concrete_gray_6);
		       			removeFrameBlock(world, x+i, y+9, z+j-4, LoadBuildersBlocks.concrete_gray_6);
		       		}
		        }
	        }
	}


	
	@Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
    {
		return false;
    }
	

}
