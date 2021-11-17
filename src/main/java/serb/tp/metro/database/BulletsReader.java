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
		if (bullet != null) {
			bullet.setWeight(getFloat(splitParameters, "weight"));
		}
		else {
			items.add(new ItemBullet(getString(splitParameters, "name"), 
							getString(splitParameters, "description"),
							getFloat(splitParameters, "weight"), 
							"models/bullets/"+getString(splitParameters, "model"), 
							getFloatsArray(splitParameters, "sizeModel"), 
							getFloatsArray(splitParameters, "pos"), 
							getFloatsArray(splitParameters, "rotation"), 
							getFloatsArray(splitParameters, "onInventoryPos"),  
							getFloatsArray(splitParameters, "rightHandPos"), 
							getFloatsArray(splitParameters, "rightHandRotation"), 
							getInt(splitParameters, "damage"), 
							getInt(splitParameters, "penetration"), 
							getFloat(splitParameters, "jamming"), 
							getFloat(splitParameters, "armorDamage"), 
							getFloat(splitParameters, "fragmentationChance"), 
							getInt(splitParameters, "parts")));
		}
	}
	
	
}
