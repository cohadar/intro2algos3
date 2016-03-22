import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class MergeSortTest {

	private void test(int[] A, int left, int right) {
		int[] B = Arrays.copyOf(A, A.length);
		MergeSort.sort(B, left, right);
		for (int i = 0; i < left; i++) {
			assertEquals("left", A[i], B[i]);
		}
		for (int i = right + 1; i < A.length; i++) {
			assertEquals("right", A[i], B[i]);
		}
		for (int i = left + 1; i <= right; i++) {
			assertTrue("sorted", B[i-1] <= B[i]);
		}
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

	private int inversionCount(int[] B) {
		int inv = 0;
		for (int i = 0; i < B.length; i++) {
			for (int j = i + 1; j < B.length; j++) {
				if (B[i] > B[j]) {
					inv++;
				}
			}
		}
		return inv;
	}

	@Test
	public void testInversionCount() {
		for (int i = 0; i < 100; i++) {
			int[] A = randomArray(1000, -10000, 10000);
			int[] B = Arrays.copyOf(A, A.length);
			int inv1 = MergeSort.sort(A, 0, A.length - 1);
			int inv2 = inversionCount(B);
			assertEquals(inv1, inv2);
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

	static final int ITERATIONS = 1000;

	public static double timeInsertionSort(int n) {
		long start = System.currentTimeMillis();
		for (int i = 0; i < ITERATIONS; i++) {
			int[] A = randomArray(n, -1000, 1000);
			MergeSort.insertionSort(A, 0, A.length - 1);
		}
		long end = System.currentTimeMillis();
		return (end - start) / 1e3;
	}

	public static double timeMergeSort(int n) {
		long start = System.currentTimeMillis();
		for (int i = 0; i < ITERATIONS; i++) {
			int[] A = randomArray(n, -1000, 1000);
			MergeSort.sort(A, 0, A.length - 1);
		}
		long end = System.currentTimeMillis();
		return (end - start) / 1e3;
	}

	/**
	* Find the granularity border
	*/
	public static void main(String[] args) {
		for (int n = 550; n < 650; n++) {
			random = new Random(12345);
			double t1 = timeInsertionSort(n);
			random = new Random(12345);
			double t2 = timeMergeSort(n);
			System.out.printf("n=%d, t1=%f, t2=%f, insertion=%b\n", n, t1, t2, t1 <= t2);
		}
	}

}
