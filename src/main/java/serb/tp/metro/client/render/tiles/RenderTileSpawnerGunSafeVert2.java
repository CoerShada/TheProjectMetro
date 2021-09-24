package serb.tp.metro.client.render.tiles;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import serb.tp.metro.Main;

public class RenderTileSpawnerGunSafeVert2 extends TileEntitySpecialRenderer {

	private int list;
	ResourceLocation texture =  new ResourceLocation(Main.modid, "textures/models/items/armor/backpack_gekkon.png");
	
    public RenderTileSpawnerGunSafeVert2(ResourceLocation res)
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
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_CULL_FACE);
		//GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		int meta = tileentity.getBlockMetadata();

		if(meta==0) {
			GL11.glTranslated(d0+0.5, d1+1.35, d2+1);
			GL11.glRotated(180, 0, 1, 0);
			
		}
		else if(meta==1) {
			GL11.glTranslated(d0, d1+1.35, d2+0.5);
			GL11.glRotated(90, 0, 1, 0);
		}
		else if(meta==2) {
			GL11.glTranslated(d0+0.5, d1+1.35, d2);
			GL11.glRotated(0, 0, 1, 0);
		}
		else if(meta==3) {
			GL11.glTranslated(d0+1, d1+1.35, d2+0.5);
			GL11.glRotated(270, 0, 1, 0);
		}
		GL11.glRotated(90, 1, 0, 0);
		GL11.glScaled(0.45, 0.45, 0.45);
		GL11.glCallList(list);
		GL11.glEnable(GL11.GL_CULL_FACE);
		//GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();	
	}

}
