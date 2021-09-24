package serb.tp.metro.blocks;

import java.util.Date;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import serb.tp.metro.blocks.tiles.TileEntityRediction;
import serb.tp.metro.blocks.tiles.storages.TileEntityStorage;
import serb.tp.metro.blocks.tiles.storages.spawners.TileEntityStorageSpawner;
import serb.tp.metro.common.CommonProxy;
import serb.tp.metro.creativetabs.LoadTabs;
import serb.tp.metro.handlers.GuiHandler;

public class BlockStorageSpawner extends BlockStorage{

	public BlockStorageSpawner(Material material, String name, int width, int height, int sizeX, int sizeY, int sizeZ,
			Class<? extends TileEntity> te) {
		super(material, name, width, height, sizeX, sizeY, sizeZ, te);
		this.setBlockUnbreakable();
		this.setHardness(60000F);
		this.setCreativeTab(LoadTabs.furnitureUnbreakebleBlocks);
		
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile instanceof TileEntityStorageSpawner) {
			if (player.capabilities.isCreativeMode) {
				TileEntityStorageSpawner te = (TileEntityStorageSpawner)tile;
				if (te.inventory == null) return true;
				GuiHandler.openGui(player, CommonProxy.GUI_STORAGE_SPAWNER, x, y, z);
			}
			else {
				TileEntityStorageSpawner te = (TileEntityStorageSpawner)tile;
				if (te.inventory == null) return true;
				if (new Date().getTime()>te.lastSpawnTime+te.spawnTime)
					te.spawnLoot();
				GuiHandler.openGui(player, CommonProxy.GUI_STORAGE, x, y, z);
			}
		}

		return true;
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
								world.setBlock(x+i, y+j, z+k, LoadBuildersBlocks.blockRedictionUnbreakable);
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
								world.setBlock(x+k, y+j, z+i, LoadBuildersBlocks.blockRedictionUnbreakable);
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

}
