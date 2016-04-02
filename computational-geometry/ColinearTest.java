import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class ColinearTest {

	public static Random random = new Random();
	
	public static boolean colinear(Point a, Point b, Point c) {
		return a.subtract(c).crossProduct(b.subtract(c)) == 0;
	}

	public static int colinearN3(Point[] P) {
		int count = 0;
		for (int ia = 0; ia < P.length; ia++) {
			for (int ib = ia + 1; ib < P.length; ib++) {
				for (int ic = ib + 1; ic < P.length; ic++) {
					if (colinear(P[ia], P[ib], P[ic])) {
						count++;
					}
				}
			}
		}
		return count;
	}

	// 33.1-4
	public static int colinearN2LgN(Point[] P) {
		// no idea
		return -1;
	}

	@Test
	public void testColinear() {
		Point[] P = new Point[1000];
		for (int i = 0; i < P.length; i++) {
			P[i] = randomPointFQ();
		}
		int c1 = colinearN3(P);
		int c2 = colinearN2LgN(P);
		debug(c1, c2);
		assertEquals(c1, c2);
	}

	public static Point randomPointFQ() {
		return new Point(random.nextInt(1000) - 500, random.nextInt(1000) - 500);
	}

	static void debug(Object...os) {
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
