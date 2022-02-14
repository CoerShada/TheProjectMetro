package serb.tp.metro.client.gui.elements;

import java.awt.Color;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.common.MinecraftForge;
import serb.tp.metro.client.Type;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.server.ChengeMaxQuantityLootMessage;

public class GuiListScrolling<T extends GuiButton> extends GuiScreen{
	private final GuiScreen screen;
	private int x;
	private int y;
	private int width;
	private int height;
	private int elementHeight;
	private int scrollY = 0;
	private float scrollSpeed = 10f;
	private ArrayList<T> content = new ArrayList<T>();
	private int value = 0;
	private boolean push = false;
	private GuiButtonScroll scrollButton;
	
	public GuiListScrolling(GuiScreen screen, int x, int y, int width, int height, int elementHeight) {
		this.screen = screen;
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
		this.elementHeight = elementHeight;
	}
	
	 @Override
	 public void initGui() {
		 this.scrollButton = new GuiButtonScroll(1, this.x+this.width-5, this.y, 5, this.height);
		 this.buttonList.add(this.scrollButton);
	 }
	
    @Override
    protected void actionPerformed(GuiButton button) {
        
    }
	
	public void addContent(T  element) {
		element.xPosition = this.x;
		element.yPosition = this.y;
		this.content.add(element);
	}
	
	public T getContentByIndex(int index) {
		return content.get(index);
	}
	
	public int getContentSize() {
		return content.size();
	}
	
	@Override
	public void drawScreen(int x, int y, float tick)
	{
		
		this.drawRect(this.x, this.y, this.width+this.x, this.height+this.y, new Color(255, 255, 255).getRGB());
		super.drawScreen(x, y, tick);
	}
	
	
    @Override
    public void setWorldAndResolution(Minecraft mc, int width, int height)
    {
        this.mc = mc;
        this.fontRendererObj = mc.fontRenderer;

        if (!MinecraftForge.EVENT_BUS.post(new InitGuiEvent.Pre(this, this.buttonList)))
        {
            this.buttonList.clear();
            this.initGui();

        }
        MinecraftForge.EVENT_BUS.post(new InitGuiEvent.Post(this, this.buttonList));
    }
    

	
	protected void glScissor(int x, int y, int width, int height){
		Minecraft mc = Minecraft.getMinecraft();
	    ScaledResolution resolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
	    int scale = resolution.getScaleFactor();

	    int scissorWidth = width * scale;
	    int scissorHeight = height * scale;
	    int scissorX = x * scale;
	    int scissorY = mc.displayHeight - scissorHeight - (y * scale);
	    GL11.glScissor(scissorX, scissorY, scissorWidth, scissorHeight);
	}
	

	private class GuiButtonScroll extends GuiButton{

		private final int MAIN_COLOR = new Color(230, 230, 230).getRGB();
		private boolean push = false;
		private int value;
		private int min;
		private int max;
		private int size;
		
		
		public GuiButtonScroll(int id, int xPos, int yPos, int width, int height) {
			super(id, xPos, yPos, width, height, "");
			this.value = 0;
			this.min = 0;
			this.max = getContentSize();
			setScrollRectSize();
		}
		
		public void setScrollRectSize() {
			this.size = ((Math.floorDiv(height, elementHeight))/getContentSize())*height;
			if (this.size>height) this.size = height;
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
	            //PacketDispatcher.sendToServer(new ChengeMaxQuantityLootMessage(this.value, tile.xCoord, tile.yCoord, tile.zCoord));
	            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	            //this.drawRect(this.xPosition, this.yPosition + (int)((float)this.value/this.max  * (this.width - 8)), this.width, this.size, this.MAIN_COLOR);
	            //this.drawTexturedModalRect(this.xPosition + (int)((float)this.value/this.max * (this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
	        }
	    	

	    }
		
		public void drawButton(Minecraft mc, int mouseX_, int mouseY)
	    {
	        if (this.visible)
	        {
	        	
	            GL11.glPushMatrix();
	            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	            this.field_146123_n = mouseX_ >= this.xPosition && mouseY >= this.yPosition && mouseX_ < this.xPosition + this.width && mouseY < this.yPosition + this.height;
	            int k = this.getHoverState(this.field_146123_n);
	            GL11.glEnable(GL11.GL_BLEND);
	            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
	            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	            GL11.glScalef(0.5F, 0.5F, 0.5F);

	            this.drawRect(x + this.xPosition, y+this.yPosition, this.width+x+this.xPosition, this.height+y+this.yPosition, MAIN_COLOR);
	            this.mouseDragged(mc, mouseX_, mouseY);
	            GL11.glPopMatrix();

	        }
	    }
		
		
	}
}
