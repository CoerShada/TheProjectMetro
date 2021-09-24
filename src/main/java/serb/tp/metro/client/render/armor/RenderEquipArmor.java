package serb.tp.metro.client.render.armor;

import java.util.Arrays;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import serb.tp.metro.Main;
import serb.tp.metro.client.ClientProxy;
import serb.tp.metro.client.resources.Resources;
import serb.tp.metro.items.ItemBackpack;
import serb.tp.metro.items.armor.ItemHelmet;
import serb.tp.metro.items.armor.LoadItemArmor;
import serb.tp.metro.items.weapons.ItemWeapon;


public class RenderEquipArmor {

	private static ResourceLocation texture;
	private static String model;

	public static void setResources(Item equip) {

		if(equip instanceof ItemBackpack) {
			texture =  new ResourceLocation(Main.modid, "textures/models/items/armor/"+equip.getUnlocalizedName().substring(5)+".png");
			model = "armor/backpacks/" +equip.getUnlocalizedName().substring(5);
		}
		
		if(equip instanceof ItemHelmet) {
			texture =  new ResourceLocation(Main.modid, "textures/models/items/armor/helmets/"+equip.getUnlocalizedName().substring(5)+"/default.png");
			model = "armor/helmets/"+equip.getUnlocalizedName().substring(5);
		}
		if(equip.equals(LoadItemArmor.armor_redut)) {
			texture =  new ResourceLocation(Main.modid, "textures/models/items/armor/armor.png");
			model = "armor/bulletproofVests/armor_redut";
		}
		if(equip.equals(LoadItemArmor.mask_screen)) {
			texture =  new ResourceLocation(Main.modid, "textures/models/items/armor/backpack_gekkon.png");
			model = "armor/masks/screen";
		}
		if(equip instanceof ItemWeapon) {
			texture =  new ResourceLocation(Main.modid, "textures/models/items/weapons/automatic/"+equip.getUnlocalizedName().substring(5)+".png");
			model = "weapons/automatic/" +equip.getUnlocalizedName().substring(5);
		}
		
	}

	public static void renderHelmet(RenderPlayer renderModel, Item equip, ItemStack item) {
		setResources(equip);
		GL11.glPushMatrix();
		renderModel.modelBipedMain.bipedHead.postRender(0.055F);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		

		GL11.glScalef(0.25F, 0.25F, 0.25F);
		
		GL11.glTranslatef(0F, -1F, 0F);
		
		GL11.glRotatef(180.0f, 1F, 0F, 0F);
		GL11.glRotatef(-90.0f, 0F, 1.05F, 0F);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glCallList(ClientProxy.getRenderAll(model));
		
		
		GL11.glShadeModel(GL11.GL_FLAT);

		GL11.glPopMatrix();
	}
	
	public static void renderMask(RenderPlayer renderModel, Item equip, ItemStack item) {
		texture = Resources.armor[Arrays.asList(Resources.names).indexOf((equip.getUnlocalizedName().substring(5)+"_default"))];
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_NORMALIZE);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		//GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		renderModel.modelBipedMain.bipedHead.postRender(0.0625F);
		GL11.glRotatef(180.0f, 1F, 0F, 0F);
		GL11.glRotatef(-90.0f, 0F, 1F, 0F);
		GL11.glScalef(0.255F, 0.255F, 0.255F);
		GL11.glTranslatef(0F, 0.95F, 0.76F);
		GL11.glCallList(ClientProxy.getRenderAll("armor/masks/" + equip.getUnlocalizedName().substring(5)));
		GL11.glDisable(GL11.GL_NORMALIZE);
		GL11.glPopMatrix();
		if(item.hasTagCompound() && item.getTagCompound().getLong("filter")!=0) 
		{
			GL11.glPushMatrix();
			texture = Resources.armor[Arrays.asList(Resources.names).indexOf("filter_" + equip.getUnlocalizedName().substring(5))];
			GL11.glEnable(GL11.GL_NORMALIZE);
			Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			renderModel.modelBipedMain.bipedHead.postRender(0.0625F);
			GL11.glRotatef(180.0f, 1F, 0F, 0F);
			GL11.glRotatef(-90.0f, 0F, 1F, 0F);
			GL11.glScalef(0.25F, 0.25F, 0.25F);
			GL11.glTranslatef(0F, 0.95F, 0.76F);
			GL11.glCallList(ClientProxy.getRenderAll("armor/masks/filter_" + equip.getUnlocalizedName().substring(5)));
			GL11.glPopMatrix();
		}
		
	}

	public static void renderBulletproofVest(RenderPlayer renderModel, Item equip, ItemStack item) 
	{    
		setResources(equip);
		GL11.glPushMatrix();
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		renderModel.modelBipedMain.bipedBody.postRender(0.0625F);
		GL11.glEnable(GL11.GL_NORMALIZE);
		GL11.glRotatef(180.0f, 1F, 0F, 0F);
		GL11.glRotatef(90.0f, 0F, 1F, 0F);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		GL11.glTranslatef(0F, -5.35F, 0F);
		GL11.glCallList(ClientProxy.getRenderAll(model));
		GL11.glPopMatrix();
		/*if (item.hasTagCompound() && item.getTagCompound().getInteger("defanceArms")==2) 
		{
			GL11.glPushMatrix();
			renderModel.modelBipedMain.bipedLeftArm.postRender(0.0625F);
			GL11.glRotatef(180, 1, 0, 0);
			GL11.glRotatef(-90, 0, 1, 0);
			GL11.glTranslatef(0, -1.2F, 0.31F);
			GL11.glScalef(0.25F, 0.25F, 0.25F);
			GL11.glCallList(ClientProxy.getRenderPart(model, "leftarm"));
			GL11.glPopMatrix();
			
			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			renderModel.modelBipedMain.bipedRightArm.postRender(0.0625F);
			GL11.glRotatef(180, 1, 0, 0);
			GL11.glRotatef(-90, 0F, 1F, 0F);
			GL11.glTranslatef(0, -1.2F, -0.31F);
			GL11.glScalef(0.25F, 0.25F, 0.25F);
			GL11.glCallList(ClientProxy.getRenderPart(model, "rightarm"));
			GL11.glPopMatrix();
		}*/
		GL11.glDisable(GL11.GL_NORMALIZE);


	}
	
	
	public static void renderLegs(RenderPlayer renderModel, Item equip) {
		//Дописать!
		
	}

	public static void renderPants(RenderPlayer renderModel, Item equip) {
		setResources(equip);

		/*GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_NORMALIZE);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		renderModel.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
		GL11.glRotatef(180.0f, 1F, 0F, 0F);
		GL11.glTranslatef(-.1275F, 0.75F, 0F);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		GL11.glCallList(ClientProxy.getRenderPart(model, "legLeft"));
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		renderModel.modelBipedMain.bipedRightLeg.postRender(0.0625F);
		GL11.glRotatef(180.0f, 1F, 0F, 0F);
		GL11.glTranslatef(.1275F, 0.75F, 0F);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		GL11.glCallList(ClientProxy.getRenderPart(model, "legRight"));
		GL11.glDisable(GL11.GL_NORMALIZE);
		GL11.glPopMatrix();*/
	}

	public static void renderBoots(RenderPlayer renderModel, Item equip) {
		setResources(equip);

		/*GL11.glPushMatrix();
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		renderModel.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
		GL11.glRotatef(180.0f, 1F, 0F, 0F);
		GL11.glTranslatef(-.125F, 0.75F, 0F);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		GL11.glCallList(ClientProxy.getRenderPart(model, "bootLeft"));
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		renderModel.modelBipedMain.bipedRightLeg.postRender(0.0625F);
		GL11.glRotatef(180.0f, 1F, 0F, 0F);
		GL11.glTranslatef(.125F, 0.75F, 0F);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		GL11.glCallList(ClientProxy.getRenderPart(model, "bootRight"));
		GL11.glPopMatrix();*/
	}

	public static void renderBackpack(RenderPlayer renderModel, Item equip) {
		setResources(equip);

		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_NORMALIZE);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		renderModel.modelBipedMain.bipedBody.postRender(0.0625F);
		GL11.glRotatef(180.0f, 1F, 0F, 0F);
		GL11.glRotatef(-90.0f, 0F, 1F, 0F);
		GL11.glScalef(0.25F, 0.25F, 0.25F);
		GL11.glTranslatef(-0.1F, -3.6F, 0F);
		GL11.glCallList(ClientProxy.getRenderAll(model));
		GL11.glDisable(GL11.GL_NORMALIZE);
		GL11.glPopMatrix();
	}



	public static void renderBracers(RenderPlayer renderModel, Item equip) {
		setResources(equip);

		/*GL11.glPushMatrix();
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		renderModel.modelBipedMain.bipedLeftArm.postRender(0.0625F);
		GL11.glRotatef(180.0f, 1F, 0F, 0F);
		GL11.glTranslatef(-.315F, .125F, 0F);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		GL11.glCallList(ClientProxy.getRenderPart(model, "bracerLeft"));
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		renderModel.modelBipedMain.bipedRightArm.postRender(0.0625F);
		GL11.glRotatef(180.0f, 1F, 0F, 0F);
		GL11.glTranslatef(.315F, .125F, 0F);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		GL11.glCallList(ClientProxy.getRenderPart(model, "bracerRight"));
		GL11.glPopMatrix();*/
	}

	public static void renderGloves(RenderPlayer renderModel, Item equip) {
		setResources(equip);

		/*GL11.glPushMatrix();
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		renderModel.modelBipedMain.bipedLeftArm.postRender(0.0625F);
		GL11.glRotatef(180.0f, 1F, 0F, 0F);
		GL11.glTranslatef(-.315F, .125F, 0F);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		GL11.glCallList(ClientProxy.getRenderPart(model, "gloveLeft"));
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		renderModel.modelBipedMain.bipedRightArm.postRender(0.0625F);
		GL11.glRotatef(180.0f, 1F, 0F, 0F);
		GL11.glTranslatef(.315F, .125F, 0F);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		GL11.glCallList(ClientProxy.getRenderPart(model, "gloveRight"));
		GL11.glPopMatrix();*/
	}
	
	public static void renderGun(RenderPlayer renderModel, Item equip) {
		setResources(equip);
		
		GL11.glPushMatrix();
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		renderModel.modelBipedMain.bipedBody.postRender(0.0625F);
		GL11.glTranslatef(-0.26F, 0.25F, 0.28F);
		GL11.glRotatef(90.0f, 1F, 0F, 0F);
		GL11.glRotatef(180.0f, 0F, 0F, 1F);
		GL11.glRotatef(3.0f, 0F, 1F, 0F);
		GL11.glScalef(0.06F, 0.06F, 0.06F);
		//GL11.glCallList(ClientProxy.getRenderAll(model));
		
		GL11.glPopMatrix();
	}


}