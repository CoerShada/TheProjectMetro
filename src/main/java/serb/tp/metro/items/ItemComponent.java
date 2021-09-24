package serb.tp.metro.items;

import net.minecraft.item.Item;
import serb.tp.metro.creativetabs.LoadTabs;

public class ItemComponent extends Item3D{

	public ItemComponent(String name, String description, float weight, String model, float[] sizeModel, float[] pos,
			float[] rotation, float[] onInventoryPos, float[] rightHandPos, float[] rightHandRotation) {
		super(name, description, weight, model, sizeModel, pos, rotation, onInventoryPos, rightHandPos, rightHandRotation);
		this.setCreativeTab(LoadTabs.itemsComponents);
	}


	

}
