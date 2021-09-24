package serb.tp.metro.client.gui.containers;

import java.awt.Color;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import serb.tp.metro.Main;
import serb.tp.metro.blocks.tiles.storages.spawners.TileEntityStorageSpawner;
import serb.tp.metro.client.Type;
import serb.tp.metro.client.gui.elements.GuiSliderQuantityItems;
import serb.tp.metro.client.gui.elements.GuiSliderSpawnItems;
import serb.tp.metro.client.resources.Resources;
import serb.tp.metro.containers.ContainerStorageSpawner;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.client.SyncGuiContainerSpawnerMessage;
import serb.tp.metro.network.server.ChangeSpawnProbabilityMessage;
import serb.tp.metro.network.server.ChangeSpawnTimeMessage;

public class GuiStorageSpawner extends GuiContainer{


	private ResourceLocation inventoryTexture = Resources.container_spawner_texture;
	private ResourceLocation slots = Resources.container_spawner_slots_texture;
	
    private GuiTextField hourse;
    private GuiTextField minutes;
    private GuiTextField seconds;
	private final IInventory add_inventory;
	private int xSize_lo;
	private int ySize_lo;
	private int xSize = 200;
	private int ySize = 88;
	private int elemetnsMoveY=0;
	ContainerStorageSpawner cont;
	private final InventoryPlayer inventoryDefault;
	private int sizeTile;
	TileEntityStorageSpawner tile;
	EntityPlayer player;

	public GuiStorageSpawner(EntityPlayer player, TileEntityStorageSpawner tile) {
		super(new ContainerStorageSpawner(player, tile));
		this.inventoryDefault = player.inventory;
		this.add_inventory = tile.spawningIventory;
		if (tile.spawningIventory == null) return;
		cont = (ContainerStorageSpawner) this.inventorySlots;
		this.sizeTile = add_inventory.getSizeInventory();
		this.tile = tile;
		this.player = player;
		}

	@Override
    public void initGui()
    {
        super.initGui(); 
        hourse = new GuiTextField(fontRendererObj, guiLeft+126, guiTop-13, 20, 10);
		minutes = new GuiTextField(fontRendererObj, guiLeft+166, guiTop-13, 20, 10);
		seconds = new GuiTextField(fontRendererObj, guiLeft+206, guiTop-13, 20, 10);

		Long time = tile.spawnTime/1000;
		hourse.setText(String.valueOf((long) time/3600));
		time %=3600;
		minutes.setText(String.valueOf((long) time/60));
		time %=60;
		seconds.setText(String.valueOf((long) time));
		for (int i = 0; i<sizeTile; i++) {
			int left = 163;
			if (i>=6)
				left = 90000;
			if (i%2==0)
				this.buttonList.add(new GuiSliderSpawnItems(i, guiLeft+left, guiTop + 25*i+24 , player, tile, 1));
			else
				this.buttonList.add(new GuiSliderSpawnItems(i, guiLeft+left, guiTop + 25*i+24 , player, tile, 0));
		}
		this.buttonList.add(new GuiButton(399, guiLeft+249, guiTop-22, 65, 20 ,Type.getTranslate("gui.button.saveTime")));
		this.buttonList.add(new GuiSliderQuantityItems(400, guiLeft+125, guiTop, player, tile));
		
    }
	
    @Override
    protected void actionPerformed(GuiButton button) {
        super.actionPerformed(button);
        if (button.id == 399) {
        	this.saveTime();
        }
    }
	
    @Override
    public void updateScreen() {
    	hourse.updateCursorCounter();
    	minutes.updateCursorCounter();
    	seconds.updateCursorCounter();
    }
	
	@Override
    public void handleMouseInput()
    {
		super.handleMouseInput();
		int pixels = Mouse.getDWheel()/6;
		if (pixels>0)
			pixels = -25;
		else if(pixels<0)
			pixels = 25;
		
		elemetnsMoveY-=pixels;
		if (elemetnsMoveY<-25*(sizeTile-6))
			elemetnsMoveY = -25*(sizeTile-6);
		else if(elemetnsMoveY>0)
			elemetnsMoveY = 0;
        cont.shiftSlots(elemetnsMoveY);
        shiftElements();
    }
	
    @Override
    protected void keyTyped(char p_73869_1_, int keyId) {
    	String symbols = "1234567890";
    	if (symbols.contains(Character.toString(p_73869_1_)) && seconds.getText().length()<2 || keyId==14) {
    		this.seconds.textboxKeyTyped(p_73869_1_, keyId);
    		if (this.seconds.getText().length()>0 && new Integer(seconds.getText())>59) {
    			this.seconds.setText("59");
    		}
    	}
    	if (symbols.contains(Character.toString(p_73869_1_)) && minutes.getText().length()<2 || keyId==14) {
    		this.minutes.textboxKeyTyped(p_73869_1_, keyId);
    		if (this.minutes.getText().length()>0 && new Integer(minutes.getText())>59) {
    			this.minutes.setText("59");
    		}
    	}
    	if (symbols.contains(Character.toString(p_73869_1_)) && hourse.getText().length()<2 || keyId==14) {
    		this.hourse.textboxKeyTyped(p_73869_1_, keyId);
    		if (this.hourse.getText().length()>0 && new Integer(hourse.getText())>72) {
    			this.hourse.setText("72");
    		}
    	}
    	super.keyTyped(p_73869_1_, keyId);

    }
	
	private void shiftElements() {
		for (int i = 0; i<sizeTile; i++) {
			GuiSliderSpawnItems button = (GuiSliderSpawnItems) buttonList.get(i);
			button.yPosition=button.yPosDefault+elemetnsMoveY;
			if(button.yPosition<guiTop+2 || button.yPosition>guiTop+150) {
				button.xPosition=guiLeft+90000;
			}
			else {
				button.xPosition=guiLeft+163;
			}
		}
	}
	
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton)  {
    	seconds.mouseClicked(mouseX, mouseY, mouseButton);
    	minutes.mouseClicked(mouseX, mouseY, mouseButton);
    	hourse.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
	
    @Override
    public void drawScreen(int x, int y, float p_73863_3_)
    {
    	
    	seconds.drawTextBox();
    	minutes.drawTextBox();
    	hourse.drawTextBox();
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

    	super.drawScreen(x, y, p_73863_3_);
    }
        

	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY) {
		
		mc.getTextureManager().bindTexture(inventoryTexture);
		drawTexturedModalRect(guiLeft-50, guiTop, 0, 0, xSize, ySize);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		for (int i = 0; i<6; i++) {
			mc.getTextureManager().bindTexture(slots);
			if (i==0) { 
				drawTexturedModalRect(guiLeft+125, guiTop+i*25+19, 0, 0, xSize, 27);
			}
			else  
			{ 
				drawTexturedModalRect(guiLeft+125, guiTop+i*25+21, 0, 50, xSize, 27);
			}

		}
		for (int i = 0; i<6; i++) {
			mc.fontRenderer.drawString(String.valueOf(Math.abs(elemetnsMoveY)/25+i+1), guiLeft+130, guiTop+i*25+30, 0x303030);
		}
		mc.fontRenderer.drawString(Type.getTranslate("characteristic.all.time.hours").substring(0,3)+".", guiLeft+148, guiTop-12, 0x999999);
		mc.fontRenderer.drawString(Type.getTranslate("characteristic.all.time.minuts").substring(0,3)+".", guiLeft+188, guiTop-12, 0x999999);
		mc.fontRenderer.drawString(Type.getTranslate("characteristic.all.time.seconds").substring(0,3)+".", guiLeft+228, guiTop-12, 0x999999);

	}

	public void saveTime() {
        long time = 0;
        if (this.seconds.getText().length()>0)
        	time += new Integer(seconds.getText()) * 1000;
        if (this.minutes.getText().length()>0)
        	time += new Integer(minutes.getText()) * 60000;
        if (this.hourse.getText().length()>0)
        	time += new Integer(hourse.getText()) * 3600000;
        PacketDispatcher.sendToServer(new ChangeSpawnTimeMessage(time, tile.xCoord, tile.yCoord, tile.zCoord));
	}
	
	@Override
    public void onGuiClosed()
    {
        if (this.mc.thePlayer != null)
        {
            this.inventorySlots.onContainerClosed(this.mc.thePlayer);
        }

        this.saveTime();
    }

}
