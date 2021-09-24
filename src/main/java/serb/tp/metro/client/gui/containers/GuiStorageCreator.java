package serb.tp.metro.client.gui.containers;

import java.util.concurrent.CompletableFuture;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.common.MinecraftForge;
import serb.tp.metro.blocks.BlockCreator;
import serb.tp.metro.blocks.tiles.storages.creators.TileEntityCreator;
import serb.tp.metro.client.Type;
import serb.tp.metro.client.gui.elements.GuiButtonChooseRecipe;
import serb.tp.metro.client.gui.elements.GuiScrollingList;
import serb.tp.metro.client.resources.Resources;
import serb.tp.metro.containers.ContainerCreator;
import serb.tp.metro.containers.InventoryItemStorage;
import serb.tp.metro.items.ItemBackpack;
import serb.tp.metro.items.ItemChestrig;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.server.NewCraftMessage;

@SideOnly(Side.CLIENT)
public class GuiStorageCreator extends GuiContainer {

	private ResourceLocation textureContainer;
	private TileEntityCreator tile;
	private BlockCreator block;
	private EntityPlayer player;
	private int xSize_lo;
	private int ySize_lo;
	private int xSize = 214;
	private int ySize = 88;
	private int leftOffset;
	private int lastIndexNotRecipe = 0;
	ContainerCreator cont;
	private final InventoryPlayer inventoryDefault;
	private int choosed;
	private GuiButton buttonCraft;
	private GuiScrollingList contentField;
	private int scrollOffset;

	public static RenderItem itemRenderer = new RenderItem();

	public GuiStorageCreator(EntityPlayer player, TileEntityCreator tile) {
		super(new ContainerCreator(player, tile, tile.width, tile.height));
		this.inventoryDefault = player.inventory;
		this.cont = (ContainerCreator) this.inventorySlots;
		this.tile = tile;
		this.block = (BlockCreator) tile.getBlockType();
		this.player = player;
		if (tile.inventory == null) return;
		cont = (ContainerCreator) this.inventorySlots;
		this.leftOffset = 125 - (tile.width+1)*9;
		this.textureContainer = Resources.creator_texture;
		this.scrollOffset = 0;
		}
	
	public int getGuiTop() {
		return this.guiTop;
	}
	

	@Override
    public void initGui()
    {
        super.initGui(); 
        this.buttonCraft = new GuiButton(398, guiLeft+139, guiTop+9, 55, 13,Type.getTranslate("gui.button.createItem"));
		buttonList.add(buttonCraft);
		
		this.lastIndexNotRecipe = buttonList.size();
		for (int i = 0; i<block.recipes.length; i++) {
			boolean thisChoose = false;
			if (choosed == 399 + i)
				 thisChoose = true;
			
			GuiButtonChooseRecipe button = new GuiButtonChooseRecipe(this, 399+i, guiLeft+1, guiTop-14+22*i, 136, 20, block.recipes[i], thisChoose);
			this.buttonList.add(button);
			/*if (i>=3) {
					button.height = button.yPosition-guiTop+9;
			}*/
			//this.buttonList.add(button);
			
	
		}
    }
	
	public int getScrollOffset ()
	{
		return this.scrollOffset;
	}
	
	@Override
    public void handleMouseInput(){
       
		super.handleMouseInput();
            int delta = Mouse.getDWheel();
            if(delta != 0){
                if(delta > 0){
                    delta = -1;
                }else if(delta < 0){
                    delta = 1;
                }
                int maxScrollOffset = Math.max(0, (this.block.recipes.length-4) * 22 + this.guiTop-14);
                this.scrollOffset = (int)Math.max(Math.min(this.scrollOffset + (delta * 10), maxScrollOffset), 0);
            }
        
    }
	
	
	
    @Override
    protected void actionPerformed(GuiButton button) {
        super.actionPerformed(button);
        if (button.id==398 && choosed!=0 && tile.getTimeLeft()==0) {
        	this.buttonCraft.enabled = false;
        	PacketDispatcher.sendToServer(new NewCraftMessage(choosed-399, tile.xCoord, tile.yCoord, tile.zCoord));
    		CompletableFuture.runAsync(() -> {
    			try {
    				Thread.sleep(tile.getTimeLeft()*1000);
    				this.buttonCraft.enabled = true;
    				
    			} catch (InterruptedException e) {
    				
    				e.printStackTrace();
    			}
    		});
        }
        else if(button instanceof GuiButtonChooseRecipe) {
	        GuiButtonChooseRecipe buttonChooseRecipe = (GuiButtonChooseRecipe) button;
	        if (choosed==button.id)
	        	choosed = 0;
	        else
	        	choosed = button.id;
	        for (int i = 0; i<this.buttonList.size(); i++) {
	        	
	        	if (this.buttonList.get(i) instanceof GuiButtonChooseRecipe) {
	        		
	        		boolean choosen = false;
	        		GuiButtonChooseRecipe buttonBuf = (GuiButtonChooseRecipe) this.buttonList.get(i);
	        		if (buttonBuf.id==choosed)
	        			choosen = true;
	        		buttonBuf.choosen = choosen;
	        		this.buttonList.set(i, buttonBuf);
	        	}
	        }	
        }
    }


	

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY) {
		
		mc.getTextureManager().bindTexture(Resources.inventoryTexture);
		drawTexturedModalRect(guiLeft, guiTop+60, 0, 0, xSize-18, ySize);
		
		
		//рендер кастомных слотов когда пустые
		for (int i = 0; i < 3; i++) 
		{
			int noEquip = 0;
			if(inventoryDefault.getStackInSlot(i+15) != null) 
			{
				noEquip = 2000;
			}
			drawTexturedModalRect(guiLeft + 65, guiTop + 67 + 18 * i, noEquip, 18 * i + ySize, 18, 18);
		}
		
		for (int i = 0; i < 2; i++) 
		{
			int noEquip = 18;
			if(inventoryDefault.getStackInSlot(i+18) != null) 
			{
				noEquip = 2000;
			}
			drawTexturedModalRect(guiLeft + 101, guiTop + 67 + 18 * i, noEquip, 18 * i + ySize, 18, 18);
		}	
		
		for (int i = 0; i < 3; i++) 
		{
			int noEquip = 36;
			if(inventoryDefault.getStackInSlot(i) != null) 
			{
				noEquip = 2000;
			}
			drawTexturedModalRect(guiLeft + 65 + 18 * i, guiTop + 123, noEquip, 18 * i + ySize, 18, 18);
		}
		if (inventoryDefault.getStackInSlot(19)!=null && inventoryDefault.getStackInSlot(19).getItem() instanceof ItemChestrig) 
		{
			ItemChestrig item = (ItemChestrig) inventoryDefault.getStackInSlot(19).getItem();
			mc.getTextureManager().bindTexture(Resources.chestrig_Texture);
			drawTexturedModalRect(guiLeft+xSize-18, guiTop+60, 0, 0, xSize, ySize);
			InventoryItemStorage inv = cont.inv;
			int columns = 2;
			if (inv.getSizeInventory()%3!=0)
				columns = 3;
			for (int i = 0; i < inv.getSizeInventory()/columns; ++i) 
			{
				for (int j = 0; j < columns; ++j)
				{ 
					int noEquip = 494;
					if(inv.getStackInSlot(columns * i + j) != null) 
					{
						noEquip = 476;
					}
					drawTexturedModalRect(guiLeft+xSize - 15 +(j*18), guiTop + (i * 18) + 67, noEquip, 0, 18, 18);
				}
			}
		}
		if (inventoryDefault.getStackInSlot(18)!=null && inventoryDefault.getStackInSlot(18).getItem() instanceof ItemBackpack) {
			ItemBackpack itemBackpack = (ItemBackpack) inventoryDefault.getStackInSlot(18).getItem();
			InventoryItemStorage inv = cont.inventoryBackpack;
			mc.getTextureManager().bindTexture(Resources.backpack_texture);
			drawTexturedModalRect(guiLeft, guiTop+143, 0, 0, xSize, ySize);
			int numColumns = inv.getSizeInventory() / 3;
			for (int i = 0; i < 3; ++i) {
				for (int j = 0; j < numColumns; ++j) {

					drawTexturedModalRect(guiLeft+ 70 +(j*18), guiTop + (i * 18) + 144, 494, 0, 18, 18);
				}
			}
		}
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(textureContainer);
		drawTexturedModalRect(guiLeft, guiTop-15, 0, 0, xSize-18, ySize);
		
		

		for (int i = 0; i<tile.height; i++)
			for (int j = 0; j<tile.width; j++) {
				drawTexturedModalRect(guiLeft + 139 + j * 18, guiTop+i*18 + 22, xSize-18, 0, 18, 18);
			}
		
		
		long time = tile.getTimeLeft();
		
		
		if (tile.getTimeLeft()==0) {

	
	        for (int i = this.lastIndexNotRecipe; i<this.buttonList.size(); i++) {
	        	
	        	if (this.buttonList.get(i) instanceof GuiButtonChooseRecipe) {
	        		
	        		GuiButtonChooseRecipe buttonBuf = (GuiButtonChooseRecipe) this.buttonList.get(i);
	        	
	        		if (buttonBuf.id ==choosed) {
	        			time = this.block.recipes[i-this.lastIndexNotRecipe].craftTime/1000;
	
	        		}
	        		
	            	/*int y1;
	            	int y2;
	            	if (buttonBuf.yPosition-this.scrollOffset<this.getGuiTop()-14)
	            		y1 = this.getGuiTop()-14;
	            	else
	            		y1 = buttonBuf.yPosition-this.scrollOffset;
	            	
	            	if (buttonBuf.yPosition + this.height-this.scrollOffset>this.getGuiTop()+58)
	            		y2 = this.getGuiTop()+58;
	            	else
	            		y2 = buttonBuf.yPosition+this.height-this.scrollOffset;*/
	        		
	        		if (buttonBuf.mousePressed(mc, mouseX, mouseY)) {
	        			time = this.block.recipes[i-this.lastIndexNotRecipe].craftTime/1000;
	        			break;
	        		}
	
	        	}
	        }
		}
		else {
			/*if (this.buttonCraft.enabled)
				this.buttonCraft.enabled = false;*/
			time = tile.getTimeLeft();
		}
        
		String hourse = String.valueOf((int) (time/3600));
		if (hourse.length()<2) {
			hourse="0" + hourse;
		}
		time %=3600;
		String minutes = String.valueOf((int) (time/60));
		if (minutes.length()<2) {
			minutes="0" + minutes;
		}
		time %=60;
		String seconds = String.valueOf((int) time);
		if (seconds.length()<2) {
			seconds="0" + seconds;
		}
		
		mc.fontRenderer.drawString(hourse + ":" + minutes + ":" + seconds , guiLeft+140, guiTop-7, 0x303030);

	}

}
