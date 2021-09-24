package serb.tp.metro.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
import serb.tp.metro.Main;

public class TPConfigParser {

	public static String[] readTpconfig(ResourceLocation path) {
		IResource res;
		try {
			res = Minecraft.getMinecraft().getResourceManager().getResource(path);
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(res.getInputStream()));
			
		    StringBuffer buffer = new StringBuffer();
		    while (true) {
		      String str = bufferReader.readLine();
		      if (str == null) {
		    	  break;
		      } 
		      str = str.trim();
		      
		      if (!str.startsWith("//"))
		    	  buffer.append(str.split("//", 2)[0]);
		      
		      
		    }
		    return buffer.toString().split("}");
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
