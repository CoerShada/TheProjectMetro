package serb.tp.metro.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import serb.tp.metro.blocks.LoadFurniture;
import serb.tp.metro.blocks.LoadFurnitureUnbreakable;

public class BlocksFurnitureUndreakeble extends CreativeTabs {
	public BlocksFurnitureUndreakeble(int nextID, String lable) {
		super(nextID, lable);

	}
	
	@Override
	public Item getTabIconItem() {
		return Item.getItemFromBlock(LoadFurnitureUnbreakable.spawner_box_standart_1);
	}
}
