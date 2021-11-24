package serb.tp.metro.client.gui.clan;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import serb.tp.metro.Main;
import serb.tp.metro.client.gui.elements.GuiButtonTextured;
import serb.tp.metro.client.resources.Resources;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.server.AddClanMessage;

public class GuiClanCreate extends GuiScreen
{

    EntityPlayer player;
	private int xSize_lo;
    private int ySize_lo;
    private int xSize = 256;
    private int ySize = 143;
    private int centerX;
    private int centerY;
    private GuiTextField nameFaction;
    private GuiLabel descriptionNameFaction;
    private String str = "Это тестовый текст для описания создания клана";
    
    public GuiClanCreate(EntityPlayer player) {
		super();
		this.player = player;
		System.out.println(Main.proxy.clanIEEP.get(player).getClan());
	}
    
    @Override
    public void initGui() {
        super.initGui();
        centerX = width/2-xSize/2;
        centerY = height/2-ySize/2;
        //Добавить проверку на созданность клана
        descriptionNameFaction = new GuiLabel();
        	
        	
        nameFaction = new GuiTextField(fontRendererObj, centerX+110, centerY+44, 100, 10);
        Keyboard.enableRepeatEvents(true);
        buttonList.add(new GuiButtonTextured(11, centerX+90, centerY+76, "Сбросить", "button_clans.png"));
        buttonList.add(new GuiButtonTextured(12, centerX+150, centerY+76, "Создать", "button_clans.png"));
        
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

        }
        if(button.id == 11) {
        	nameFaction.setText("");
        }
        if(button.id == 12) {
            
            PacketDispatcher.sendToServer(new AddClanMessage(nameFaction.getText()));
            this.onGuiClosed();
        }
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
		mc.getTextureManager().bindTexture(Resources.clanBackground);
		drawTexturedModalRect(centerX, centerY, 0, 0, xSize, ySize);
		nameFaction.drawTextBox();
		nameFaction.drawString(fontRendererObj, str, centerX+72, centerY+54, 10526880);		
		mc.fontRenderer.drawString(""+mc.thePlayer.getDisplayName(), centerX+196, centerY+4, 15526880);
        super.drawScreen(x, y, p_73863_3_);
        

    }
    
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton)  {
    	nameFaction.mouseClicked(mouseX, mouseY, mouseButton);
    	super.mouseClicked(mouseX, mouseY, mouseButton);
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
