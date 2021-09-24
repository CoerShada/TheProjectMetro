package serb.tp.metro.client.gui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

public class GuiAdtibuteHandler {
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
    public void onRenderOverlayPre(RenderGameOverlayEvent.Pre event) {
		
		if (event.type == ElementType.FOOD  || event.type == ElementType.HEALTH || event.type == ElementType.EXPERIENCE) {
			
			GL11.glPushMatrix();
			GL11.glScalef(0, 0, 0);
		}
		
		/*if(!Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode && event.type == ElementType.HOTBAR) {
			GL11.glPushMatrix();
			GL11.glScalef(0, 0, 0);
		}*/
	}
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
    public void onRenderOverlayPost(RenderGameOverlayEvent.Post event) {			
		
		if (event.type == ElementType.FOOD || event.type == ElementType.HEALTH || event.type == ElementType.EXPERIENCE ) {
			
			GL11.glPopMatrix();
		}
		
		/*if(!Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode && event.type == ElementType.HOTBAR) {
			GL11.glPopMatrix();
		}*/
		
		
		if (event.type == ElementType.TEXT) {
			
			AdtributeBarRenderer.getInstance().renderFoodIcon();
			AdtributeBarRenderer.getInstance().renderThirstyIcon();;
			AdtributeBarRenderer.getInstance().renderStaminaBar();
			AdtributeBarRenderer.getInstance().renderHPBar();
		}
		
	}
}
