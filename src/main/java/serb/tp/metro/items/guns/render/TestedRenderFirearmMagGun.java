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

public class TestedRenderFirearmMagGun implements IItemRenderer {
	ResourceLocation texture =  new ResourceLocation(Main.modid, "textures/models/items/armor/backpack_gekkon.png");
	private int list;
	private float rotateX;
	private float rotateY;
	private float rotateZ;
	private float onAimingRotateX;
	private float onAimingRotateY;
	private float onAimingRotateZ;
	private float PosX;
	private float PosY;
	private float PosZ;
	private float onAimingPosX;
	private float onAimingPosY;
	private float onAimingPosZ;
	private float onInventorySize;
	private float onGroundSize;
	private float inHandSize;

	private float magPosX;
	private float magPosY;
	private float magPosZ;
	
	public TestedRenderFirearmMagGun(ResourceLocation res, ItemFirearmMagGun gun) {
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
		
		if (type == ItemRenderType.INVENTORY) {
			GL11.glPushMatrix();
			GL11.glScalef(0.25F, 0.25F, 0.25F);
			Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			GL11.glCallList(list);
			GL11.glPopMatrix();	
		} else if (type == ItemRenderType.EQUIPPED) {
			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			GL11.glRotated(-90F, 1, 0, 0F);
			GL11.glRotated(5F, 0, 1, 0);
			GL11.glRotated(40F, 0, 0, 1);
			GL11.glScalef(0.2F, 0.2F, 0.2F);
			GL11.glTranslatef(this.PosX, this.PosY, this.PosZ);
			GL11.glCallList(list);
			GL11.glPopMatrix();
			//тут отрисовка магазина
		} 
		else if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
			
			GL11.glPushMatrix();
			
			if (ClientProxy.isAiming) {
				GL11.glRotated(this.onAimingRotateX, 1, 0, 0);
				GL11.glRotated(this.onAimingRotateY, 0, 1, 0);
				GL11.glRotated(this.onAimingRotateZ, 0, 0, 1);
				GL11.glTranslatef(this.onAimingPosX, this.onAimingPosY, this.onAimingPosZ);
				
			}
			else {
				GL11.glRotated(-25F, 1, 0, 0);
				GL11.glRotated(-5F, 0, 1, 0);
				GL11.glRotated(-40F, 0, 0, 1);
				GL11.glScalef(0.25F, 0.25F, 0.25F);
				GL11.glTranslatef(this.onAimingPosX, this.onAimingPosY, this.onAimingPosZ);
				//
			}
			
			
			Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			GL11.glCallList(list);
			GL11.glPopMatrix();
		}else {
			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			GL11.glCallList(list);
			GL11.glScalef(0F, 0F, 0F);
			GL11.glTranslatef(0F, 0.5F, 0F);
			GL11.glScalef(this.onGroundSize, this.onGroundSize, this.onGroundSize);
			GL11.glPopMatrix();
		}
	}
}
