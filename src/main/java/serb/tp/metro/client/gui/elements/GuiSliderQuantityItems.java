package serb.tp.metro.client.gui.elements;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import serb.tp.metro.blocks.tiles.storages.spawners.TileEntityStorageSpawner;
import serb.tp.metro.client.Type;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.client.SyncGuiContainerSpawnerMaxQuantityLootMessage;
import serb.tp.metro.network.server.ChengeMaxQuantityLootMessage;

public class GuiSliderQuantityItems extends GuiButton{

 	private int value;
    public boolean push;
    private final int min;
    private final int max;
    public final int xPosDefault;
    public final int yPosDefault;
    private static final String __OBFID = "CL_00000680";
    TileEntityStorageSpawner tile;
    EntityPlayerMP player;

    public float getValue() {
    	return value;
    }
    
    public GuiSliderQuantityItems(int id, int xPos, int yPos, EntityPlayer player, TileEntityStorageSpawner tile)
    {
        this(id, xPos, yPos, 0, tile.inventory.getSizeInventory(), player, tile);
    }

    public GuiSliderQuantityItems(int id, int xPos, int yPos, int min, int max, EntityPlayer player, TileEntityStorageSpawner tile)
    {
        super(id, xPos, yPos, 188, 20, "");
        this.xPosDefault = xPos;
        this.yPosDefault = yPos;
        this.min = min;
        this.max = max;
        this.tile = tile;
        this.value = tile.maxQuantityLoot;
        this.displayString = Type.getTranslate("guiElements.guiSliderQuantityItems.maxQuantity") + ": " +  value;
        
        
    }

    public int getHoverState(boolean p_146114_1_)
    {
        return 0;
    }

    protected void mouseDragged(Minecraft p_146119_1_, int p_146119_2_, int p_146119_3_)
    {
        if (this.visible)
        {
            if (this.push)
            {
                this.value = (int) (((p_146119_2_ - (this.xPosition + 4))*this.max / (float)(this.width - 8)));

                if (this.value < min)
                {
                    this.value = min;
                }

                if (this.value > max)
                {
                    this.value = max;
                }
                
                this.displayString = Type.getTranslate("guiElements.guiSliderQuantityItems.maxQuantity") + ": " +  this.value;
                
            }
            PacketDispatcher.sendToServer(new ChengeMaxQuantityLootMessage(this.value, tile.xCoord, tile.yCoord, tile.zCoord));
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(this.xPosition + (int)((float)this.value/this.max  * (this.width - 8)), this.yPosition, 0, 66, 4, 20);
            this.drawTexturedModalRect(this.xPosition + (int)((float)this.value/this.max * (this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
        }
    	

    }


    public boolean mousePressed(Minecraft p_146116_1_, int p_146116_2_, int p_146116_3_)
    {
        if (super.mousePressed(p_146116_1_, p_146116_2_, p_146116_3_))
        {
        	
            this.value =  (int) (((p_146116_2_ - (this.xPosition + 4))*this.max / (float)(this.width - 8)));
            if (this.value < min)
            {
                this.value = min;
            }

            if (this.value > max)
            {
                this.value = max;
            }
            
            this.push = true;
            PacketDispatcher.sendToServer(new ChengeMaxQuantityLootMessage(this.value, tile.xCoord, tile.yCoord, tile.zCoord));

            return true;
        }
        else
        {
            PacketDispatcher.sendToServer(new ChengeMaxQuantityLootMessage(this.value, tile.xCoord, tile.yCoord, tile.zCoord));
            return false;
        }
        
    }

    public void mouseReleased(int p_146118_1_, int p_146118_2_)
    {
        PacketDispatcher.sendToServer(new ChengeMaxQuantityLootMessage(this.value, tile.xCoord, tile.yCoord, tile.zCoord));
        this.push = false;
    }
}