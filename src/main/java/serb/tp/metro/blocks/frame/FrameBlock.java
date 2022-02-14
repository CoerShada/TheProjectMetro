package serb.tp.metro.blocks.frame;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import scala.actors.threadpool.Arrays;
import serb.tp.metro.Main;
import serb.tp.metro.blocks.tiles.TileEntityRediction;

public class FrameBlock {

	private String blockName;
	
	private int[] offset = new int[3];
	
	private int meta;
	
	public FrameBlock(String blockName, int xOffset, int yOffset, int zOffset, int meta) {
		this.blockName = blockName;
		offset[0] = xOffset;
		offset[1] = yOffset;
		offset[2] = zOffset;
		this.meta = meta;
	}
	
	public void build(World world, int parentX, int parentY, int parentZ, int rotation) {
		Block block = GameRegistry.findBlock(Main.modid, blockName);
		if (block==null)
			block = GameRegistry.findBlock("minecraft", blockName);

		int meta;
		int[] pos = changeOffsetWithRotation(rotation);
		pos[0]+=parentX;
		pos[1]+=parentY;
		pos[2]+=parentZ;
		meta = changeMetaWithRotation(block, rotation);
		if(world.getBlock(pos[0], pos[1], pos[2]).isAir(world, pos[0], pos[1], pos[2])) {
			world.setBlock(pos[0], pos[1], pos[2], block);
			world.setBlockMetadataWithNotify(pos[0], pos[1], pos[2], meta, 1);
		}
		
		
	}
	
	public void buildWithTileEntity(World world, int parentX, int parentY, int parentZ, int rotation, TileEntity te) {
		build(world, parentX, parentY, parentZ, rotation);
		int[] pos = changeOffsetWithRotation(rotation);
		pos[0]+=parentX;
		pos[1]+=parentY;
		pos[2]+=parentZ;
		world.setTileEntity(pos[0], pos[1], pos[2], te);
		
	}
	
	private int[] changeOffsetWithRotation(int rotation) {
		
		int[] newOffset = this.offset.clone();
		int temp;
		switch(rotation) {
			case 1:
				temp = newOffset[0];
				newOffset[0] = newOffset[2];
				newOffset[2] = temp;
				newOffset[0]*=-1;
				break;
			case 2:
				newOffset[0]*=-1;
				newOffset[2]*=-1;
				break;
			case 3:
				temp = newOffset[0];
				newOffset[0] = newOffset[2];
				newOffset[2] = temp;
				newOffset[2]*=-1;
				break;
		}
		
		return newOffset;
	}
	
	private int changeMetaWithRotation(Block block, int rotation) {
		int newMeta = 0;
		if (block instanceof BlockStairs) {
			int[] metaArray = new int[] {2, 1, 3, 0};
			int index = (Arrays.binarySearch(metaArray, this.meta & 3) + rotation) & 3;
			newMeta = metaArray[index];
			if (this.meta>=4) {
				newMeta+=4;
			}

		}
		else {
			newMeta = (this.meta+rotation*2) & 7;
		}
		return newMeta;
	}

	public void remove(World world, int parentX, int parentY, int parentZ, int meta) {
		int rotation = meta;
		int[] pos = changeOffsetWithRotation(rotation);
		pos[0]+=parentX;
		pos[1]+=parentY;
		pos[2]+=parentZ;
		world.removeTileEntity(pos[0], pos[1], pos[2]);
		world.setBlock(pos[0], pos[1], pos[2], Blocks.air);
	}
	
}
