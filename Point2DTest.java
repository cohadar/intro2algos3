import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;
import javafx.geometry.Point2D;

/**
  * @author Mighty Cohadar 
  */
public class Point2DTest {

	/** 
	 *	@returns true iff y is within relative or absolute 'epsilon' of x. 
	 *  By default, 'epsilon' is 1e-6.
	 */
	public static boolean isApproximatelyEqual(double x, double y, double epsilon) {
		// Check absolute precision.
		if (Math.abs(x - y) <= epsilon) {
			return true;
		}
		// Is x or y too close to zero?
		if ((Math.abs(x) <= epsilon) || (Math.abs(y) <= epsilon)) {
 			return false;
 		}
		// Check relative precision.
		return (Math.abs((x - y) / x) <= epsilon) || (Math.abs((x - y) / y) <= epsilon);
	}

	public static boolean isClockwise(Point2D p1, Point2D p2) {
		return p1.crossProduct(p2).getZ() < 0.0;
	}

	public static double normalizeAngle(double angle) {
		return Math.atan2(Math.sin(angle), Math.cos(angle));
	}

	public static Point2D rotate(Point2D p1, double angle) {
		double c = Math.cos(angle);
		double s = Math.sin(angle);
		return new Point2D(p1.getX() * c - p1.getY() * s, p1.getY() * c + p1.getX() * s);
	}
	
	@Test
	public void testClockwise() {
		Point2D p1 = new Point2D(3.0, 4.0);
		int diffs = 0;
		for (double angle = 2.3 + -10*Math.PI; angle <= 10*Math.PI; angle+=Math.PI/12) {
			Point2D p2 = rotate(p1, angle);
			debug(normalizeAngle(angle), normalizeAngle(angle) < 0.0, isClockwise(p1, p2));
			if (normalizeAngle(angle) < 0.0 != isClockwise(p1, p2)) {
				diffs++;
			}
		}
		debug(diffs);
		assertTrue(diffs < 2);
	}

	
	public boolean segmentsIntersect(Point2D p1, Point2D p2, Point2D p3, Point2D p4) {
		return false;
	}

	@Test
	public void testSegmentsIntersect() {
		assertTrue(segmentsIntersect(new Point2D(2, 1), new Point2D(3, 3), new Point2D(1, 4), new Point2D(4, 2)));
		assertTrue(segmentsIntersect(new Point2D(1, 1), new Point2D(6, 7), new Point2D(2, 0), new Point2D(0, 2)));
		assertFalse(segmentsIntersect(new Point2D(1, 4), new Point2D(3, 1), new Point2D(3, 3), new Point2D(5, 2)));
	}

	static void debug(Object...os) {
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

	public static Random random = new Random();

	public static Point2D randomPoint2D(double low, double high) {
		assert low <= high;
		return new Point2D(low + Math.random() * (high - low), low + Math.random() * (high - low));
	}

}
