package serb.tp.metro.client.gui.clan;

import java.util.HashMap;
import java.util.Map.Entry;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.common.MinecraftForge;
import serb.tp.metro.client.gui.elements.GuiButtonClan;
import serb.tp.metro.client.gui.elements.GuiButtonTextured;
import serb.tp.metro.client.gui.elements.GuiScrollingList;
import serb.tp.metro.client.gui.elements.IClickListener;
import serb.tp.metro.common.clans.Clan;
import serb.tp.metro.common.clans.ClanHandler;
import serb.tp.metro.common.clans.Permission;
import serb.tp.metro.common.clans.Relation;
import serb.tp.metro.common.clans.RelationType;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.server.ChangeRelationMessage;

public class GuiClanSubscreenClans extends GuiSubscreen{

	private GuiScrollingList<GuiButtonClan> clansScrollingList;
	private HashMap<Integer, Clan> clans;
	private HashMap<String, Relation> relations;
	private Clan currentClan;
	private Relation relation;
	
	
	public GuiClanSubscreenClans(EntityPlayer player, int x, int y, GuiClanMainWindow parent) {
		super(player, x, y, parent);
		
		
		ClanHandler handler = ClanHandler.get(Minecraft.getMinecraft().theWorld);
		clans = handler.getClans();
		relations = handler.getRelations();
	}

	public void bindCurrentClan(Clan clan) {
		if (this.currentClan!=clan) {
			this.currentClan = clan;
			String id = "";
			if (parent.clan!=null) {
				if (clan.getId()<parent.clan.getId()) {
					id= clan.getId()+"|"+parent.clan.getId();
				}
				else {
					id= parent.clan.getId() + "|" + clan.getId();
				}
			}
			this.relation = relations.get(id);
		}
		else {
			this.currentClan=null;
			this.relation = null;
		}
	}
	
	 @Override
	 protected void actionPerformed(GuiButton button) {
		 switch (button.id) {
		 	case 12000:
		 		relation.toDeclare(RelationType.WAR);
		 		PacketDispatcher.sendToServer(new ChangeRelationMessage(relation.getNBT()));
		 		break;
		 	case 12001:
		 		relation.improveRelations(parent.clan.getId());
		 		PacketDispatcher.sendToServer(new ChangeRelationMessage(relation.getNBT()));
		 		break;
		 	case 12002:
		 		relation.toDeclare(RelationType.WAR);
		 		PacketDispatcher.sendToServer(new ChangeRelationMessage(relation.getNBT()));
		 		break;
		 	case 12003:
		 		relation.improveRelations(parent.clan.getId());
		 		PacketDispatcher.sendToServer(new ChangeRelationMessage(relation.getNBT()));
		 		break;
		 	case 12004:
		 		relation.improveRelations(parent.clan.getId());
		 		PacketDispatcher.sendToServer(new ChangeRelationMessage(relation.getNBT()));
		 		break;
		 	case 12005:
		 		relation.toDeclare(RelationType.NEUTRAL);
		 		PacketDispatcher.sendToServer(new ChangeRelationMessage(relation.getNBT()));
		 		break;
		 	case 12006:
		 		relation.improveRelations(parent.clan.getId());
		 		PacketDispatcher.sendToServer(new ChangeRelationMessage(relation.getNBT()));
		 		break;
		 }
		 
	 }
	
    @Override
    public void initGui() {
        super.initGui();
        int margin = 10;
        IClickListener<GuiButtonClan> listener = new IClickListener<GuiButtonClan>() {

			@Override
			public void onEntryClicked(GuiButtonClan entry, int mouseX, int mouseY, int buttonNumber) {
		    	GuiButtonClan button = (GuiButtonClan) entry;
		    	bindCurrentClan(button.getClan());
		    	updateButtonStatus();
			}
        	
        };
		this.clansScrollingList = new GuiScrollingList<GuiButtonClan>(this, this.x, this.y+15, 105, this.height-this.y-16, margin,  listener);

		int i = 0;
		for (Entry<Integer, Clan> entry: clans.entrySet()) {
			Clan clan = entry.getValue();
			Relation relation = null;
			if (parent.clan!=null) {
				String id;
				if (clan.getId()<parent.clan.getId()) {
					id = clan.getId() + "|" +parent.clan.getId();
				}
				else {
					id = parent.clan.getId() + "|" + clan.getId();
				}
				relation = this.relations.get(id);
			}
			this.clansScrollingList.addElement(new GuiButtonClan(1210+clan.getId(), this.clansScrollingList.getX(), this.clansScrollingList.getY() + i, "button_clans_list.png", entry.getValue(), parent.clan, relation,player));
			i+=this.clansScrollingList.getEntryHeight();
		}
		
		
		Keyboard.enableRepeatEvents(true);
		
		buttonList.add(new GuiButtonTextured(12000, this.x + 120, this.y + 15, "Объявить войну", 60, 15, "button_clans_menu.png"));
		
		buttonList.add(new GuiButtonTextured(12001, this.x + 120, this.y + 35, "Предложить нейтралитет", 60, 15, "button_clans_menu.png"));
		buttonList.add(new GuiButtonTextured(12002, this.x + 120, this.y + 35, "Отозвать нейтралитет", 60, 15, "button_clans_menu.png"));
		buttonList.add(new GuiButtonTextured(12003, this.x + 120, this.y + 35, "Принять нейтралитет", 60, 15, "button_clans_menu.png"));

		buttonList.add(new GuiButtonTextured(12004, this.x + 120, this.y + 55, "Предложить союз", 60, 15, "button_clans_menu.png"));
		buttonList.add(new GuiButtonTextured(12005, this.x + 120, this.y + 55, "Отозвать союз", 60, 15, "button_clans_menu.png"));
		buttonList.add(new GuiButtonTextured(12006, this.x + 120, this.y + 55, "Принять союз", 60, 15, "button_clans_menu.png"));

		
		buttonList.add(new GuiButtonTextured(12007, this.x + 120, this.y + 100, "Предложить договор", 60, 15, "button_clans_menu.png"));
		buttonList.add(new GuiButtonTextured(12008, this.x + 120, this.y + 120, "Выдвинуть ультиматум", 60, 15, "button_clans_menu.png"));
		
		buttonList.add(new GuiButtonTextured(12009, this.x + 120, this.y + 120, "Подать заявку", 60, 15, "button_clans_menu.png"));
		updateButtonStatus();
    }
    
    public void updateButtonStatus() {

		for (int i = 0; i<this.buttonList.size(); i++) {
			GuiButton button = (GuiButton) buttonList.get(i);
			button.visible = false;
		}
		GuiButton button;
		if (parent.clan==null && relation==null) {
			button = (GuiButton) buttonList.get(9);
			button.visible=true;
			
			button = (GuiButton) buttonList.get(7);
			button.visible = true;

		}
		else if (relation!=null){
			if (parent.rank.isPermitted(Permission.ChangeRelation)) {
				if (relation.getType()==RelationType.WAR && relation.getImprooveRelations()==-1 ) {
					button = (GuiButton) buttonList.get(1);
					button.visible = true;
				}
				else if (relation.getType()==RelationType.WAR && relation.getImprooveRelations()==parent.clan.getId()) {
					button = (GuiButton) buttonList.get(2);
					button.visible = true;
				}
				else if(relation.getType()==RelationType.WAR) {
					button = (GuiButton) buttonList.get(3);
					button.visible = true;
				}
				
				else if (relation.getType()==RelationType.NEUTRAL && relation.getImprooveRelations()==-1) {
					button = (GuiButton) buttonList.get(0);
					button.visible = true;
					
					button = (GuiButton) buttonList.get(4);
					button.visible = true;
					
				}
				else if(relation.getType()==RelationType.NEUTRAL && relation.getImprooveRelations()==parent.clan.getId()) {
					button = (GuiButton) buttonList.get(0);
					button.visible = true;
					
					button = (GuiButton) buttonList.get(5);
					button.visible = true;
				}
				else if(relation.getType()==RelationType.NEUTRAL) {
					button = (GuiButton) buttonList.get(0);
					button.visible = true;
					
					button = (GuiButton) buttonList.get(6);
					button.visible = true;
				}
				else {
					button = (GuiButton) buttonList.get(0);
					button.visible = true;
					
					button = (GuiButton) buttonList.get(5);
					button.visible = true;
				}
			}
			button = (GuiButton) buttonList.get(7);
			button.visible = true;
			button = (GuiButton) buttonList.get(8);
			button.visible = true;

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
