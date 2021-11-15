package serb.tp.metro.utils;

public class IntHelper {

	public static int cutAfterSymbols(int number, int size) {
		String numberStr = String.valueOf(number);
		if (size<0) {
			size = numberStr.length()+size;
		}
		numberStr = numberStr.substring(size);
		return Integer.valueOf(numberStr);
	}
	
	public static int cutBeforeSymbols(int number, int size) {
		String numberStr = String.valueOf(number);
		if (size<0) {
			size = numberStr.length()+size;
		}
		numberStr = numberStr.substring(0, size);
		return Integer.valueOf(numberStr);
	}
	
	public static int concat(int... number) {
		if(number.length==0) {
			return -1;
		}
		else {
			String newNumber = "";
			for (int num: number) {
				newNumber+= String.valueOf(num);
			}
			return Integer.valueOf(newNumber);
		}
	}
	
	public static int indexOf(int number, int finded) {
		String numberStr = String.valueOf(number);
		return numberStr.indexOf(String.valueOf(finded));
	}
}
