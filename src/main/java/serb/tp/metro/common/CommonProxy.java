package serb.tp.metro.common;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import serb.tp.metro.PotionsRegistry;
import serb.tp.metro.blocks.LoadBuildersBlocks;
import serb.tp.metro.blocks.LoadFurniture;
import serb.tp.metro.blocks.LoadFurnitureUnbreakable;
import serb.tp.metro.blocks.LoadTunnels;
import serb.tp.metro.common.commands.CommandCreateFrame;
import serb.tp.metro.common.commands.CommandSyncDatabase;
import serb.tp.metro.common.handlers.CustomArmorTick;
import serb.tp.metro.common.handlers.DeathPlayerHandler;
import serb.tp.metro.common.handlers.PickupHandler;
import serb.tp.metro.common.handlers.WeaponSystemHandler;
import serb.tp.metro.common.handlers.WorldHandler;
import serb.tp.metro.common.handlers.equip.PlayerUpdateEquipBackpuck;
import serb.tp.metro.common.handlers.equip.PlayerUpdateEquipGun;
import serb.tp.metro.common.handlers.equip.PlayerUpdateEquipMask;
import serb.tp.metro.common.handlers.equip.PlayerUpdateEquipOuterwear;
import serb.tp.metro.common.handlers.equip.PlayerUpdateEquipPants;
import serb.tp.metro.common.ieep.BuildingSystem;
import serb.tp.metro.common.ieep.ExtendedPlayer;
import serb.tp.metro.common.ieep.WeaponSystem;
import serb.tp.metro.creativetabs.LoadTabs;
import serb.tp.metro.database.BulletsReader;
import serb.tp.metro.database.CustomizationSlotsReader;
import serb.tp.metro.database.ModulesReader;
import serb.tp.metro.database.WeaponsReader;
import serb.tp.metro.entities.player.PropertiesRegistry;
import serb.tp.metro.entities.player.handlers.RadiationHandler;
import serb.tp.metro.entities.player.handlers.StaminaHandler;
import serb.tp.metro.entities.player.handlers.ThirstyHandler;
import serb.tp.metro.entities.player.handlers.WeightHandler;
import serb.tp.metro.items.ItemSelector;

public class CommonProxy {
	
	private static int modGuiIndex = 0;
	public WeaponSystem ws;
	public BuildingSystem bs;

	public ExtendedPlayer clanIEEP;
	public static final int GUI_CUSTOM_INV = modGuiIndex++,
			GUI_BACPACK= modGuiIndex++, 
			GUI_CLAN = modGuiIndex++,
			GUI_STORAGE = modGuiIndex++,
			GUI_STORAGE_SPAWNER = modGuiIndex++,
			GUI_CREATOR = modGuiIndex++,
			GUI_MODIFY = modGuiIndex++;

	public void preInit() {
		loadTabs();
		loadArmors();
		loadBlocks();
		noPickup();
		atribute();
		ws = new WeaponSystem();
		bs = new BuildingSystem();
		clanIEEP = new ExtendedPlayer();
		//death();
		loadOrUpdateContent();
	}

	public void Init() {	
		playerHandler();
		playerUpdateEquip();
		worldHandlers();
		WeaponSystemHandler wsh = new WeaponSystemHandler();
		MinecraftForge.EVENT_BUS.register(wsh);
		FMLCommonHandler.instance().bus().register(wsh);
		//MinecraftForge.EVENT_BUS.register(new HandlerRegisterCommands());
		
	}

	public void postInit() {

	}

	public void loadTabs() {
		LoadTabs.registerTabs();
	}

	public void loadArmors() {
		//LoadItemArmor.registerItems();
	}

	public void loadOrUpdateContent() {
		BulletsReader.LoadBullets();
		ModulesReader.LoadModules();
		WeaponsReader.LoadWeapons();
		CustomizationSlotsReader.LoadCustomizationSlots();
		new ItemSelector("selector");
	}
	
	
	public void loadBlocks() {
		
		new LoadBuildersBlocks();
		new LoadFurniture();
		new LoadTunnels();
		new LoadFurnitureUnbreakable();
		//new LoadPlayersBlocks();
		//new LoadCreators();
		
	}

	public void playerHandler() {
		MinecraftForge.EVENT_BUS.register(new CustomArmorTick());
		//MinecraftForge.EVENT_BUS.register(new DeathPlayerHandler());

	}
	
	public void worldHandlers() {
		WorldHandler wh = new WorldHandler();
		MinecraftForge.EVENT_BUS.register(wh);
		
	}

	public void playerUpdateEquip() {
		MinecraftForge.EVENT_BUS.register(new PlayerUpdateEquipMask());
		MinecraftForge.EVENT_BUS.register(new PlayerUpdateEquipOuterwear());
		MinecraftForge.EVENT_BUS.register(new PlayerUpdateEquipPants());
		MinecraftForge.EVENT_BUS.register(new PlayerUpdateEquipBackpuck());
		MinecraftForge.EVENT_BUS.register(new PlayerUpdateEquipGun());
	}

	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		return ctx.getServerHandler().playerEntity;
	}
	
	public void noPickup() {
		MinecraftForge.EVENT_BUS.register(new PickupHandler());	
	}
	
	public void atribute() {
    	PotionsRegistry.register();
    	MinecraftForge.EVENT_BUS.register(new PropertiesRegistry());
    	MinecraftForge.EVENT_BUS.register(new ThirstyHandler());
    	MinecraftForge.EVENT_BUS.register(new RadiationHandler());
    	MinecraftForge.EVENT_BUS.register(new WeightHandler());
    	MinecraftForge.EVENT_BUS.register(new StaminaHandler());
	}
	
	public void death() {
		MinecraftForge.EVENT_BUS.register(new DeathPlayerHandler());
	}
	

	
    public void serverStarting(FMLServerStartingEvent event) {
    	
    	event.registerServerCommand(new CommandSyncDatabase());//Регистрация команд	
    	event.registerServerCommand(new CommandCreateFrame());
    }

	
}