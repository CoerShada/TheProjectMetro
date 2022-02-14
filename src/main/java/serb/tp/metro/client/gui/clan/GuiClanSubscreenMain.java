package serb.tp.metro.client.gui.clan;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import serb.tp.metro.client.Type;
import serb.tp.metro.client.gui.elements.GuiButtonTextured;
import serb.tp.metro.common.clans.Clan;
import serb.tp.metro.common.handlers.ClanHandler;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.general.AddClanMessage;

public class GuiClanSubscreenMain extends GuiSubscreen{

	public GuiClanSubscreenMain(EntityPlayer player, int x, int y, GuiScreen parent) {
		super(player, x, y, parent);
	}
	@Override
    public void initGui() {
        super.initGui();
 

    }
    
	 @Override
	 protected void actionPerformed(GuiButton button) {
		 
		 
		 super.actionPerformed(button);

	 }
	 
	 
	 @Override
	 public void drawScreen(int x, int y, float p_73863_3_)
	 {	
		 
	     Clan clan = this.clan.getClan();
		 mc.fontRenderer.drawString(Type.getTranslate("clans.name")+": "+clan.getName(), this.x+2, this.y+20, 15526880);
		 mc.fontRenderer.drawString(Type.getTranslate("clans.description")+": "+clan.description, this.x+2, this.y+30, 15526880);
		 mc.fontRenderer.drawString(clan.getRankFromIndex(0).getName()+": "+clan.getLeaderName(), this.x+2, this.y+70, 15526880);
		 mc.fontRenderer.drawString(Type.getTranslate("clans.type")+": "+Type.getTranslate("clans.type."+clan.getClanType().toString().toLowerCase()), this.x+2, this.y+80, 15526880);
		 mc.fontRenderer.drawString(Type.getTranslate("clans.structure")+": "+Type.getTranslate("clans.structure."+clan.getClanStructure().toString().toLowerCase()), this.x+2, this.y+90, 15526880);

		 super.drawScreen(x, y, p_73863_3_); 
	 }
	 
	 @Override
	 protected void mouseClicked(int mouseX, int mouseY, int mouseButton)  { 
		 super.mouseClicked(mouseX, mouseY, mouseButton);
		 
	 }

	 

	    
	 @Override
	 public boolean doesGuiPauseGame()
	 {
		 return false;
	 }
	 
	
}
