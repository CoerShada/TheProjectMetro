package serb.tp.metro.client.render.tiles;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import serb.tp.metro.Main;
import serb.tp.metro.client.ClientProxy;

public class RenderTileTunnelLine1 extends TileEntitySpecialRenderer {

	private int list;
	private float xOffset = 0;
	private float yOffset = 0;
	private float zOffset = 0;
	ResourceLocation texture =  new ResourceLocation(Main.modid, "textures/models/items/armor/backpack_gekkon.png");
	
    public RenderTileTunnelLine1(ResourceLocation res)
    {
        IModelCustom model;
        model = AdvancedModelLoader.loadModel(res);
        //model = new ModelWrapperDisplayList((WavefrontObject) model);
        list = GL11.glGenLists(1);
        GL11.glNewList(list, GL11.GL_COMPILE);
        model.renderAll();
		
        GL11.glEndList();
    }
    
    public RenderTileTunnelLine1(ResourceLocation res, float xOffset, float yOffset, float zOffset) {
    	this(res);
    	this.xOffset = xOffset;
    	this.yOffset = yOffset;
    	this.zOffset = zOffset;
    }
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float pticks) {
		float size = 10.0F;
		GL11.glPushMatrix();
		
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		//GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		GL11.glTranslated(this.xOffset, this.yOffset, this.zOffset);
		int meta = tileentity.getBlockMetadata()-2;
		if(meta==0) {
			GL11.glTranslated(d0 + 0.405, d1+4.835, d2 + 4.596);
			GL11.glRotated(180, 0, 1, 0);
		}

		else if(meta==1) {
			GL11.glTranslated(d0 - 3.6, d1+4.835, d2 + 0.4);
			GL11.glRotated(90, 0, 1, 0);
		}
		else if(meta==2) {
			GL11.glTranslated(d0 + 0.6, d1+4.835, d2 - 3.6);
			GL11.glRotated(0, 0, 1, 0);
		}
		else if(meta==3) {
			GL11.glTranslated(d0 + 4.605, d1+4.835, d2 + 0.6);
			GL11.glRotated(270, 0, 1, 0);
		}
		GL11.glScaled(size, size, size);
		//ClientProxy.getRenderAll(model)
		GL11.glCallList(list);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		GL11.glPopMatrix();	
	}

}
