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
		if (weapons==null) return;
		
		for (String weapon: weapons) {
			addItemFirearmMagWeapon(weapon.substring(weapon.indexOf("{")+1));
		}
			
	}
	
	private static void addItemFirearmMagWeapon(String parameters) {
		String[] splitParameters = parameters.split(";");
		ItemFirearmMagWeapon weapon = (ItemFirearmMagWeapon) GameRegistry.findItem(Main.modid, "item." + getString(splitParameters, "name"));
		
		String description = getString(splitParameters, "description");
		float weight = getFloat(splitParameters, "weight");
		String model = "models/weapons/automatic/"+getString(splitParameters, "model");
		float[] sizeModel = new float[] {getFloat(splitParameters, "sizeModel"), getFloat(splitParameters, "sizeModel")*0.75F, getFloat(splitParameters, "sizeModel")*0.75F, getFloat(splitParameters, "sizeModel")*0.5F};
		float[] pos = getFloatsArray(splitParameters, "pos");
		float[] rotation = getFloatsArray(splitParameters, "rotation");
		float[] onInventoryPos = getFloatsArray(splitParameters, "onInventoryPos");
		float[] rightHandPos = getFloatsArray(splitParameters, "rightHandPos");
		float[] rightHandRotation = getFloatsArray(splitParameters, "rightHandRotation");
		
		float[] leftHandPos = getFloatsArray(splitParameters, "leftHandPos");
		float[] leftHandRotation = getFloatsArray(splitParameters, "leftHandRotation");
		
		float[] onAimingPos = getFloatsArray(splitParameters, "onAimingPos");
		float[] onAimingRotation = getFloatsArray(splitParameters, "onAimingRotation");
		
		float[] onAimingLeftHandPos = getFloatsArray(splitParameters, "onAimingLeftHandPos");
		float[] onAimingLeftHandRotation = getFloatsArray(splitParameters, "onAimingLeftHandRotation");
		
		float[] onAimingRightHandPos = getFloatsArray(splitParameters, "onAimingRightHandPos");
		float[] onAimingRightHandRotation = getFloatsArray(splitParameters, "onAimingRightHandRotation");
		
		int soundRadius = getInt(splitParameters, "soundRadius");
		int rateOfFire = getInt(splitParameters, "rateOfFire");
		int loadTime = getInt(splitParameters, "loadTime");
		int unloadTime = getInt(splitParameters, "unloadTime");
		float jammingChance = getFloat(splitParameters, "jammingChance");
		float penetrationMod = getFloat(splitParameters, "penetrationMod");
		float accuracy = getFloat(splitParameters, "accuracy");
		float recoilVert = getFloat(splitParameters, "recoilVert");
		float recoilHoriz =getFloat(splitParameters, "recoilHoriz");
		float convenience = getFloat(splitParameters, "convenience");
		FireMod[] fireMods = getFireMods(splitParameters, "fireMods");
		String[] mags = getStringArray(splitParameters, "mags", ", ");
		
		
		
		if (weapon != null) {
			weapon.setDescription(description);
			weapon.setWeight(weight);
			weapon.setModel(model);
			weapon.sizeModel = sizeModel;
			weapon.pos = pos;
			weapon.rotation = rotation;
			weapon.onInventoryPos = onInventoryPos;
			weapon.rightHandPos = rightHandPos;
			weapon.rightHandRotation = rightHandRotation;
			weapon.leftHandPos = leftHandPos;
			weapon.leftHandRotation = leftHandRotation;
			weapon.onAimingRightHandPos = onAimingRightHandPos;
			weapon.onAimingRightHandRotation = onAimingRightHandRotation;
			weapon.onAimingLeftHandPos = onAimingLeftHandPos;
			weapon.onAimingLeftHandRotation = onAimingLeftHandRotation;
			weapon.onAimingPos = onAimingPos;
			weapon.onAimingRotation = onAimingRotation;
			weapon.setSoundRadius(soundRadius);
			weapon.setRateOfFire(rateOfFire);
			weapon.setLoadTime(unloadTime);
			weapon.setUnloadTime(unloadTime);
			weapon.setJammingChance(jammingChance);
			weapon.setPenetrationMod(penetrationMod);
			weapon.setAccuracy(accuracy);
			weapon.setRecoilVert(recoilVert);
			weapon.setRecoilHoriz(recoilHoriz);
			weapon.setConvenience(convenience);
			weapon.setMags(mags);
			
		}
		else {
			items.add(new ItemFirearmMagWeapon(getString(splitParameters, "name"), 
							description, model, weight, sizeModel, 
							pos, rotation, onInventoryPos,
							rightHandPos, rightHandRotation, 
							leftHandPos, leftHandRotation, 
							onAimingPos, onAimingRotation, 
							onAimingLeftHandPos, 
							onAimingLeftHandRotation, 
							onAimingRightHandPos, 
							onAimingRightHandRotation, 
							soundRadius,
							rateOfFire,
							penetrationMod,
							jammingChance,
							loadTime,
							unloadTime,
							recoilVert,
							recoilHoriz,
							accuracy,
							convenience,
							fireMods,
							mags));
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
