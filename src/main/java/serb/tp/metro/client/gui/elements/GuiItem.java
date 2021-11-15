package serb.tp.metro.client.gui.elements;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import serb.tp.metro.client.gui.containers.GuiStorageCreator;
import serb.tp.metro.client.resources.Resources;

public class GuiItem extends GuiButton{
	private ResourceLocation texture = Resources.creator_texture;
	private ItemStack itemStack;
	private GuiContainer parent;
	boolean show;


    public GuiItem(GuiContainer parent, int id, int posX, int posY, int width, int height, ItemStack itemStack)
    {
    	super(id, posX, posY, width, height, "");
    	this.itemStack = itemStack;
    	this.parent = parent;
    }
    
    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
    	if (this.visible)
        {
    		GuiStorageCreator parent = (GuiStorageCreator) this.parent;
    		GL11.glEnable(GL11.GL_SCISSOR_TEST);
    		glScissor(this.xPosition, parent.getGuiTop()-14, this.width, 72);
            FontRenderer fontrenderer = mc.fontRenderer;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        	int y1;
        	int y2;
        	if (this.yPosition-parent.getScrollOffset()<parent.getGuiTop()-14)
        		y1 = parent.getGuiTop()-14;
        	else
        		y1 = this.yPosition-parent.getScrollOffset();
        	
        	if (this.yPosition + this.height-parent.getScrollOffset()>parent.getGuiTop()+58)
        		y2 = parent.getGuiTop()+58;
        	else
        		y2 = this.yPosition+this.height-parent.getScrollOffset();
            this.field_146123_n = mouseX >= this.xPosition && mouseY >= y1 && mouseX < this.xPosition + this.width && mouseY < y2;
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.mouseDragged(mc, mouseX, mouseY);
            GL11.glPushMatrix();
            GL11.glAlphaFunc(516, 0.1F);
            GL11.glEnable(3042);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
           
            mc.getTextureManager().bindTexture(texture);
            drawTexturedModalRect(this.xPosition-1, this.yPosition-1-parent.getScrollOffset(), 451, 0, 20, 20);
            GL11.glDisable(GL11.GL_SCISSOR_TEST);
            if (itemStack!=null) {
        		GL11.glEnable(GL11.GL_SCISSOR_TEST);
        		glScissor(this.xPosition, parent.getGuiTop()-14, this.width, 72);
            	renderStack(itemStack, this.xPosition+1, this.yPosition-parent.getScrollOffset());
            	
            	if (itemStack.stackSize!=1) {
            		mc.fontRenderer.drawString(String.valueOf(itemStack.stackSize), this.xPosition+18-mc.fontRenderer.getStringWidth(String.valueOf(itemStack.stackSize)), this.yPosition+9-parent.getScrollOffset(), 0xFFFFFF);
            	}
            	GL11.glDisable(GL11.GL_SCISSOR_TEST);
                if (field_146123_n && itemStack!=null) {

                	this.drawRect(this.xPosition+this.width/2-mc.fontRenderer.getStringWidth(itemStack.getDisplayName())/2-2, this.yPosition-10-parent.getScrollOffset(), this.xPosition + this.width/2+mc.fontRenderer.getStringWidth(itemStack.getDisplayName())/2+2, this.yPosition-parent.getScrollOffset(), Integer.MIN_VALUE | 0x000000);
                	mc.fontRenderer.drawString(itemStack.getDisplayName(), this.xPosition-mc.fontRenderer.getStringWidth(itemStack.getDisplayName())/2+this.width/2, this.yPosition-10-parent.getScrollOffset(), 0xFFFFFF);

                }
            }
            GL11.glPopMatrix();

        }
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
    	return false;
    }
    
    public void renderStack(ItemStack stack, int x, int y)
    {
       RenderHelper.enableGUIStandardItemLighting();
       Minecraft mc = Minecraft.getMinecraft();
       RenderItem.getInstance().renderItemAndEffectIntoGUI(mc.fontRenderer, mc.getTextureManager(), stack, x, y);
       RenderHelper.disableStandardItemLighting();
    }
    
    private void glScissor(int x, int y, int width, int height){
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution resolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int scale = resolution.getScaleFactor();

        int scissorWidth = width * scale;
        int scissorHeight = height * scale;
        int scissorX = x * scale;
        int scissorY = mc.displayHeight - scissorHeight - (y * scale);

        GL11.glScissor(scissorX, scissorY, scissorWidth, scissorHeight);
    }
}
