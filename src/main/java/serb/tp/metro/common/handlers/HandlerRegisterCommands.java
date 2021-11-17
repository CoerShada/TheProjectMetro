package serb.tp.metro.common.handlers;

import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import serb.tp.metro.common.commands.CommandSyncDatabase;

public class HandlerRegisterCommands {

	@SubscribeEvent
    public void serverStarting(FMLServerStartingEvent event) {
    	
    	event.registerServerCommand(new CommandSyncDatabase());//Регистрация команд	

    }
}
