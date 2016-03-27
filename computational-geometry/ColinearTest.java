import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;
import javafx.geometry.Point2D;

/**
  * @author Mighty Cohadar 
  */
public class ColinearTest {

	public static boolean colinear(Point2D a, Point2D b, Point2D c) {
		b = b.subtract(a);
		c = c.subtract(a);
		// maybe fuzzy equal here?
		return crossProduct(b, c) == 0.0;
	}

	public static int numColinearN3(Point2D[] P) {
		int ret = 0;
		for (int i = 0; i < P.length; i++) {
			for (int j = i + 1; j < P.length; j++) {
				for (int k = j + 1; k < P.length; k++) {
					if (colinear(P[i], P[j], P[k])) {
						ret++;
					}
				}
			}
		}
		return ret;
	}

	public static double crossProduct(Point2D p1, Point2D p2) {
		return p1.getX() * p2.getY() - p1.getY() * p2.getX();
	}		

	public static int crossProduct(int x1, int y1, int x2, int y2) {
		return x1 * y2 - y1 * x2;
	}			

	public static int pick2(int x) {
		return x * (x - 1) / 2;
	}

	static void swap(Point2D[] A, int i, int j) {
		Point2D t = A[i];
		A[i] = A[j];
		A[j] = t;
	}

	public static int leftmost(Point2D[] P, int l) {
		int ll = l;
		for (int i = l; i < P.length; i++) {
			if (P[ll].getX() > P[i].getX()) {
				ll = i;
			}
		}
		return ll;
	}

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

	// WARNING: this algorithm is not resistant to point duplicates
	public static int numColinearN2LgN(Point2D[] P) {
		int ret = 0;
		for (int i = 0; i < P.length; i++) {
			int ll = leftmost(P, i);
			swap(P, i, ll);
			final Point2D ref = P[i];
			Comparator<Point2D> comparator = new Comparator<Point2D> () {
				public int compare(Point2D a, Point2D b) {
					int x1 = (int)(a.getX() - ref.getX());
					int y1 = (int)(a.getY() - ref.getY());
					int x2 = (int)(b.getX() - ref.getX());
					int y2 = (int)(b.getY() - ref.getY());
					return Double.compare(x1 * y2, y1 * x2);
				}
			};			
			for (int l = i + 1; l < P.length; l++) {
				for (int r = l + 1; r < P.length; r++) {
					int c1 = comparator.compare(P[l], P[r]);
					int c2 = -comparator.compare(P[r], P[l]);
					assertEquals("" + P[l] + P[r], c1, c2);
				}
			}
			Arrays.sort(P, i + 1, P.length, comparator);
			for (int j = i + 1; j < P.length; j++) {
				for (int k = j + 1; k < P.length; k++) {
					if (colinear(ref, P[j], P[k])) {
						ret++;
					} else {
						break;
					}
				}
			}
		}
		return ret;
	}	

	@Test
	public void testColinear() {
		assertTrue(colinear(new Point2D(0, 0), new Point2D(0, 0), new Point2D(0, 0)));
		assertTrue(colinear(new Point2D(0, 0), new Point2D(0, 0), new Point2D(123, 456)));
		assertTrue(colinear(new Point2D(0, 0), new Point2D(2, 1), new Point2D(4, 2)));
		assertFalse(colinear(new Point2D(0, 0), new Point2D(2, 1), new Point2D(4, 3)));
		assertFalse(colinear(new Point2D(0, 0), new Point2D(2, 2), new Point2D(4, 2)));
		assertFalse(colinear(new Point2D(1, 0), new Point2D(2, 1), new Point2D(4, 2)));
	}

	@Test
	public void testColinearSet() {
		int n = 1000;
		Point2D[] P = new Point2D[n];
		for (int i = 0; i < n; i++) {
			P[i] = randomPoint();
		}
		int c1 = numColinearN3(P);
		int c2 = numColinearN2LgN(P);
		debug(c1, c2);
		assertEquals(c1, c2);
	}

	public static Random random = new Random();
	
	public static Point2D randomPoint() {
		int low = 10;
		int high = 20;
		return new Point2D(low + random.nextInt(high - low + 1), low + random.nextInt(high - low + 1));
	}

	static void debug(Object...os) {
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
