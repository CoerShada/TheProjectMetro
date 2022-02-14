package serb.tp.metro.client.gui.clan;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import serb.tp.metro.DebugMessage;
import serb.tp.metro.Main;
import serb.tp.metro.client.Type;
import serb.tp.metro.client.gui.elements.GuiButtonTextured;
import serb.tp.metro.common.ieep.ExtendedPlayer;

public abstract class GuiSubscreen extends GuiScreen{

	 EntityPlayer player;
	 ExtendedPlayer clan;
	 int x, y;
	 GuiScreen parent;

	 
	 public GuiSubscreen(EntityPlayer player, int x, int y, GuiScreen parent) {
		 this.player = player;
		 this.x = x;
		 this.y = y;
		 this.clan = Main.proxy.clanIEEP.get(player);
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
