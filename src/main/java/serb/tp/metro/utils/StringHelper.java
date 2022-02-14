package serb.tp.metro.utils;

import serb.tp.metro.client.Type;

public abstract class StringHelper {

	public static String getFinalChars(String string, int counter) {
		if (counter>string.length()) counter = string.length();
		return string.substring(string.length()-1-Math.abs(counter));
	}
	
	public static String[] floatSplit(String str, int size) {
		int beginIndex = 0;
		int lastIndex = 0;
		int index = 0;
		
		while (index<str.length()) {
			if (str.substring(index, index+1).equals("@")) {
				beginIndex = index;
				lastIndex = index;
				
				
			}
			else if (str.substring(index, index+1).equals(" ")) {
				if (index-beginIndex<size) {
					lastIndex = index;
				}
				else if (index-beginIndex>size) {
					str = str.substring(0, lastIndex) + "@" + str.substring(lastIndex+1);
					beginIndex = lastIndex;
					lastIndex = index;
				}
				else {
					str = str.substring(0, index) + "@" + str.substring(index+1);
					beginIndex = index;
					lastIndex = index;
				}
			}
			index++;
		}

		return str.split("@");
	}
}
