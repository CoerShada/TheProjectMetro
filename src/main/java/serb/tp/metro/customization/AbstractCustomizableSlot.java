package serb.tp.metro.customization;

import serb.tp.metro.items.Item3D;
import serb.tp.metro.items.modules.ItemModule;

public class AbstractCustomizableSlot {

	public final int indexSlot;
	public final int x;
	public final int y;
	public final float[] pos;
	public final float[] rotation;
	public final ItemModule[] subItems;
	
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

}
