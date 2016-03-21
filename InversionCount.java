import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class InversionCount {

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

	public static int merge(int[] A, int l, int m, int r) {
		int ret = 0;
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
				ret += ir; // <-----------------<<
			} else if (L[il] <= R[ir]) {
				A[i] = L[il++];
				ret += ir; // <-----------------<<
			} else {
				A[i] = R[ir++];
			}
		}
		return ret;
	}

	public static int sort(int[] A, int l, int r) {
		if (l == r) {
			return 0; // use insertion sort here when r - l <= 10
		}
		int m = (l + r) >>> 1;
		int ret = 0;
		ret += sort(A, l, m);
		ret += sort(A, m+1, r);
		ret += merge(A, l, m, r);
		return ret;
	}

	public static int sort(int[] A) {
		return sort(A, 0, A.length - 1);
	}

	public static int countInversions(int[] A) {
		int ret = 0;
		for (int l = 0; l < A.length - 1; l++) {
			for (int r = l + 1; r < A.length; r++) {
				if (A[l] > A[r]) {
					ret++;
				}
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		int[] A = randomArray(20, -20, 20);
		int invq = countInversions(A);
		int invm = sort(A);
		debug(invq, invm);
		assert invq == invm;
		debug(A);
		for (int i = 1; i < A.length; i++) {
			assert A[i-1] <= A[i];
		}
	}

	static void debug(Object...os) {
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
