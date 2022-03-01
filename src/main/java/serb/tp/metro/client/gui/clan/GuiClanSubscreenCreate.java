package serb.tp.metro.client.gui.clan;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import serb.tp.metro.client.Type;
import serb.tp.metro.client.gui.elements.GuiButtonTextured;
import serb.tp.metro.common.clans.ClanHandler;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.general.AddClanMessage;
import serb.tp.metro.utils.StringHelper;

public class GuiClanSubscreenCreate extends GuiSubscreen{

    private GuiTextField nameFaction;
    private String str = "Это тестовый текст для описания создания клана";
	private boolean error = false;
    
	public GuiClanSubscreenCreate(EntityPlayer player, int x, int y, GuiClanMainWindow parent) {
		super(player, x, y, parent);
		
	}

    @Override
    public void initGui() {
        super.initGui();
        buttonList = new ArrayList();  
    	nameFaction = new GuiTextField(fontRendererObj, this.x+40, this.y+50, 100, 10);
	    buttonList.add(new GuiButtonTextured(11, this.x+25, this.y+70, Type.getTranslate("gui.button.clear"), 68, 20, "button_clans.png"));
	    buttonList.add(new GuiButtonTextured(12, this.x+85, this.y+70, Type.getTranslate("gui.button.create"), 68, 20, "button_clans.png"));
	    Keyboard.enableRepeatEvents(true);
    }
    
	 @Override
	 protected void actionPerformed(GuiButton button) {
		 
		 if(button.id == 11) {
	        	nameFaction.setText("");
	        }
	        if(button.id == 12) {
	            String name = nameFaction.getText().trim();
	            if (name.length()<5) return;
	            ClanHandler handler = ClanHandler.get(Minecraft.getMinecraft().theWorld);
	            if (!handler.theNameIsUnique(name)) {
	            	nameFaction.drawString(fontRendererObj, str, this.x+5, this.y+15, 10526880);		
	       		 	error = true;
	            	return;
	            }
	            PacketDispatcher.sendToServer(new AddClanMessage(nameFaction.getText()));
	    		handler.createClan(name, player.getUniqueID());
	        }
		 
	        super.actionPerformed(button);

	 }
	 
	 @Override
	 protected void keyTyped(char ch, int keyId) {
		 
		 String symbolsName = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя 1234567890-\"";
		 if (symbolsName.contains(Character.toString(ch).toLowerCase()) && nameFaction.getText().length()<15 || keyId==14)
			 if (this.nameFaction.getText().length()==0)
				 ch = Character.toUpperCase(ch);
		 if (this.nameFaction.getText().length()==0 && ch==' ')
			 return;
		 this.nameFaction.textboxKeyTyped(ch, keyId);
		 
		 super.keyTyped(ch, keyId);
	 
	 
	 }
	 
	 @Override
	 public void drawScreen(int x, int y, float p_73863_3_)
	 {
		 GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		 nameFaction.drawTextBox();
		 drawStringsFromArray(StringHelper.floatSplit(str, 45), this.x+5, this.y+30, 0, 10);
		 if (error) {
			 drawStringsFromArray(StringHelper.floatSplit(Type.getTranslate("gui.clan.label.createError.nameAlreadyExists"), 45), this.x+5, this.y+90, 0, 10);
		 }
	     super.drawScreen(x, y, p_73863_3_); 

	 }
	 
	 @Override
	 protected void mouseClicked(int mouseX, int mouseY, int mouseButton)  { 
		 super.mouseClicked(mouseX, mouseY, mouseButton);
		 nameFaction.mouseClicked(mouseX, mouseY, mouseButton); 

	 }

	 
	 @Override
	 public void updateScreen() {
		 nameFaction.updateCursorCounter();
	 }
	    
	 @Override
	 public boolean doesGuiPauseGame()
	 {
		 return false;
	 }
	 
}
