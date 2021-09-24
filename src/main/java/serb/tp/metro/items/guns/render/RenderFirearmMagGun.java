package serb.tp.metro.items.guns.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.client.model.obj.WavefrontObject;
import serb.tp.metro.Main;
import serb.tp.metro.client.ClientProxy;
import serb.tp.metro.client.render.ModelWrapperDisplayList;
import serb.tp.metro.items.guns.ItemFirearmMagGun;

public class RenderFirearmMagGun implements IItemRenderer {
	ResourceLocation texture =  new ResourceLocation(Main.modid, "textures/models/items/armor/backpack_gekkon.png");
	private int list;

	
	public RenderFirearmMagGun(ResourceLocation res) {
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
		
		/*if (type == ItemRenderType.INVENTORY) {
			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			GL11.glCallList(list);
			GL11.glScalef(this.onInventorySize, this.onInventorySize, this.onInventorySize);
			GL11.glRotated(45, 1, 0, 0);
			GL11.glPopMatrix();	
		} else if (type == ItemRenderType.EQUIPPED) {
			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			GL11.glCallList(list);
			GL11.glRotated(this.rotateX, 1, 0, 0F);
			GL11.glRotated(this.rotateY, 0, 1, 0);
			GL11.glRotated(this.rotateZ, 0, 0, 1);
			GL11.glScalef(this.inHandSize, this.inHandSize, this.inHandSize);
			GL11.glTranslatef(this.PosX, this.PosY, this.PosZ);
			GL11.glPopMatrix();
			//тут отрисовка магазина
		} 
		else if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
			
			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			GL11.glCallList(list);
			if (ClientProxy.isAiming) {
				GL11.glRotated(this.onAimingRotateX, 1, 0, 0);
				GL11.glRotated(this.onAimingRotateY, 0, 1, 0);
				GL11.glRotated(this.onAimingRotateZ, 0, 0, 1);
				GL11.glTranslatef(this.onAimingPosX, this.onAimingPosY, this.onAimingPosZ);
				
			}
			else {
				GL11.glRotated(this.rotateX, 1, 0, 0);
				GL11.glRotated(this.rotateY, 0, 1, 0);
				GL11.glRotated(this.rotateZ, 0, 0, 1);
				GL11.glTranslatef(this.onAimingPosX, this.onAimingPosY, this.onAimingPosZ);
				//
			}

			GL11.glScalef(this.inHandSize, this.inHandSize, this.inHandSize);
			GL11.glPopMatrix();
		}else {
			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			GL11.glCallList(list);
			GL11.glTranslatef(0F, 0.5F, 0F);
			GL11.glScalef(this.onGroundSize, this.onGroundSize, this.onGroundSize);
			GL11.glPopMatrix();
		}*/
	}
}
