package serb.tp.metro.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import serb.tp.metro.blocks.LoadBuildersBlocks;

public class BuildersBlocks extends CreativeTabs{

	
	public BuildersBlocks(int nextID, String lable) {
		super(nextID, lable);

	}
	
	@Override
	public Item getTabIconItem() {
		return Item.getItemFromBlock(LoadBuildersBlocks.concrete_brick_1);
	}

}
