package serb.tp.metro.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import serb.tp.metro.items.LoadItems;
import serb.tp.metro.items.armor.LoadItemArmor;
import serb.tp.metro.items.guns.LoadGuns;

public class TabRPGi extends CreativeTabs {

	public TabRPGi(int nextID, String lable) {
		super(nextID, lable);
	}

	@Override
	public Item getTabIconItem() {
		return LoadGuns.Ppsh;
	}

}