import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class BinarySearch {

	public static Random random = new Random();
	
	public static int[] randomArray(int n, int min, int max) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = min + random.nextInt(max - min + 1);
		}
		return A;
	}

	public static int binarySearch(int[] A, int v, int l, int r) {
		while (l <= r) {
			int m = (l + r) >>> 1;
			if (A[m] < v) {
				l = m + 1;
			} else if (A[m] > v) {
				r = m - 1;
			} else {
				return m;
			}
		}
		return ~l;
	}

	public static void main(String[] args) {
		int[] A = randomArray(1000, -1000, 1000);
		Arrays.sort(A);
		for (int i = 0; i < 10000000; i++) {
			int v = -1111 + random.nextInt(2222);
			int a = Arrays.binarySearch(A, v);
			int b = binarySearch(A, v, 0, A.length - 1);
			if (a < 0 && b < 0) {
				assert a == b;
				continue;
			}	
			assert A[a] == A[b];
 		}
	}

	static void debug(Object...os) {
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}
}
