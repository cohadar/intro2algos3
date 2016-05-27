import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class MinMaxTest {

	public static int min(int[] A) {
		int min = A[0];
		for (int i = 1; i < A.length; i++) {
			if (min > A[i]) {
				min = A[i];
			}
		}
		return min;
	}

	public static int max(int... A) {
		int max = A[0];
		for (int i = 1; i < A.length; i++) {
			if (max < A[i]) {
				max = A[i];
			}
		}
		return max;
	}

	@Test
	public void testMinMax() {
		for (int t = 0; t < 100; t++) {
			int[] A = randomArray(1 + random.nextInt(1000), -1000, 1000);
			int[] mm = MinMax.minMax(A);
			assertEquals("min", min(A), mm[0]);
			assertEquals("max", max(A), mm[1]);
		}
	}

	@Test
	public void testMinMin() {
		for (int t = 0; t < 100; t++) {
			int[] A = randomArray(2 + random.nextInt(1000), -1000, 1000);
			int[] mm = MinMax.minMin(A);
			Arrays.sort(A);
			assertEquals("min1", A[0], mm[0]);
			assertEquals("min2", A[1], mm[1]);
		}
	}	

	@Test 
	public void testOrderStatistic() {
		for (int t = 0; t < 100; t++) {
			int[] A = randomArray(2 + random.nextInt(1000), -1000, 1000);
			int order = 1 + random.nextInt(A.length);
			int v = MinMax.orderStatistic(A, 0, A.length - 1, order);
			int min = MinMax.orderStatistic(A, 0, A.length - 1, 1);
			int max = MinMax.orderStatistic(A, 0, A.length - 1, A.length);
			Arrays.sort(A);
			assertEquals("order rnd", A[order - 1], v);
			assertEquals("order min", A[0], min);
			assertEquals("order max", A[A.length - 1], max);
		}
	}

	public static Random random = new Random();
	
	public static int[] randomArray(int n, int min, int max) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = min + random.nextInt(max - min + 1);
		}
		return A;
	}

	static void debug(Object...os) {
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
