package serb.tp.metro.client.gui.containers;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import serb.tp.metro.Main;
import serb.tp.metro.blocks.tiles.storages.TileEntityRack;
import serb.tp.metro.blocks.tiles.storages.TileEntityStorage;
import serb.tp.metro.client.resources.Resources;
import serb.tp.metro.containers.ContainerBackpackStorage;
import serb.tp.metro.containers.ContainerCustomPlayer;
import serb.tp.metro.containers.InventoryItemStorage;
import serb.tp.metro.containers.StorageContainer;
import serb.tp.metro.items.ItemBackpack;
import serb.tp.metro.items.ItemChestrig;

@SideOnly(Side.CLIENT)
public class GuiStorage extends GuiContainer {

	private ResourceLocation textureContainer;

	private TileEntityStorage tile;
	private int xSize_lo;
	private int ySize_lo;
	private int xSize = 214;
	private int ySize = 88;
	private int leftOffset;
	StorageContainer cont;
	private final InventoryPlayer inventoryDefault;

	public GuiStorage(EntityPlayer player, TileEntityStorage tile) {
		super(new StorageContainer(player, tile, tile.width, tile.height));
		this.inventoryDefault = player.inventory;
		this.tile = tile;
		if (tile.inventory == null) return;
		cont = (StorageContainer) this.inventorySlots;
		this.leftOffset = 125 - (tile.width+1)*9;
		this.textureContainer = Resources.container_texture;
		//this.textureContainer = new ResourceLocation(Adtime.modid, "textures/gui/containers/"+ tile.getBlockType().getUnlocalizedName().substring(5) +".png");
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
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize-18, ySize);
		
		for (int i = 0; i<tile.height; i++)
			for (int j = 0; j<tile.width; j++) {
				drawTexturedModalRect(guiLeft + this.leftOffset - 1 + j * 18, guiTop+i*18 + 4, xSize-18, 0, 18, 18);
			}


	}

}
