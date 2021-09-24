package serb.tp.metro.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import serb.tp.metro.Main;
import serb.tp.metro.blocks.tiles.storages.creators.TileEntityCreatorLoom;
import serb.tp.metro.blocks.tiles.tunnels.TileEntityTunnelLine;
import serb.tp.metro.blocks.tiles.tunnels.TileEntityTunnelLineExitLeft;
import serb.tp.metro.blocks.tiles.tunnels.TileEntityTunnelLineExitRight;
import serb.tp.metro.client.render.blocks.RenderBlockModelled;
import serb.tp.metro.recipes.LoadRecipes;
import serb.tp.metro.recipes.Recipe;

public class LoadCreators {
	//public static final Block 
	//creator_loom = new BlockCreator(Material.iron, "creator_loom", TileEntityCreatorLoom.class, 3, 2, 2, new Recipe[]{LoadRecipes.clothRecipe, LoadRecipes.clothRecipe, LoadRecipes.clothRecipe, LoadRecipes.clothRecipe, LoadRecipes.clothRecipe, LoadRecipes.clothRecipe}, null, false, false) ;


	public static void renderBlocks() {
		//MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LoadTunnels.tunnel1line), new RenderBlockModelled(new ResourceLocation(Main.modid, "models/static/buildings/tunnels/tunnel1line.obj"), 0.5, -0.5, 0.5, 0.5));
		//MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LoadTunnels.tunnel1line_exit_left), new RenderBlockModelled(new ResourceLocation(Main.modid, "models/static/buildings/tunnels/TonnelTehPR.obj"), 0.5, -0.5, 0.5, 0.5));
		//MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LoadTunnels.tunnel1line_exit_right), new RenderBlockModelled(new ResourceLocation(Main.modid, "models/static/buildings/tunnels/TonnelTeh.obj"), 0.5, -0.5, 0.5, 0.5));
	}
}
