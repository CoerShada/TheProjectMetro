package serb.tp.metro.client.gui.elements;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import serb.tp.metro.Main;

public class GuiButtonTextured extends GuiButton{
	
	private ResourceLocation buttonTextures;

	public GuiButtonTextured(int id, int xPos, int yPos, String string, String textureName) {
		super(id, xPos, yPos, 68, 20, string);
		buttonTextures = new ResourceLocation(Main.modid, "textures/gui/guiScreen/" + textureName);
		
	}
	
	public void drawButton(Minecraft mc, int mouseX_, int mouseY)
    {
        if (this.visible)
        {
            FontRenderer fontrenderer = mc.fontRenderer;
            mc.getTextureManager().bindTexture(this.buttonTextures);
            GL11.glPushMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.field_146123_n = mouseX_ >= this.xPosition && mouseY >= this.yPosition && mouseX_ < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            int k = this.getHoverState(this.field_146123_n);
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glScalef(0.5F, 0.5F, 0.5F);

            this.drawTexturedModalRect(this.xPosition*2, this.yPosition*2, 0,  (k-1) * this.height * 2, this.width * 2, this.height * 2);
            //this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition,  this.width / 2,  k * 20, this.width / 2, this.height);
            this.mouseDragged(mc, mouseX_, mouseY);
            int l = 14737632;
            GL11.glPopMatrix();
            if (packedFGColour != 0)
            {
                l = packedFGColour;
            }
            else if (!this.enabled)
            {
                l = 10526880;
            }
            else if (this.field_146123_n)
            {
                l = 16777120;
            }

            this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2 , this.yPosition + (this.height - 8) / 2, l);
            
        }
    }

}
