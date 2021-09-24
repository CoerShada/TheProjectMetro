package serb.tp.metro.entities.player.potion;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import serb.tp.metro.Main;

public class PotionBase extends Potion {
	
	/*
	 * Основа эффектов.
	 */
	
	private final ResourceLocation icon;

	public PotionBase(String potionName, int id, boolean isBadEffect, int liquidColor) {
		
		super(id, isBadEffect, liquidColor);
				
		this.icon = new ResourceLocation(Main.modid, "textures/potions/" + potionName + ".png");
		
		this.setName(potionName);
	}
	
	public void setName(String potionName) {
		
		this.setPotionName("effect." + Main.modid + ":" + potionName);
	}
	
	@Override
	public boolean hasStatusIcon() {
		
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void renderInventoryEffect(int x, int y, PotionEffect potionEffect, Minecraft mc) {
		
		if (mc.currentScreen != null) {
			
	        RenderHelper.disableStandardItemLighting();
	        
	        GL11.glDisable(GL11.GL_LIGHTING);
	        GL11.glDisable(GL11.GL_DEPTH_TEST);
			
			mc.getTextureManager().bindTexture(this.icon);
			
			Gui.func_146110_a(x + 6, y + 7, 0, 0, 18, 18, 18, 18);
			
	        GL11.glEnable(GL11.GL_DEPTH_TEST);
	        GL11.glEnable(GL11.GL_LIGHTING);
	                
	        RenderHelper.enableGUIStandardItemLighting();
		}
	}
}