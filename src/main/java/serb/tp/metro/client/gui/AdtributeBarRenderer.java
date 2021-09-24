package serb.tp.metro.client.gui;

import java.awt.Color;
import java.util.Date;

import javax.sound.sampled.AudioFileFormat.Type;

import org.lwjgl.opengl.GL11;

import com.ibm.icu.impl.duration.impl.Utils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import serb.tp.metro.Main;
import serb.tp.metro.entities.player.handlers.StaminaHandler;
import serb.tp.metro.entities.player.handlers.ThirstyHandler;

@SideOnly(Side.CLIENT)
public class AdtributeBarRenderer {

	private Minecraft mc = Minecraft.getMinecraft();
	
	private static final AdtributeBarRenderer INSTANCE = new AdtributeBarRenderer();
	private static final ResourceLocation THIRST_ICON = new ResourceLocation(Main.modid, "textures/gui/thirst.png");	
	private static final ResourceLocation FOOD_ICON = new ResourceLocation(Main.modid, "textures/gui/food.png");
	private static final ResourceLocation STAMINA_BAR = new ResourceLocation(Main.modid, "textures/gui/stamina_bar.png");
	private static final ResourceLocation HP_BAR = new ResourceLocation(Main.modid, "textures/gui/hp_bar.png");

	private AdtributeBarRenderer() {}
	
	public static AdtributeBarRenderer getInstance() {
		
		return INSTANCE;
	}
	
	public void renderThirstyIcon() {
		        	
        EntityPlayer player = (EntityPlayer) this.mc.thePlayer;

		if (!player.capabilities.isCreativeMode && !(this.mc.currentScreen instanceof GuiGameOver)) {
		        			        
	        ScaledResolution resolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
	        
	        int 
	        i = resolution.getScaledWidth() - 30,
	        j = resolution.getScaledHeight() - 50;
	        		
	        GL11.glEnable(GL11.GL_BLEND);
	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        	     
	        if((int)ThirstyHandler.getThirsty(player)<=6) 
	        {
	        	this.mc.getTextureManager().bindTexture(this.THIRST_ICON); 
	        	Gui.func_146110_a(i, j-40, 0, 0, 20, 20, 20, 20);	
	        }
	        GL11.glDisable(GL11.GL_BLEND);
		} 
	}
	
	public void renderFoodIcon() {
        EntityPlayer player = (EntityPlayer) this.mc.thePlayer;

		if (!player.capabilities.isCreativeMode && !(this.mc.currentScreen instanceof GuiGameOver)) {
		        			        
	        ScaledResolution resolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
	        
	        int 
	        i = resolution.getScaledWidth() - 30,
	        j = resolution.getScaledHeight() - 50;
	        		
	        GL11.glEnable(GL11.GL_BLEND);
	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        
	        if((int)player.getFoodStats().getFoodLevel()<=6) {
	        	this.mc.getTextureManager().bindTexture(this.FOOD_ICON); 
	        	Gui.func_146110_a(i, j-70, 0, 0, 20, 20, 20, 20);	
	        }
	        	     
	        GL11.glDisable(GL11.GL_BLEND);
		} 
	}
	
	public void renderStaminaBar() {
    	
        EntityPlayer player = (EntityPlayer) this.mc.thePlayer;

		if (!player.capabilities.isCreativeMode && !(this.mc.currentScreen instanceof GuiGameOver)) {
		        			        
	        ScaledResolution resolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
	        
	        int 
	        i = resolution.getScaledWidth()-126,
	        j = resolution.getScaledHeight()-22;
	        		
	        GL11.glEnable(GL11.GL_BLEND);
	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        
	        
	        this.mc.getTextureManager().bindTexture(this.STAMINA_BAR); 
	        Gui.func_146110_a(i, j, 0, 0, 116, 4, 116, 116);	
	        Gui.func_146110_a(i, j, 0, 5, (int) (116*StaminaHandler.getStamina(player)*0.01), 4, 116, 116);	
	        //Gui.func_146110_a(i-90, j-62, 0, 18, 116, 8, 116, 116);
	        
	     
	        GL11.glDisable(GL11.GL_BLEND);
		} 
	}
	
	public void renderHPBar() {
        EntityPlayer player = (EntityPlayer) this.mc.thePlayer;

		if (!player.capabilities.isCreativeMode && !(this.mc.currentScreen instanceof GuiGameOver)) {
		        			        
	        ScaledResolution resolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
	        
	        int 
	        i = resolution.getScaledWidth()-126,
	        j = resolution.getScaledHeight()-30;
	        		
	        GL11.glEnable(GL11.GL_BLEND);
	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        
	        this.mc.getTextureManager().bindTexture(this.HP_BAR); 
	        Gui.func_146110_a(i, j, 0, 0, 116, 8, 116, 116);	
	        Gui.func_146110_a(i, j, 0, 8, (int) (116*player.getHealth()*0.05), 8, 116, 116);
	        Gui.func_146110_a(i, j, 0, 16, 116, 8, 116, 116);	
	     
	        GL11.glDisable(GL11.GL_BLEND);
		}
	}
	
	public void renderHotbar() {
		
		/*ScaledResolution resolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
		
		GL11.glPushMatrix();
		GL11.glTranslatef((float)(resolution.getScaledWidth() / 2 - 50 + 2), (float)(resolution.getScaledHeight() - 19), 0.0F);

		Tessellator t = Tessellator.instance;

		GL11.glBindTexture(3553, 0);

		if(this.mc.thePlayer.inventory.currentItem >= 0 && this.mc.thePlayer.inventory.currentItem <= 5) {
			t.startDrawingQuads();
		    t.addVertexWithUV((double)(-2 + this.mc.thePlayer.inventory.currentItem * 20), -2.0D, 0.0D, 0.0D, 0.0D);
		    t.addVertexWithUV((double)(-2 + this.mc.thePlayer.inventory.currentItem * 20), 18.0D, 0.0D, 0.0D, 1.0D);
		    t.addVertexWithUV((double)(18 + this.mc.thePlayer.inventory.currentItem * 20), 18.0D, 0.0D, 1.0D, 1.0D);
		    t.addVertexWithUV((double)(18 + this.mc.thePlayer.inventory.currentItem * 20), -2.0D, 0.0D, 1.0D, 0.0D);
		    t.draw();
		}
		
		if(this.mc.thePlayer.inventory.currentItem > 5 && this.mc.thePlayer.inventory.currentItem < 8) {
			this.mc.thePlayer.inventory.currentItem = 0;
		}
		
		if(this.mc.thePlayer.inventory.currentItem >= 5 && this.mc.thePlayer.inventory.currentItem <= 8) {
			this.mc.thePlayer.inventory.currentItem = 5;
		}
		
		for(int i = 0; i < 6; ++i) {

			GL11.glBindTexture(3553, 0);
		    GL11.glEnable(3042);
		    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		    RenderHelper.disableStandardItemLighting();
		    t.startDrawingQuads();
		    t.addVertexWithUV((double)(0 + i * 20), 0.0D, 0.0D, 0.0D, 0.0D);
		    t.addVertexWithUV((double)(0 + i * 20), 16.0D, 0.0D, 0.0D, 1.0D);
		    t.addVertexWithUV((double)(16 + i * 20), 16.0D, 0.0D, 1.0D, 1.0D);
		    t.addVertexWithUV((double)(16 + i * 20), 0.0D, 0.0D, 1.0D, 0.0D);
		    t.draw();
		    //renderInventorySlot(i, i * 20, 0, 0.0F);
		}
		RenderHelper.disableStandardItemLighting();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();*/
	}
	

}
