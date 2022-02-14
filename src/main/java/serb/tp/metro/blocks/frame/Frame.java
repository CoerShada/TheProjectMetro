package serb.tp.metro.blocks.frame;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import serb.tp.metro.blocks.tiles.TileEntityRediction;

public class Frame {

	private ArrayList<FrameBlock> frame = new ArrayList<FrameBlock>();
	
	public void buildFrame(World world, int parentX, int parentY, int parentZ, int rotation, TileEntity te) {
		
		for (FrameBlock frameBlock: frame) {
			if (te!=null) {
				frameBlock.buildWithTileEntity(world, parentX, parentY, parentZ, rotation, te);
			}
			else {
				frameBlock.build(world, parentX, parentY, parentZ, rotation);
			}
		}
	}
	
	public void removeFrame(World world, int parentX, int parentY, int parentZ, int meta) {
		
		for (FrameBlock frameBlock: frame) {
			frameBlock.remove(world, parentX, parentY, parentZ, meta);
		}
	}
	
	void addFrameBlock(String blockName, int xOffset, int yOffset, int zOffset, int meta){
		frame.add(new FrameBlock(blockName, xOffset, yOffset, zOffset, meta));
	}



}
