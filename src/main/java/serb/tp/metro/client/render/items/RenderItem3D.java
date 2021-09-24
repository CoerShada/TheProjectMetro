package serb.tp.metro.client.render.items;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import serb.tp.metro.Main;
import serb.tp.metro.client.ClientProxy;
import serb.tp.metro.client.render.loaders.obj.Obj;
import serb.tp.metro.items.Item3D;

public class RenderItem3D implements IItemRenderer{
	ResourceLocation texture =  new ResourceLocation(Main.modid, "textures/models/items/armor/backpack_gekkon.png");
	Item3D item;
	private int list;
	
	public RenderItem3D(Item3D item) {
		this.item = item;
		
		Obj model = ClientProxy.loader.loadModel(item.model);
	    list = ClientProxy.loader.createDisplayList(model);
	    GL11.glNewList(list, GL11.GL_COMPILE);
	    ClientProxy.loader.render(model);
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
		GL11.glShadeModel(GL11.GL_SMOOTH);
		//GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		if (type == ItemRenderType.INVENTORY) {
			GL11.glPushMatrix();
			GL11.glScalef(this.item.sizeModel[3], this.item.sizeModel[3], this.item.sizeModel[3]);
			Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			GL11.glCallList(list);
			GL11.glPopMatrix();	
		} else if (type == ItemRenderType.EQUIPPED) {
			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			GL11.glRotated(this.item.rotation[0], 1, 0, 0F);
			GL11.glRotated(this.item.rotation[1], 0, 1, 0);
			GL11.glRotated(this.item.rotation[2], 0, 0, 1);
			GL11.glScalef(this.item.sizeModel[2], this.item.sizeModel[2], this.item.sizeModel[2]);
			GL11.glTranslatef(this.item.pos[0], this.item.pos[1], this.item.pos[2]);
			GL11.glCallList(list);;
			GL11.glShadeModel(GL11.GL_FLAT);
			GL11.glPopMatrix();
		} 
		else if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
			GL11.glPushMatrix();
			GL11.glRotated(this.item.rotation[0], 1, 0, 0);
			GL11.glRotated(this.item.rotation[1], 0, 1, 0);
			GL11.glRotated(this.item.rotation[2], 0, 0, 1);
			GL11.glScalef(this.item.sizeModel[1], this.item.sizeModel[1], this.item.sizeModel[1]);
			GL11.glTranslatef(this.item.pos[0], this.item.pos[1], this.item.pos[2]);
			Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			GL11.glCallList(list);
			GL11.glPopMatrix();
		}else {
			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			GL11.glTranslatef(0F, 0.5F, 0F);
			GL11.glScalef(this.item.sizeModel[3], this.item.sizeModel[3], this.item.sizeModel[3]);
			GL11.glCallList(list);
			GL11.glPopMatrix();
		}
		GL11.glShadeModel(GL11.GL_FLAT);
		GL11.glEnable(GL11.GL_CULL_FACE);
	}
	
}
