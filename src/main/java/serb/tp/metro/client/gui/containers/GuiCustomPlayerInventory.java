package serb.tp.metro.client.gui.containers;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import serb.tp.metro.Main;
import serb.tp.metro.client.resources.Resources;
import serb.tp.metro.containers.ContainerCustomPlayer;
import serb.tp.metro.containers.InventoryItemStorage;
import serb.tp.metro.entities.player.handlers.RadiationHandler;
import serb.tp.metro.entities.player.handlers.ThirstyHandler;
import serb.tp.metro.entities.player.handlers.WeightHandler;
import serb.tp.metro.items.ItemChestrig;

public class GuiCustomPlayerInventory extends GuiContainer {

	private int xSize_lo;
	private int ySize_lo;
	private int xSize;
	private int ySize;
	private static final ResourceLocation THIRST_ICON = new ResourceLocation(Main.modid, "textures/gui/thirst.png");	
	private static final ResourceLocation FOOD_ICON = new ResourceLocation(Main.modid, "textures/gui/food.png");	

	ContainerCustomPlayer cont;
	private final InventoryPlayer inventoryDefault;

	public GuiCustomPlayerInventory(EntityPlayer player) {
		super(new ContainerCustomPlayer(player));
		this.inventoryDefault = player.inventory;
		cont = (ContainerCustomPlayer) this.inventorySlots;
		xSize = 200;
		ySize = 88;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float f) {
		super.drawScreen(mouseX, mouseY, f);
		xSize_lo = mouseX;
		ySize_lo = mouseY;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(Resources.inventoryTexture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		//рендер кастомных слотов когда пустые
		for (int i = 0; i < 3; i++) 
		{
			int noEquip = 0;
			if(inventoryDefault.getStackInSlot(i+15) != null) 
			{
				noEquip = 2000;
			}
			drawTexturedModalRect(guiLeft + 65, guiTop + 7 + 18 * i, noEquip, 18 * i + ySize, 18, 18);
		}
		
		for (int i = 0; i < 2; i++) 
		{
			int noEquip = 18;
			if(inventoryDefault.getStackInSlot(i+18) != null) 
			{
				noEquip = 2000;
			}
			drawTexturedModalRect(guiLeft + 101, guiTop + 7 + 18 * i, noEquip, 18 * i + ySize, 18, 18);
		}	
		
		for (int i = 0; i < 3; i++) 
		{
			int noEquip = 36;
			if(inventoryDefault.getStackInSlot(i) != null) 
			{
				noEquip = 2000;
			}
			drawTexturedModalRect(guiLeft + 65 + 18 * i, guiTop + 63, noEquip, 18 * i + ySize, 18, 18);
		}
		if (inventoryDefault.getStackInSlot(19)!=null && inventoryDefault.getStackInSlot(19).getItem() instanceof ItemChestrig) 
		{
			ItemChestrig item = (ItemChestrig) inventoryDefault.getStackInSlot(19).getItem();
			mc.getTextureManager().bindTexture(Resources.chestrig_Texture);
			drawTexturedModalRect(guiLeft+xSize-4, guiTop, 0, 0, xSize, ySize);
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
					drawTexturedModalRect(guiLeft+xSize -1 +(j*18), guiTop + (i * 18) + 7, noEquip, 0, 18, 18);
				}
			}
		}

		
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					
		mc.getTextureManager().bindTexture(Resources.thirst_icon);
		Gui.func_146110_a(guiLeft+3, guiTop+26, 0, 0, 8, 8, 8, 8);				
		mc.fontRenderer.drawString(String.valueOf((int)(ThirstyHandler.getThirsty(mc.thePlayer)/ThirstyHandler.getThirstyMax(mc.thePlayer)*100)) + "%", guiLeft+12, guiTop+26, 0x303030);
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(Resources.food_icon);
		Gui.func_146110_a(guiLeft+3, guiTop+38, 0, 0, 9, 9, 9, 9);				
		mc.fontRenderer.drawString(String.valueOf(100 * mc.thePlayer.getFoodStats().getFoodLevel()/20) + "%", guiLeft+12, guiTop+38, 0x303030);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(Resources.weight_icon);
		Gui.func_146110_a(guiLeft+3, guiTop+68, 0, 0, 9, 9, 9, 9);				
		mc.fontRenderer.drawString(String.format("%.2f", WeightHandler.getWeight(mc.thePlayer)/1000), guiLeft+12, guiTop+70, 0x303030);
		
		
		mc.fontRenderer.drawString(String.format("%.2f", RadiationHandler.getRadiation(mc.thePlayer))+" M3B", guiLeft+12, guiTop+50, 0x303030);
		//рендер игрока в инвентаре
		GuiInventory.func_147046_a(guiLeft - 70, guiTop + 165, 85, guiLeft + 51 - xSize_lo, guiTop + 25 - ySize_lo, mc.thePlayer);		
	}
	

}