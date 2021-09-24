package serb.tp.metro.client.gui.elements;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import serb.tp.metro.blocks.tiles.storages.spawners.TileEntityStorageSpawner;
import serb.tp.metro.client.Type;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.client.SyncGuiContainerSpawnerMessage;
import serb.tp.metro.network.server.ChangeSpawnProbabilityMessage;

public class GuiSliderSpawnItems extends GuiButton{

	 	private float field_146134_p;
	    public boolean field_146135_o;
	    private final float field_146132_r;
	    private final float field_146131_s;
	    public final int xPosDefault;
	    public final int yPosDefault;
	    private static final String __OBFID = "CL_00000680";
	    TileEntityStorageSpawner tile;
	    EntityPlayerMP player;
	    int painted;

	    public float getValue() {
	    	return field_146134_p;
	    }
	    
	    public GuiSliderSpawnItems(int id, int xPos, int yPos, EntityPlayer player, TileEntityStorageSpawner tile, int painted)
	    {
	        this(id, xPos, yPos, 0.0F, 1.0F,player, tile, painted);
	    }

	    public GuiSliderSpawnItems(int id, int xPos, int yPos, float f1, float f2, EntityPlayer player, TileEntityStorageSpawner tile, int painted)
	    {
	        super(id, xPos, yPos, 140, 20, "");
	        this.xPosDefault = xPos;
	        this.yPosDefault = yPos;
	        this.field_146134_p = 1.0F;
	        this.field_146132_r = f1;
	        this.field_146131_s = f2;
	        this.tile = tile;
	        this.painted = painted;
	        this.field_146134_p = tile.spawnProbability[id];
	        this.displayString = Type.getTranslate("guiElements.guiSliderSpawnItems.spawnProbability") + ": " + String.format("%.2f", field_146134_p*100) + "%";
	        
	        
	    }

	    public int getHoverState(boolean p_146114_1_)
	    {
	        return this.painted;
	    }

	    protected void mouseDragged(Minecraft p_146119_1_, int p_146119_2_, int p_146119_3_)
	    {
	        if (this.visible)
	        {
	            if (this.field_146135_o)
	            {
	                this.field_146134_p = (float)(p_146119_2_ - (this.xPosition + 4)) / (float)(this.width - 8);

	                if (this.field_146134_p < 0.0F)
	                {
	                    this.field_146134_p = 0.0F;
	                }

	                if (this.field_146134_p > 1.0F)
	                {
	                    this.field_146134_p = 1.0F;
	                }
	                
	                this.displayString = Type.getTranslate("guiElements.guiSliderSpawnItems.spawnProbability") + ": " + String.format("%.2f", field_146134_p*100) + "%";
	            }

	            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	            this.drawTexturedModalRect(this.xPosition + (int)(this.field_146134_p * (float)(this.width - 8)), this.yPosition, 0, 66, 4, 20);
	            this.drawTexturedModalRect(this.xPosition + (int)(this.field_146134_p * (float)(this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
	        }
	    	PacketDispatcher.sendToServer(new ChangeSpawnProbabilityMessage(id, this.field_146134_p, tile.xCoord, tile.yCoord, tile.zCoord));

	    }


	    public boolean mousePressed(Minecraft p_146116_1_, int p_146116_2_, int p_146116_3_)
	    {
	        if (super.mousePressed(p_146116_1_, p_146116_2_, p_146116_3_))
	        {
	            this.field_146134_p = (float)(p_146116_2_ - (this.xPosition + 4)) / (float)(this.width - 8);

	            if (this.field_146134_p < 0.0F)
	            {
	                this.field_146134_p = 0.0F;
	            }

	            if (this.field_146134_p > 1.0F)
	            {
	                this.field_146134_p = 1.0F;
	            }
	            
	            this.field_146135_o = true;
		    	PacketDispatcher.sendToServer(new ChangeSpawnProbabilityMessage(id, this.field_146134_p, tile.xCoord, tile.yCoord, tile.zCoord));

	            return true;
	        }
	        else
	        {
		    	PacketDispatcher.sendToServer(new ChangeSpawnProbabilityMessage(id, this.field_146134_p, tile.xCoord, tile.yCoord, tile.zCoord));
	            return false;
	        }
	        
	    }

	    public void mouseReleased(int p_146118_1_, int p_146118_2_)
	    {
	    	PacketDispatcher.sendToServer(new ChangeSpawnProbabilityMessage(id, this.field_146134_p, tile.xCoord, tile.yCoord, tile.zCoord));
	        this.field_146135_o = false;
	    }
}
