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
import serb.tp.metro.client.ClientProxy;
import serb.tp.metro.client.gui.containers.GuiStorageCreator;
import serb.tp.metro.client.resources.Resources;
import serb.tp.metro.entities.player.handlers.ThirstyHandler;
import serb.tp.metro.recipes.Recipe;

@SideOnly(Side.CLIENT)
public class GuiButtonChooseRecipe extends GuiButton
{
    public boolean choosen;
    private GuiItem[] guiItems;
    private GuiStorageCreator parent;
    

    public GuiButtonChooseRecipe(GuiStorageCreator parent, int id, int posX, int posY, Recipe recipe, boolean choosen)
    {
        this(parent, id, posX, posY, 200, 20, recipe, choosen);
    }

    public GuiButtonChooseRecipe(GuiStorageCreator parent, int id, int posX, int posY, int width, int height, Recipe recipe, boolean choosen)
    {
    	super(id, posX, posY, width, height, "");
    	this.parent = parent;
    	this.visible = true;
    	this.choosen = choosen;
    	this.guiItems = new GuiItem[7];

    	for (int i = 0; i<5; i++) {
    		if (i>=recipe.neededItems.length) {
    			guiItems[i] = new GuiItem(parent, i, this.xPosition+2+18*i, this.yPosition+2, 18, 18, null);
    			continue;
    		}
    		ItemStack is = new ItemStack(recipe.neededItems[i]);
    		is.stackSize = recipe.quantityNeededItems[i];
    		guiItems[i] = new GuiItem(parent, i, this.xPosition+2+18*i, this.yPosition+2,18, 18, is);
    	}
    	
    	for (int i = 0; i<2; i++) {
    		if (i>=recipe.receivedItems.length) {
    			guiItems[6-i] = new GuiItem(parent, 6 - i, this.xPosition+this.width-(18+18*i), this.yPosition+2, 18, 18, null);
    			continue;
    		}
    		ItemStack is = new ItemStack(recipe.receivedItems[i]);
    		is.stackSize = recipe.quantityReceivedItems[i];
    		guiItems[6-i] = new GuiItem(parent, 6 - i, this.xPosition+this.width-(18+18*i), this.yPosition+2, 18, 18, is);
    	}

    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
    	if (this.visible)
        {


            FontRenderer fontrenderer = mc.fontRenderer;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        	int y1;
        	int y2;
        	if (this.yPosition-parent.getScrollOffset()<parent.getGuiTop()-14)
        		y1 = parent.getGuiTop()-14;
        	else
        		y1 = this.yPosition-parent.getScrollOffset();
        	
        	if (this.yPosition + this.height-parent.getScrollOffset()<parent.getGuiTop()+58) 
        		y2 = this.yPosition+this.height-parent.getScrollOffset();
        	else
        		y2 = parent.getGuiTop()+58;
            this.field_146123_n = mouseX >= this.xPosition && mouseY >= y1 && mouseX < this.xPosition + this.width && mouseY < y2;
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    		GL11.glEnable(GL11.GL_SCISSOR_TEST);
    		glScissor(this.xPosition, parent.getGuiTop()-14, this.width, 72);
            if (field_146123_n) {
            	this.drawRect(this.xPosition, this.yPosition-parent.getScrollOffset(), this.xPosition+this.width, this.yPosition+this.height-parent.getScrollOffset(), Integer.MIN_VALUE | 0xFFFFFF);
            }
            if (this.choosen) {
            	this.drawRect(this.xPosition, this.yPosition-parent.getScrollOffset(), this.xPosition+this.width, this.yPosition+this.height-parent.getScrollOffset(), Integer.MIN_VALUE | 0xDCDCDC);
            }
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.mouseDragged(mc, mouseX, mouseY);
            GL11.glPushMatrix();
            GL11.glAlphaFunc(516, 0.1F);
            GL11.glEnable(3042);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            mc.fontRenderer.drawString("➟", this.xPosition+91, this.yPosition+6-parent.getScrollOffset(), 0xFFFFFF);
           
            
            for(int i = 0; i<guiItems.length; i++) {
            	guiItems[i].drawButton(mc, mouseX, mouseY);
            }
           
            GL11.glPopMatrix();
            GL11.glDisable(GL11.GL_SCISSOR_TEST);
            
        }
    }
    
    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
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
    	
        return this.enabled && this.visible && mouseX >= this.xPosition && mouseY >= y1 && mouseX < this.xPosition + this.width && mouseY < y2;
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