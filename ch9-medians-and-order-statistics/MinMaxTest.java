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
			int m1 = min(A);
			for (int i = 0; i < A.length; i++) {
				if (A[i] == m1) {
					A[i] = Integer.MAX_VALUE;
					break;
				}
			}
			int m2 = min(A);
			assertEquals("min1", m1, mm[0]);
			assertEquals("min2", m2, mm[1]);
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
