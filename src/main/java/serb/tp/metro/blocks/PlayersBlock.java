package serb.tp.metro.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import serb.tp.metro.Main;
import serb.tp.metro.blocks.tiles.TileEntityPlayersBlock;
import serb.tp.metro.creativetabs.LoadTabs;

public class PlayersBlock extends Block implements ITileEntityProvider{
	
	/*private Block prevBlock;
	private int prevBlockMeta;*/

	public PlayersBlock(Material material, String name, float resistance, float hardness, int light) {
		super(material);
		this.setBlockName(name);
		this.setResistance(resistance);
		this.setLightOpacity(light);
		this.setHardness(hardness);
		this.setBlockTextureName(Main.modid +":players/"+name);
		this.setCreativeTab(LoadTabs.furnitureBlocks);
		GameRegistry.registerBlock(this, name);
		GameRegistry.registerTileEntity(TileEntityPlayersBlock.class, name+":TileEntity");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityPlayersBlock();
	}

    public void breakBlock(World world, int x, int y, int z, Block block, int i)
    {
    	TileEntityPlayersBlock te = (TileEntityPlayersBlock) world.getTileEntity(x, y, z);
    	Block newBlock = te.getPrevBlock();
    	int newBlockMeta = te.getPrevBlockMeta();
    	world.removeTileEntity(x, y, z);
    	if (newBlock!=null && Block.getIdFromBlock(newBlock)!=0) {
    		world.setBlock(x, y, z, newBlock);
    		world.setBlockMetadataWithNotify(x, y, z, newBlockMeta, 1);
    	}
    }
	
}
