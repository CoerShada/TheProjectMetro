package serb.tp.metro.client.render.items;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import serb.tp.metro.Main;
import serb.tp.metro.client.ClientProxy;
import serb.tp.metro.client.render.loaders.obj.Obj;
import serb.tp.metro.containers.InventoryItemStorage;
import serb.tp.metro.customization.AbstractCustomizableSlot;
import serb.tp.metro.customization.ICustomizable;
import serb.tp.metro.items.Item3D;
import serb.tp.metro.items.weapons.ItemWeapon;

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

		float [] mods = new float[] {this.item.pos[0], this.item.pos[1], this.item.pos[2]};
		if (data.length==2 && data[1] instanceof float[]) {
			mods = (float[]) data[1];
		}
		
		GL11.glShadeModel(GL11.GL_SMOOTH);

		GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		GL11.glPushMatrix();
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		int index;
		if (type == ItemRenderType.INVENTORY) {
			index = 2;
			

		} else if (type == ItemRenderType.EQUIPPED) {
			index = 1;
			if (mods[0]==this.item.pos[0] && mods[1]==this.item.pos[1] && mods[2]==this.item.pos[2]) {
				GL11.glRotated(45, 0, 1, 0);
				GL11.glRotated(-45, 1, 0, 0);
			}
			GL11.glRotated(this.item.rotation[1], 1, 0, 0);
			GL11.glRotated(this.item.rotation[0], 0, 1, 0);
			GL11.glRotated(this.item.rotation[2], 0, 0, 1);
			
		} 
		else if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
			index = 0;
			GL11.glRotated(this.item.rotation[0], 1, 0, 0);
			GL11.glRotated(this.item.rotation[1], 0, 1, 0);
			GL11.glRotated(this.item.rotation[2], 0, 0, 1);
			
		}	
		else {
			index = 3;
			GL11.glRotated(this.item.rotation[0], 1, 0, 0);
			GL11.glRotated(this.item.rotation[1], 0, 1, 0);
			GL11.glRotated(this.item.rotation[2], 0, 0, 1);
			//if (mods[0]==this.item.pos[0] && mods[1]==this.item.pos[1] && mods[2]==this.item.pos[2]) GL11.glTranslatef(0F, 0.5F, 0F);
			//if (mods[0]==this.item.pos[0] && mods[1]==this.item.pos[1] && mods[2]==this.item.pos[2])
				//GL11.glTranslatef(0F, 0.5F, 0F);

		}

		float coef = this.item.sizeModel[1]/this.item.sizeModel[index];
		//System.out.println(coef);
		GL11.glTranslatef(mods[0] + this.item.pos[0] * 1, mods[1] + this.item.pos[1] * 1, mods[2] + this.item.pos[2] * 1);
		
		GL11.glScalef(this.item.sizeModel[index], this.item.sizeModel[index], this.item.sizeModel[index]);

		GL11.glCallList(list);
		
		if (!(item.getItem() instanceof ICustomizable)) return;
		ICustomizable customizable = (ICustomizable) item.getItem();
		InventoryItemStorage inv = new InventoryItemStorage(item);
		for (int i = 0; i<inv.getSizeInventory(); i++) {
			ItemStack is = inv.getStackInSlot(i);
			if (is==null) continue;
			
			try {
				AbstractCustomizableSlot slot = customizable.getSlotsCustomization().get(i);
				IItemRenderer render = MinecraftForgeClient.getItemRenderer(is, type);
				
				render.renderItem(type, is, data, new float[] {mods[0]+slot.pos[0] * 1, slot.pos[1] + mods[1]* 1, slot.pos[2] + mods[2]* 1});			}
			catch(Exception e) {}
		}

		GL11.glPopMatrix();
		GL11.glShadeModel(GL11.GL_FLAT);
		GL11.glEnable(GL11.GL_CULL_FACE);
	}
	
}
