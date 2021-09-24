package serb.tp.metro.common.handlers;

import java.util.Date;

import org.lwjgl.input.Mouse;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import serb.tp.metro.client.ClientProxy;
import serb.tp.metro.items.guns.ItemGun;

public class MouseEvents {
	boolean canShoot = true;
	long canDo;
	int time=0;
	Minecraft mc;
	
	public MouseEvents(Minecraft mc) {
		this.mc = mc;
	}

	
	@SubscribeEvent
	public void mouseEvents(PlayerTickEvent e) 
	{
		if(Mouse.isButtonDown(1)) {
			ClientProxy.isAiming = true;
		}
		else
			ClientProxy.isAiming = false;
				
		if (e.phase==Phase.END)
			return;
		
		if (time==e.player.ticksExisted){
			return;
		}
		ItemStack itemStack;

		
	    if(Mouse.isButtonDown(0))
	    {

	    	itemStack = e.player.inventory.getCurrentItem();
	    	if (itemStack==null || !(itemStack.getItem() instanceof ItemGun)) return;
	    	
	    	if (new Date().getTime()>canDo+10 && itemStack!=null && itemStack.hasTagCompound() && itemStack.getItem() instanceof ItemGun && !itemStack.getTagCompound().getBoolean("safetyMod") &&canShoot &&!itemStack.getTagCompound().getBoolean("fireMod")) 
	    	{
	    		
	    		if(mc.theWorld.isRemote) {

	    			((ItemGun) itemStack.getItem()).shoot(itemStack, e.player, e.player.getEntityWorld());
	    		}
	    		canShoot = false;
	    	}
	    	else if (new Date().getTime()>canDo+10 && itemStack!=null && itemStack.hasTagCompound() && itemStack.getItem() instanceof ItemGun && !itemStack.getTagCompound().getBoolean("safetyMod") && itemStack.getTagCompound().getBoolean("fireMod"))
	    	{
	    		if(mc.theWorld.isRemote) {

	    			((ItemGun) itemStack.getItem()).shoot(itemStack, e.player, e.player.getEntityWorld());
	    		}
	    		canDo = new Date().getTime();
	    	}
	    }
	    else 
	    {
	    	canShoot = true;
	    }
	    
	}
}
