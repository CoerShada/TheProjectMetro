package serb.tp.metro.client.gui.containers;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.MinecraftForgeClient;
import serb.tp.metro.Main;
import serb.tp.metro.client.ClientProxy;
import serb.tp.metro.client.gui.elements.GuiButtonItem;
import serb.tp.metro.client.gui.elements.GuiButtonMore;
import serb.tp.metro.client.render.loaders.obj.Obj;
import serb.tp.metro.client.resources.Resources;
import serb.tp.metro.containers.ContainerCustomization;
import serb.tp.metro.containers.InventoryItemStorage;
import serb.tp.metro.items.Item3D;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.server.EditCustomizationMessage;

public class GuiCustomization extends GuiContainer {
	private int xSize_lo;
	private int ySize_lo;
	private static int xSize = 237;
	private static int ySize = 150;
	private int inventoryRows;
	private InventoryItemStorage maininventory;
	ContainerCustomization cont;
	EntityPlayer player;
	ItemRenderer renderer = new ItemRenderer(Minecraft.getMinecraft());
	Minecraft mc = Minecraft.getMinecraft();
	private int list;
	Item3D item;
	ResourceLocation texture =  new ResourceLocation(Main.modid, "textures/models/items/armor/backpack_gekkon.png");
	int buttonMoreOpened = -1;
	
	
	public GuiCustomization(EntityPlayer player, ItemStack is) {
		super(new ContainerCustomization(1, player, is, xSize/2-9, ySize/2-9));
		this.maininventory = new InventoryItemStorage(is);

		cont = (ContainerCustomization) this.inventorySlots;

		this.player = player;
		this.item = (Item3D) is.getItem();
		
		Obj model = ClientProxy.loader.loadModel(item.model);
	    list = ClientProxy.loader.createDisplayList(model);
	    GL11.glNewList(list, GL11.GL_COMPILE);
	    ClientProxy.loader.render(model);
	    GL11.glEndList();
		
	}

	@Override
    public void initGui()
    {
        super.initGui();
        actualizationButtons();
    }
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float f) {
		super.drawScreen(mouseX, mouseY, f);
		xSize_lo = mouseX;
		ySize_lo = mouseY;
		for (int i = 0; i<this.buttonList.size(); i++){
			GuiButton guiButton = (GuiButton) buttonList.get(i);
			if (guiButton instanceof GuiButtonItem) {
				GuiButtonItem guiButtonItem = (GuiButtonItem) guiButton;
				guiButtonItem.drawButton(mc, mouseX, mouseY);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			}
			
		}
		this.renderWeapon();
		this.renderSlots(mouseX, mouseY, f);
	}

    protected boolean isMouseOverSlot(Slot slot, int x, int y)
    {
        return this.func_146978_c(slot.xDisplayPosition, slot.yDisplayPosition, 16, 16, x, y);
    }
	
	protected void renderSlots(int mouseX, int mouseY, float f) {
		
		ArrayList <ContainerCustomization>containers = this.cont.getSubContainers();
        int k = this.guiLeft;
        int l = this.guiTop;
        GL11.glPushMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float)k, (float)l, 0.0F);
		for (ContainerCustomization cont: containers) {
			//int contX = cont.x;
			//int contY = cont.y;
			for (int i1 = 0; i1 < cont.inventorySlots.size(); i1++)
	        {
				
	            Slot slot = (Slot)cont.inventorySlots.get(i1);
	            RenderHelper.enableGUIStandardItemLighting();
	            this.func_146977_a(slot);
	            RenderHelper.disableStandardItemLighting();
	            if (this.isMouseOverSlot(slot, mouseX, mouseY) && slot.func_111238_b())
	            {
	            	GL11.glPushMatrix();
	                this.theSlot = slot;
	                GL11.glDisable(GL11.GL_LIGHTING);
	                GL11.glDisable(GL11.GL_DEPTH_TEST);
	                int x = slot.xDisplayPosition;
	                int y = slot.yDisplayPosition;
	                GL11.glColorMask(true, true, true, false);
	                float tempZLevel = this.zLevel;
	                this.drawGradientRect(x, y, x + 16, y + 16, -2130706433, -2130706433);
	                GL11.glColorMask(true, true, true, true);
	                GL11.glEnable(GL11.GL_LIGHTING);
	                GL11.glEnable(GL11.GL_DEPTH_TEST);
	                GL11.glPopMatrix();
	                
	            }
	            
	    		GL11.glEnable(GL11.GL_LIGHTING);
	    		GL11.glEnable(GL11.GL_DEPTH_TEST);
	    		InventoryPlayer inventoryplayer = this.mc.thePlayer.inventory;
	    	    if (inventoryplayer.getItemStack() == null && this.theSlot != null && this.theSlot.getHasStack())
	    	    {
	    	    	ItemStack itemstack1 = this.theSlot.getStack();
	    	    	this.renderToolTip(itemstack1, mouseX-this.guiLeft, mouseY-this.guiTop);
	    	    }

	    	    GL11.glEnable(GL11.GL_LIGHTING);
	    	    GL11.glEnable(GL11.GL_DEPTH_TEST);
	    	    RenderHelper.enableStandardItemLighting();
	    	    
	    	    
	        }
		}
		GL11.glPopMatrix();
		 

	}
	
	@Override
	public void drawGuiContainerBackgroundLayer(float i1, int i2, int i3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(Resources.customizationTexture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		ArrayList <ContainerCustomization>containers = this.cont.getSubContainers();

		for (ContainerCustomization cont: containers) {
			int contX = cont.x;
			int contY = cont.y;
			for (int i = 0; i < cont.inventorySlots.size(); i++)
	        {
				Slot slot = (Slot) cont.inventorySlots.get(i);
				this.drawLine(contX, contY, slot.xDisplayPosition, slot.yDisplayPosition);
	        }
		}
		
		ArrayList<int[]> coords = cont.getSlotsCoords();
		
		
		for (int i = 0; i<coords.size(); i++) {
			drawTexturedModalRect(guiLeft + coords.get(i)[0]-1, guiTop + coords.get(i)[1]-1, xSize+1, 0, 18, 18);
		}
		


	}
	
    @Override
    protected void actionPerformed(GuiButton button) {
        super.actionPerformed(button);
        if (button instanceof GuiButtonMore) {
        	GuiButtonMore buttonMore = (GuiButtonMore) button;
        	if(button.id != buttonMoreOpened) {
        		buttonMoreOpened = button.id;
        		actualizationButtons();
        		
        	}
        	else if (button.id==buttonMoreOpened) {
        		buttonMoreOpened=-1;
        		actualizationButtons();
        	}
        	else if (button.id!=buttonMoreOpened && buttonMore.isOpen()) {
        		buttonMoreOpened=buttonMore.id;
        		actualizationButtons();
        	}
        }
        else if (button instanceof GuiButtonItem) {
        	GuiButtonItem buttonItem = (GuiButtonItem) button;
        	buttonMoreOpened = -1;
        	cont.changeItemInRecursionInventory(buttonItem.id, buttonItem.parentID);
        	PacketDispatcher.sendToServer(new EditCustomizationMessage(buttonItem.id, buttonItem.parentID));
        	actualizationButtons();
        	
        }  
    }
    
    private void actualizationButtons() {
        ArrayList<int[]> coords = cont.getSlotsCoords();
        ArrayList<Integer> ids = cont.getSlotsIds();
        
	     int counter = 0;
	     //Очистка кнопок
	    
	     while (counter<this.buttonList.size()) {
	    	 //Очистка всеъ кнопок открытия подъинвентарей если их нет в актуальном дереве инвентаря
	    	 if(this.buttonList.get(counter) instanceof GuiButtonMore) {
	    		 GuiButtonMore tempButton = (GuiButtonMore) this.buttonList.get(counter);
	    		 if (!(ids.contains(tempButton.id))) {
	    			 this.buttonList.remove(counter);
	    			 continue;
	    		 }
	    	 }
	    	 //Очистка всех кнопок-предметов
	    	 else if (this.buttonList.get(counter) instanceof GuiButtonItem) {
		    		GuiButtonItem buttonItem = (GuiButtonItem) this.buttonList.get(counter);
		    		this.buttonList.remove(counter);
		    		continue;
	    	 }
	    	 counter++;
	    }
        
	     //Добавление кнопок открытия подинвентарей, если их нет в текущем листе кнопок
		for (int i = 0; i<coords.size(); i++) {
			int buttonID = ids.get(i);
			boolean find = false;
			for (int j = 0; j<this.buttonList.size(); j++) {
				GuiButton tempButton = (GuiButton) this.buttonList.get(j);
				if (tempButton.id==buttonID) {
					find = true;
					break;
				}
			}
			if (!find) {
				this.buttonList.add(new GuiButtonMore(ids.get(i), guiLeft + coords.get(i)[0]+17, guiTop + coords.get(i)[1]-1));
			}
		}
		//Открытие подъинвентаря и рендер его слотов
		if (this.buttonMoreOpened!=-1) {
			GuiButtonMore buttonMore = null;
			for (int i = 0; i<this.buttonList.size(); i++) {
				GuiButton buttonTemp = (GuiButton) this.buttonList.get(i);
				if (buttonTemp.id == this.buttonMoreOpened) {
					buttonMore = (GuiButtonMore) buttonTemp;
					break;
				}
			}
			if (buttonMore.id == this.buttonMoreOpened && buttonMore.id!=-1) {
				Map<Integer, ItemStack> slots = this.cont.findPotentialSlots(buttonMore.id);
				int x = buttonMore.xPosition-18;
				int mod = 1;
				int buttonSize = 18;
				counter = 0;
				if (buttonMore.xPosition-buttonSize*5<this.guiLeft)
					mod=-1;
				int y = buttonMore.yPosition+buttonSize;
	    			 
				for (Entry<Integer, ItemStack> slot: slots.entrySet()) {
					if (counter%4==0 && counter!=0)
						y+=buttonSize;
					this.buttonList.add(new GuiButtonItem(slot.getKey(), x+(counter%4)*buttonSize, y, buttonMore.id, slot.getValue(), false));
					counter++;
				}
				while (counter%4!=0) {
					this.buttonList.add(new GuiButtonItem(5000+counter, x+(counter%4)*buttonSize, y, buttonMore.id, null, true));
					counter++;
				}
			}
			if (buttonMore.id!= this.buttonMoreOpened && buttonMore.isOpen()) {
				buttonMore.closeButton();
			}
		}

    }
	
	private void renderWeapon() {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		
		RenderHelper.enableStandardItemLighting();
		IItemRenderer render = MinecraftForgeClient.getItemRenderer(player.inventory.getCurrentItem(), ItemRenderType.EQUIPPED);
		
		
		GL11.glPushMatrix();
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glDisable(GL11.GL_CULL_FACE);
		int size = 70;
		GL11.glTranslated(this.guiLeft+this.xSize/2, this.guiTop + this.ySize/2, 0);
		GL11.glScalef(size, size, size);
		GL11.glRotated(-90, 1, 0, 0);
		GL11.glRotated(90, 0, 1, 0);
		GL11.glRotated(-90, 0, 0, 1);
		render.renderItem(ItemRenderType.INVENTORY, player.inventory.getCurrentItem(), this);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glPopMatrix();
		
		RenderHelper.disableStandardItemLighting();
		GL11.glShadeModel(GL11.GL_FLAT);

	}

	protected void drawLine(int x1, int y1, int x2, int y2) {
		
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glColor4f(1F, 1F, 1F, 1F);
		GL11.glLineWidth(2);
		GL11.glBegin(GL11.GL_LINE_STRIP);
	    GL11.glVertex3d(this.guiLeft + x1+9, this.guiTop + y1+9, zLevel);
	    GL11.glVertex3d(this.guiLeft + x2+9, this.guiTop + y2+9, zLevel);
	    GL11.glEnd();
	    GL11.glEnable(GL11.GL_TEXTURE_2D);
	    GL11.glColor4f(1, 1, 1, 1F);
	    GL11.glDisable(GL11.GL_LINE_SMOOTH);
	}
	

}
