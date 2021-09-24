package serb.tp.metro.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import serb.tp.metro.blocks.tiles.TileEntityRediction;
import serb.tp.metro.blocks.tiles.storages.TileEntityStorage;

public class BlockModeledBigSizeUsable extends BlockModeled{
	protected int sizeX;
	protected int sizeY;
	protected int sizeZ;

	protected BlockModeledBigSizeUsable(Material material, String name, Class<? extends TileEntity> te, int sizeX, int sizeY, int sizeZ) {
		super(material, name, te);
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.sizeZ = sizeZ;
		
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
		int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile instanceof TileEntityStorage) {
			TileEntityStorage te = (TileEntityStorage)tile;
			
			if(l==0 || l==2) {
				for (int i = 0-(int) sizeX/2; i<sizeX -(int) sizeX/2; i++)
					for (int j = 0; j<sizeY; j++)
						for (int k = 0; k<sizeZ; k++) 
						{
							if(x+i!=x || y+j!=y || z+k!=z) {
								world.setBlock(x+i, y+j, z+k, LoadBuildersBlocks.blockRediction);
								world.setTileEntity(x+i, y+j, z+k, new TileEntityRediction(x,y,z));
								
							}
						}
			
			}
			else {
				for (int i = 0-(int) sizeX/2; i<sizeX -(int) sizeX/2; i++)
					for (int j = 0; j<sizeY; j++)
						for (int k = 0; k<sizeZ; k++)  
						{
							if(x+k!=x || y+j!=y || z+i!=z) {
								world.setBlock(x+k, y+j, z+i, LoadBuildersBlocks.blockRediction);
								world.setTileEntity(x+k, y+j, z+i, new TileEntityRediction(x,y,z));
							}
						}
						
			}
			
			if (itemStack.hasDisplayName() && te.inventory != null) {
				te.inventory.func_110133_a(itemStack.getDisplayName());
			}
		}
		
		world.setBlockMetadataWithNotify(x, y, z, l, 2);
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block blockOld, int metadataOld) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile instanceof TileEntityStorage) {
			TileEntityStorage te = (TileEntityStorage)tile;
			if (te==null ||  te.inventory == null) return;
			for (int i1 = 0; i1 < te.inventory.getSizeInventory(); ++i1) {
				ItemStack itemstack = te.inventory.getStackInSlot(i1);

				if (itemstack != null) {
					float f = world.rand.nextFloat() * 0.8F + 0.1F;
					float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
					float f2 = world.rand.nextFloat() * 0.8F + 0.1F;

					while (itemstack.stackSize > 0) {
						int j1 = world.rand.nextInt(21) + 10;

						if (j1 > itemstack.stackSize) {
							j1 = itemstack.stackSize;
						}

						itemstack.stackSize -= j1;
						EntityItem entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

						if (itemstack.hasTagCompound()) {
							entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
						}

						float f3 = 0.05F;
						entityitem.motionX = (double)((float)world.rand.nextGaussian() * f3);
						entityitem.motionY = (double)((float)world.rand.nextGaussian() * f3 + 0.2F);
						entityitem.motionZ = (double)((float)world.rand.nextGaussian() * f3);
						world.spawnEntityInWorld(entityitem);
					}
				}
			}
			
			if(te.getBlockMetadata()==0 || te.getBlockMetadata()==2) {
				for (int i = 0-(int) sizeX/2; i<sizeX -(int) sizeX/2; i++)
					for (int j = 0; j<sizeY; j++)
						for (int k = 0; k<sizeZ; k++) 
						{
							if(x+i!=x || y+j!=y || z+k!=z) {
								world.setBlock(x+i, y+j, z+k, Blocks.air);
								world.setTileEntity(x+i, y+j, z+k, null);
							}
						}
			
			}
			else {
				for (int i = 0-(int) sizeX/2; i<sizeX -(int) sizeX/2; i++)
					for (int j = 0; j<sizeY; j++)
						for (int k = 0; k<sizeZ; k++)  
						{
							if(x+k!=x || y+j!=y || z+i!=z) {
								world.setBlock(x+k, y+j, z+i, Blocks.air);
								world.setTileEntity(x+k, y+j, z+i, null);
							}
						}
						
			}
			
		}
		super.breakBlock(world, x, y, z, blockOld, metadataOld);
		world.setBlock(x, y, z, Blocks.air);
		
	}
	
	public int[] getSize() {
		return new int[] {sizeX, sizeY, sizeZ};
	}

}
