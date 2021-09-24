package serb.tp.metro.client.gui.factions;

import java.io.IOException;

import javax.imageio.IIOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.Tessellator;
import serb.tp.metro.client.resources.Resources;
import serb.tp.metro.factions.FactionsData;

public class GuiFactionStartPage extends GuiScreen
{
    private int xSize_lo;
    private int ySize_lo;
    private int xSize = 256;
    private int ySize = 143;
    private int centerX;
    private int centerY;
    private GuiTextField nameFaction;
    private GuiTextField tagFaction;
    private GuiLabel descriptionNameFaction;
    private String str = "Это тестовый текст для описания создания клана";
    
    @Override
    public void initGui() {
        super.initGui();
        centerX = width/2-xSize/2;
        centerY = height/2-ySize/2;
        //Добавить проверку на созданность клана
        	descriptionNameFaction = new GuiLabel();
        	
        	
        	nameFaction = new GuiTextField(fontRendererObj, centerX+110, centerY+44, 100, 10);
        	tagFaction = new GuiTextField(fontRendererObj, centerX+110, centerY+64, 100, 10);
        	Keyboard.enableRepeatEvents(true);
                buttonList.add(new GuiButton(11, centerX+109, centerY+76, 50, 20 ,"Создать"));
                buttonList.add(new GuiButton(12, centerX+161, centerY+76, 50, 20 ,"Сбросить"));
        
        buttonList.add(new GuiButton(0, centerX+1, centerY+1, 62, 20 ,"Главная"));
        buttonList.add(new GuiButton(1, centerX+1, centerY+21, 62, 20 ,"Профиль"));
        buttonList.add(new GuiButton(2, centerX+1, centerY+41, 62, 20 ,"Политика"));
        buttonList.add(new GuiButton(3, centerX+1, centerY+61, 62, 20 ,"Дипломатия"));
        buttonList.add(new GuiButton(4, centerX+1, centerY+81, 62, 20 ,"Состав"));
        buttonList.add(new GuiButton(5, centerX+1, centerY+101, 62, 20 ,"Территории"));
        buttonList.add(new GuiButton(6, centerX+1, centerY+121, 62, 20 ,"Новости"));
        buttonList.add(new GuiButton(8, centerX+235, centerY+1, 20, 20 ,""));
        
        
    }
    
    @Override
    protected void actionPerformed(GuiButton button) {
        super.actionPerformed(button);

     
        
        if (button.id == 0) {
	    	String namesNbt= "⇒fgd⇒oil⇒hjg⇒fjh⇒kgf⇒hjh";
		String[] names = namesNbt.split("⇒");
		for (int i=0; i<names.length; i++)
		    System.out.print(names[i]+" ");
		System.out.println();
		System.out.println(names[1]);
		System.out.println(namesNbt.substring(0, namesNbt.length()-1));
        }
        if(button.id == 11) {
            //FactionsData faction = new FactionsData(mc.theWorld., str, str)
        }
        if(button.id == 12) {
            nameFaction.setText("");
            tagFaction.setText("");
        }
    }
    
    @Override
    protected void keyTyped(char p_73869_1_, int keyId) {
	String symbolsName = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя 1234567890-\"";
	String symbolsTag = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ1234567890";
	if (symbolsName.contains(Character.toString(p_73869_1_).toLowerCase()) && nameFaction.getText().length()<15 || keyId==14)
	    if (this.nameFaction.getText().length()==0)
		p_73869_1_ = Character.toUpperCase(p_73869_1_);
	if (this.nameFaction.getText().length()==0 && p_73869_1_==' ')
	    return;
	    this.nameFaction.textboxKeyTyped(p_73869_1_, keyId);
	if (symbolsTag.contains(Character.toString(p_73869_1_).toUpperCase()) && tagFaction.getText().length()<4 || keyId==14) 
	    this.tagFaction.textboxKeyTyped(Character.toUpperCase(p_73869_1_), keyId);
	super.keyTyped(p_73869_1_, keyId);
    }

    @Override
    public void drawScreen(int x, int y, float p_73863_3_)
    {
	
	
	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	mc.getTextureManager().bindTexture(Resources.factionStartPageTexture);
	drawTexturedModalRect(centerX, centerY, 0, 0, xSize, ySize);
	nameFaction.drawTextBox();
	tagFaction.drawTextBox();
	nameFaction.drawString(fontRendererObj, str, centerX+72, centerY+54, 10526880);		
	mc.fontRenderer.drawString(""+mc.thePlayer.getDisplayName(), centerX+196, centerY+4, 15526880);
        super.drawScreen(x, y, p_73863_3_);
        

    }
    
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton)  {
	nameFaction.mouseClicked(mouseX, mouseY, mouseButton);
	tagFaction.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void updateScreen() {
    	nameFaction.updateCursorCounter();
    	tagFaction.updateCursorCounter();
    }
    
    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }
    

    
    
}
