package serb.tp.metro.customization;

import serb.tp.metro.DebugMessage;
import serb.tp.metro.database.CustomizationSlotsReader;
import serb.tp.metro.items.Item3D;
import serb.tp.metro.items.modules.ItemModule;
import serb.tp.metro.utils.DefenceVariables;

public class AbstractCustomizableSlot {

	public final int indexSlot;
	protected int x;
	protected int y;
	protected float[] pos;
	protected float[] rotation;
	protected ItemModule[] subItems;
	
	public AbstractCustomizableSlot(int indexSlot, int x, int y, float[] pos, float[] rotation, Item3D[] subItems) {
		this.indexSlot = indexSlot;
		this.x = x;
		this.y = y;
		this.pos = pos;
		this.rotation = rotation;
		this.subItems = new ItemModule[subItems.length];
		for (int i  = 0; i<subItems.length; i++) {
			this.subItems[i] = (ItemModule) subItems[i];
		}
	}

	public final int getX() {
		return x;
	}

	public final void setX(int x) {
		if (DefenceVariables.authorizedAccess(CustomizationSlotsReader.class)) {
			this.x = x;
			
		}
	}

	public final int getY() {
		return y;
	}

	public final void setY(int y) {
		if (DefenceVariables.authorizedAccess(CustomizationSlotsReader.class)) {
			this.y = y;
		}
	}

	public final float[] getPos() {
		return pos;
	}

	public final void setPos(float[] pos) {
		if (DefenceVariables.authorizedAccess(CustomizationSlotsReader.class)) {
			this.pos = pos;
		}
	}

	public final float[] getRotation() {
		return rotation;
	}

	public final void setRotation(float[] rotation) {
		if (DefenceVariables.authorizedAccess(CustomizationSlotsReader.class)) {
			this.rotation = rotation;
		}
	}

	public final ItemModule[] getSubItems() {
		return subItems;
	}

	public final void setSubItems(Item3D[] subItems) {
		if (DefenceVariables.authorizedAccess(CustomizationSlotsReader.class)) {
			this.subItems = new ItemModule[subItems.length];
			for (int i  = 0; i<subItems.length; i++) {
				this.subItems[i] = (ItemModule) subItems[i];
			}
		}
	}

}
