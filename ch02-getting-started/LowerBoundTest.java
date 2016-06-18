import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class LowerBoundTest {

	static int lowerBound(int[] A, int li, int re, int val) {
		while (li < re) {
			int mi = (li + re) >>> 1;
			if (A[mi] >= val) {
				re = mi;
			} else {
				li = mi + 1;
			}
		}
		return li;
	}

	static int lowerBoundRec(int[] A, int li, int re, int val) {
		if (li < re) {
			int mi = (li + re) >>> 1;
			if (A[mi] >= val) {
				return lowerBoundRec(A, li, mi, val);
			} else {
				return lowerBoundRec(A, li + 1, re, val);
			}
		}
		return li;
	}

	static int lowerBoundLinear(int[] A, int li, int re, int val) {
		int i = li;
		for (; i < re; i++) {
			if (A[i] >= val) {
				return i;
			}
		}
		return i;
	}

	private void test1() {
		int[] A = randomArray(10000, -1000, 1000);
		Arrays.sort(A);
		int val = nextInt(-1100, 1100);
		int t1 = random.nextInt(A.length);
		int t2 = random.nextInt(A.length);
		int li = Math.min(t1, t2);
		int re = Math.max(t1, t2) + 1;
		int a = lowerBoundLinear(A, li, re, val);
		int b = lowerBound(A, li, re, val);
		int c = lowerBoundRec(A, li, re, val);
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
