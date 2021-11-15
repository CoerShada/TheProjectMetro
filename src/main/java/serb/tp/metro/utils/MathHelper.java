package serb.tp.metro.utils;

public class MathHelper{

	public static float sumPercent(float... percents) {
		if(percents.length==0) return 0;
		float sum = 1;
		for (float percent: percents) {
			
			sum*=(1+percent);
	
		}
		return sum;
	}
}
