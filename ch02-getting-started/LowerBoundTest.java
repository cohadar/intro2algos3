import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class LowerBoundTest {

	static int lowerBound(int[] A, int v, int li, int re) {
		while (li < re) {
			int mi = (li + re) >>> 1;
			if (A[mi] >= v) {
				re = mi;
			} else {
				li = mi + 1;
			}
		}
		return li;
	}

	static int lowerBoundRec(int[] A, int v, int li, int re) {
		if (li < re) {
			int mi = (li + re) >>> 1;
			if (A[mi] >= v) {
				return lowerBoundRec(A, v, li, mi);
			} else {
				return lowerBoundRec(A, v, li + 1, re);
			}
		}
		return li;
	}

	static int lowerBoundLinear(int[] A, int v, int li, int re) {
		int i = li;
		for (; i < re; i++) {
			if (A[i] >= v) {
				return i;
			}
		}
		return i;
	}

	private void test1() {
		int[] A = randomArray(10000, -1000, 1000);
		Arrays.sort(A);
		int v = nextInt(-1100, 1100);
		int t1 = random.nextInt(A.length);
		int t2 = random.nextInt(A.length);
		int li = Math.min(t1, t2);
		int re = Math.max(t1, t2) + 1;
		int a = lowerBoundLinear(A, v, li, re);
		int b = lowerBound(A, v, li, re);
		int c = lowerBoundRec(A, v, li, re);
		assertEquals(a, b);
		assertEquals(a, c);
	}

	@Test
	public void test() {
		for (int i = 0; i < 1000; i++) {
			test1();
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

	public static int nextInt(int low, int high) {
		return low + random.nextInt(high - low + 1);
	}

}
