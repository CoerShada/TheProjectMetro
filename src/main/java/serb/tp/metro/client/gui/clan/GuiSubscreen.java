package serb.tp.metro.client.gui.clan;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import serb.tp.metro.DebugMessage;
import serb.tp.metro.Main;
import serb.tp.metro.client.ClientProxy;
import serb.tp.metro.client.Type;
import serb.tp.metro.client.gui.elements.GuiButtonTextured;
import serb.tp.metro.common.clans.Clan;
import serb.tp.metro.common.ieep.ExtendedPlayer;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.server.GetClanByPlayerMessage;

public abstract class GuiSubscreen extends GuiScreen{

	 EntityPlayer player;
	 int x, y;
	 GuiClanMainWindow parent;

	 
	 public GuiSubscreen(EntityPlayer player, int x, int y, GuiClanMainWindow parent) {
		 this.player = player;
		 this.x = x;
		 this.y = y;

		 this.parent = parent;
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
	 protected void keyTyped(char ch, int keyId) {
		 super.keyTyped(ch, keyId);
	 }

	 @Override
	 public void drawScreen(int x, int y, float p_73863_3_)
	 {
		 
		 super.drawScreen(x, y, p_73863_3_);
		 this.drawRect(this.x, this.y, this.x+this.width, this.y+this.height, 0xFFFFFF);
	 }
	    

	 	 
	 @Override
	 public boolean doesGuiPauseGame()
	 {
		 return false;
	 }
	 
	 public void handleMouseInput()
	 {
		 int i = Mouse.getEventX() * this.parent.width / this.mc.displayWidth;
	     int j = this.parent.height - Mouse.getEventY() * this.parent.height / this.mc.displayHeight - 1;
	     
	     int k = Mouse.getEventButton();

	     if (Mouse.getEventButtonState())
	     {
	    	 if (this.mc.gameSettings.touchscreen && this.field_146298_h++ > 0)
	         {
	    		 return;
	         }
	         this.eventButton = k;
	         this.lastMouseEvent = Minecraft.getSystemTime();
	         this.mouseClicked(i, j, this.eventButton);
	     }
	     else if (k != -1)
	     {
	    	 if (this.mc.gameSettings.touchscreen && --this.field_146298_h > 0)
	         {
	    		 return;
	         }

	         this.eventButton = -1;
	         this.mouseMovedOrUp(i, j, k);
	     }
	     else if (this.eventButton != -1 && this.lastMouseEvent > 0L)
	     {
	    	 long l = Minecraft.getSystemTime() - this.lastMouseEvent;
	         this.mouseClickMove(i, j, this.eventButton, l);
	     }
	 }
	 
	 protected void glScissor(int x, int y, int width, int height){
	        Minecraft mc = Minecraft.getMinecraft();
	        ScaledResolution resolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
	        int scale = resolution.getScaleFactor();

	        int scissorWidth = width * scale;
	        int scissorHeight = height * scale;
	        int scissorX = x * scale;
	        int scissorY = mc.displayHeight - scissorHeight - (y * scale);
	        GL11.glScissor(scissorX, scissorY, scissorWidth, scissorHeight);
	 }
	 
	 protected final void drawStringsFromArray(String[] arr, int beginX, int beginY, int stepX, int stepY) {
		 int x = beginX;
		 int y = beginY;
		 for (String str: arr) {
			 this.drawString(fontRendererObj, str, x, y, 10526880);	
			 x+=stepX;
			 y+=stepY;
		 }
	 }
}
