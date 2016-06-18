import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class MergeSortTest {

	static void swap(int[] A, int i, int j) {
		int t = A[i];
		A[i] = A[j];
		A[j] = t;
	}

	static void insertionSort(int[] A, int li, int re) {
		for (int r = li + 1; r < re; r++) {
			int val = A[r];
			int l = r;
			while (li < l && A[l-1] > A[l]) {
				A[l] = A[l-1];
				l--;
			}
			swap(A, l, r);
		}
	}

	static void merge(int[] A, int[] B, int li, int mi, int re) {
		int l = li;
		int r = mi;
		int j = li;
		while (l < mi && r < re) {
			if (A[l] <= A[r]) {
				B[j++] = A[l++];
			} else {
				B[j++] = A[r++];
			}
		}
		while (l < mi) {
			B[j++] = A[l++];
		}
		for (int i = li; i < j; i++) {
			A[i] = B[i];
		}
	}

	static void mergeSort(int[] A, int[] B, int li, int re) {
		if (li >= re) {
			return;
		}
		if (re - li <= 10) {
			insertionSort(A, li, re);
		} else {
			int mi = (li + re) >>> 1;
			mergeSort(A, B, li, mi);
			mergeSort(A, B, mi, re);
			merge(A, B, li, mi, re);	
		}
	}

	static void mergeSort(int[] A, int li, int re) {
		mergeSort(A, new int[A.length], li, re);
	}

	private void test(int[] A, int l, int re) {
		int[] B = Arrays.copyOf(A, A.length);
		mergeSort(B, l, re); // <---------<<
		for (int i = 0; i < l; i++) {
			assertEquals("left", A[i], B[i]);
		}
		for (int i = re; i < A.length; i++) {
			assertEquals("re", A[i], B[i]);
		}
		for (int i = l + 1; i < re; i++) {
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

	public static Random random = new Random(12345);
	
	public static int[] randomArray(int n, int li, int re) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = li + random.nextInt(re - li);
		}
		return A;
	}

}
