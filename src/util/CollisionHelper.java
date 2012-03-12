package util;

public class CollisionHelper {
	
	/*
	 * Checks if 2 shapes are intersecting
	 * 
	 * @param x value of first shape
	 * @param y value of first shape
	 * @param width of first shape
	 * @param height of first shape
	 * @param x value of second shape
	 * @param y value of second shape
	 * @param width of second shape
	 * @param height of second shape
	 * 
	 */
	public static boolean intersectingShapes(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
		if ((x1 + h1 > x2 && x1 < x2 + h2) && (y1 + h1 > y2 && y1 < y2 + h2)) {
			return true;
		} else {
			return false;
		}
	}
}
