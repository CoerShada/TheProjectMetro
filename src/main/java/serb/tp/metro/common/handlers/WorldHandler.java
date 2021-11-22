package serb.tp.metro.common.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import serb.tp.metro.DebugMessage;

public class WorldHandler {
	
	private ClanHandler data;
	
	@SubscribeEvent
    public void worldLoad(WorldEvent.Load event)
    {
		ClanHandler.get(event.world);
    }

    @SubscribeEvent
    public void worldSave(WorldEvent.Save event)
    {
    	ClanHandler.get(event.world).markDirty();
    }
}
