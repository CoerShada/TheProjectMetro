package serb.tp.metro.client.gui.elements;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import serb.tp.metro.Main;
import serb.tp.metro.client.gui.containers.GuiStorageCreator;

public class GuiButtonItem extends GuiButton{
	private ItemStack itemStack;
	public static ResourceLocation buttonTextures = new ResourceLocation(Main.modid, "textures/gui/guiScreen/button_item.png");
	private boolean closed;
	public final int parentID;

    public GuiButtonItem(int id, int posX, int posY, int parentID, ItemStack itemStack, boolean closed)
    {
    	super(id, posX, posY, 18, 18, "");
    	this.itemStack = itemStack;
    	this.closed = closed;
    	this.parentID = parentID;
    }
	
	@Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
    	if (this.visible)
        {
            FontRenderer fontrenderer = mc.fontRenderer;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        	int y1 = this.yPosition;
        	int y2 = this.yPosition + this.height;
            this.field_146123_n = mouseX >= this.xPosition && mouseY >= y1 && mouseX < this.xPosition + this.width && mouseY < y2;
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.mouseDragged(mc, mouseX, mouseY);
            GL11.glPushMatrix();
            GL11.glAlphaFunc(516, 0.1F);
            GL11.glEnable(3042);
            GL11.glTranslated(0, 0, 200);
            mc.getTextureManager().bindTexture(buttonTextures);
            int k = this.getHoverState(this.field_146123_n);
            
            drawTexturedModalRect(this.xPosition, this.yPosition, 0, 0, 18, 18);
            if (itemStack!=null) {
            	renderStack(itemStack, this.xPosition+1, this.yPosition);

            	if(field_146123_n) {
            		GL11.glTranslated(0, 0, 201);
            		this.drawRect(this.xPosition+this.width/2-mc.fontRenderer.getStringWidth(itemStack.getDisplayName())/2-2, this.yPosition-10, this.xPosition + this.width/2+mc.fontRenderer.getStringWidth(itemStack.getDisplayName())/2+2, this.yPosition, Integer.MIN_VALUE | 0x000000);
                	mc.fontRenderer.drawString(itemStack.getDisplayName(), this.xPosition-mc.fontRenderer.getStringWidth(itemStack.getDisplayName())/2+this.width/2, this.yPosition-10, 0xFFFFFF);

                	
                	this.drawRect(this.xPosition+1, this.yPosition+1, this.xPosition + this.width - 1, this.yPosition + this.height -1, Integer.MIN_VALUE | 0xFFFFFF);

            	}
  
            	
            }
            else if (this.closed){
            	this.drawTexturedModalRect(this.xPosition, this.yPosition, 18, 0, 18, this.height);
            }
            else {
            	this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 0, this.width, this.height);

            	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            	this.drawTexturedModalRect(this.xPosition, this.yPosition, 18, 18, this.width, this.height);
            	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            	if (field_146123_n) {
            		this.drawRect(this.xPosition+1, this.yPosition+1, this.xPosition + this.width-1, this.yPosition + this.height-1, Integer.MIN_VALUE | 0xFFFFFF);
            	}
            }
            
            GL11.glPopMatrix();
            
        }
    }

	
    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
    	if (this.enabled && this.visible && mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition+this.height){	
    		return true;
    	}
    	return false;
    }
    
    public void renderStack(ItemStack stack, int x, int y)
    {
       RenderHelper.enableGUIStandardItemLighting();
       Minecraft mc = Minecraft.getMinecraft();
       RenderItem.getInstance().renderItemAndEffectIntoGUI(mc.fontRenderer, mc.getTextureManager(), stack, x, y+1);
       RenderHelper.disableStandardItemLighting();
    }

}
