package serb.tp.metro.blocks;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import serb.tp.metro.blocks.tiles.storages.TileEntityStorage;
import serb.tp.metro.common.CommonProxy;
import serb.tp.metro.handlers.GuiHandler;

public class BlockStorage extends BlockModeledBigSizeUsable implements ITileEntityProvider {

	protected int width;
	protected int height;
	
	public BlockStorage(Material material, String name, int width, int height, int sizeX, int sizeY, int sizeZ, Class<? extends TileEntity> te) {
		super(material, name, te, sizeX, sizeY, sizeZ);
		this.setBlockName(name);
		this.width = width;
		this.height = height;
		
	}


	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		TileEntityStorage tile = null;
		try {
			Constructor<TileEntityStorage> ctor = (Constructor<TileEntityStorage>) te.getConstructor(int.class, int.class);
			tile = ctor.newInstance(width, height);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return tile;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile instanceof TileEntityStorage) {
			TileEntityStorage te = (TileEntityStorage)tile;
			if (te.inventory == null) return true;
			GuiHandler.openGui(player, CommonProxy.GUI_STORAGE, x, y, z);
		}

		return true;
	}


    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
    	return -1;
    }
    
    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return null;
    }

	

}
