import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class QuickSortTest {

	@Test
	public void testWithDutch() {
		int[] A = randomArray(1000, -1000, 1000);
		QuickSort.sort_with_dutch(A, 0, A.length - 1);
		assertTrue("sorted", isSorted(A));
	}	

	@Test
	public void testWithHoare() {
		int[] A = randomArray(1000, -1000, 1000);
		QuickSort.sort_with_hoare(A, 0, A.length - 1);
		assertTrue("sorted", isSorted(A));
	}	

	@Test
	public void testWithRandom() {
		int[] A = randomArray(1000, -1000, 1000);
		QuickSort.sort_with_random(A, 0, A.length - 1);
		assertTrue("sorted", isSorted(A));
	}

	public static Random random = new Random();
	
	public static int[] randomArray(int n, int min, int max) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = min + random.nextInt(max - min + 1);
		}
		return A;
	}

	public static boolean isSorted(int[] A) {
		for (int i = 1; i < A.length; i++) {
			if (A[i-1] > A[i]) {
				return false;
			}
		}
		return true;
	}

	static void debug(Object...os) {
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
