package serb.tp.metro.blocks.render;

import java.util.Arrays;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.client.model.obj.WavefrontObject;
import serb.tp.metro.Main;
import serb.tp.metro.blocks.tiles.TileEntityTunnelLine;
import serb.tp.metro.blocks.tiles.render.RenderTileTunnelLine1;
import serb.tp.metro.client.ClientProxy;
import serb.tp.metro.client.render.ModelWrapperDisplayList;
import serb.tp.metro.client.resources.Resources;

public class RenderBlockModelled implements IItemRenderer {
	
	private int list;
	private final double size;
	private double xOffset;
	private double yOffset;
	private double zOffset;
	ResourceLocation texture =  new ResourceLocation(Main.modid, "textures/models/items/armor/backpack_gekkon.png");
	
	public RenderBlockModelled(ResourceLocation res, double size, double xOffset, double yOffset, double zOffset) {
		this.size = size;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.zOffset = zOffset;
        IModelCustom model;
        model = AdvancedModelLoader.loadModel(res);
        model = new ModelWrapperDisplayList((WavefrontObject) model);
        list = GL11.glGenLists(1);
        
        GL11.glNewList(list, GL11.GL_COMPILE);
        model.renderAll();
		
        GL11.glEndList();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		if (type == ItemRenderType.INVENTORY) 
		{
			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glDisable(GL11.GL_CULL_FACE);
	    	Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			GL11.glRotated(90, 0, 1, 0);
			GL11.glScaled(this.size, this.size, this.size);
			GL11.glCallList(list);
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();	
			
			
		} 
		else if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) 
		{

			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glDisable(GL11.GL_CULL_FACE);
	    	Minecraft.getMinecraft().renderEngine.bindTexture(texture);
	    	
			GL11.glRotated(90, 0, 1, 0);
			GL11.glScaled(this.size, this.size, this.size);
			GL11.glTranslated(xOffset, yOffset, zOffset);
			GL11.glCallList(list);
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();	
		
		}
		else if (type == ItemRenderType.EQUIPPED) 
		{

			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glDisable(GL11.GL_CULL_FACE);
	    	Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			GL11.glRotated(90, 0, 1, 0);
			GL11.glScaled(this.size, this.size, this.size);
			GL11.glTranslated(xOffset, yOffset, zOffset);
			GL11.glCallList(list);
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();	
		
		}
		else 
		{
			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glDisable(GL11.GL_CULL_FACE);
	    	Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			GL11.glRotated(90, 0, 1, 0);
			GL11.glScaled(this.size * 3, this.size * 3, this.size * 3);
			GL11.glCallList(list);
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();	
		}
		
	}




}
