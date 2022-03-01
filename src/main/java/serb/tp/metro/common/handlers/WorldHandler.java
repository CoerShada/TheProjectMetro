package serb.tp.metro.common.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import serb.tp.metro.DebugMessage;
import serb.tp.metro.common.clans.ClanHandler;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.server.GetServerCHMessage;

public class WorldHandler {
	
	private ClanHandler data;
	
	@SubscribeEvent
	@SideOnly(Side.SERVER)
    public void worldLoad(WorldEvent.Load event)
    {
		ClanHandler.get(event.world);

		
    }

    @SubscribeEvent
    @SideOnly(Side.SERVER)
    public void worldSave(WorldEvent.Save event)
    {
    	ClanHandler.get(event.world).markDirty();
    }
    
    
}
