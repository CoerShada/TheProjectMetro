package serb.tp.metro.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockTunnelLineExitLeft extends BlockTunnelLine{

	protected BlockTunnelLineExitLeft(Material material, String name, Class<? extends TileEntity> te) {
		super(material, name, te);
		
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack)
	{
		 int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
	     if (l == 0)
	     {
	    	 world.setBlockMetadataWithNotify(x, y, z, 2, 2);
	    	 for(int i = 0; i<9; i++) {
	        	if (i==3 || i==4) {
		        	setFrameBlock(world, x, y, z+i, LoadBlocks.blockInvisible);
		        	setFrameBlock(world, x-1, y, z+i, LoadBlocks.blockInvisible);
		        	setFrameBlock(world, x-2, y, z+i, LoadBlocks.blockInvisible);
		        	setFrameBlock(world, x-3, y, z+i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-4, y+1, z+i, LoadBlocks.blockInvisible);
	        		setFrameBlock(world, x-5, y+2, z+i, LoadBlocks.blockInvisible);
	        		setFrameBlock(world, x-5, y+3, z+i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-5, y+4, z+i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-5, y+5, z+i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-4, y+6, z+i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-4, y+7, z+i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-3, y+8, z+i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-2, y+8, z+i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-1, y+9, z+i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x, y+9, z+i, LoadBlocks.blockInvisible);
		        	setFrameBlock(world, x+1, y, z+i, LoadBlocks.blockInvisible);
		        	setFrameBlock(world, x+2, y, z+i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+3, y, z+i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+4, y, z+i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+5, y, z+i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+5, y+4, z+i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+5, y+5, z+i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+4, y+6, z+i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+4, y+7, z+i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+3, y+8, z+i, LoadBlocks.blockInvisible);
		        	setFrameBlock(world, x+2, y+8, z+i, LoadBlocks.blockInvisible);
		        	setFrameBlock(world, x+1, y+9, z+i, LoadBlocks.blockInvisible);
	        	}
	        	else {
	        		setFrameBlock(world, x, y, z+i, LoadBlocks.blockInvisible);
	        		setFrameBlock(world, x-1, y, z+i, LoadBlocks.blockInvisible);
	        		setFrameBlock(world, x-2, y, z+i, LoadBlocks.blockInvisible);
	        		setFrameBlock(world, x-3, y, z+i, LoadBlocks.blockInvisible);
	        		setFrameBlock(world, x-4, y+1, z+i, LoadBlocks.blockInvisible);
	        		setFrameBlock(world, x-5, y+2, z+i, LoadBlocks.blockInvisible);
	        		setFrameBlock(world, x-5, y+3, z+i, LoadBlocks.blockInvisible);
	        		setFrameBlock(world, x-5, y+4, z+i, LoadBlocks.blockInvisible);
	        		setFrameBlock(world, x-5, y+5, z+i, LoadBlocks.blockInvisible);
	        		setFrameBlock(world, x-4, y+6, z+i, LoadBlocks.blockInvisible);
	        		setFrameBlock(world, x-4, y+7, z+i, LoadBlocks.blockInvisible);
	        		setFrameBlock(world, x-3, y+8, z+i, LoadBlocks.blockInvisible);
	        		setFrameBlock(world, x-2, y+8, z+i, LoadBlocks.blockInvisible);
	        		setFrameBlock(world, x-1, y+9, z+i, LoadBlocks.blockInvisible);
	        		setFrameBlock(world, x, y+9, z+i, LoadBlocks.blockInvisible);
	        		setFrameBlock(world, x+1, y, z+i, LoadBlocks.blockInvisible);
	        		setFrameBlock(world, x+2, y, z+i, LoadBlocks.blockInvisible);
	        		setFrameBlock(world, x+3, y, z+i, LoadBlocks.blockInvisible);
	        		setFrameBlock(world, x+4, y+1, z+i, LoadBlocks.blockInvisible);
	        		if (i==2 || i==5)
	        			setFrameBlock(world, x+5, y+1, z+i, LoadBlocks.blockInvisible);
	        		setFrameBlock(world, x+5, y+2, z+i, LoadBlocks.blockInvisible);
	        		setFrameBlock(world, x+5, y+3, z+i, LoadBlocks.blockInvisible);
	        		setFrameBlock(world, x+5, y+4, z+i, LoadBlocks.blockInvisible);
	        		setFrameBlock(world, x+5, y+5, z+i, LoadBlocks.blockInvisible);
	        		setFrameBlock(world, x+4, y+6, z+i, LoadBlocks.blockInvisible);
	        		setFrameBlock(world, x+4, y+7, z+i, LoadBlocks.blockInvisible);
	        		setFrameBlock(world, x+3, y+8, z+i, LoadBlocks.blockInvisible);
	       			setFrameBlock(world, x+2, y+8, z+i, LoadBlocks.blockInvisible);
	       			setFrameBlock(world, x+1, y+9, z+i, LoadBlocks.blockInvisible);
	        	}
	        	for (int j = 0; j<11; j++) {
	        		setFrameBlock(world, x+j-5, y-1, z+i, LoadBlocks.concrete_gray_6);
	        		setFrameBlock(world, x-6, y+j, z+i, LoadBlocks.concrete_gray_6);
	        		if (!((i==3 || i==4) && (j>=1 && j<=3)))
	        			setFrameBlock(world, x+6, y+j, z+i, LoadBlocks.concrete_gray_6);
	        		setFrameBlock(world, x+j-5, y+10, z+i, LoadBlocks.concrete_gray_6);
	        	}
	        		
	    	 }
	     }

	     if (l == 1)
	     {
	        world.setBlockMetadataWithNotify(x, y, z, 3, 2);
	       	for(int i = 0; i<9; i++) {
	       		if (i==3 || i==4) {
		       		setFrameBlock(world, x-i, y, z, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-i, y, z-1, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-i, y, z-2, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-i, y, z-3, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-i, y+1, z-4, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-i, y+2, z-5, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-i, y+3, z-5, LoadBlocks.blockInvisible);
		        	setFrameBlock(world, x-i, y+4, z-5, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-i, y+5, z-5, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-i, y+6, z-4, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-i, y+7, z-4, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-i, y+8, z-3, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-i, y+8, z-2, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-i, y+9, z-1, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-i, y+9, z, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-i, y, z+1, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-i, y, z+2, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-i, y, z+3, LoadBlocks.blockInvisible);
		        	setFrameBlock(world, x-i, y, z+4, LoadBlocks.blockInvisible);
		        	setFrameBlock(world, x-i, y, z+5, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-i, y+4, z+5, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-i, y+5, z+5, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-i, y+6, z+4, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-i, y+7, z+4, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-i, y+8, z+3, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-i, y+8, z+2, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-i, y+9, z+1, LoadBlocks.blockInvisible);
	       		}
	       		else {
	       			setFrameBlock(world, x-i, y, z, LoadBlocks.blockInvisible);
	       			setFrameBlock(world, x-i, y, z-1, LoadBlocks.blockInvisible);
	       			setFrameBlock(world, x-i, y, z-2, LoadBlocks.blockInvisible);
	       			setFrameBlock(world, x-i, y, z-3, LoadBlocks.blockInvisible);
	       			setFrameBlock(world, x-i, y+1, z-4, LoadBlocks.blockInvisible);
	       			setFrameBlock(world, x-i, y+2, z-5, LoadBlocks.blockInvisible);
	       			setFrameBlock(world, x-i, y+3, z-5, LoadBlocks.blockInvisible);
	       			setFrameBlock(world, x-i, y+4, z-5, LoadBlocks.blockInvisible);
	       			setFrameBlock(world, x-i, y+5, z-5, LoadBlocks.blockInvisible);
	       			setFrameBlock(world, x-i, y+6, z-4, LoadBlocks.blockInvisible);
	       			setFrameBlock(world, x-i, y+7, z-4, LoadBlocks.blockInvisible);
	       			setFrameBlock(world, x-i, y+8, z-3, LoadBlocks.blockInvisible);
	       			setFrameBlock(world, x-i, y+8, z-2, LoadBlocks.blockInvisible);
	       			setFrameBlock(world, x-i, y+9, z-1, LoadBlocks.blockInvisible);
	       			setFrameBlock(world, x-i, y+9, z, LoadBlocks.blockInvisible);
	       			setFrameBlock(world, x-i, y, z+1, LoadBlocks.blockInvisible);
	       			setFrameBlock(world, x-i, y, z+2, LoadBlocks.blockInvisible);
	       			setFrameBlock(world, x-i, y, z+3, LoadBlocks.blockInvisible);
	       			setFrameBlock(world, x-i, y+1, z+4, LoadBlocks.blockInvisible);
	        		if (i==2 || i==5)
	        			setFrameBlock(world, x-i, y+1, z+5, LoadBlocks.blockInvisible);
	       			setFrameBlock(world, x-i, y+2, z+5, LoadBlocks.blockInvisible);
	       			setFrameBlock(world, x-i, y+3, z+5, LoadBlocks.blockInvisible);
	       			setFrameBlock(world, x-i, y+4, z+5, LoadBlocks.blockInvisible);
	       			setFrameBlock(world, x-i, y+5, z+5, LoadBlocks.blockInvisible);
	       			setFrameBlock(world, x-i, y+6, z+4, LoadBlocks.blockInvisible);
	       			setFrameBlock(world, x-i, y+7, z+4, LoadBlocks.blockInvisible);
	       			setFrameBlock(world, x-i, y+8, z+3, LoadBlocks.blockInvisible);
	       			setFrameBlock(world, x-i, y+8, z+2, LoadBlocks.blockInvisible);
	       			setFrameBlock(world, x-i, y+9, z+1, LoadBlocks.blockInvisible);
	       		}
	        	for (int j = 0; j<11; j++) {
	        		setFrameBlock(world, x-i, y-1, z+j-5, LoadBlocks.concrete_gray_6);
	        		setFrameBlock(world, x-i, y+j, z-6, LoadBlocks.concrete_gray_6);
	        		if (!((i==3 || i==4) && (j>=1 && j<=3)))
	        			setFrameBlock(world, x-i, y+j, z+6, LoadBlocks.concrete_gray_6);
	       			setFrameBlock(world, x-i, y+10, z+j-5, LoadBlocks.concrete_gray_6);
	       		}
	       	}
	     }

	     if (l == 2)
	     {
	    	 for(int i = 0; i<9; i++) {
	    		if (i==3 || i==4) 
	    		{
	    			setFrameBlock(world, x, y, z-i, LoadBlocks.blockInvisible);
			       	setFrameBlock(world, x-1, y, z-i, LoadBlocks.blockInvisible);
			       	setFrameBlock(world, x-2, y, z-i, LoadBlocks.blockInvisible);
			       	setFrameBlock(world, x-3, y, z-i, LoadBlocks.blockInvisible);
			       	setFrameBlock(world, x-4, y, z-i, LoadBlocks.blockInvisible);
			       	setFrameBlock(world, x-5, y, z-i, LoadBlocks.blockInvisible);
			       	setFrameBlock(world, x-5, y+4, z-i, LoadBlocks.blockInvisible);
			      	setFrameBlock(world, x-5, y+5, z-i, LoadBlocks.blockInvisible);
			       	setFrameBlock(world, x-4, y+6, z-i, LoadBlocks.blockInvisible);
			        setFrameBlock(world, x-4, y+7, z-i, LoadBlocks.blockInvisible);
			        setFrameBlock(world, x-3, y+8, z-i, LoadBlocks.blockInvisible);
			       	setFrameBlock(world, x-2, y+8, z-i, LoadBlocks.blockInvisible);
			       	setFrameBlock(world, x-1, y+9, z-i, LoadBlocks.blockInvisible);
			       	setFrameBlock(world, x, y+9, z-i, LoadBlocks.blockInvisible);
			       	setFrameBlock(world, x+1, y, z-i, LoadBlocks.blockInvisible);
			       	setFrameBlock(world, x+2, y, z-i, LoadBlocks.blockInvisible);
			       	setFrameBlock(world, x+3, y, z-i, LoadBlocks.blockInvisible);
			       	setFrameBlock(world, x+4, y+1, z-i, LoadBlocks.blockInvisible);
			       	setFrameBlock(world, x+5, y+2, z-i, LoadBlocks.blockInvisible);
			    	setFrameBlock(world, x+5, y+3, z-i, LoadBlocks.blockInvisible);
			       	setFrameBlock(world, x+5, y+4, z-i, LoadBlocks.blockInvisible);
			        setFrameBlock(world, x+5, y+5, z-i, LoadBlocks.blockInvisible);
			        setFrameBlock(world, x+4, y+6, z-i, LoadBlocks.blockInvisible);
			       	setFrameBlock(world, x+4, y+7, z-i, LoadBlocks.blockInvisible);
			       	setFrameBlock(world, x+3, y+8, z-i, LoadBlocks.blockInvisible);
			       	setFrameBlock(world, x+2, y+8, z-i, LoadBlocks.blockInvisible);
			       	setFrameBlock(world, x+1, y+9, z-i, LoadBlocks.blockInvisible);
	    		 }
	    		 else 
	    		 {
		       		setFrameBlock(world, x, y, z-i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-1, y, z-i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-2, y, z-i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-3, y, z-i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-4, y+1, z-i, LoadBlocks.blockInvisible);
	        		if (i==2 || i==5)
	        			setFrameBlock(world, x-5, y+1, z-i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-5, y+2, z-i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-5, y+3, z-i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-5, y+4, z-i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-5, y+5, z-i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-4, y+6, z-i, LoadBlocks.blockInvisible);
		        	setFrameBlock(world, x-4, y+7, z-i, LoadBlocks.blockInvisible);
		        	setFrameBlock(world, x-3, y+8, z-i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-2, y+8, z-i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x-1, y+9, z-i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x, y+9, z-i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+1, y, z-i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+2, y, z-i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+3, y, z-i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+4, y+1, z-i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+5, y+2, z-i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+5, y+3, z-i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+5, y+4, z-i, LoadBlocks.blockInvisible);
		        	setFrameBlock(world, x+5, y+5, z-i, LoadBlocks.blockInvisible);
		        	setFrameBlock(world, x+4, y+6, z-i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+4, y+7, z-i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+3, y+8, z-i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+2, y+8, z-i, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+1, y+9, z-i, LoadBlocks.blockInvisible);
	    		}
	       		for (int j = 0; j<11; j++) {
	       			setFrameBlock(world, x+j-5, y-1, z-i, LoadBlocks.concrete_gray_6);
	       			if (!((i==3 || i==4) && (j>=1 && j<=3)))
	       				setFrameBlock(world, x-6, y+j, z-i, LoadBlocks.concrete_gray_6);
	       			setFrameBlock(world, x+6, y+j, z-i, LoadBlocks.concrete_gray_6);
	        		setFrameBlock(world, x+j-5, y+10, z-i, LoadBlocks.concrete_gray_6);
	       		}
	       	}
	       	world.setBlockMetadataWithNotify(x, y, z, 4, 2);
	        	
	     }

	     if (l == 3)
	     {
	        world.setBlockMetadataWithNotify(x, y, z, 2, 2);
	        for(int i = 0; i<9; i++) 
	        {
	        	if (i==3 || i==4) {
		        	setFrameBlock(world, x+i, y, z, LoadBlocks.blockInvisible);
		        	setFrameBlock(world, x+i, y, z-1, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y, z-2, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y, z-3, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y, z-4, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y, z-5, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+4, z-5, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+5, z-5, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+6, z-4, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+7, z-4, LoadBlocks.blockInvisible);
		        	setFrameBlock(world, x+i, y+8, z-3, LoadBlocks.blockInvisible);
		        	setFrameBlock(world, x+i, y+8, z-2, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+9, z-1, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+9, z, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y, z+1, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y, z+2, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y, z+3, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+1, z+4, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+2, z+5, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+3, z+5, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+4, z+5, LoadBlocks.blockInvisible);
		        	setFrameBlock(world, x+i, y+5, z+5, LoadBlocks.blockInvisible);
		        	setFrameBlock(world, x+i, y+6, z+4, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+7, z+4, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+8, z+3, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+8, z+2, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+9, z+1, LoadBlocks.blockInvisible);
	        	}
	        	else {
		        	setFrameBlock(world, x+i, y, z, LoadBlocks.blockInvisible);
		        	setFrameBlock(world, x+i, y, z-1, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y, z-2, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y, z-3, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+1, z-4, LoadBlocks.blockInvisible);
	        		if (i==2 || i==5)
	        			setFrameBlock(world, x+i, y+1, z-5, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+2, z-5, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+3, z-5, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+4, z-5, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+5, z-5, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+6, z-4, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+7, z-4, LoadBlocks.blockInvisible);
		        	setFrameBlock(world, x+i, y+8, z-3, LoadBlocks.blockInvisible);
		        	setFrameBlock(world, x+i, y+8, z-2, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+9, z-1, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+9, z, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y, z+1, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y, z+2, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y, z+3, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+1, z+4, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+2, z+5, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+3, z+5, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+4, z+5, LoadBlocks.blockInvisible);
		        	setFrameBlock(world, x+i, y+5, z+5, LoadBlocks.blockInvisible);
		        	setFrameBlock(world, x+i, y+6, z+4, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+7, z+4, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+8, z+3, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+8, z+2, LoadBlocks.blockInvisible);
		       		setFrameBlock(world, x+i, y+9, z+1, LoadBlocks.blockInvisible);
	        	}
	       		for (int j = 0; j<11; j++) {
	        		setFrameBlock(world, x+i, y-1, z+j-5, LoadBlocks.concrete_gray_6);
	        		if (!((i==3 || i==4) && (j>=1 && j<=3)))
	        			setFrameBlock(world, x+i, y+j, z-6, LoadBlocks.concrete_gray_6);
	        		setFrameBlock(world, x+i, y+j, z+6, LoadBlocks.concrete_gray_6);
	       			setFrameBlock(world, x+i, y+10, z+j-5, LoadBlocks.concrete_gray_6);
	       		}
	        }
	        world.setBlockMetadataWithNotify(x, y, z, 5, 2);
	        	
	     }

	     if (itemStack.hasDisplayName())
	     {
	        ((TileEntityFurnace)world.getTileEntity(x, y, z)).func_145951_a(itemStack.getDisplayName());
	    }
	 }

}
