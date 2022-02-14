package serb.tp.metro.client.gui.clan;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.common.MinecraftForge;
import serb.tp.metro.Main;
import serb.tp.metro.client.Type;
import serb.tp.metro.client.gui.elements.GuiButtonTextured;
import serb.tp.metro.client.resources.Resources;
import serb.tp.metro.common.ieep.ExtendedPlayer;

public class GuiClanMainWindow extends GuiScreen
{

    EntityPlayer player;
	private int xSize_lo;
    private int ySize_lo;
    private int xSize = 256;
    private int ySize = 143;
    protected int centerX;
    protected int centerY;
    GuiSubscreen subscreen;
    ExtendedPlayer clan;
    protected boolean needUpdated = true;
    
    public GuiClanMainWindow(EntityPlayer player) {
		super();
		this.player = player;
		this.clan = Main.proxy.clanIEEP.get(player);
		System.out.println(this.clan.getClan());
	}
    
    @Override
    public void initGui() {
        super.initGui();
        centerX = width/2-xSize/2;
        centerY = height/2-ySize/2;

        buttonList.add(new GuiButtonTextured(0, centerX+1, centerY+1, Type.getTranslate("gui.clan.button.main"), 62, 20 , "button_clans_menu.png"));
       
        buttonList.add(new GuiButtonTextured(1, centerX+1, centerY+21, Type.getTranslate("gui.clan.button.profile"), 62, 20, "button_clans_menu.png"));
        buttonList.add(new GuiButtonTextured(2, centerX+1, centerY+41, Type.getTranslate("gui.clan.button.clanlist"), 62, 20, "button_clans_menu.png"));
        buttonList.add(new GuiButtonTextured(3, centerX+1, centerY+61, Type.getTranslate("gui.clan.button.ranks"), 62, 20, "button_clans_menu.png"));
        buttonList.add(new GuiButton(4, centerX+1, centerY+81, 62, 20 ,"Состав"));
        buttonList.add(new GuiButton(5, centerX+1, centerY+101, 62, 20 ,"Территории"));
        buttonList.add(new GuiButton(6, centerX+1, centerY+121, 62, 20 ,"Новости"));

        updateButtons();
        Keyboard.enableRepeatEvents(true);
    }
    
    public void updateButtons() {
        if (clan.getClan()==null) {
        	subscreen = new GuiClanSubscreenCreate(player, centerX+63, centerY, this);
        	for (int i = 3; i<buttonList.size(); i++) {
        		GuiButton button = (GuiButton) buttonList.get(i);
        		button.visible = false;
        	}
        }
        else {
        	subscreen = new GuiClanSubscreenMain(player, centerX+63, centerY, this);{
        		for (int i = 3; i<buttonList.size(); i++) {
            		GuiButton button = (GuiButton) buttonList.get(i);
            		button.visible = true;
            	}
        	}
        }
    }
    
    @Override
    public void setWorldAndResolution(Minecraft mc, int width, int height)
    {
        this.mc = mc;
        this.fontRendererObj = mc.fontRenderer;
        this.width = width;
        this.height = height;
        if (!MinecraftForge.EVENT_BUS.post(new InitGuiEvent.Pre(this, this.buttonList)))
        {
            this.buttonList.clear();
            this.initGui();
            if (subscreen!=null)
            	subscreen.setWorldAndResolution(mc, width, height);
        }
        MinecraftForge.EVENT_BUS.post(new InitGuiEvent.Post(this, this.buttonList));
    }
    
    @Override
    protected void actionPerformed(GuiButton button) {
        super.actionPerformed(button);
        /*StackTraceElement[] stes = Thread.currentThread().getStackTrace();
        for (StackTraceElement ste: stes) {
        	System.out.println(ste);
        }*/
        if (subscreen!=null) {
        	subscreen.actionPerformed(button);
        }
        
        if (button.id == 0) {
        	updateButtons();
            subscreen.setWorldAndResolution(Minecraft.getMinecraft(), subscreen.width, subscreen.height);
        }
        else if (button.id == 1) {

        }
        else if (button.id == 2) {
        	subscreen = new GuiClanSubscreenClans(player, centerX+63, centerY, this);
            subscreen.setWorldAndResolution(Minecraft.getMinecraft(), this.centerX + this.xSize, this.centerY+this.ySize);
        }
        else if (button.id==3) {
        	
        	subscreen = new GuiClanSubscreenRanks(player, centerX+63, centerY, this);
            subscreen.setWorldAndResolution(Minecraft.getMinecraft(), this.centerX + this.xSize, this.centerY+this.ySize);
        }
        

    }
    
    
	@Override
	protected void keyTyped(char ch, int keyId) {
		 super.keyTyped(ch, keyId);
	     if (subscreen!=null)
	    	 subscreen.keyTyped(ch, keyId);
	 }
    
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton)
    {
    	
        if (subscreen!=null) {
        	subscreen.mouseClicked(mouseX, mouseY, mouseButton);
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);

        
    }

    @Override
    public void drawScreen(int x, int y, float p_73863_3_)
    {

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);		
		mc.getTextureManager().bindTexture(Resources.clanBackground);
		drawTexturedModalRect(centerX, centerY, 0, 0, xSize, ySize);
		updateMainPageClan();
        if (subscreen!=null) {
        	subscreen.drawScreen(x, y, p_73863_3_);
        }
        
        super.drawScreen(x, y, p_73863_3_);
        

    }
    
    public void updateMainPageClan() {
		if (clan.getClan()!=null){
			mc.fontRenderer.drawString(Type.getTranslate("clans.isClan")+" «"+clan.getClan().getName()+ "» "+mc.thePlayer.getDisplayName(), centerX+65, centerY+4, 15526880);
    	}
		else {
			mc.fontRenderer.drawString(Type.getTranslate("clans.noneClan")+" "+mc.thePlayer.getDisplayName(), centerX+65, centerY+4, 15526880);
		}
    }
    
    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }
    
    @Override
	public void updateScreen() {
    	super.updateScreen();
    	subscreen.updateScreen();
		
		 
	}
    
	 
	@Override
	public void handleMouseInput()
	{	 
		subscreen.handleMouseInput();
		super.handleMouseInput();
		 
	}

    
    
}
