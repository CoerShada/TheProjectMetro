package serb.tp.metro.client.gui.menu;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraftforge.client.event.GuiOpenEvent;

public class ModifiedMenuHandler{

	@SubscribeEvent
    public void onGuiOpenEvent(GuiOpenEvent event)
    {
    	
        if(event.gui instanceof GuiIngameMenu && !(event.gui instanceof ModifiedIngameMenu))
        {
        	System.out.println("Меню открыто");
        	event.gui = new ModifiedIngameMenu();
        }
    }
    

}