package serb.tp.metro.creativetabs;

import net.minecraft.creativetab.CreativeTabs;

public class LoadTabs {

	public static CreativeTabs 	tabRPGi, 
								buildersBlocks,
								furnitureBlocks,
								tunnelsBlocks, 
								furnitureUnbreakebleBlocks,
								bullets,
								weapons,
								modules,
								blocksInvisible;

	public static void registerTabs() {
		//tabRPGi = new TabRPGi(CreativeTabs.getNextID(), "tabRPGi");
		buildersBlocks = new BuildersBlocks(CreativeTabs.getNextID(), "buildersBlocks");
		furnitureUnbreakebleBlocks = new BlocksFurnitureUndreakeble(CreativeTabs.getNextID(), "furnitureUnbreakebleBlocks");
		tunnelsBlocks = new TunnelsBlocks(CreativeTabs.getNextID(), "tunnelsBlocks");
		furnitureBlocks = new BlocksFurniture(CreativeTabs.getNextID(), "furnitureBlocks");
		blocksInvisible = new BlocksInvisible(CreativeTabs.getNextID(), "blocksInvisible");
		bullets = new CreativeTab(CreativeTabs.getNextID(), "bullets", "bullet_shell_factory_9x18mm");
		weapons = new CreativeTab(CreativeTabs.getNextID(), "weapons", "item_firearm_mag_weapon_ppsh");
		modules = new CreativeTab(CreativeTabs.getNextID(), "modules", "item_mag_ppsh_35");
		
	}

}