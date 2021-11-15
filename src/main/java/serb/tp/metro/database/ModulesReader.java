package serb.tp.metro.database;

import net.minecraft.util.ResourceLocation;
import scala.actors.threadpool.Arrays;
import serb.tp.metro.Main;
import serb.tp.metro.items.modules.ItemMag;
import serb.tp.metro.items.modules.ItemWeaponModule;

public class ModulesReader extends Reader{
	
	public static void LoadModules() {
		
		String[] modules;
		modules = DBParser.readDB(new String[] {"weapon_modules", "weapon_modules_mags"});
		for (String module: modules) {
			
			if (module.contains(ModuleType.ItemWeaponModule.toString())) {
				
				addItemWeaponModule(module.substring(module.indexOf("{")+1));
			}
			else if(module.contains(ModuleType.ItemMag.toString())){
				addItemMag(module.substring(module.indexOf("{")+1));
			}
		}
	}
	
	private static void addItemWeaponModule(String parameters) {
		String[] splitParameters = parameters.split(";");

		items.add(new ItemWeaponModule(getString(splitParameters, "name"),
							getString(splitParameters, "description"),
							getFloat(splitParameters, "weight"),
							"models/modules/weapon/"+getString(splitParameters, "model"), 
							getFloatsArray(splitParameters, "sizeModel"), 
							getFloatsArray(splitParameters, "pos"), 
							getFloatsArray(splitParameters, "onInventoryPos"),
							getFloatsArray(splitParameters, "rotation"), 
							getFloatsArray(splitParameters, "rightHandPos"), 
							getFloatsArray(splitParameters, "rightHandRotation"), 
							getFloat(splitParameters, "verticalRecoilMod"), 
							getFloat(splitParameters, "horizontalRecoilMod"),
							getFloat(splitParameters, "convenienceMod"),
							getFloat(splitParameters, "accuracyMod"),
							getFloat(splitParameters, "penetrationMod"),
							getFloat(splitParameters, "jummingMod")));
	}
	
	private static void addItemMag(String parameters) {
		String[] splitParameters = parameters.split(";");
		items.add(new ItemMag(getString(splitParameters, "name"),
							getString(splitParameters, "description"),
							getFloat(splitParameters, "weight"),
							"models/modules/weapon/"+getString(splitParameters, "model"), 
							getFloatsArray(splitParameters, "sizeModel"), 
							getFloatsArray(splitParameters, "pos"), 
							getFloatsArray(splitParameters, "onInventoryPos"),
							getFloatsArray(splitParameters, "rotation"), 
							getFloatsArray(splitParameters, "rightHandPos"), 
							getFloatsArray(splitParameters, "rightHandRotation"), 
							getFloat(splitParameters, "verticalRecoil"), 
							getFloat(splitParameters, "horizontalRecoil"),
							getFloat(splitParameters, "convenience"),
							getFloat(splitParameters, "accuracy"),
							getFloat(splitParameters, "powerMod"),
							getFloat(splitParameters, "jummingMod"),
							getInt(splitParameters, "maxAmmo"),
							getStringArray(splitParameters, "bullets", ","),
							getInt(splitParameters, "cooldownLoading"),
							getInt(splitParameters, "cooldownLoading")
							));
	}
	

	
	private enum ModuleType{
		ItemWeaponModule,
		ItemWeaponModuleMuzzleDevice,
		ItemMag
	}
}
