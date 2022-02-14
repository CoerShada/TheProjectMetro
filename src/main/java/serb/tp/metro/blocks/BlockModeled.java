package serb.tp.metro.blocks;

import java.io.FileNotFoundException;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import serb.tp.metro.blocks.frame.Frame;
import serb.tp.metro.blocks.frame.FrameManager;
import serb.tp.metro.blocks.tiles.TileEntityChair;
import serb.tp.metro.creativetabs.LoadTabs;

public class BlockModeled extends Block implements ITileEntityProvider{

	Class<? extends TileEntity> te;
	private Frame frame = null;
	
	
	protected BlockModeled(Material material, String name, Class<? extends TileEntity> te) {
		super(material);
		this.setBlockName(name);
		this.setCreativeTab(LoadTabs.furnitureBlocks);
		GameRegistry.registerBlock(this, name);
		GameRegistry.registerTileEntity(te, name+":TileEntity");
		this.te = te;
		bindFrame(this.getUnlocalizedName());

	}
	
	
    @Override
    public boolean isBlockNormalCube() {
        return false;
    }
    
    @Override
    public boolean renderAsNormalBlock() {
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
	public TileEntity createNewTileEntity(World world, int i) {
		TileEntity tile = null;
		try {
			tile = this.te.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return tile;
	}
	
	@Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack)
    {
		int rotation = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, rotation, 1);
		if(this.frame==null) return;
		this.frame.buildFrame(world, x, y, z, rotation, null);
    }
	
    @Override
	public void breakBlock(World world, int x, int y, int z, Block blockOld, int metadataOld) {
    	if(this.frame==null) return;
    	this.frame.removeFrame(world, x, y, z, metadataOld);
    }
	
	


	public void bindFrame(String name) {
		name = name.substring(5);
		try {
			this.frame = FrameManager.INSTANCE.loadFrame(name);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
