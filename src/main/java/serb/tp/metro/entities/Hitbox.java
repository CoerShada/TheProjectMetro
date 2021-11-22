package serb.tp.metro.entities;

public class Hitbox {

	private Point[] points = new Point[4];
	private Point rotatePoint;
	
	public Hitbox(float x, float y, float z, float length, float height, float width) {
		addPoint(x + length, y + height, z + width);
		addPoint(x - length, y + height, z + width);
		
		addPoint(x + length, y + height, z - width);
		addPoint(x - length, y + height, z - width);
		
		addPoint(x + length, y - height, z + width);
		addPoint(x - length, y - height, z + width);
		
		addPoint(x + length, y - height, z - width);
		addPoint(x - length, y - height, z - width);
		
	}
	
	public void addPoint(float x, float y, float z) {
		for (int i = 0; i<points.length; i++) {
			if(points[i]==null) {
				points[i] = new Point(i, x, y, z);
			}
		}
	}
	
	public Point[] getPoints() {
		return points;
	}
}
