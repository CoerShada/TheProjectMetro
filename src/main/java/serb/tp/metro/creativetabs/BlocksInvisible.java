package serb.tp.metro.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import serb.tp.metro.blocks.LoadBuildersBlocks;
import serb.tp.metro.blocks.LoadFurniture;

public class BlocksInvisible extends CreativeTabs{

	public BlocksInvisible(int id, String label) {
		super(id, label);
	}

	
	@Override
	public Item getTabIconItem() {
		return Item.getItemFromBlock(LoadBuildersBlocks.blockInvisible);
	}
}
