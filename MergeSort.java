import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class MergeSort {

	public static Random random = new Random();
	
	public static int[] randomArray(int n, int min, int max) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = min + random.nextInt(max - min + 1);
		}
		return A;
	}

	static void swap(int[] A, int i, int j) {
		int t = A[i];
		A[i] = A[j];
		A[j] = t;
	}

	public static void merge(int[] A, int l, int m, int r) {
		int nl = m - l + 1;
		int nr = r - (m+1) + 1;
		int[] L = new int[nl];
		int[] R = new int[nr];
		for (int i = 0; i < nl; i++) {
			L[i] = A[l + i];
		}
		for (int i = 0; i < nr; i++) {
			R[i] = A[m+1 + i];
		}
		int il = 0;
		int ir = 0;
		for (int i = l; i <= r; i++) {
			if (il >= L.length) {
				A[i] = R[ir++];
			} else if (ir >= R.length) {
				A[i] = L[il++];
			} else if (L[il] <= R[ir]) {
				A[i] = L[il++];
			} else {
				A[i] = R[ir++];
			}
		}
	}

	public static void sort(int[] A, int l, int r) {
		if (l == r) {
			return; // use insertion sort here when r - l <= 10
		}
		int m = (l + r) >>> 1;
		sort(A, l, m);
		sort(A, m+1, r);
		merge(A, l, m, r);
	}

	public static void sort(int[] A) {
		sort(A, 0, A.length - 1);
	}

	public static void main(String[] args) {
		int[] A = randomArray(20, -20, 20);
		sort(A);
		debug(A);
		for (int i = 1; i < A.length; i++) {
			assert A[i-1] <= A[i];
		}
	}

	static void debug(Object...os) {
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
