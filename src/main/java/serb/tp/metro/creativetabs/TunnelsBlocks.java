package serb.tp.metro.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import serb.tp.metro.blocks.LoadBuildersBlocks;
import serb.tp.metro.blocks.LoadTunnels;

public class TunnelsBlocks extends CreativeTabs {
	public TunnelsBlocks(int nextID, String lable) {
		super(nextID, lable);

	}
	
	@Override
	public Item getTabIconItem() {
		return Item.getItemFromBlock(LoadTunnels.tunnel1line);
	}
}
