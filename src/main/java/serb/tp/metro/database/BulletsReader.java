package serb.tp.metro.database;

import java.util.ArrayList;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import serb.tp.metro.Main;
import serb.tp.metro.client.render.items.RenderItem3D;
import serb.tp.metro.items.Item3D;
import serb.tp.metro.items.modules.ItemMag;
import serb.tp.metro.items.weapons.ItemBullet;

public class BulletsReader extends Reader{
	
	
	public static void LoadBullets() {
		String[] modules;

		modules = DBParser.readDB("bullets");
		
		for (String module: modules) {
			addItemBullet(module.substring(module.indexOf("{")+1));
		}
			
	}
	
	private static void addItemBullet(String parameters) {
		String[] splitParameters = parameters.split(";");
		ItemBullet bullet = (ItemBullet) GameRegistry.findItem(Main.modid, "item." + getString(splitParameters, "name"));
		
		String description = getString(splitParameters, "description");
		float weight = getFloat(splitParameters, "weight");
		String model = "models/bullets/"+getString(splitParameters, "model");
		float[] sizeModel = getFloatsArray(splitParameters, "sizeModel");
		float[] pos = getFloatsArray(splitParameters, "pos");
		float[] rotation = getFloatsArray(splitParameters, "rotation");
		float[] onInventoryPos = getFloatsArray(splitParameters, "onInventoryPos");
		float[] rightHandPos = getFloatsArray(splitParameters, "rightHandPos");
		float[] rightHandRotation = getFloatsArray(splitParameters, "rightHandRotation");
		int damage = getInt(splitParameters, "damage");
		int penetration = getInt(splitParameters, "penetration");
		int parts = getInt(splitParameters, "parts");
		float armorDamage = getFloat(splitParameters, "armorDamage");
		float fragmentationChance = getFloat(splitParameters, "fragmentationChance");
		float jamming = getFloat(splitParameters, "jamming");
		
		
		if (bullet != null) {
			bullet.setWeight(weight);
			bullet.setDescription(description);
			bullet.setModel(model);
			bullet.sizeModel = sizeModel;
			bullet.pos = pos;
			bullet.rotation = rotation;
			bullet.onInventoryPos = onInventoryPos;
			bullet.rightHandPos = rightHandPos;
			bullet.rightHandRotation = rightHandRotation;
			bullet.setDamage(damage);
			bullet.setPenetration(penetration);
			bullet.setParts(parts);
			bullet.setArmorDamage(armorDamage);
			bullet.setFragmentationChance(fragmentationChance);
			bullet.setJamming(jamming);
			
		}
		else {
			items.add(new ItemBullet(getString(splitParameters, "name"), 
									description, weight, model, sizeModel, 
									pos, rotation, onInventoryPos,rightHandPos, 
									rightHandRotation, damage, penetration, 
									jamming, armorDamage, fragmentationChance, 
									parts));
		}
	}
	
	
}
