package serb.tp.metro.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import serb.tp.metro.blocks.LoadFurniture;
import serb.tp.metro.blocks.LoadTunnels;

public class BlocksFurniture extends CreativeTabs {
	public BlocksFurniture(int nextID, String lable) {
		super(nextID, lable);

	}
	
	@Override
	public Item getTabIconItem() {
		return Item.getItemFromBlock(LoadFurniture.chair1);
	}
}