import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class BinarySearchTest {

	static int binarySearch(int[] A, int li, int re, int val) {
		while (li < re) {
			int mi = (li + re) >>> 1;
			if (A[mi] > val) {
				re = mi;
			} else if (A[mi] < val) {
				li = mi + 1;
			} else {
				return mi;
			}
		}
		return ~li;
	}

	@Test
	public void testFullRange() {
		int[] A = randomArray(1000, -1000, 1000);
		Arrays.sort(A);
		for (int i = 0; i < 100000; i++) {
			int v = -1111 + random.nextInt(2222);
			int a = Arrays.binarySearch(A, v);
			int b = binarySearch(A, 0, A.length, v);
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
			int li = Math.min(t1, t2);
			int re = Math.max(t1, t2) + 1;
			int a = Arrays.binarySearch(A, li, re, v);
			int b = binarySearch(A, li, re, v);
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
