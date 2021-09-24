package serb.tp.metro.client.render.items.guns;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import serb.tp.metro.Main;
import serb.tp.metro.client.ClientProxy;
import serb.tp.metro.client.render.loaders.obj.OBJLoader;
import serb.tp.metro.client.render.loaders.obj.Obj;
import serb.tp.metro.items.weapons.ItemFirearmMagWeapon;

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
	
	
	Obj model;
	
	public TestedRenderFirearmMagGun(ResourceLocation res, ItemFirearmMagWeapon gun) {   
        
		model = ClientProxy.loader.loadModel(res);
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

		GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
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
			GL11.glCallList(list);;
			GL11.glShadeModel(GL11.GL_FLAT);
			GL11.glPopMatrix();
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
				GL11.glTranslatef(this.PosX, this.PosY, this.PosZ);
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
		GL11.glShadeModel(GL11.GL_FLAT);
		GL11.glEnable(GL11.GL_CULL_FACE);
	}
}
