package serb.tp.metro.database;

import net.minecraft.item.Item;
import serb.tp.metro.containers.slots.SlotCustomization;
import serb.tp.metro.customization.AbstractCustomizableSlot;
import serb.tp.metro.customization.ICustomizable;
import serb.tp.metro.items.modules.ItemModule;

public class CustomizationSlotsReader extends Reader{
	

	
	
	public static void LoadCustomizationSlots() {

		String[] modules;

		modules = DBParser.readDB("custom_slots");
		
		for (String module: modules) {
			addSlot(module.substring(module.indexOf("{")+1));
			
		}
		
			
	}
	
	private static void addSlot(String parameters) {
		String[] splitParameters = parameters.split(";");
		if (!(getItems(splitParameters, getString(splitParameters, "parentItems"))[0] instanceof ICustomizable)) return;
		
		ICustomizable item = (ICustomizable) getItems(splitParameters, getString(splitParameters, "parentItems"))[0];
		item.clearSlots();
		Item item_test = (Item) item;
		item.addSlot(new AbstractCustomizableSlot(	item.getIndexNewSlot(), 
													getInt(splitParameters, "x"), 
													getInt(splitParameters, "y"), 
													getFloatsArray(splitParameters, "pos"),
													getFloatsArray(splitParameters, "rotation"),
													getItems(splitParameters, getString(splitParameters, "subItems")
													)));
		
		
	}
	
	
}
