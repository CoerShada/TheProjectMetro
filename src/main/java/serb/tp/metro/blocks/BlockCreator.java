package serb.tp.metro.blocks;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import serb.tp.metro.blocks.tiles.storages.TileEntityStorage;
import serb.tp.metro.blocks.tiles.storages.creators.TileEntityCreator;
import serb.tp.metro.common.CommonProxy;
import serb.tp.metro.creativetabs.LoadTabs;
import serb.tp.metro.handlers.GuiHandler;
import serb.tp.metro.recipes.Recipe;

public class BlockCreator extends BlockStorage{
	
	public final Recipe[] recipes;
	public final Item neededItem;

	protected BlockCreator(Material material, String name, Class<? extends TileEntity> te, int xSize, int ySize, int zSize, Recipe[] recipes, Item neededItem, boolean needEnergy, boolean needFuel) {
		super(material, name, 3, 2, xSize, ySize, zSize, te);
		this.recipes = recipes;
		this.neededItem = neededItem;
		this.setCreativeTab(LoadTabs.furnitureBlocks);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			TileEntity tile = world.getTileEntity(x, y, z);
			if (tile instanceof TileEntityCreator) {
				
				TileEntityCreator te = (TileEntityCreator)tile;
				if (te.inventory == null) return true;
				GuiHandler.openGui(player, CommonProxy.GUI_CREATOR, x, y, z);
			}
		}

		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		TileEntityCreator tile = null;
		try {
			Constructor<TileEntityCreator> ctor = (Constructor<TileEntityCreator>) te.getConstructor(int.class, int.class);
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

}
