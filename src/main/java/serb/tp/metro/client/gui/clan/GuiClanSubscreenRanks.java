package serb.tp.metro.client.gui.clan;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import serb.tp.metro.client.Type;
import serb.tp.metro.client.gui.elements.GuiButtonTextured;
import serb.tp.metro.common.handlers.ClanHandler;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.general.AddClanMessage;

public class GuiClanSubscreenRanks extends GuiSubscreen{

	ArrayList<GuiButtonTextured> buttonRanks = new ArrayList<GuiButtonTextured>();
	
	public GuiClanSubscreenRanks(EntityPlayer player, int x, int y, GuiScreen parent) {
		super(player, x, y, parent);
		this.height=143;
		this.width=163;
	}

    @Override
    public void initGui() {
        super.initGui();
        //buttonList = new ArrayList();  
    	Keyboard.enableRepeatEvents(true);
	    buttonList.add(new GuiButtonTextured(10001, this.x+25, this.y-20, Type.getTranslate("gui.button.clear"), 68, 20, "button_clans.png"));
	    buttonList.add(new GuiButtonTextured(10002, this.x+85, this.y+140, Type.getTranslate("gui.button.create"), 68, 20, "button_clans.png"));
    }
    
	@Override
	protected void actionPerformed(GuiButton button) {
		 
		if(button.id == 10001) {
		}
		if(button.id == 10002) {
	
		}
 
		super.actionPerformed(button);

	}
	 
	@Override
	protected void keyTyped(char ch, int keyId) {
		 super.keyTyped(ch, keyId);
	}
	
	@Override
	public void drawScreen(int x, int y, float p_73863_3_)
	{
		
		glScissor(this.x, this.y, this.width, this.height);
		GL11.glEnable(GL11.GL_SCISSOR_TEST);
		super.drawScreen(x, y, p_73863_3_); 
		GL11.glDisable(GL11.GL_SCISSOR_TEST);
	}
 
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton)  { 
		 super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	 
	@Override
	public void updateScreen() {

	}
	    
	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}
	
	

}
