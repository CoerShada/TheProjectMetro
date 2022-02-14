package serb.tp.metro.client.gui.clan;

import java.awt.Color;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.common.MinecraftForge;
import serb.tp.metro.Main;
import serb.tp.metro.client.Type;
import serb.tp.metro.client.gui.elements.GuiButtonClan;
import serb.tp.metro.client.gui.elements.GuiListScrolling;
import serb.tp.metro.client.gui.elements.GuiScrollingList;
import serb.tp.metro.client.resources.Resources;
import serb.tp.metro.common.clans.Clan;
import serb.tp.metro.common.handlers.ClanHandler;

public class GuiClanSubscreenClans extends GuiSubscreen{

	private GuiScrollingList<GuiButtonClan> clansScrollingList;
	private ArrayList<Clan> clans;
	
	public GuiClanSubscreenClans(EntityPlayer player, int x, int y, GuiScreen parent) {
		super(player, x, y, parent);
		ClanHandler handler = ClanHandler.get(MinecraftServer.getServer().getEntityWorld());
		clans = handler.getClans();
	}

    @Override
    public void initGui() {
        super.initGui();
        int margin = 25;
		this.clansScrollingList = new GuiScrollingList<GuiButtonClan>(this, this.x, this.y+15, 80, this.height-this.y-16, margin);

		int i = 0;
		for (Clan clan: clans) {
			this.clansScrollingList.addElement(new GuiButtonClan(clan.getId(), this.clansScrollingList.getX(), this.clansScrollingList.getY() + i, "button_clans_menu.png", clan, player));
			i+=this.clansScrollingList.getEntryHeight();
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
            /*if (clans!=null)
            	clans.setWorldAndResolution(mc, this.width, this.height);*/
        }
        MinecraftForge.EVENT_BUS.post(new InitGuiEvent.Post(this, this.buttonList));
    }
    
    
	@Override
	public void drawScreen(int x, int y, float p_73863_3_)
	{	
		super.drawScreen(x, y, p_73863_3_); 
		clansScrollingList.drawScreen(x, y, p_73863_3_);

	}
	
	 @Override
	 protected void mouseClicked(int mouseX, int mouseY, int mouseButton)  { 
		 clansScrollingList.mouseClicked(mouseX, mouseY, mouseButton);

		 super.mouseClicked(mouseX, mouseY, mouseButton);
	 }
	 
	 @Override
	 protected void mouseClickMove(int mouseX, int mouseY, int button, long time) {
		 super.mouseClickMove(mouseX, mouseY, button, time);
		 clansScrollingList.mouseClickMove(mouseX, mouseY, button);
	 }
	 
	 public void handleMouseInput()
	 {	 
		 clansScrollingList.handleMouseInput();
		 super.handleMouseInput();
		 
	 }
	 
	 public void updateScreen() {
		 clansScrollingList.updateScreen();
		 super.updateScreen();
		 
	 }
	 

}
