import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class HeapSortTest {

	private void assertHeap(int[] H) {
		for (int c = H.length - 1; c >= 2; c--) {
			int p = c / 2;
			assertTrue("heap", H[p] >= H[c]);
		}
	}

	private void assertSorted(int[] H) {
		for (int i = 2; i < H.length; i++) {
			assertTrue("sorted", H[i - 1] <= H[i]);
		}
	}

	private static int[] array021(int[] A) {
		int[] H = new int[1 + A.length];
		for (int i = 0; i < A.length; i++) {
			H[1 + i] = A[i];
		}
		return H;		
	}	

	@Test
	public void testBuildMaxHeap() {
		int[] A = randomArray(1000, -1000, 1000);
		int[] H = array021(A);
		HeapSort.buildMaxHeap(H, A.length);
		assertHeap(H);
	}

	@Test
	public void testHeapSort() {
		int[] A = randomArray(1000, -1000, 1000);
		int[] H = array021(A);
		HeapSort.heapSort(H, A.length);
		debug(H);
		assertSorted(H);		

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
