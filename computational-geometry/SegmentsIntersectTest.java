import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;
import javafx.geometry.Point2D;

/**
  * @author Mighty Cohadar 
  */
public class SegmentsIntersectTest {

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

	// we assume points are colinear
	public static boolean betweenColinear(Point2D a, Point2D m, Point2D b) {
		if (Math.min(a.getX(), b.getX()) <= m.getX()
		&&  Math.max(a.getX(), b.getX()) >= m.getX()
        &&  Math.min(a.getY(), b.getY()) <= m.getY()
        &&  Math.max(a.getY(), b.getY()) >= m.getY()) {
			return true;
		}
		return false;
	}

	
	public boolean segmentsIntersect(Point2D p1, Point2D p2, Point2D p3, Point2D p4) {
		double c1 = crossProduct(p4.subtract(p3), p1.subtract(p3));
		double c2 = crossProduct(p4.subtract(p3), p2.subtract(p3));
		double c3 = crossProduct(p2.subtract(p1), p3.subtract(p1));
		double c4 = crossProduct(p2.subtract(p1), p4.subtract(p1));
		boolean straddle12 = Math.min(c1, c2) < 0.0 && Math.max(c1, c2) > 0.0;
		boolean straddle34 = Math.min(c3, c4) < 0.0 && Math.max(c3, c4) > 0.0;
		if (straddle12 && straddle34) {
			return true;
		}
		if (fuzzyEqual(c1, 0.0, 1e-9) && betweenColinear(p3, p1, p4)) {
			return true;
		}
		if (fuzzyEqual(c2, 0.0, 1e-9) && betweenColinear(p3, p2, p4)) {
			return true;
		}		
		if (fuzzyEqual(c3, 0.0, 1e-9) && betweenColinear(p1, p3, p2)) {
			return true;
		}				
		if (fuzzyEqual(c4, 0.0, 1e-9) && betweenColinear(p1, p4, p2)) {
			return true;
		}						
		return false;
	}

	@Test
	public void testSegmentsIntersect() {
		// straddle
		assertTrue(segmentsIntersect(new Point2D(2, 1), new Point2D(3, 3), new Point2D(1, 4), new Point2D(4, 2)));
		// endpoint p1 on segment p3p4
		assertTrue(segmentsIntersect(new Point2D(1, 1), new Point2D(6, 7), new Point2D(2, 0), new Point2D(0, 2)));
		// endpoint p2 on segment p3p4
		assertTrue(segmentsIntersect(new Point2D(6, 7), new Point2D(1, 1), new Point2D(2, 0), new Point2D(0, 2)));		
		// endpoint p3 on segment p1p2
		assertTrue(segmentsIntersect(new Point2D(2, 0), new Point2D(0, 2), new Point2D(1, 1), new Point2D(6, 7)));		
		// endpoint p4 on segment p1p2
		assertTrue(segmentsIntersect(new Point2D(2, 0), new Point2D(0, 2), new Point2D(6, 7), new Point2D(1, 1)));				
		// no intersection
		assertFalse(segmentsIntersect(new Point2D(1, 4), new Point2D(3, 1), new Point2D(3, 3), new Point2D(5, 2)));
	}

	static void debug(Object...os) {
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
