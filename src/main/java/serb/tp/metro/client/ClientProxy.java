package serb.tp.metro.client;

import java.util.HashMap;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.common.MinecraftForge;
import serb.tp.metro.KeybindingRegistry;
import serb.tp.metro.Main;
import serb.tp.metro.blocks.LoadFurniture;
import serb.tp.metro.blocks.LoadFurnitureUnbreakable;
import serb.tp.metro.blocks.LoadTunnels;
import serb.tp.metro.blocks.tiles.TileEntityBurrel;
import serb.tp.metro.blocks.tiles.TileEntityChair;
import serb.tp.metro.blocks.tiles.storages.TileEntityBoxMedicalSmall;
import serb.tp.metro.blocks.tiles.storages.TileEntityRack;
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
import serb.tp.metro.client.gui.GuiAdtibuteHandler;
import serb.tp.metro.client.gui.menu.ModifiedMenuHandler;
import serb.tp.metro.client.handlers.KeyBindingHandler;
import serb.tp.metro.client.handlers.OpenInventory;
import serb.tp.metro.client.handlers.PlayerRenderHandler;
import serb.tp.metro.client.render.entity.player.CustomRenderPlayer;
import serb.tp.metro.client.render.items.RenderItem;
import serb.tp.metro.client.render.loaders.obj.OBJLoader;
import serb.tp.metro.client.render.tiles.RenderTileBoxSmall;
import serb.tp.metro.client.render.tiles.RenderTileBurrel;
import serb.tp.metro.client.render.tiles.RenderTileChair1;
import serb.tp.metro.client.render.tiles.RenderTileRack;
import serb.tp.metro.client.render.tiles.RenderTileSpawnerAmmoBox1;
import serb.tp.metro.client.render.tiles.RenderTileSpawnerBoxStandart1;
import serb.tp.metro.client.render.tiles.RenderTileSpawnerBoxStandart2;
import serb.tp.metro.client.render.tiles.RenderTileSpawnerBoxStandart3;
import serb.tp.metro.client.render.tiles.RenderTileSpawnerBoxStandart4;
import serb.tp.metro.client.render.tiles.RenderTileSpawnerGunBoxMed1;
import serb.tp.metro.client.render.tiles.RenderTileSpawnerGunSafeVert1;
import serb.tp.metro.client.render.tiles.RenderTileSpawnerGunSafeVert2;
import serb.tp.metro.client.render.tiles.RenderTileSpawnerGunSafeVert3;
import serb.tp.metro.client.render.tiles.RenderTileSpawnerGunSafeVert4;
import serb.tp.metro.client.render.tiles.RenderTileSpawnerGunSafeVert5;
import serb.tp.metro.common.CommonProxy;
import serb.tp.metro.database.BulletsReader;
import serb.tp.metro.database.ModulesReader;
import serb.tp.metro.items.armor.LoadItemArmor;

public class ClientProxy extends CommonProxy {
	
	public static boolean isAiming = false;
	private GameSettings gs;
	public static OBJLoader loader = new OBJLoader();

	private static final HashMap<String, Integer> hash = new HashMap<String, Integer>();

	@Override
	public void preInit() {
		super.preInit();  
		Display.setTitle(Main.name);
		
	}

	@Override
	public void Init() {
		super.Init();
		
		renderIcons();
		renderEquip();
		renderBlocks();

		openInventory();
		renderPlayer();
		KeybindingRegistry.register();
		renderBar();
		BulletsReader.setRender();
		ModulesReader.setRender();
		FMLCommonHandler.instance().bus().register(new KeyBindingHandler(Minecraft.getMinecraft()));
	}

	@Override
	public void postInit() {
		super.postInit();
		renderOBJModels();
	}

	
	public void renderEquip() {	
		MinecraftForge.EVENT_BUS.register(new PlayerRenderHandler());
	}
	
	public void renderPlayer() {
		MinecraftForge.EVENT_BUS.register(new CustomRenderPlayer());
	}
	

	private void openInventory() {
		MinecraftForge.EVENT_BUS.register(new OpenInventory());		
	}

	@Override
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		return (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(ctx));
	}

	public void renderBlocks() {
		LoadFurnitureUnbreakable.renderBlocks();
		LoadTunnels.renderBlocks();
		LoadFurniture.renderBlocks();

	}
	
	
	public void renderBar() {
		MinecraftForge.EVENT_BUS.register(new GuiAdtibuteHandler());	
	}
	
	public void guiReplace() {
		MinecraftForge.EVENT_BUS.register(new ModifiedMenuHandler());
	}
	
	public void renderIcons() {	
		
		
		//Шлема
		MinecraftForgeClient.registerItemRenderer(LoadItemArmor.helmet_6b47, new RenderItem.renderItemHelmet());
		MinecraftForgeClient.registerItemRenderer(LoadItemArmor.helmet_altyn, new RenderItem.renderItemHelmet());
		//Бронежилеты
		MinecraftForgeClient.registerItemRenderer(LoadItemArmor.armor_redut, new RenderItem.renderItemBulletproofVest());
		//Противогазы
		MinecraftForgeClient.registerItemRenderer(LoadItemArmor.mask_screen, new RenderItem.renderItemMask());
		//Рюкзаки
		MinecraftForgeClient.registerItemRenderer(LoadItemArmor.backpack_gekkon, new RenderItem.renderItemBackpack());
		//Разгрузки
		
		//Куртки
		
		//Штаны
		
		
		
	}
	
	public void renderOBJModels() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChair.class, new RenderTileChair1(new ResourceLocation(Main.modid, "models/static/furniture/chairs/chair1.obj")));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBurrel.class, new RenderTileBurrel(new ResourceLocation(Main.modid, "models/static/furniture/other/burrel.obj")));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRack.class, new RenderTileRack(new ResourceLocation(Main.modid, "models/static/furniture/storages/rack.obj")));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBoxMedicalSmall.class, new RenderTileBoxSmall(new ResourceLocation(Main.modid, "models/static/furniture/storages/box_medical_small.obj")));

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySpawnerBoxStandart1.class, new RenderTileSpawnerBoxStandart1(new ResourceLocation(Main.modid, "models/static/furniture/storages/spawners/spawner_box_standart_1.obj")));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySpawnerBoxStandart2.class, new RenderTileSpawnerBoxStandart2(new ResourceLocation(Main.modid, "models/static/furniture/storages/spawners/spawner_box_standart_1.obj")));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySpawnerBoxStandart3.class, new RenderTileSpawnerBoxStandart3(new ResourceLocation(Main.modid, "models/static/furniture/storages/spawners/spawner_box_standart_1.obj")));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySpawnerBoxStandart4.class, new RenderTileSpawnerBoxStandart4(new ResourceLocation(Main.modid, "models/static/furniture/storages/spawners/spawner_box_standart_1.obj")));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySpawnerGunSafeVert1.class, new RenderTileSpawnerGunSafeVert1(new ResourceLocation(Main.modid, "models/static/furniture/storages/spawners/spawner_gun_safe_vert_1.obj")));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySpawnerGunSafeVert2.class, new RenderTileSpawnerGunSafeVert2(new ResourceLocation(Main.modid, "models/static/furniture/storages/spawners/spawner_gun_safe_vert_1.obj")));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySpawnerGunSafeVert3.class, new RenderTileSpawnerGunSafeVert3(new ResourceLocation(Main.modid, "models/static/furniture/storages/spawners/spawner_gun_safe_vert_1.obj")));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySpawnerGunSafeVert4.class, new RenderTileSpawnerGunSafeVert4(new ResourceLocation(Main.modid, "models/static/furniture/storages/spawners/spawner_gun_safe_vert_1.obj")));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySpawnerGunSafeVert5.class, new RenderTileSpawnerGunSafeVert5(new ResourceLocation(Main.modid, "models/static/furniture/storages/spawners/spawner_gun_safe_vert_1.obj")));

		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySpawnerAmmoBox1.class, new RenderTileSpawnerAmmoBox1(new ResourceLocation(Main.modid, "models/static/furniture/storages/spawners/spawner_ammo_box_1.obj")));

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySpawnerGunBoxMed1.class, new RenderTileSpawnerGunBoxMed1(new ResourceLocation(Main.modid, "models/static/furniture/storages/spawners/spawner_gun_box_med_standart_1.obj")));
		
		
	}
		

	/*public static int getRenderPart(String model, String partName) {
		if(hash.containsKey(model + "_" + partName)) 
			return hash.get(model + "_" + partName);

		int displayList = GLAllocation.generateDisplayLists(1);
		GL11.glNewList(displayList, GL11.GL_COMPILE);
		AdvancedModelLoaderModify.loadModel(new ResourceLocation(Main.modid, "models/" + model + ".obj")).renderPart(partName);
		GL11.glEndList();
		hash.put(model + "_" + partName, displayList);
		return displayList;
	}*/
	
	public static int getRenderAll(String model) {
		if (hash.containsKey(model)) return hash.get(model);
		int displayList = GLAllocation.generateDisplayLists(1);
		GL11.glNewList(displayList, GL11.GL_COMPILE);
		//OBJModel modelOBJ = OBJLoader.loadOBJModel("models/" + model + ".obj", loader);
		//render.render(modelOBJ);
		//OBJLoader loader = new OBJLoader();
		//loader.render(loader.loadModel(new ResourceLocation(Main.modid, "models/" + model + ".obj")));
		//loader.createDisplayList(loader.loadModel(new ResourceLocation(Main.modid, "models/" + model + ".obj")));
		AdvancedModelLoader.loadModel(new ResourceLocation(Main.modid, "models/" + model + ".obj")).renderAll();
		GL11.glEndList();
		hash.put(model, displayList);
		return displayList;
	}
	
	public static int getRenderAll(ResourceLocation model) {
		if (hash.containsKey(model.toString())) return hash.get(model.toString());
		int displayList = GLAllocation.generateDisplayLists(1);
		GL11.glNewList(displayList, GL11.GL_COMPILE);
		//OBJModel modelOBJ = OBJLoader.loadOBJModel("models/" + model + ".obj", loader);
		//render.render(modelOBJ);
		//OBJLoader loader = new OBJLoader();
		//loader.render(loader.loadModel(new ResourceLocation(Main.modid, "models/" + model + ".obj")));
		//loader.createDisplayList(loader.loadModel(new ResourceLocation(Main.modid, "models/" + model + ".obj")));
		AdvancedModelLoader.loadModel(model).renderAll();
		GL11.glEndList();
		hash.put(model.toString(), displayList);
		return displayList;
	}
	
	public static int getRenderAllTest(String model) {
		if (hash.containsKey(model)) return hash.get(model);
		int displayList = GLAllocation.generateDisplayLists(1);
		GL11.glNewList(displayList, GL11.GL_COMPILE);
		//OBJModel modelOBJ = OBJLoader.loadOBJModel("models/" + model + ".obj", loader);
		//render.render(modelOBJ);
		//OBJModel modelOBJ = new OBJModel(new PApplet(), new ResourceLocation(Main.modid, "models/" + model + ".obj"));
		//modelOBJ.draw();
		//loader.createDisplayList(loader.loadModel(new ResourceLocation(Main.modid, "models/" + model + ".obj")));
		//AdvancedModelLoader.loadModel(new ResourceLocation(Main.modid, "models/" + model + ".obj")).renderAll();
		GL11.glEndList();
		hash.put(model, displayList);
		return displayList;
	}
	
	public void initRender() {
		/*RenderEngine engine = RenderEngine.init();

		Scene scene = SceneLoader.loadScene(GeneralSettings.RES_FOLDER);

		while (!Display.isCloseRequested()) {
			scene.getCamera().move();
			scene.getAnimatedModel().update();
			engine.renderScene(scene);
			engine.update();
		}

		engine.close();*/
	}
	

}