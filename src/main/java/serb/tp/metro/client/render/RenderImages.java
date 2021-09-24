package serb.tp.metro.client.render;

import java.awt.Color;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiPlayerInfo;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.Direction;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.chunk.Chunk;
import serb.tp.metro.Main;
import serb.tp.metro.containers.CustomSlots;
import serb.tp.metro.entities.player.ExtendedPlayer;
import serb.tp.metro.items.armor.ItemHelmet;
import serb.tp.metro.items.armor.LoadItemArmor;

public class RenderImages extends GuiIngame {
	String texture = "";

	public RenderImages(Minecraft minecraft) {
		super(minecraft);
	}
	
    public void renderGameOverlay(float p_73830_1_, boolean p_73830_2_, int p_73830_3_, int p_73830_4_)
    {
        ScaledResolution scaledresolution = new ScaledResolution(mc, this.mc.displayWidth, this.mc.displayHeight);
        int k = scaledresolution.getScaledWidth();
        int l = scaledresolution.getScaledHeight();
        FontRenderer fontrenderer = this.mc.fontRenderer;
        mc.entityRenderer.setupOverlayRendering();
        GL11.glEnable(GL11.GL_BLEND);


        if (mc.gameSettings.thirdPersonView == 0 && mc.thePlayer.inventory.getStackInSlot(15) != null)
        {
            renderMask(k, l);
        }

        if(mc.gameSettings.thirdPersonView == 0 && mc.thePlayer.inventory.armorItemInSlot(3)!=null && mc.thePlayer.inventory.armorItemInSlot(3).getItem() instanceof ItemHelmet && mc.thePlayer.inventory.armorItemInSlot(3).hasTagCompound() && mc.thePlayer.inventory.armorItemInSlot(3).getTagCompound().getString("visor").equalsIgnoreCase("close")) {
        	renderVisor(k, l);
        }

        int i1;
        int j1;
        int k1;

        
        int l4;


        l4 = 16777215;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 0F);
        i1 = k / 2 - 91;
        int l1;
        int i2;
        int j2;
        int k2;
        float f3;
        short short1;

        if (this.mc.playerController.gameIsSurvivalOrAdventure())
        {
            this.mc.mcProfiler.startSection("expBar");
            this.mc.getTextureManager().bindTexture(Gui.icons);
            j1 = this.mc.thePlayer.xpBarCap();


            this.mc.mcProfiler.endSection();

        }


        
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
    }
	
	
    public void renderMask(int x, int y)
    {
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.03F);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        String texture = "textures/gui/hud/masks/" + mc.thePlayer.inventory.getStackInSlot(15).getUnlocalizedName().substring(5)+".png";
        if(!this.texture.equals(texture))
        	this.texture = texture;
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Main.modid, this.texture));
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0D, (double)y, -90.0D, 0.0D, 1.0D);
        tessellator.addVertexWithUV((double)x, (double)y, -90.0D, 1.0D, 1.0D);
        tessellator.addVertexWithUV((double)x, 0.0D, -90.0D, 1.0D, 0.0D);
        tessellator.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
        tessellator.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        
    }
    
    public void renderVisor(int x, int y)
    {
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1F);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        String texture = "textures/gui/hud/visors/" + mc.thePlayer.inventory.armorItemInSlot(3).getUnlocalizedName().substring(5)+".png";
        if(!this.texture.equals(texture))
        	this.texture = texture;
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Main.modid, this.texture));
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0D, (double)y, -90.0D, 0.0D, 1.0D);
        tessellator.addVertexWithUV((double)x, (double)y, -90.0D, 1.0D, 1.0D);
        tessellator.addVertexWithUV((double)x, 0.0D, -90.0D, 1.0D, 0.0D);
        tessellator.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
        tessellator.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        
    }

}
