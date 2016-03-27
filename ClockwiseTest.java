import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;
import javafx.geometry.Point2D;

/**
  * @author Mighty Cohadar 
  */
public class ClockwiseTest {

	/** 
	 *	@returns true iff y is within relative or absolute 'epsilon' of x. 
	 *  By default, 'epsilon' is 1e-6.
	 */
	public static boolean fuzzyEqual(double x, double y, double epsilon) {
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

	public static double crossProduct(Point2D p1, Point2D p2) {
		return p1.getX() * p2.getY() - p1.getY() * p2.getX();
	}		

	public static boolean clockwise(Point2D p1, Point2D p2) {
		return crossProduct(p1, p2) < 0.0;
	}	

	public static double normalizeAngle(double angle) {
		return Math.atan2(Math.sin(angle), Math.cos(angle));
	}

	public static Point2D rotate(Point2D p, double angle) {
		double c = Math.cos(angle);
		double s = Math.sin(angle);
		return new Point2D(p.getX() * c - p.getY() * s, p.getY() * c + p.getX() * s);
	}
	
	@Test
	public void testClockwise() {
		Point2D p1 = new Point2D(3.0, 4.0);
		int diffs = 0;
		for (double angle = 2.3 + -10*Math.PI; angle <= 10*Math.PI; angle+=Math.PI/12) {
			Point2D p2 = rotate(p1, angle);
			debug(normalizeAngle(angle), normalizeAngle(angle) < 0.0, clockwise(p1, p2));
			if (normalizeAngle(angle) < 0.0 != clockwise(p1, p2)) {
				diffs++;
			}
		}
		debug(diffs);
		assertTrue(diffs < 2);
	}

	static void debug(Object...os) {
		// System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
