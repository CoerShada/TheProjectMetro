package serb.tp.metro.blocks;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import serb.tp.metro.Main;
import serb.tp.metro.blocks.tiles.TileEntityRediction;
import serb.tp.metro.blocks.tiles.storages.TileEntityStorage;
import serb.tp.metro.handlers.GuiHandler;

public class BlockRediction extends Block implements ITileEntityProvider{

	protected BlockRediction(String name) {
		super(Material.clay);
		GameRegistry.registerBlock(this, name);
		GameRegistry.registerTileEntity(TileEntityRediction.class, name+":TileEntity");
		
	}

    @Override
    public boolean isBlockNormalCube() {
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
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityRediction();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile instanceof TileEntityRediction) {
			TileEntityRediction teTmp = (TileEntityRediction)tile;	
			TileEntityStorage te = (TileEntityStorage) world.getTileEntity(teTmp.masterCordX, teTmp.masterCordY, teTmp.masterCordZ);
			if (te==null) return true;
			world.getBlock(teTmp.masterCordX, teTmp.masterCordY, teTmp.masterCordZ).onBlockActivated(world, teTmp.masterCordX, teTmp.masterCordY, teTmp.masterCordZ, player, side, hitX, hitY, hitZ);
			//GuiHandler.openGui(player, Adtime.GUI_STORAGE, teTmp.masterCordX, teTmp.masterCordY, teTmp.masterCordZ);
		}
		return true;
	}
	
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return null;
    }
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block blockOld, int metadataOld) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile instanceof TileEntityRediction) {
			TileEntityRediction teTmp = (TileEntityRediction) tile;
			tile = world.getTileEntity(teTmp.masterCordX, teTmp.masterCordY, teTmp.masterCordZ);
			if (world.getBlock(teTmp.masterCordX, teTmp.masterCordY, teTmp.masterCordZ) instanceof BlockStorage) {
				TileEntityStorage tileBreaked = (TileEntityStorage) tile;
				tileBreaked.getBlockType().breakBlock(world, teTmp.masterCordX, teTmp.masterCordY, teTmp.masterCordZ, blockOld, metadataOld);
			}
		}
	}

}
