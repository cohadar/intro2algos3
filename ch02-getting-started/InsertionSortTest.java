import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class InsertionSortTest {

	static void insertionSort(int[] A, int li, int re) {
		for (int r = li + 1; r < re; r++) {
			int val = A[r];
			int l = r;
			while (li < l && A[l-1] > val) {
				A[l] = A[l-1];
				l--;
			}
			A[l] = val;
		}
	}

	private void test(int[] A, int l, int re) {
		int[] B = Arrays.copyOf(A, A.length);
		int[] C = Arrays.copyOf(A, A.length);
		insertionSort(B, l, re); // <---------<<
		Arrays.sort(C, l, re);
		for (int i = 0; i < l; i++) {
			assertEquals("left", A[i], B[i]);
		}
		for (int i = re; i < A.length; i++) {
			assertEquals("re", A[i], B[i]);
		}
		for (int i = l + 1; i < re; i++) {
			assertTrue("sorted", B[i-1] <= B[i]);
		}
		assertArrayEquals("control", B, C);
	}

	@Test
	public void testFullRange() {
		for (int i = 0; i < 100; i++) {
			int[] A = randomArray(1000, -10000, 10000);
			test(A, 0, A.length - 1);
		}
	}	

	@Test
	public void testRandomRanges() {
		for (int i = 0; i < 100; i++) {
			int[] A = randomArray(1000, -10000, 10000);
			int a = random.nextInt(A.length);
			int b = random.nextInt(A.length);
			test(A, Math.min(a, b), Math.max(a, b));
		}
	}

	public static Random random = new Random();
	
	public static int[] randomArray(int n, int li, int re) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = li + random.nextInt(re - li);
		}
		return A;
	}

}
