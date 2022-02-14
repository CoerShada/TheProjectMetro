package serb.tp.metro.client.render.tiles;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import serb.tp.metro.Main;

public class RenderTileTunnelTurnLeft extends TileEntitySpecialRenderer {

	private int list;
	ResourceLocation texture =  new ResourceLocation(Main.modid, "textures/models/items/armor/backpack_gekkon.png");
	
    public RenderTileTunnelTurnLeft(ResourceLocation res)
    {
        IModelCustom model;
        model = AdvancedModelLoader.loadModel(res);
        //model = new ModelWrapperDisplayList((WavefrontObject) model);
        list = GL11.glGenLists(1);
        GL11.glNewList(list, GL11.GL_COMPILE);
        model.renderAll();
		
        GL11.glEndList();
    }
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float pticks) {
		float size = 10.0F;
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		int meta = tileentity.getBlockMetadata();
		if(meta==0) {
			GL11.glTranslated(d0 + 7.2125, d1+4.835, d2 + 14.0);
			GL11.glRotated(180, 0, 1, 0);
		}

		else if(meta==1) {
			GL11.glTranslated(d0 - 13.0, d1+4.835, d2 + 7.215);
			GL11.glRotated(90, 0, 1, 0);
		}
		else if(meta==2) {
			GL11.glTranslated(d0 - 6.21, d1+4.835, d2 - 13.0);
			GL11.glRotated(0, 0, 1, 0);
		}
		else if(meta==3) {
			GL11.glTranslated(d0 + 14.01, d1+4.835, d2 - 6.215);
			GL11.glRotated(270, 0, 1, 0);
		}
		
		GL11.glScaled(size, size, size);
		GL11.glCallList(list);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		GL11.glPopMatrix();	
	}

}
