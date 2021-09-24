package serb.tp.metro.creativetabs;

import net.minecraft.creativetab.CreativeTabs;

public class LoadTabs {

	public static CreativeTabs 	tabRPGi, 
								buildersBlocks,
								furnitureBlocks,
								tunnelsBlocks, 
								furnitureUnbreakebleBlocks,
								itemsComponents,
								blocksInvisible;

	public static void registerTabs() {
		//tabRPGi = new TabRPGi(CreativeTabs.getNextID(), "tabRPGi");
		buildersBlocks = new BuildersBlocks(CreativeTabs.getNextID(), "buildersBlocks");
		furnitureUnbreakebleBlocks = new BlocksFurnitureUndreakeble(CreativeTabs.getNextID(), "furnitureUnbreakebleBlocks");
		tunnelsBlocks = new TunnelsBlocks(CreativeTabs.getNextID(), "tunnelsBlocks");
		furnitureBlocks = new BlocksFurniture(CreativeTabs.getNextID(), "furnitureBlocks");
		blocksInvisible = new BlocksInvisible(CreativeTabs.getNextID(), "blocksInvisible");
		//itemsComponents = new BuildersBlocks(CreativeTabs.getNextID(), "itemsComponents");
		
		
	}

}