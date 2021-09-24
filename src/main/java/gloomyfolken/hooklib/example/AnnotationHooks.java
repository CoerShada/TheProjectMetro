package gloomyfolken.hooklib.example;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Project;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gloomyfolken.hooklib.asm.Hook;
import gloomyfolken.hooklib.asm.ReturnCondition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.model.obj.Face;
import net.minecraftforge.client.model.obj.Vertex;
import serb.tp.metro.Main;

public class AnnotationHooks {
	
	
    /**
     * Уменьшение хотбара до 6 слотов
     * 
     */
    /*@Hook(injectOnExit = true, returnCondition = ReturnCondition.ALWAYS) 
    public static int getHotbarSize(InventoryPlayer inventory)
    {
        return 6;
    }*/
    
    @Hook(returnCondition = ReturnCondition.ALWAYS)
    @SideOnly(Side.CLIENT)
    public static void drawPanorama(GuiMainMenu menu, int p_73970_1_, int p_73970_2_, float p_73970_3_)
    {
    	Tessellator tessellator = Tessellator.instance;
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        Project.gluPerspective(90.0F, 1.0F, 0.05F, 10.0F);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glPushMatrix();

        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Main.modid, "textures/gui/guiScreen/background/bg.png"));
        tessellator.startDrawingQuads();
        float f4 = 0.0F;
        tessellator.addVertexWithUV(-1.0D, -1.0D, 1.0D, (double)(0.0F + f4), (double)(0.0F + f4));
        tessellator.addVertexWithUV(1.0D, -1.0D, 1.0D, (double)(1.0F - f4), (double)(0.0F + f4));
        tessellator.addVertexWithUV(1.0D, 1.0D, 1.0D, (double)(1.0F - f4), (double)(1.0F - f4));
        tessellator.addVertexWithUV(-1.0D, 1.0D, 1.0D, (double)(0.0F + f4), (double)(1.0F - f4));
        tessellator.draw();
        GL11.glPopMatrix();    
        GL11.glColorMask(true, true, true, false);
        tessellator.setTranslation(0.0D, 0.0D, 0.0D);
        GL11.glColorMask(true, true, true, true);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPopMatrix();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPopMatrix();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }
    
    @Hook(createMethod = true, returnCondition = ReturnCondition.ALWAYS)
    @SideOnly(Side.CLIENT)
    public static void drawScreen(GuiMainMenu menu, int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        
        renderSkybox(menu, p_73863_1_, p_73863_2_, p_73863_3_);
        int k;
        for (k = 0; k < menu.buttonList.size(); ++k)
        {
            ((GuiButton)menu.buttonList.get(k)).drawButton(menu.mc, p_73863_1_, p_73863_2_);
        }

    }
    
    @Hook(targetMethod = "<init>")
    @SideOnly(Side.CLIENT)
    public static void guiButtonConstructor(GuiButton button, int p_i1021_1_, int p_i1021_2_, int p_i1021_3_, int p_i1021_4_, int p_i1021_5_, String p_i1021_6_) {
    	button.buttonTextures = new ResourceLocation(Main.modid, "textures/gui/guiScreen/guiBattonsMainMenu/background.png");
    }
    
    /*@Hook(injectOnExit = true)
    @SideOnly(Side.CLIENT)
    public static void initGui(GuiOptions menu)
    {
    	
    }*/
    
    /*@Hook(returnCondition = ReturnCondition.ALWAYS)
    public static Vertex calculateFaceNormal(Face face)
    {
    	
        Vec3 v2 = Vec3.createVectorHelper(face.vertices[1].x - face.vertices[0].x, face.vertices[1].y - face.vertices[0].y, face.vertices[1].z - face.vertices[0].z);
        Vec3 v3 = Vec3.createVectorHelper(face.vertices[2].x - face.vertices[1].x, face.vertices[2].y - face.vertices[1].y, face.vertices[2].z - face.vertices[1].z);
        //рассчитываем нормаль к полигону
        Vec3 normalVector = v2.crossProduct(v3).normalize();
        //если нормали в obj модели нет, то рассчитываем
        if(face.vertexNormals == null) {

        	return new Vertex((float) normalVector.xCoord, (float) normalVector.yCoord, (float) normalVector.zCoord);
        } 
        else { //если есть, то берем готовую из obj модели

        	return new Vertex((float) face.vertexNormals[0].x, (float) face.vertexNormals[0].y, (float) face.vertexNormals[0].z);
        }
    }*/
    
    @SideOnly(Side.CLIENT)
    @Hook(returnCondition = ReturnCondition.ALWAYS)
    public static void hurtCameraEffect(EntityRenderer e, float f)
    {
        Minecraft mc = Minecraft.getMinecraft();
        EntityLivingBase entitylivingbase = mc.renderViewEntity;
        float f1 = (float)entitylivingbase.hurtTime - f;
        float f2;

        
        if (f1 >= 0.0F)
        {
            f1 /= (float)entitylivingbase.maxHurtTime;
            f1 = MathHelper.sin(f1 * f1 * f1 * f1 * (float)Math.PI);
            f2 = entitylivingbase.attackedAtYaw;
            GL11.glRotatef(0, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0 * 14.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(0, 0.0F, 1.0F, 0.0F);
        }
    }
    
    @Hook(injectOnExit = true)
    @SideOnly(Side.CLIENT)
    public static void initGui(GuiMainMenu menu)
    {
    	int k = 0;
    	for (int i = 0; i<menu.buttonList.size(); i++) {
    		GuiButton tempbutton = ((GuiButton) menu.buttonList.get(i));
    		if (tempbutton.id==5 ||  tempbutton.id==6 || tempbutton.id==14) {
    			((GuiButton) menu.buttonList.get(i)).visible = false;
    		}
    		else {
    			tempbutton.xPosition = menu.width-120;
    			if (tempbutton.id!=4)
    				tempbutton.yPosition = k*22+25;
    			tempbutton.width = 118;
    			k++;
    		}	
    	}
    }
    
    @Hook(injectOnExit = true)
    @SideOnly(Side.CLIENT)
    public static void initGui(GuiIngameMenu menu)
    {
    	for (int i = 0; i<menu.buttonList.size(); i++) {
    		GuiButton tempbutton = ((GuiButton) menu.buttonList.get(i));
    		
    		if (tempbutton.id==6 /*|| tempbutton.id==7*/ || tempbutton.id==12)
    			tempbutton.visible=false;
    		}
    	
    	GuiButton thisbutton = ((GuiButton) menu.buttonList.get(0));
    	thisbutton.xPosition = menu.width/2-148/2;
    	thisbutton.width = 148;
    	thisbutton.yPosition = 4*22+75;
    	
    	thisbutton = ((GuiButton) menu.buttonList.get(1));
    	thisbutton.xPosition = menu.width/2-148/2;
    	thisbutton.width = 148;
    	thisbutton.yPosition = 0*22+75;
    	
    	thisbutton = ((GuiButton) menu.buttonList.get(2));
    	thisbutton.xPosition = menu.width/2-148/2;
    	thisbutton.width = 148;
    	thisbutton.yPosition = 2*22+75;
    	
    	thisbutton = ((GuiButton) menu.buttonList.get(5));
    	thisbutton.xPosition = menu.width/2-148/2;
    	thisbutton.width = 148;
    	thisbutton.yPosition = 1*22+75;
    }
    
    
    
    @Hook(targetMethod = "<init>")
    @SideOnly(Side.CLIENT)
    public static void musicTickerConstuctor(MusicTicker mt, Minecraft p_i45112_1_)
    {
    	MusicTicker.MusicType.MENU.field_148645_h = new ResourceLocation(Main.modid, "main_menu_music");
    }
    
    @Hook()
    @SideOnly(Side.CLIENT)
    public static void drawButton(GuiButton button, Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_)
    {
        if (button.visible)
        {
        	p_146112_1_.getTextureManager().bindTexture(new ResourceLocation(Main.modid, "textures/gui/guiScreen/guiBattonsMainMenu/background.png"));
        }

    }
    
    @Hook(returnCondition = ReturnCondition.ALWAYS)
    @SideOnly(Side.CLIENT)
    public static void renderSkybox(GuiMainMenu menu, int p_73971_1_, int p_73971_2_, float p_73971_3_)
    {
    	drawPanorama(menu, p_73971_1_, p_73971_2_, p_73971_3_);
    }
    


    /**
     * Цель: запретить возможность телепортироваться в ад и обратно чаще, чем раз в пять секунд.
     */
    @Hook(returnCondition = ReturnCondition.ON_TRUE, intReturnConstant = 100)
    public static boolean getPortalCooldown(EntityPlayer player) {
        return player.dimension == 0;
    }

    
    /**
     * Получение итема из хотбара
     */
    /*@Hook(injectOnExit = true, returnCondition = ReturnCondition.ALWAYS) 
    public static ItemStack getCurrentItem(InventoryPlayer inventory)
    {
        return inventory.currentItem < 6 && inventory.currentItem >= 0 ? inventory.mainInventory[inventory.currentItem] : null;
    }*/
    
    /*@SideOnly(Side.CLIENT)
    @Hook(returnCondition = ReturnCondition.ALWAYS) 
    public static void changeCurrentItem(InventoryPlayer inventory, int p_70453_1_)
    {
        if (p_70453_1_ > 0)
        {
            p_70453_1_ = 1;
        }

        if (p_70453_1_ < 0)
        {
            p_70453_1_ = -1;
        }

        for (inventory.currentItem -= p_70453_1_; inventory.currentItem < 0; inventory.currentItem += 6)
        {
            ;
        }

        while (inventory.currentItem >= 6)
        {
        	inventory.currentItem -= 6;
        }
    }*/
}
