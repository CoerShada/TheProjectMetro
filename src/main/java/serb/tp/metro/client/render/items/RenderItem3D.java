package serb.tp.metro.client.render.items;



import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.IModelCustom;
import serb.tp.metro.Main;
import serb.tp.metro.client.ClientProxy;
import serb.tp.metro.client.render.loaders.obj.Obj;
import serb.tp.metro.containers.InventoryItemStorage;
import serb.tp.metro.customization.AbstractCustomizableSlot;
import serb.tp.metro.customization.ICustomizable;
import serb.tp.metro.items.Item3D;

public class RenderItem3D implements IItemRenderer{
	ResourceLocation textureRL =  new ResourceLocation(Main.modid, "textures/models/items/armor/ksk.jpg");
	Item3D item;
	private int list;
	private int counter;
	IModelCustom model;
	SimpleTexture texture;
	int textInt;
	public RenderItem3D(Item3D item) {
		this.item = item;
		
		Obj model = ClientProxy.loader.loadModel(item.getModel());
		
		texture = new SimpleTexture(textureRL);
	
		list = GL11.glGenLists(1);
        GL11.glNewList(list, GL11.GL_COMPILE);
        {
        	ClientProxy.loader.render(model);
        }
        GL11.glEndList();

        
	    //list = ClientProxy.loader.createDisplayList(model);
	    /*GL11.glNewList(list, GL11.GL_COMPILE);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);

	    ClientProxy.loader.render(model);
	    GL11.glEndList();*/
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

		int index;
		switch(type) {
			case EQUIPPED_FIRST_PERSON:
				index = 0;
				break;
			case EQUIPPED:
				index = 1;
				break;
			case INVENTORY:
				index = 2;
				break;
			default:
				index = 3;
				break;
		}
		
		float coef = this.item.sizeModel[index]/this.item.sizeModel[1];
		float coefOld = 1;
		//float coef = 1;
		float [] mods = new float[] {this.item.pos[0]*coef, this.item.pos[1]*coef, this.item.pos[2]*coef, 0, 0, 0};
		boolean firstRender = true;
		for (int i = 0; i<data.length; i++) {
			if (data[i] instanceof float[]) {
				//System.out.println(type.toString());
				float [] modsTemp = (float[]) data[i];
				coefOld=modsTemp[6];
				mods[0] = mods[0] + modsTemp[0] * coefOld;
				mods[1] = mods[1] + modsTemp[1] * coefOld;
				mods[2] = mods[2] + modsTemp[2] * coefOld;
				
				mods[3] = modsTemp[3];
				mods[4] = modsTemp[4];
				mods[5] = modsTemp[5];
			}
			if (data[i] instanceof Boolean) {
				firstRender = (boolean) data[i];
			}
		}


		
		//System.out.println(this.item.rotation[1]);
		GL11.glShadeModel(GL11.GL_SMOOTH);


		GL11.glPushMatrix();
		
		if(firstRender && type==ItemRenderType.EQUIPPED) {


			GL11.glTranslated(0.0, 1.0, 0);
			GL11.glRotated(45, 0, 1, 0);
			GL11.glTranslated(0.0, -1.0, 0);
			 //translate 1 to the right, and down

			GL11.glTranslated(1.0, 0.0, 0);
			GL11.glRotatef(-90, 1, 0, 0);
			GL11.glTranslated(-1.0, 0.0, 0);
			
			GL11.glTranslated(1.0, 0.0, 0);
			GL11.glRotatef(15, 1, 0, 0);
			GL11.glTranslated(-1.0, 0.0, 0);
			
			GL11.glTranslated(0.0, -1.0, 0);
			

		}
		if(firstRender && type==ItemRenderType.EQUIPPED_FIRST_PERSON) {
			
			GL11.glTranslated(0.0, 1.0, 0);
			GL11.glRotated(-45, 0, 1, 0);	
			GL11.glTranslated(0, -1.0, 0);
			
			GL11.glTranslated(0.0, 1.0, -0.5);
			//GL11.glTranslated(0.0, 1.0, 0.0);
		}

		GL11.glTranslated(0.0, 0.0, 1.0);
		GL11.glRotated(mods[5], 0, 0, 1);
		GL11.glTranslated(0.0, 0.0, -1.0);
		
		GL11.glTranslated(1.0, 0.0, 0.0);
		GL11.glRotated(mods[3], 1, 0, 0);
		GL11.glTranslated(-1.0, 0.0, 0.0);
		
		GL11.glTranslated(0.0, 1.0, 0.0);
		GL11.glRotated(mods[4], 0, 1, 0);
		GL11.glTranslated(0.0, -1.0, 0.0);
		


		GL11.glTranslated(0.0, 0.0, 1.0);
		GL11.glRotated(this.item.rotation[2], 0, 0, 1);
		GL11.glTranslated(0.0, 0.0, -1.0);
		
		GL11.glTranslated(1.0, 0.0, 0.0);
		GL11.glRotated(this.item.rotation[0], 1, 0, 0);
		GL11.glTranslated(-1.0, 0.0, 0.0);
		
		GL11.glTranslated(0.0, 1.0, 0.0);
		GL11.glRotated(this.item.rotation[1], 0, 1, 0);
		GL11.glTranslated(0.0, -1.0, 0.0);
		


		GL11.glTranslatef(mods[0], mods[1], mods[2]);
		

		
		if (item.getItem() instanceof ICustomizable) {
			ICustomizable customizable = (ICustomizable) item.getItem();
			InventoryItemStorage inv = new InventoryItemStorage(item);
			for (int i = 0; i<inv.getSizeInventory(); i++) {
				ItemStack is = inv.getStackInSlot(i);
				if (is==null) continue;
				
				try {
					AbstractCustomizableSlot slot = customizable.getSlotsCustomization().get(i);
					RenderItem3D render = (RenderItem3D) MinecraftForgeClient.getItemRenderer(is, type);
					float[] pos = slot.getPos();
					float[] rotation = slot.getRotation();
					render.renderItem(type, is, data, new float[] {pos[0],  pos[1],  pos[2], rotation[0], rotation[1], rotation[2], coef}, false);			
				}
				catch(Exception e) {
					
				}
			}
			
		}
		
		GL11.glScalef(this.item.sizeModel[index], this.item.sizeModel[index], this.item.sizeModel[index]);
		GL11.glCallList(list);
		
		GL11.glPopMatrix();
		GL11.glShadeModel(GL11.GL_FLAT);
		
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
	}
	
}
