import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class InsertionSortTest {

	private void test(int[] A, int left, int right) {
		int[] B = Arrays.copyOf(A, A.length);
		InsertionSort.insertionSort(B, left, right);
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

	public static Random random = new Random();
	
	public static int[] randomArray(int n, int min, int max) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = min + random.nextInt(max - min + 1);
		}
		return A;
	}

}
