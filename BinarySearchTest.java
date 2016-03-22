import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class BinarySearchTest {

	@Test
	public void testFullRange() {
		int[] A = randomArray(1000, -1000, 1000);
		Arrays.sort(A);
		for (int i = 0; i < 100000; i++) {
			int v = -1111 + random.nextInt(2222);
			int a = Arrays.binarySearch(A, v);
			int b = BinarySearch.binarySearch(A, v, 0, A.length - 1);
			if (a < 0 && b < 0) {
				assert a == b;
				continue;
			}	
			assert A[a] == A[b];
 		}		
	}

	@Test
	public void testRandomSubrange() {
		int[] A = randomArray(1000, -1000, 1000);
		Arrays.sort(A);
		for (int i = 0; i < 100000; i++) {
			int v = -1111 + random.nextInt(2222);
			int t1 = random.nextInt(A.length);
			int t2 = random.nextInt(A.length);
			int left = Math.min(t1, t2);
			int right = Math.max(t1, t2);
			int a = Arrays.binarySearch(A, left, right+1, v);
			int b = BinarySearch.binarySearch(A, v, left, right);
			if (a < 0 && b < 0) {
				assert a == b;
				continue;
			}	
			assert A[a] == A[b];
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
