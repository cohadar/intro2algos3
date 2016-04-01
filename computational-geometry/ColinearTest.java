import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class ColinearTest {

	static class Point2D {
		final int x;
		final int y;
		Point2D(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
		public Point2D subtract(Point2D that) {
			return new Point2D(this.x - that.x, this.y - that.y);
		}
		public String toString() {
			return String.format("(x=%d, y=%d)", x, y);
		}	
	}

	public static int crossProduct(Point2D p1, Point2D p2) {
		return p1.getX() * p2.getY() - p1.getY() * p2.getX();
	}		

	public static boolean colinear(Point2D a, Point2D b, Point2D c) {
		b = b.subtract(a);
		c = c.subtract(a);
		// maybe fuzzy equal here?
		return crossProduct(b, c) == 0;
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

	// WARNING: this algorithm is not resistant to point duplicates
	// Arrays.sort MUST HAVE Comparator with TOTAL ORDERING.
	// Since crossProduct is only a partial ordering on angles, default java sort is not good for this.
	public static int numColinearN2LgN(Point2D[] P) {
		int ret = 0;
		for (int i = 0; i < P.length; i++) {
			final Point2D ref = P[i]; 
			final int rx = ref.getX(); 
			final int ry = ref.getY();
			Comparator<Point2D> comparator = new Comparator<Point2D> () {
				public int compare(Point2D a, Point2D b) {
					int x1 = (a.getX() - rx);
					int y1 = (a.getY() - ry);
					int x2 = (b.getX() - rx);
					int y2 = (b.getY() - ry);
					return Integer.compare(x1 * y2, y1 * x2);
					//return Double.compare(Math.atan2(y1, x1), Math.atan2(y2, x2));
				}
			};			
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
		int low = -1000;
		int high = 1000;
		return new Point2D(low + random.nextInt(high - low + 1), low + random.nextInt(high - low + 1));
	}

	static void debug(Object...os) {
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
