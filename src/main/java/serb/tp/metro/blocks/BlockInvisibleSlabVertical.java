package serb.tp.metro.blocks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import serb.tp.metro.Main;
import serb.tp.metro.blocks.tiles.TileEntityPlayersBlock;

public class BlockInvisibleSlabVertical extends BlockInvisible{
	//protected boolean canMove;
	

	public BlockInvisibleSlabVertical(String name) {
		super(name);
		
	}
	
	@Override
	public void setBlockBoundsForItemRender() {
		setBlockBounds(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}
	
	@Override
    public void setBlockBoundsBasedOnState(IBlockAccess block, int x, int y, int z)
    {

    	int meta = block.getBlockMetadata(x, y, z);
    	
        if 		(meta == 0 || meta==1) 	setBlockBounds(0.0F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
        else if (meta == 2 || meta==3) 	setBlockBounds(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F);
        else if (meta == 4 || meta==5) 	setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
        else if (meta == 6 || meta==7)	setBlockBounds(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

    }
	

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		
		if(player.inventory.getCurrentItem()!=null && player.inventory.getCurrentItem().getItem().getClass().equals(ItemBlock.class) && Block.getBlockFromItem(player.inventory.getCurrentItem().getItem()).getClass().equals(PlayersBlock.class)) {
			TileEntityPlayersBlock te = new TileEntityPlayersBlock(world.getBlock(x, y, z), world.getBlockMetadata(x, y, z));
			world.removeTileEntity(x, y, z);
			world.setBlock(x, y, z, Block.getBlockFromItem(player.inventory.getCurrentItem().getItem()));
			world.setTileEntity(x, y, z, te);
			if (!player.capabilities.isCreativeMode)
				player.inventory.decrStackSize(player.inventory.currentItem, 1);
		}
		return true;
		
	}
    
	@Override
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity colliedEntity)
    {
        this.setBlockBoundsBasedOnState(world, x, y, z);
        super.addCollisionBoxesToList(world, x, y, z, mask, list, colliedEntity);
    }
	
	

	
	
	
}
