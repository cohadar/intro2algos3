import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;
import javafx.geometry.Point2D;

/**
  * @author Mighty Cohadar 
  */
public class SortByPolarAngleTest {

	public static Random random = new Random();
	
	public static void shuffle(Point2D[] A) {
		for (int i = 0; i < A.length-1; i++) {
			int j = i + random.nextInt(A.length - i);
			Point2D temp = A[i];
			A[i] = A[j];
			A[j] = temp;
		}
	}

	public static double crossProduct(Point2D p1, Point2D p2) {
		return p1.getX() * p2.getY() - p1.getY() * p2.getX();
	}		

	@Test
	public void test() {
		Point2D pr = new Point2D(4, -3); // reference point must be outside bounding rectangle
		Point2D p0 = new Point2D(1, 0);
		Point2D p1 = new Point2D(1, 1);
		Point2D p2 = new Point2D(0, 1);
		Point2D p3 = new Point2D(-1, 1);
		Point2D p4 = new Point2D(-1, 0);
		Point2D p5 = new Point2D(-1, -1);
		Point2D p6 = new Point2D(0, -1);
		Point2D p7 = new Point2D(1, -1);
		Point2D[] P = new Point2D[] {p0, p1, p2, p3, p4, p5, p6, p7};
		shuffle(P);
		Point2D[] Q = Arrays.copyOf(P, P.length);
		// sort P by angle
		Arrays.sort(P, new Comparator<Point2D> () {
			public int compare(Point2D a, Point2D b) {
				Point2D c = a.subtract(pr);
				Point2D d = b.subtract(pr);
				double angleA = Math.atan2(c.getY(), c.getX());
				double angleB = Math.atan2(d.getY(), d.getX());
				return Double.compare(angleA, angleB);
			}
		});
		// sort Q by crossProduct
		Arrays.sort(Q, new Comparator<Point2D> () {
			public int compare(Point2D a, Point2D b) {
				// positive crossProduct is negative angle, hence (b, a)
				return (int)Math.signum(crossProduct(b.subtract(pr), a.subtract(pr)));
			}
		});		
		assertArrayEquals(P, Q);
	}

	static void debug(Object...os) {
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
