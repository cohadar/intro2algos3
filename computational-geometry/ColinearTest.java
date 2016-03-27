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
		return b.getY() * c.getX() == b.getX() * c.getY();
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

	public static int numColinearN2LgN(Point2D[] P) {
		int ret = 0;
		for (int i = 0; i < P.length - 1; i++) {
			final Point2D ref = P[i];
			Arrays.sort(P, i+1, P.length, new Comparator<Point2D> () {
				public int compare(Point2D a, Point2D b) {
					return (int)Math.signum(crossProduct(b.subtract(ref), a.subtract(ref)));
					// Point2D c = a.subtract(ref);
					// Point2D d = b.subtract(ref);
					// double angleA = Math.atan2(c.getY(), c.getX());
					// double angleB = Math.atan2(d.getY(), d.getX());
					// return Double.compare(angleA, angleB);					
				}
			});
			for (int j = i + 1; j < P.length; j++) {
				for (int k = j + 1; k < P.length; k++) {
					if (colinear(P[i], P[j], P[k])) {
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
	public void test() {
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
