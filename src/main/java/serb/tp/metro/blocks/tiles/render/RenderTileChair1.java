package serb.tp.metro.blocks.tiles.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.client.model.obj.WavefrontObject;
import serb.tp.metro.Main;
import serb.tp.metro.client.ClientProxy;
import serb.tp.metro.client.render.ModelWrapperDisplayList;

public class RenderTileChair1 extends TileEntitySpecialRenderer  {

	private int list;
	ResourceLocation texture =  new ResourceLocation(Main.modid, "textures/models/items/armor/backpack_gekkon.png");
	
    public RenderTileChair1(ResourceLocation res)
    {
        IModelCustom model;
        model = AdvancedModelLoader.loadModel(res);
        model = new ModelWrapperDisplayList((WavefrontObject) model);
        list = GL11.glGenLists(1);
        GL11.glNewList(list, GL11.GL_COMPILE);
        model.renderAll();
		
        GL11.glEndList();
    }
	
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float pticks) {
		GL11.glPushMatrix();
		//GL11.glEnable(GL12.GL_RESCALE_NORMAL);
    	Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		int meta = tileentity.getBlockMetadata()-2;
		GL11.glTranslated(d0 + 0.5, d1+0.38, d2 + 0.5);
		GL11.glRotated(90*meta, 0, 1, 0);
		GL11.glScaled(0.02, 0.02, 0.02);
		GL11.glCallList(list);
		//GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();	
	}

}
