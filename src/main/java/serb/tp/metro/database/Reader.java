package serb.tp.metro.database;

import java.util.ArrayList;
import java.util.Arrays;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.MinecraftForgeClient;
import serb.tp.metro.DebugMessage;
import serb.tp.metro.Main;
import serb.tp.metro.client.render.items.RenderItem3D;
import serb.tp.metro.items.Item3D;

public class Reader {
	
	protected static ArrayList<Item3D> items = new ArrayList<Item3D>();
	
	protected static String getString(String[] currentString, String name) {
		try {
			for (String item: currentString) {
				String equalsStr = item.substring(0, item.indexOf("=")).trim();
				if (equalsStr.equals(name)) {
					
					return item.substring(item.indexOf("=")+1).trim();
					 
				}
			}		
		}
		catch(Exception e) {
			System.err.println(e);
		}
		return null;
	}
	
	protected static int getInt(String[] currentString, String name) {
		try {
			return Integer.parseInt(getString(currentString, name));
		}
		catch(Exception e){
			System.err.println(e);
			DebugMessage.printMessage(name);
		}
		return 0;
	}
	
	protected static float getFloat(String[] currentString, String name) {
		try {
			
			return Float.parseFloat(getString(currentString, name));
		}
		catch(Exception e){
			System.err.println(e);
		}
		return 0;
	}
	
	protected static double getDouble(String[] currentString, String name) {
		try {
			return Double.parseDouble(getString(currentString, name));
		}
		catch(Exception e){
			System.err.println(e);
		}
		return 0;
	}
	
	protected static boolean getBool(String[] currentString, String name) {
		try {
			return Boolean.parseBoolean(getString(currentString, name));
		}
		catch(Exception e){
			System.err.println(e);
		}
		return false;
	}
	
	protected static String[] getStringArray(String[] currentString, String name, String splitChar) {
		try {
			
			String[] strings = getString(currentString, name).split(splitChar);
			for (String str: strings) {
				str = str.trim();
			}
			return strings;
		}
		catch(Exception e){
			System.err.println(e);
		}
		return null;
	}
	
	protected static int[] getIntsArray(String[] currentString, String name) {
		String[] strings = getStringArray(currentString, name, ",");
		return Arrays.stream(strings).mapToInt(Integer::parseInt).toArray();
	}
	
	protected static float[] getFloatsArray(String[] currentString, String name) {
		String[] strings = getStringArray(currentString, name, ",");
		float[] floats = new float[strings.length];
		for (int i = 0; i<strings.length; i++)
			floats[i] = Float.valueOf(strings[i]);
		return floats;
	}
	
	protected static Item3D[] getItems(String[] currentString, String name) {
		
		
		String[] itemNames = getStringArray(currentString, name, ",");
		
		Item3D[] items = new Item3D[itemNames.length];
		for(int i = 0; i<itemNames.length; i++) {
			items[i] = (Item3D) GameRegistry.findItem(Main.modid, "item."+itemNames[i]);
		}
		return items;
	}
	
	@SideOnly(Side.CLIENT)
	public static void setRender() {
		for(Item3D item: items) {
			try {
				MinecraftForgeClient.registerItemRenderer(item, new RenderItem3D(item));
			}
			catch(Exception e){
				System.out.println("[Reader]Ошибка при регистрации рендера предмета" + item.getUnlocalizedName());
			}
		}
	}
}
