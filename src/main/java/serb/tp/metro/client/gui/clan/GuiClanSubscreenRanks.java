package serb.tp.metro.client.gui.clan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.common.MinecraftForge;
import serb.tp.metro.client.Type;
import serb.tp.metro.client.gui.elements.GuiButtonClan;
import serb.tp.metro.client.gui.elements.GuiButtonRank;
import serb.tp.metro.client.gui.elements.GuiButtonTextured;
import serb.tp.metro.client.gui.elements.GuiScrollingList;
import serb.tp.metro.client.gui.elements.IClickListener;
import serb.tp.metro.common.clans.Clan;
import serb.tp.metro.common.clans.ClanHandler;
import serb.tp.metro.common.clans.Permission;
import serb.tp.metro.common.clans.Rank;
import serb.tp.metro.common.clans.Relation;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.general.AddClanMessage;

public class GuiClanSubscreenRanks extends GuiSubscreen{

	private GuiScrollingList<GuiButtonRank> ranksScrollingList;
	private ArrayList<Rank> ranks;
	private Rank currentRank;
	
	public GuiClanSubscreenRanks(EntityPlayer player, int x, int y, GuiClanMainWindow parent) {
		super(player, x, y, parent);
		this.height=143;
		this.width=163;
		
		ClanHandler handler = ClanHandler.get(Minecraft.getMinecraft().theWorld);
		ranks = handler.getClanRanks(parent.clan.getId());
	}

    @Override
    public void initGui() {
        super.initGui();
        int margin = 10;
        
        IClickListener<GuiButtonRank> listener = new IClickListener<GuiButtonRank>() {

			@Override
			public void onEntryClicked(GuiButtonRank entry, int mouseX, int mouseY, int buttonNumber) {
				
				if (currentRank!=entry.getRank())
					currentRank = entry.getRank();
				else
					currentRank = null;
			}
        	
        };
        
		this.ranksScrollingList = new GuiScrollingList<GuiButtonRank>(this, this.x, this.y+15, 105, this.height-this.y-16, margin, listener);

		int i = 0;
		for (Rank rank: ranks) {

			this.ranksScrollingList.addElement(new GuiButtonRank(1310+rank.getId(), ranksScrollingList.getX(), ranksScrollingList.getY() + i, "button_clans_list.png", rank, player));
			i+=this.ranksScrollingList.getEntryHeight();
		} 
    	Keyboard.enableRepeatEvents(true);
	    buttonList.add(new GuiButtonTextured(13000, this.x+110, this.y+110, Type.getTranslate("gui.button.create"), 80, 15, "button_clans.png"));
	    buttonList.add(new GuiButtonTextured(13001, this.x+110, this.y+125, Type.getTranslate("gui.button.edit"), 80, 15, "button_clans.png"));
    
	    buttonList.add(new GuiButtonTextured(13002, this.x+110, this.y+15, "", 10, 10, "button_clans_arrow_up.png"));
	    buttonList.add(new GuiButtonTextured(13003, this.x+110, this.y+25, "", 10, 10, "button_clans_arrow_down.png"));

    }
    
	@Override
	protected void actionPerformed(GuiButton button) {
		 
		if(button.id == 13000) {
			if (parent.rank.isPermitted(Permission.ChangeSubordination) && parent.rank.getSubordinationIndex()>currentRank.getSubordinationIndex() && ranks.indexOf(currentRank)>1 && ranks.get(ranks.indexOf(currentRank)-1)!=parent.rank) {
				//Тут свап рангов
			}
		}
		if(button.id == 13001) {
			if (parent.rank.isPermitted(Permission.ChangeSubordination) && parent.rank.getSubordinationIndex()>currentRank.getSubordinationIndex() && ranks.indexOf(currentRank)> ranks.size()-1) {
				//Тут свап рангов
			}
		}
 
		super.actionPerformed(button);

	}
	 
	@Override
	protected void keyTyped(char ch, int keyId) {
		 super.keyTyped(ch, keyId);
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

        }
        MinecraftForge.EVENT_BUS.post(new InitGuiEvent.Post(this, this.buttonList));
    }
    
    
	@Override
	public void drawScreen(int x, int y, float p_73863_3_)
	{	
		super.drawScreen(x, y, p_73863_3_); 
		ranksScrollingList.drawScreen(x, y, p_73863_3_);

	}
	
	 @Override
	 protected void mouseClicked(int mouseX, int mouseY, int mouseButton)  { 
		 ranksScrollingList.mouseClicked(mouseX, mouseY, mouseButton);

		 super.mouseClicked(mouseX, mouseY, mouseButton);
	 }
	 
	 @Override
	 protected void mouseClickMove(int mouseX, int mouseY, int button, long time) {
		 super.mouseClickMove(mouseX, mouseY, button, time);
		 ranksScrollingList.mouseClickMove(mouseX, mouseY, button);
	 }
	 
	 public void handleMouseInput()
	 {	 
		 ranksScrollingList.handleMouseInput();
		 super.handleMouseInput();
		 
	 }
	 
	 public void updateScreen() {
		 ranksScrollingList.updateScreen();
		 super.updateScreen();
		 
	 }
	    
	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}
	
	
	
	

}
