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

public class GuiButtonClan extends GuiButtonTextured{

	private Clan clan;
	private ExtendedPlayer extendedPlayer;
	private EntityPlayer player;
	
    public GuiButtonClan(int id, int posX, int posY, String texture, Clan clan, EntityPlayer player)
    {
    	super(id, posX, posY, clan.getName(), 70, 20, texture);
    	this.clan = clan;
    	this.extendedPlayer = Main.proxy.clanIEEP.get(player);
    	this.player = player;
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
            //this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition,  this.width / 2,  k * 20, this.width / 2, this.height);
            this.mouseDragged(mc, mouseX_, mouseY);
            int l = 14737632;
            GL11.glPopMatrix();


            int color = EColor.FREE.color;
            
            if (extendedPlayer.getClan()==null) {
            	if (clan.isApplicationSubmitted(player))
            		color = EColor.APPLY_FOR_ADMISSION.color;
            	else
            		color = EColor.FREE.color;
            }
            else {
            	Clan playerClan = extendedPlayer.getClan();
            	Relation relation = playerClan.getRelation(clan);
            	if (relation== null) {
            		color = EColor.SELF.color;
            	}
            	else if(relation.getType() == RelationType.WAR && relation.getImprooveRelations() == clan) {
            		color = EColor.REQUEST_NEUTRAL.color;
            	}
            	else if (relation.getType() == RelationType.WAR) {
            		color = EColor.ENEMY.color;
            	}
            	else if(relation.getType() == RelationType.NEUTRAL && relation.getImprooveRelations() == clan) {
            		color = EColor.REQUEST_ALLIANCE.color;
            	}
            	else if (relation.getType() == RelationType.NEUTRAL) {
            		color = EColor.NEUTRAL.color;
            	}
            	else {
            		color = EColor.ALLIANCE.color;
            	}
            	
            }
            
            this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2 , this.yPosition + (this.height - 8) / 2, color);
            
        }
    }
    
    enum EColor{
    	ENEMY(new Color(220, 75, 35)), 
    	NEUTRAL(new Color(235, 235, 30)),
    	ALLIANCE(new Color(15, 235, 30)),
    	SELF(new Color(45, 45, 235)),
    	REQUEST_NEUTRAL(new Color(220, 100, 70)),
    	REQUEST_ALLIANCE(new Color(135, 235, 30)),
    	FREE(new Color(255, 255, 255)),
    	APPLY_FOR_ADMISSION(new Color(100, 126, 232));
    	private int color;
    	
    	EColor(Color color) {
    		this.color = color.getRGB();
    	}
    	
    }
    
}
