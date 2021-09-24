package serb.tp.metro.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import serb.tp.metro.Main;
import serb.tp.metro.blocks.tiles.TileEntityBurrel;
import serb.tp.metro.blocks.tiles.TileEntityChair;
import serb.tp.metro.blocks.tiles.storages.TileEntityBoxLarge;
import serb.tp.metro.blocks.tiles.storages.TileEntityBoxMed;
import serb.tp.metro.blocks.tiles.storages.TileEntityBoxMedicalSmall;
import serb.tp.metro.blocks.tiles.storages.TileEntityBoxSmall;
import serb.tp.metro.blocks.tiles.storages.TileEntityRack;
import serb.tp.metro.blocks.tiles.storages.spawners.TileEntityStorageSpawner;
import serb.tp.metro.blocks.tiles.tunnels.TileEntityTunnelLine;
import serb.tp.metro.client.render.blocks.RenderBlockModelled;

public class LoadFurniture {

	public static final Block 
			chair1 = new BlockChair(Material.wood, "chair_1", TileEntityChair.class),
			burrel = new BlockModeled(Material.iron, "burrel", TileEntityBurrel.class),
			rack = new BlockStorage(Material.wood, "rack", 5, 3, 3, 3, 1, TileEntityRack.class),
			box_small = new BlockStorage(Material.wood, "box_small", 2, 2, 1, 1, 1, TileEntityBoxSmall.class),
			box_med = new BlockStorage(Material.wood, "box_med", 4, 2, 2, 1, 1, TileEntityBoxMed.class),
			box_large = new BlockStorage(Material.wood, "box_large", 6, 6, 2, 2, 2, TileEntityBoxLarge.class),
			box_medical_small = new BlockStorage(Material.wood, "box_medical_small", 3, 2, 1, 1, 1, TileEntityBoxMedicalSmall.class);
	
	public static void renderBlocks() {
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LoadFurniture.chair1), new RenderBlockModelled(new ResourceLocation(Main.modid, "models/static/furniture/chairs/chair1.obj"), 0.025, -0.5, 0.5, 0.5));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LoadFurniture.burrel), new RenderBlockModelled(new ResourceLocation(Main.modid, "models/static/furniture/other/burrel.obj"), 0.35, -0.5, 0.5, 0.5));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LoadFurniture.rack), new RenderBlockModelled(new ResourceLocation(Main.modid, "models/static/furniture/storages/rack.obj"), 0.35, -0.5, 0.5, 0.5));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LoadFurniture.box_medical_small), new RenderBlockModelled(new ResourceLocation(Main.modid, "models/static/furniture/storages/box_medical_small.obj"), 0.5, -0.5, 0.5, 0.5));
	}
	
			
}
