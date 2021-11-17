package serb.tp.metro.utils;

public class DefenceVariables {

    /**
     * Checks if the class from which the method is called can change the fields of the current object. Args: classes
     */
	public static boolean authorizedAccess(Class... classes) {
		if (classes.length==0) return false;
		String ste = Thread.currentThread().getStackTrace()[3].toString();
		ste = ste.substring(0, ste.indexOf("("));
		ste = ste.substring(0, ste.lastIndexOf("."));
		Class clazzThread;
		
		try {
			clazzThread = Class.forName(ste);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		for (Class clazz: classes) {
			if (clazz.isAssignableFrom(clazzThread)) return true;
		}
		return false;
	}
	

}
