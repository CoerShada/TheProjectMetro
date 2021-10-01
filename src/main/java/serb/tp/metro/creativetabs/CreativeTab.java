package serb.tp.metro.creativetabs;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import serb.tp.metro.Main;
import serb.tp.metro.blocks.LoadFurniture;
import serb.tp.metro.blocks.LoadTunnels;

public class CreativeTab extends CreativeTabs {
	
	private final String itemName;
	
	public CreativeTab(int nextID, String lable, String itemName) {
		super(nextID, lable);
		this.itemName = itemName;

	}
	
	@Override
	public Item getTabIconItem() {
		return GameRegistry.findItem(Main.modid, "item."+itemName);
	}
}
