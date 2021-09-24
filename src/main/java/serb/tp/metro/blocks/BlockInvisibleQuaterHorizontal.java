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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import serb.tp.metro.Main;
import serb.tp.metro.blocks.tiles.TileEntityPlayersBlock;

public class BlockInvisibleQuaterHorizontal extends BlockInvisible{

	public BlockInvisibleQuaterHorizontal(String name) {
		super(name);
	}

	@Override
	public void setBlockBoundsForItemRender() {
	    setBlockBounds(0.0F, 0.0F, 0.5F, 1.0F, 0.5F, 1.0F);
	}

	@Override
    public void setBlockBoundsBasedOnState(IBlockAccess block, int x, int y, int z)
    {

    	int meta = block.getBlockMetadata(x, y, z);
    	
    	if (meta == 0) 		setBlockBounds(0.0F, 0.0F, 0.5F, 1.0F, 0.5F, 1.0F);
        else if (meta == 1) setBlockBounds(0.0F, 0.0F, 0.0F, 0.5F, 0.5F, 1.0F);
        else if (meta == 2) setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 0.5F);
        else if (meta == 3) setBlockBounds(0.5F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        else if (meta == 4) setBlockBounds(0.0F, 0.5F, 0.5F, 1.0F, 1.0F, 1.0F);
        else if (meta == 5) setBlockBounds(0.0F, 0.5F, 0.0F, 0.5F, 1.0F, 1.0F);
        else if (meta == 6) setBlockBounds(0.0F, 0.6F, 0.0F, 1.0F, 1.0F, 0.5F);
        else 				setBlockBounds(0.5F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
          
        
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
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
		int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) + l, 1);
		
	
		
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
				
	            String text = "setFrameBlock(world, x" + stringX + ", y"+stringY+", z" + stringZ + ", LoadBuildersBlocks."+this.getUnlocalizedName().substring(5) +", " + world.getBlockMetadata(x, y, z) +");\n";
	            writer.write(text);

	            writer.flush();
	        }
	        catch(IOException ex){
	             
	            System.out.println(ex.getMessage());
	        } 
		}
		
		
	}
	
	@Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
    {
		//world.setBlockMetadataWithNotify(x, y, z, 1, (this.field_150004_a ? meta : (side != 0 && (side == 1 || (double)hitY <= 0.5D) ? meta : meta | 8)));
		
		if (side != 0 && (side == 1 || (double)hitY <= 0.5D)){
			return meta;
		}
        return 4;
        
    }
}
