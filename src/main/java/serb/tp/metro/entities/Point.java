package serb.tp.metro.entities;

public class Point {

	public final int number;
	float x;
	float y;
	float z;
	public Point(int number, float x, float y, float z) {
		this.number = number;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public final float getX() {
		return x;
	}
	public final void setX(float x) {
		this.x = x;
	}
	public final float getY() {
		return y;
	}
	public final void setY(float y) {
		this.y = y;
	}
	public final float getZ() {
		return z;
	}
	public final void setZ(float z) {
		this.z = z;
	}
	
	
}
