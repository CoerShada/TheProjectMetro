package serb.tp.metro.database;

import cpw.mods.fml.common.registry.GameRegistry;
import serb.tp.metro.Main;
import serb.tp.metro.items.modules.ItemMag;
import serb.tp.metro.items.weapons.FireMod;
import serb.tp.metro.items.weapons.ItemFirearmMagWeapon;

public class WeaponsReader extends Reader{
	
	
	public static void LoadWeapons() {
		String[] weapons;

		weapons = DBParser.readDB("weapons");
		
		for (String weapon: weapons) {
			addItemFirearmMagWeapon(weapon.substring(weapon.indexOf("{")+1));
		}
			
	}
	
	private static void addItemFirearmMagWeapon(String parameters) {
		String[] splitParameters = parameters.split(";");
		ItemFirearmMagWeapon weapon = (ItemFirearmMagWeapon) GameRegistry.findItem(Main.modid, "item." + getString(splitParameters, "name"));
		if (weapon != null) {
			weapon.setWeight(getFloat(splitParameters, "weight"));
		}
		else {
			items.add(new ItemFirearmMagWeapon(getString(splitParameters, "name"), 
							getString(splitParameters, "description"),
							"models/weapons/automatic/"+getString(splitParameters, "model"), 
							getFloat(splitParameters, "weight"), 
							getFloatsArray(splitParameters, "sizeModel"), 
							getFloatsArray(splitParameters, "pos"), 
							getFloatsArray(splitParameters, "rotation"),
							getFloatsArray(splitParameters, "onInventoryPos"),
							getFloatsArray(splitParameters, "rightHandPos"), 
							getFloatsArray(splitParameters, "rightHandRotation"), 
							getFloatsArray(splitParameters, "leftHandPos"), 
							getFloatsArray(splitParameters, "leftHandRotation"), 
							getFloatsArray(splitParameters, "onAimingPos"), 
							getFloatsArray(splitParameters, "onAimingRotation"), 
							getFloatsArray(splitParameters, "onAimingLeftHandPos"), 
							getFloatsArray(splitParameters, "onAimingLeftHandRotation"), 
							getFloatsArray(splitParameters, "onAimingRightHandPos"), 
							getFloatsArray(splitParameters, "onAimingRightHandRotation"), 
							getInt(splitParameters, "soundRadius"),
							getInt(splitParameters, "rateOfFire"),
							getFloat(splitParameters, "penetrationMod"),
							getFloat(splitParameters, "jummingChance"),
							getInt(splitParameters, "loadTime"),
							getInt(splitParameters, "unloadTime"),
							getFloat(splitParameters, "recoilVert"),
							getFloat(splitParameters, "recoilHoriz"),
							getFloat(splitParameters, "accuracy"),
							getFloat(splitParameters, "convenience"),
							getFireMods(splitParameters, "fireMods"),
							getStringArray(splitParameters, "mags", ", ")));
		}
		
	}
	
	private static FireMod[] getFireMods(String[] currentString, String name) {
		String[] fireModsStr = getStringArray(currentString, name, ", ");
		FireMod[] fireMods = new FireMod[fireModsStr.length];
		for(int i = 0; i <fireModsStr.length; i++) {
			fireMods[i] = FireMod.valueOf(fireModsStr[i]);
		}
		return fireMods;
		
	}
	
}
