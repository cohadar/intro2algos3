import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class SortByPolarAngleTest {

	public static Random random = new Random();
	
	@Test
	public void test() {
		Point[] P = new Point[100];
		for (int i = 0; i < P.length; i++) {
			P[i] = randomPointFQ();
		}
		Point[] Q = Arrays.copyOf(P, P.length);
		// sort P by angle
		Arrays.sort(P, new Comparator<Point> () {
			public int compare(Point a, Point b) {
				double angleA = Math.atan2(a.y, a.x);
				double angleB = Math.atan2(b.y, b.x);
				return Double.compare(angleB, angleA);
			}
		});
		// sort Q by crossProduct
		Arrays.sort(Q, new Comparator<Point> () {
			public int compare(Point a, Point b) {
				return (int)Math.signum(a.crossProduct(b));
			}
		});		
		assertArrayEquals(P, Q);
	}

	/**
	 * Sign of angle difference and crossProduct match as long as all points belong to the Quadrant pairs (1,2) or (1,4).
	 * That is as long either all X have the same sign or all Y have the same sign.
	 */
	@Test
	public void testFirstQuadrant() {
		for (int i = 0; i < 100; i++) {
			Point p1 = randomPointFQ();
			Point p2 = randomPointFQ();
			double angle1 = Math.atan2(p1.y, p1.x);
			double angle2 = Math.atan2(p2.y, p2.x);
			double cross = p1.crossProduct(p2);
			double dot = p1.dotProduct(p2);
			debug(Math.signum(angle2 - angle1), Math.signum(cross));
			assertEquals(Math.signum(angle2 - angle1), Math.signum(cross), 1e-6);
		}
	}

	public static Point randomPointFQ() {
		return new Point(Math.random() * 1000 - 500, Math.random() * 1000);
	}

	static void debug(Object...os) {
		// System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
