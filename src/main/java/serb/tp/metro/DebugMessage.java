package serb.tp.metro;

import java.text.SimpleDateFormat;
import java.util.Date;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class DebugMessage {

	public static void printMessage(String message) {
		if (Main.debug) {
			
			String ste = Thread.currentThread().getStackTrace()[2].toString();
			ste = ste.substring(0, ste.indexOf("(")) + ":"+ ste.substring(ste.indexOf(":")+1, ste.length()-1);
			String side;
			if (FMLCommonHandler.instance().getEffectiveSide()==Side.SERVER)
				side = "Server";
			else
				side = "Client";
		    Date dateNow = new Date();
		    SimpleDateFormat formatForDateNow = new SimpleDateFormat("hh:mm:ss");
			System.out.print("["+ formatForDateNow.format(dateNow) +"] [TP Metro " + side +"/Debug]: "+"["+ste.toString()+"] " + message + "\n");
		}
	}
	
	public static void printMessage(float message) {
		printMessage(String.valueOf(message));
	}
	
	public static void printMessage(int message) {
		printMessage(String.valueOf(message));
	}
	
	public static void printMessage(double message) {
		printMessage(String.valueOf(message));
	}
	
	public static void printMessage(boolean message) {
		printMessage(String.valueOf(message));
	}
}
