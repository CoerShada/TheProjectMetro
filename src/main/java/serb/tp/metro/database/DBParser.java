package serb.tp.metro.database;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import net.minecraft.util.ResourceLocation;
import serb.tp.metro.Main;

public class DBParser {

	public static String[] readDB(String nameTable) {
		
        return readDB(new String[] {nameTable});
		
	}
	
	public static String[] readDB(String[] namesTables) {
		
		
		StringBuilder sb = new StringBuilder();
		for (String nameTable : namesTables)
		{
	        try{
	        	if (Main.debug)
	        		nameTable = "debug_"+nameTable;
	        	else
	        		nameTable = "server_"+nameTable;
	            String link = "http://the-project.ru/site/get_items.php?table="+nameTable;
	            URL url = new URL(link);
	            URLConnection conn = url.openConnection();
	            conn.setDoOutput(true);
	            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "windows-1251"));
	            
	            String line = null;
	            while ((line = reader.readLine()) != null){
	                sb.append(line);
	                break;
	            }
	            System.out.println("[Data Base]" + sb.toString());
	            
	        }
	        catch (Exception e){
	
	            System.out.println("Получено исключение"  + e);
	            return null;
	        }
		}
		return sb.toString().split("}");
	}
	
}
