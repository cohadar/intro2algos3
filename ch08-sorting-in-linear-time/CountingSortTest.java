import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class CountingSortTest {

	@Test
	public void test() {
		int[] A = randomArray(1000, 0, 199);
		int[] B = CountingSort.sort(A, 200);
		Arrays.sort(A);
		assertArrayEquals(A, B);
	}

	public static Random random = new Random();
	
	public static int[] randomArray(int n, int min, int max) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = min + random.nextInt(max - min + 1);
		}
		return A;
	}

	public boolean isSorted(int[] A) {
		for (int i = 1; i < A.length; i++) {
			if (A[i - 1] > A[i]) {
				return false;
			}
		}
		return true;
	}

}
