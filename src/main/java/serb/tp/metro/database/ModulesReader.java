package serb.tp.metro.database;

import cpw.mods.fml.common.registry.GameRegistry;
import serb.tp.metro.Main;
import serb.tp.metro.items.modules.ItemMag;
import serb.tp.metro.items.modules.ItemWeaponModule;

public class ModulesReader extends Reader{
	
	public static void LoadModules() {
		
		String[] modules;
		modules = DBParser.readDB(new String[] {"weapon_modules", "weapon_modules_mags"});
		for (String module: modules) {
			
			if (module.contains(ModuleType.ItemWeaponModule.toString())) {
				
				addOrUpdateItemWeaponModule(module.substring(module.indexOf("{")+1));
			}
			else if(module.contains(ModuleType.ItemMag.toString())){
				addItemMag(module.substring(module.indexOf("{")+1));
			}
		}
	}
	
	private static void addOrUpdateItemWeaponModule(String parameters) {
		String[] splitParameters = parameters.split(";");
		ItemWeaponModule module = (ItemWeaponModule) GameRegistry.findItem(Main.modid, "item." + getString(splitParameters, "name"));
		
		String description = getString(splitParameters, "description");
		float weight = getFloat(splitParameters, "weight");
		String model = "models/modules/weapon/"+getString(splitParameters, "model");
		float[] sizeModel = new float[] {getFloat(splitParameters, "sizeModel"), getFloat(splitParameters, "sizeModel")*0.75F, getFloat(splitParameters, "sizeModel")*0.75F, getFloat(splitParameters, "sizeModel")*0.5F};
		float[] pos = getFloatsArray(splitParameters, "pos");
		float[] rotation = getFloatsArray(splitParameters, "rotation");
		float[] onInventoryPos = getFloatsArray(splitParameters, "onInventoryPos");
		float[] rightHandPos = getFloatsArray(splitParameters, "rightHandPos");
		float[] rightHandRotation = getFloatsArray(splitParameters, "rightHandRotation");
		
		float verticalRecoilMod = getFloat(splitParameters, "verticalRecoilMod");
		float horizontalRecoilMod =getFloat(splitParameters, "horizontalRecoilMod");
		float convenienceMod = getFloat(splitParameters, "convenienceMod");
		float accuracyMod = getFloat(splitParameters, "accuracyMod");
		float penetrationMod = getFloat(splitParameters, "penetrationMod");
		float jammingMod = getFloat(splitParameters, "jammingMod");
		
		if (module != null) {	
			module.setDescription(description);
			module.setWeight(weight);
			module.setModel(model);
			module.sizeModel = sizeModel;
			module.pos = pos;
			module.rotation = rotation;
			module.onInventoryPos = onInventoryPos;
			module.rightHandPos = rightHandPos;
			module.rightHandRotation = rightHandRotation;
			module.setVerticalRecoilMod(verticalRecoilMod);
			module.setHorizontalRecoilMod(horizontalRecoilMod);
			module.setConvenienceMod(convenienceMod);
			module.setAccuracyMod(accuracyMod);
			module.setPenetrationMod(penetrationMod);
			module.setJammingMod(jammingMod);
			
		}
		else {
			items.add(new ItemWeaponModule(getString(splitParameters, "name"),
					description, weight, model, sizeModel, pos, rotation,
					onInventoryPos, rightHandPos, rightHandRotation, 
					verticalRecoilMod, horizontalRecoilMod, convenienceMod,
					accuracyMod, penetrationMod, jammingMod ));
		}

	}
	
	private static void addItemMag(String parameters) {
		String[] splitParameters = parameters.split(";");
		ItemMag mag = (ItemMag) GameRegistry.findItem(Main.modid, "item." + getString(splitParameters, "name"));
		
		String description = getString(splitParameters, "description");
		float weight = getFloat(splitParameters, "weight");
		String model = "models/modules/weapon/"+getString(splitParameters, "model");
		float[] sizeModel = new float[] {getFloat(splitParameters, "sizeModel"), getFloat(splitParameters, "sizeModel")*0.75F, getFloat(splitParameters, "sizeModel")*0.75F, getFloat(splitParameters, "sizeModel")*0.5F};
		float[] pos = getFloatsArray(splitParameters, "pos");
		float[] rotation = getFloatsArray(splitParameters, "rotation");
		float[] onInventoryPos = getFloatsArray(splitParameters, "onInventoryPos");
		float[] rightHandPos = getFloatsArray(splitParameters, "rightHandPos");
		float[] rightHandRotation = getFloatsArray(splitParameters, "rightHandRotation");
		
		float verticalRecoilMod = getFloat(splitParameters, "verticalRecoilMod");
		float horizontalRecoilMod =getFloat(splitParameters, "horizontalRecoilMod");
		float convenienceMod = getFloat(splitParameters, "convenienceMod");
		float accuracyMod = getFloat(splitParameters, "accuracyMod");
		float penetrationMod = getFloat(splitParameters, "penetrationMod");
		float jammingMod = getFloat(splitParameters, "jammingMod");
		String[] bullets = getStringArray(splitParameters, "bullets", ",");
		int cooldownLoading = getInt(splitParameters, "cooldownLoading");
		int cooldownUnloading = getInt(splitParameters, "cooldownUnloading");
		
		int maxAmmo = getInt(splitParameters, "maxAmmo");
		
		if (mag != null) {
			mag.setDescription(description);
			mag.setWeight(weight);
			mag.setModel(model);
			mag.sizeModel = sizeModel;
			mag.pos = pos;
			mag.rotation = rotation;
			mag.onInventoryPos = onInventoryPos;
			mag.rightHandPos = rightHandPos;
			mag.rightHandRotation = rightHandRotation;
			mag.setVerticalRecoilMod(verticalRecoilMod);
			mag.setHorizontalRecoilMod(horizontalRecoilMod);
			mag.setConvenienceMod(convenienceMod);
			mag.setAccuracyMod(accuracyMod);
			mag.setPenetrationMod(penetrationMod);
			mag.setJammingMod(jammingMod);
			mag.setMaxAmmo(maxAmmo);
			mag.setBullets(bullets);
			mag.setCooldownLoading(cooldownLoading);
			mag.setCooldownUnloading(cooldownUnloading);
		}
		else {
			items.add(new ItemMag(getString(splitParameters, "name"),
								description, weight, model, sizeModel, 
								pos, rotation, onInventoryPos, rightHandPos, 
								rightHandRotation, verticalRecoilMod, 
								horizontalRecoilMod, convenienceMod,
								accuracyMod, penetrationMod, jammingMod,
								maxAmmo, bullets, cooldownLoading,
								cooldownUnloading
								));
		}
	}
	

	
	private enum ModuleType{
		ItemWeaponModule,
		ItemWeaponModuleMuzzleDevice,
		ItemMag
	}
}
