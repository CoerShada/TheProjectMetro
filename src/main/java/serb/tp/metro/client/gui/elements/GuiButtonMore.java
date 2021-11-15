package serb.tp.metro.client.gui.elements;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import serb.tp.metro.Main;
import serb.tp.metro.client.ClientProxy;
import serb.tp.metro.client.gui.containers.GuiStorageCreator;
import serb.tp.metro.client.resources.Resources;
import serb.tp.metro.entities.player.handlers.ThirstyHandler;
import serb.tp.metro.recipes.Recipe;

@SideOnly(Side.CLIENT)
public class GuiButtonMore extends GuiButton
{
	public static ResourceLocation buttonTextures = new ResourceLocation(Main.modid, "textures/gui/guiScreen/button_more.png");
	private boolean open = false;
    public GuiButtonMore(int id, int posX, int posY)
    {
    	super(id, posX, posY, 7, 18, "");
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
    	if (this.visible)
        {
            FontRenderer fontrenderer = mc.fontRenderer;
            mc.getTextureManager().bindTexture(buttonTextures);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.field_146123_n = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            int k = this.getHoverState(this.field_146123_n);
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            if (!open)
            	this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 - (1-k) * 20, this.width, this.height);
            else
            	this.drawTexturedModalRect(this.xPosition, this.yPosition, 7, 46 - (1-k) * 20, this.width+7, this.height);
            //this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, this.width, 46 + 20, this.width , this.height);
            this.mouseDragged(mc, mouseX, mouseY);
    
        }
    }
    
    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
    	if (this.enabled && this.visible && mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition+this.height){	
    		this.open = !this.open;

        	return true;
    	}
    	return false;

    }
    
    public void closeButton() {
    	this.open = false;
    }
    
    public boolean isOpen() {
    	return open;
    }


}