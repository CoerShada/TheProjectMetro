package serb.tp.metro.client.gui.elements;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import serb.tp.metro.Main;
import serb.tp.metro.common.clans.Clan;
import serb.tp.metro.common.clans.Rank;
import serb.tp.metro.common.clans.Relation;
import serb.tp.metro.common.clans.RelationType;
import serb.tp.metro.common.ieep.ExtendedPlayer;

public class GuiButtonRank extends GuiButtonTextured{

	private EntityPlayer player;
	private Rank rank;
	
    public GuiButtonRank(int id, int posX, int posY, String texture, Rank rank, EntityPlayer player)
    {
    	super(id, posX, posY, rank.getName(), 105, 10, texture);
    	this.player = player;
    	this.rank = rank;
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

            this.drawTexturedModalRect(this.xPosition*2, this.yPosition*2, 0,  (k-1) * this.height * 2, this.width*2, this.height * 2);
            this.mouseDragged(mc, mouseX_, mouseY);
            int l = 14737632;
            
            /*if (relation!=null) {
            	k = 3;
            	if (relation.getImprooveRelations()==playersClan.getId()) {
            		k=2;
            	}
            	else if (relation.getImprooveRelations()==clan.getId()) {
            		k=1;
            	}
            	this.drawTexturedModalRect((this.xPosition + this.width)*2-30, this.yPosition*2, 0,  k * this.height * 2 + this.height * 2, 22, 20);            
            }*/
            GL11.glPopMatrix();

            int color = new Color(255, 255, 255).getRGB();
            
            
            	
            
            
            this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2 , this.yPosition + (this.height - 9) / 2, color);
            
        }
    }
    

    @Override
    public boolean mousePressed(Minecraft mc, int x, int y)
    {

        return this.enabled && this.visible && x >= this.xPosition && y >= this.yPosition && x < this.xPosition + this.width && y < this.yPosition + this.height;
    }
    
	public final Rank getRank() {
		return rank;
	}

	public final EntityPlayer getPlayer() {
		return player;
	}


    
}
