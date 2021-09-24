package serb.tp.metro.common;

import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import serb.tp.metro.PotionsRegistry;
import serb.tp.metro.blocks.LoadBuildersBlocks;
import serb.tp.metro.blocks.LoadFurniture;
import serb.tp.metro.blocks.LoadFurnitureUnbreakable;
import serb.tp.metro.blocks.LoadTunnels;
import serb.tp.metro.common.handlers.CustomArmorTick;
import serb.tp.metro.common.handlers.DeathPlayerHandler;
import serb.tp.metro.common.handlers.PickupHandler;
import serb.tp.metro.common.handlers.equip.PlayerUpdateEquipBackpuck;
import serb.tp.metro.common.handlers.equip.PlayerUpdateEquipGun;
import serb.tp.metro.common.handlers.equip.PlayerUpdateEquipMask;
import serb.tp.metro.common.handlers.equip.PlayerUpdateEquipOuterwear;
import serb.tp.metro.common.handlers.equip.PlayerUpdateEquipPants;
import serb.tp.metro.creativetabs.LoadTabs;
import serb.tp.metro.database.BulletsReader;
import serb.tp.metro.database.ModulesReader;
import serb.tp.metro.database.WeaponsReader;
import serb.tp.metro.entities.player.PropertiesRegistry;
import serb.tp.metro.entities.player.handlers.RadiationHandler;
import serb.tp.metro.entities.player.handlers.StaminaHandler;
import serb.tp.metro.entities.player.handlers.ThirstyHandler;
import serb.tp.metro.entities.player.handlers.WeightHandler;

public class CommonProxy {
	
	private static int modGuiIndex = 0;
	public static final int GUI_CUSTOM_INV = modGuiIndex++,
			GUI_BACPACK= modGuiIndex++, 
			GUI_FACTIONS = modGuiIndex++,
			GUI_STORAGE = modGuiIndex++,
			GUI_STORAGE_SPAWNER = modGuiIndex++,
			GUI_CREATOR = modGuiIndex++;

	public void preInit() {
		loadTabs();
		loadArmors();
		loadBlocks();
		noPickup();
		atribute();
		//death();
		BulletsReader.LoadBullets();
		ModulesReader.LoadModules();
		WeaponsReader.LoadWeapons();
	}

	public void Init() {	
		playerHandler();
		playerUpdateEquip();

	}

	public void postInit() {    
	}

	public void loadTabs() {
		LoadTabs.registerTabs();
	}

	public void loadArmors() {
		//LoadItemArmor.registerItems();
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
	

	

	
}