package serb.tp.metro;

public class DebugMessage {

	public static void printMessage(String message, Object clazz) {
		if (Main.debug)
			System.out.println("["+clazz.getClass().getName()+"] " + message);
	}
	
	public static void printMessage(float message, Object clazz) {
		printMessage(String.valueOf(message), clazz);
	}
	
	public static void printMessage(int message, Object clazz) {
		printMessage(String.valueOf(message), clazz);
	}
	
	public static void printMessage(double message, Object clazz) {
		printMessage(String.valueOf(message), clazz);
	}
	
	public static void printMessage(boolean message, Object clazz) {
		printMessage(String.valueOf(message), clazz);
	}
}
