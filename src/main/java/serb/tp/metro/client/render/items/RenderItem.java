package serb.tp.metro.client.render.items;

import java.util.Arrays;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import serb.tp.metro.Main;
import serb.tp.metro.client.ClientProxy;
import serb.tp.metro.client.resources.Resources;
import serb.tp.metro.items.ItemBackpack;
import serb.tp.metro.items.armor.ItemHelmet;
import serb.tp.metro.items.armor.LoadItemArmor;
import serb.tp.metro.items.weapons.ItemBullet;
import serb.tp.metro.items.weapons.ItemWeapon;

public class RenderItem {			

	private static ResourceLocation texture;
	private static String model;

	public static void setResources(ItemStack item) {				
		Item equip = item.getItem();
		if(equip instanceof ItemBullet) {
			texture =  new ResourceLocation(Main.modid, "textures/models/items/armor/backpack_gekkon.png");
			model = "bullets/" + item.getUnlocalizedName().substring(5, item.getUnlocalizedName().lastIndexOf("_"));
		}
		if(equip instanceof ItemBackpack) {
			texture =  new ResourceLocation(Main.modid, "textures/models/items/armor/"+item.getUnlocalizedName().substring(5)+".png");
			model = "armor/backpacks/" +item.getUnlocalizedName().substring(5);
		}
		if(equip instanceof ItemHelmet) 
		{
			texture =  new ResourceLocation(Main.modid, "textures/models/items/armor/backpack_gekkon.png");
			model = "armor/helmets/" + item.getUnlocalizedName().substring(5);
		}
		if(equip.equals(LoadItemArmor.armor_redut)) {
			texture =  new ResourceLocation(Main.modid, "textures/models/items/armor/backpack_gekkon.png");
			model = "armor/bulletproofVests/"+ item.getUnlocalizedName().substring(5);
		}
		if(equip.equals(LoadItemArmor.mask_screen)) {
			texture =  new ResourceLocation(Main.modid, "textures/models/items/armor/backpack_gekkon.png");
			model = "armor/masks/"+ item.getUnlocalizedName().substring(5);
		}
		if(equip instanceof ItemWeapon) {
			System.out.println("we here");
			texture =  new ResourceLocation(Main.modid, "textures/models/items/weapons/automatic/"+item.getUnlocalizedName().substring(5)+".png");
			model = "weapons/automatic/" +item.getUnlocalizedName().substring(5);
		}


	}

	public static class renderItemHelmet implements IItemRenderer {

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
			setResources(item);
			GL11.glShadeModel(GL11.GL_SMOOTH);
			if (type == ItemRenderType.INVENTORY) 
			{
				GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texture);
				GL11.glScalef(0.4F, 0.4F, 0.4F);
				GL11.glCallList(ClientProxy.getRenderAll(model));
				/*if(item.hasTagCompound() && item.getTagCompound().getString("visor").equalsIgnoreCase("open")) 
				{
					GL11.glCallList(ClientProxy.getRenderAll(model));
				}
				else if(item.hasTagCompound() && item.getTagCompound().getString("visor").equalsIgnoreCase("close")) 
				{
					GL11.glCallList(ClientProxy.getRenderPart(model, "close"));
				}*/
				GL11.glPopMatrix();	
			} 
			else if (type == ItemRenderType.EQUIPPED) 
			{
				GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texture);
				GL11.glTranslatef(0.25F, 0F, 0.5F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glCallList(ClientProxy.getRenderAll(model));
				/*if(item.hasTagCompound() && item.getTagCompound().getString("visor").equalsIgnoreCase("open")) 
				{
					GL11.glCallList(ClientProxy.getRenderPart(model, "open"));
				}
				else if(item.hasTagCompound() && item.getTagCompound().getString("visor").equalsIgnoreCase("close")) 
				{
					GL11.glCallList(ClientProxy.getRenderPart(model, "close"));
				}*/
				GL11.glPopMatrix();
			}
			else 
			{
				GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texture);
				GL11.glTranslatef(0F, 0F, 0F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glCallList(ClientProxy.getRenderAll(model));
				
				GL11.glPopMatrix();
			}
			GL11.glShadeModel(GL11.GL_FLAT);
		}
	}
	
	public static class renderItemMask implements IItemRenderer {

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
		    	setResources(item);
		    	
			if (type == ItemRenderType.INVENTORY) 
			{
				GL11.glPushMatrix();
				texture = Resources.armor[Arrays.asList(Resources.names).indexOf(item.getUnlocalizedName().substring(5)+"_default")];
				Minecraft.getMinecraft().renderEngine.bindTexture(texture);
				GL11.glScalef(0.4F, 0.4F, 0.4F);
				GL11.glCallList(ClientProxy.getRenderAll("armor/masks/" + item.getUnlocalizedName().substring(5)));
				GL11.glPopMatrix();	
				if(item.hasTagCompound() && item.getTagCompound().getLong("filter")!=0) 
				{
					GL11.glPushMatrix();
					texture = Resources.armor[Arrays.asList(Resources.names).indexOf("filter_" + item.getUnlocalizedName().substring(5))];
					GL11.glEnable(GL11.GL_NORMALIZE);
					Minecraft.getMinecraft().renderEngine.bindTexture(texture);
					GL11.glScalef(0.4F, 0.4F, 0.4F);
					GL11.glCallList(ClientProxy.getRenderAll("armor/masks/filter_" + item.getUnlocalizedName().substring(5)));
					GL11.glPopMatrix();
				}
				
				
			} 
			else if (type == ItemRenderType.EQUIPPED) 
			{

				GL11.glPushMatrix();
				texture = Resources.armor[Arrays.asList(Resources.names).indexOf(item.getUnlocalizedName().substring(5)+"_default")];
				Minecraft.getMinecraft().renderEngine.bindTexture(texture);
				GL11.glScalef(0.4F, 0.4F, 0.4F);
				GL11.glRotated(180, 0, 1, 0);
				GL11.glTranslated(-2F, 0, -1F);
				GL11.glCallList(ClientProxy.getRenderAll("armor/masks/" + item.getUnlocalizedName().substring(5)));
				GL11.glPopMatrix();	
				if(item.hasTagCompound() && item.getTagCompound().getLong("filter")!=0) 
				{
					GL11.glPushMatrix();
					texture = Resources.armor[Arrays.asList(Resources.names).indexOf("filter_" + item.getUnlocalizedName().substring(5))];
					GL11.glEnable(GL11.GL_NORMALIZE);
					Minecraft.getMinecraft().renderEngine.bindTexture(texture);
					GL11.glScalef(0.4F, 0.4F, 0.4F);
					GL11.glRotated(180, 0, 1, 0);
					GL11.glTranslated(-2F, 0, -1F);
					GL11.glCallList(ClientProxy.getRenderAll("armor/masks/filter_" + item.getUnlocalizedName().substring(5)));
					GL11.glPopMatrix();
				}
			}
			else if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) 
			{
				GL11.glPushMatrix();
				texture = Resources.armor[Arrays.asList(Resources.names).indexOf(item.getUnlocalizedName().substring(5)+"_default")];
				Minecraft.getMinecraft().renderEngine.bindTexture(texture);
				GL11.glScalef(0.4F, 0.4F, 0.4F);
				GL11.glRotated(180, 0, 1, 0);
				GL11.glTranslated(0.25F, 3.5F, 0.5F);
				GL11.glCallList(ClientProxy.getRenderAll("armor/masks/" + item.getUnlocalizedName().substring(5)));
				GL11.glPopMatrix();	
				if(item.hasTagCompound() && item.getTagCompound().getLong("filter")!=0) 
				{
					GL11.glPushMatrix();
					texture = Resources.armor[Arrays.asList(Resources.names).indexOf("filter_" + item.getUnlocalizedName().substring(5))];
					GL11.glEnable(GL11.GL_NORMALIZE);
					Minecraft.getMinecraft().renderEngine.bindTexture(texture);
					GL11.glRotated(180, 0, 1, 0);
					GL11.glScalef(0.4F, 0.4F, 0.4F);
					GL11.glTranslated(0.25F, 3.5F, 0.5F);
					GL11.glCallList(ClientProxy.getRenderAll("armor/masks/filter_" + item.getUnlocalizedName().substring(5)));
					GL11.glPopMatrix();
				}
			}
			else 
			{
				GL11.glPushMatrix();
				texture = Resources.armor[Arrays.asList(Resources.names).indexOf(item.getUnlocalizedName().substring(5)+"_default")];
				Minecraft.getMinecraft().renderEngine.bindTexture(texture);
				GL11.glScalef(0.4F, 0.4F, 0.4F);
				GL11.glCallList(ClientProxy.getRenderAll("armor/masks/" + item.getUnlocalizedName().substring(5)));
				GL11.glPopMatrix();	
				if(item.hasTagCompound() && item.getTagCompound().getLong("filter")!=0) 
				{
					GL11.glPushMatrix();
					texture = Resources.armor[Arrays.asList(Resources.names).indexOf("filter_" + item.getUnlocalizedName().substring(5))];
					GL11.glEnable(GL11.GL_NORMALIZE);
					Minecraft.getMinecraft().renderEngine.bindTexture(texture);
					GL11.glScalef(0.4F, 0.4F, 0.4F);
					GL11.glCallList(ClientProxy.getRenderAll("armor/masks/filter_" + item.getUnlocalizedName().substring(5)));
					GL11.glPopMatrix();
				}
			}
		}
	}

	public static class renderItemBulletproofVest implements IItemRenderer {

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
			setResources(item);

			if (type == ItemRenderType.INVENTORY) {
				GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texture);
				GL11.glTranslatef(0F, -1.1F, 0F);
				GL11.glScalef(0.32F, 0.32F, 0.32F);
				GL11.glCallList(ClientProxy.getRenderAll(model));
				
				GL11.glPopMatrix();	
			} 
			else if (type == ItemRenderType.EQUIPPED) 
			{
				GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texture);
				GL11.glTranslatef(-0.25F, 1.5F, 0.75F);
				GL11.glScalef(1F, 1F, 1F);
				GL11.glCallList(ClientProxy.getRenderAll(model));
				GL11.glPopMatrix();
			} 
			else 
			{
				GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texture);
				GL11.glTranslatef(0F, 0.5F, 0F);
				GL11.glScalef(0.5F, 0.5F, 0.5F);
				GL11.glCallList(ClientProxy.getRenderAll(model));
				GL11.glPopMatrix();
			}
		}
	}
	
	public static class renderItemBackpack implements IItemRenderer {

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
			setResources(item);
			GL11.glShadeModel(GL11.GL_SMOOTH);
			if (type == ItemRenderType.INVENTORY) {
				GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texture);
				GL11.glRotated(90, 0, 1, 0);
				GL11.glTranslatef(0F, -0.8F, 0F);
				GL11.glScalef(0.4F, 0.4F, 0.4F);
				GL11.glCallList(ClientProxy.getRenderAll(model));
				GL11.glPopMatrix();	
			} else if (type == ItemRenderType.EQUIPPED) {
				GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texture);
				GL11.glTranslatef(0.25F, 0F, 0.5F);
				GL11.glRotatef(90F, 0, 1, 0);
				GL11.glScalef(0.65F, 0.65F, 0.65F);
				GL11.glCallList(ClientProxy.getRenderAll(model));
				GL11.glPopMatrix();
			} else if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
				GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texture);
				GL11.glTranslatef(0.25F, 0F, 0.5F);
				GL11.glScalef(0.4F, 0.4F, 0.4F);
				GL11.glCallList(ClientProxy.getRenderAll(model));
				GL11.glPopMatrix();
			} else {
				GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texture);
				GL11.glTranslatef(0F, 1.4F, 0F);
				GL11.glScalef(0.5F, 0.5F, 0.5F);
				GL11.glCallList(ClientProxy.getRenderAll(model));
				GL11.glPopMatrix();
			}
			GL11.glShadeModel(GL11.GL_FLAT);
		}
	}

	public static class renderItemBullet implements IItemRenderer {

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
			setResources(item);
			GL11.glShadeModel(GL11.GL_SMOOTH);
			if (type == ItemRenderType.INVENTORY) {
				GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texture);
				GL11.glTranslatef(0F, 0F, 0F);
				GL11.glScalef(0.4F, 0.4F, 0.4F);
				GL11.glCallList(ClientProxy.getRenderAll(model));
				GL11.glPopMatrix();	
			} else if (type == ItemRenderType.EQUIPPED) {
				GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texture);
				GL11.glTranslatef(0.25F, 2.5F, 0.5F);
				GL11.glCallList(ClientProxy.getRenderAll(model));
				GL11.glPopMatrix();
			}
			else {
				GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texture);
				GL11.glTranslatef(0F, 1.2F, 0F);
				GL11.glScalef(0.5F, 0.5F, 0.5F);
				GL11.glCallList(ClientProxy.getRenderAll(model));
				GL11.glPopMatrix();
			}
			GL11.glShadeModel(GL11.GL_FLAT);
		}
	}





	public static class renderItemGun implements IItemRenderer {

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
			setResources(item);
			GL11.glShadeModel(GL11.GL_SMOOTH);
			if (type == ItemRenderType.INVENTORY) {
				GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texture);
				GL11.glScalef(0.13F, 0.13F, 0.13F);
				GL11.glRotated(15, 1, 0, 0);
				//GL11.glCallList(ClientProxy.getRenderAll(model));
				GL11.glPopMatrix();	
			} else if (type == ItemRenderType.EQUIPPED) {
				GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texture);
				
				GL11.glRotated(225F, 0F, 1F, 0F);
				GL11.glRotated(60F, 1F, 0F, 0F);
				GL11.glRotated(180F, 0F, 1F, 0F);
				GL11.glScalef(0.3F, 0.3F, 0.3F);
				GL11.glTranslatef(-1.5F, -6.5F, 2.5F);
				GL11.glCallList(ClientProxy.getRenderAll(model));
				GL11.glPopMatrix();
			} 
			else if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
				
				GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texture);
				if (ClientProxy.isAiming) {
					GL11.glRotated(-45, 0, 1, 0);
					GL11.glRotated(0.2, 1, 0, 0);
					GL11.glTranslatef(-1.264F, 0.746F, 1.1F);
					
				}
				else {
					GL11.glRotated(-7, 1, 0, 0);
					GL11.glRotated(2, 0, 0, 1);
					GL11.glRotated(-45, 0, 1, 0);
					GL11.glRotated(6, 1, 0, 0);
					//GL11.glTranslatef(-0.5F, 0, 0.5F);
					GL11.glTranslatef(-0.7F, 0.55F, 0.5F);
					//
				}

				GL11.glScalef(0.3F, 0.3F, 0.3F);
				GL11.glCallList(ClientProxy.getRenderAll(model));
				GL11.glPopMatrix();
			}else {
				GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texture);
				GL11.glTranslatef(0F, 0.5F, 0F);
				GL11.glScalef(0.2F, 0.2F, 0.2F);
				GL11.glCallList(ClientProxy.getRenderAll(model));
				GL11.glPopMatrix();
			}
			GL11.glShadeModel(GL11.GL_FLAT);
		}
	}



}