package serb.tp.metro;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import serb.tp.metro.common.CommonProxy;
import serb.tp.metro.handlers.GuiHandler;
import serb.tp.metro.network.PacketDispatcher;



@Mod(modid = Main.modid, name = Main.name, version = Main.version)

public class Main {
	public static final String name = "The project Metro";
	public static final String modid = "tp";
	public static final String version = "Pre Alpha Build 1.9.8";
	public static final boolean debug = false;
	
	@Mod.Instance(Main.modid)
	public static Main mod;

	@SidedProxy(clientSide = "serb.tp.metro.client.ClientProxy",
				serverSide = "serb.tp.metro.common.CommonProxy")
	public static CommonProxy proxy;

	


	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {		
		proxy.preInit();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		PacketDispatcher.registerPackets();
	}

	@EventHandler
	public void Init(FMLInitializationEvent event) {
		proxy.Init();
		System.out.println(Main.name + " has been successfully initialized");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit();
	}

}