package serb.tp.metro.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import serb.tp.metro.Main;
import serb.tp.metro.blocks.tiles.storages.spawners.TileEntitySpawnerAmmoBox1;
import serb.tp.metro.blocks.tiles.storages.spawners.TileEntitySpawnerBoxStandart1;
import serb.tp.metro.blocks.tiles.storages.spawners.TileEntitySpawnerBoxStandart2;
import serb.tp.metro.blocks.tiles.storages.spawners.TileEntitySpawnerBoxStandart3;
import serb.tp.metro.blocks.tiles.storages.spawners.TileEntitySpawnerBoxStandart4;
import serb.tp.metro.blocks.tiles.storages.spawners.TileEntitySpawnerGunBoxMed1;
import serb.tp.metro.blocks.tiles.storages.spawners.TileEntitySpawnerGunSafeVert1;
import serb.tp.metro.blocks.tiles.storages.spawners.TileEntitySpawnerGunSafeVert2;
import serb.tp.metro.blocks.tiles.storages.spawners.TileEntitySpawnerGunSafeVert3;
import serb.tp.metro.blocks.tiles.storages.spawners.TileEntitySpawnerGunSafeVert4;
import serb.tp.metro.blocks.tiles.storages.spawners.TileEntitySpawnerGunSafeVert5;
import serb.tp.metro.client.render.blocks.RenderBlockModelled;

public class LoadFurnitureUnbreakable {

	public static final Block 
			spawner_box_standart_1 = new BlockStorageSpawner(Material.wood, "spawner_box_standart_1", 2, 2, 1, 1, 1, TileEntitySpawnerBoxStandart1.class),
			spawner_box_standart_2 = new BlockStorageSpawner(Material.wood, "spawner_box_standart_2", 2, 2, 1, 1, 1, TileEntitySpawnerBoxStandart2.class),
			spawner_box_standart_3 = new BlockStorageSpawner(Material.wood, "spawner_box_standart_3", 2, 2, 1, 1, 1, TileEntitySpawnerBoxStandart3.class),
			spawner_box_standart_4 = new BlockStorageSpawner(Material.wood, "spawner_box_standart_4", 2, 2, 1, 1, 1, TileEntitySpawnerBoxStandart4.class),
			spawner_gun_safe_vert_1 = new BlockStorageSpawner(Material.iron, "spawner_gun_safe_vert_1", 2, 3, 1, 3, 1, TileEntitySpawnerGunSafeVert1.class), 
			spawner_gun_safe_vert_2 = new BlockStorageSpawner(Material.iron, "spawner_gun_safe_vert_2", 2, 3, 1, 3, 1, TileEntitySpawnerGunSafeVert2.class),
			spawner_gun_safe_vert_3 = new BlockStorageSpawner(Material.iron, "spawner_gun_safe_vert_3", 2, 3, 1, 3, 1, TileEntitySpawnerGunSafeVert3.class),
			spawner_gun_safe_vert_4 = new BlockStorageSpawner(Material.iron, "spawner_gun_safe_vert_4", 2, 3, 1, 3, 1, TileEntitySpawnerGunSafeVert4.class),
			spawner_gun_safe_vert_5 = new BlockStorageSpawner(Material.iron, "spawner_gun_safe_vert_5", 2, 3, 1, 3, 1, TileEntitySpawnerGunSafeVert5.class),
			spawner_ammo_box_1 = new BlockStorageSpawner(Material.wood, "spawner_ammo_box_1", 3, 3, 1, 1, 1, TileEntitySpawnerAmmoBox1.class),
			spawner_gun_box_med_1 = new BlockStorageSpawner(Material.wood, "spawner_gun_box_med_1", 3, 2, 2, 1, 1, TileEntitySpawnerGunBoxMed1.class);
	
	public static void renderBlocks() {
		
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LoadFurnitureUnbreakable.spawner_box_standart_1), new RenderBlockModelled(new ResourceLocation(Main.modid, "models/static/furniture/storages/spawners/spawner_box_standart_1.obj"), 0.4, -0.5, 0.5, 0.5));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LoadFurnitureUnbreakable.spawner_box_standart_2), new RenderBlockModelled(new ResourceLocation(Main.modid, "models/static/furniture/storages/spawners/spawner_box_standart_1.obj"), 0.4, -0.5, 0.5, 0.5));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LoadFurnitureUnbreakable.spawner_box_standart_3), new RenderBlockModelled(new ResourceLocation(Main.modid, "models/static/furniture/storages/spawners/spawner_box_standart_1.obj"), 0.4, -0.5, 0.5, 0.5));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LoadFurnitureUnbreakable.spawner_box_standart_4), new RenderBlockModelled(new ResourceLocation(Main.modid, "models/static/furniture/storages/spawners/spawner_box_standart_1.obj"), 0.4, -0.5, 0.5, 0.5));
	
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LoadFurnitureUnbreakable.spawner_gun_safe_vert_1), new RenderBlockModelled(new ResourceLocation(Main.modid, "models/static/furniture/storages/spawners/spawner_gun_safe_vert_1.obj"), 0.2, -0.5, 0.5, 0.5));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LoadFurnitureUnbreakable.spawner_gun_safe_vert_2), new RenderBlockModelled(new ResourceLocation(Main.modid, "models/static/furniture/storages/spawners/spawner_gun_safe_vert_1.obj"), 0.2, -0.5, 0.5, 0.5));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LoadFurnitureUnbreakable.spawner_gun_safe_vert_3), new RenderBlockModelled(new ResourceLocation(Main.modid, "models/static/furniture/storages/spawners/spawner_gun_safe_vert_1.obj"), 0.2, -0.5, 0.5, 0.5));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LoadFurnitureUnbreakable.spawner_gun_safe_vert_4), new RenderBlockModelled(new ResourceLocation(Main.modid, "models/static/furniture/storages/spawners/spawner_gun_safe_vert_1.obj"), 0.2, -0.5, 0.5, 0.5));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LoadFurnitureUnbreakable.spawner_gun_safe_vert_5), new RenderBlockModelled(new ResourceLocation(Main.modid, "models/static/furniture/storages/spawners/spawner_gun_safe_vert_1.obj"), 0.2, -0.5, 0.5, 0.5));

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LoadFurnitureUnbreakable.spawner_ammo_box_1), new RenderBlockModelled(new ResourceLocation(Main.modid, "models/static/furniture/storages/spawners/spawner_ammo_box_1.obj"), 0.5, -0.5, 0.5, 0.5));
		
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LoadFurnitureUnbreakable.spawner_gun_box_med_1), new RenderBlockModelled(new ResourceLocation(Main.modid, "models/static/furniture/storages/spawners/spawner_gun_box_med_standart_1.obj"), 0.2, -0.5, 0.5, 0.5));
	}
		
}