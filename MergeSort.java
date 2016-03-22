import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class MergeSort {

	public static void swap(int[] A, int i, int j) {
		int t = A[i];
		A[i] = A[j];
		A[j] = t;
	}

	public static int merge(int[] A, int l, int m, int r) {
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
		int inv = 0;
		for (int i = l; i <= r; i++) {
			if (il >= L.length) {
				A[i] = R[ir++];
			} else if (ir >= R.length) {
				A[i] = L[il++];
				inv += ir;
			} else if (L[il] <= R[ir]) {
				A[i] = L[il++];
				inv += ir;
			} else {
				A[i] = R[ir++];
			}
		}
		return inv;
	}

	public static int insertionSort(int[] A, int left, int right) {
		int inv = 0;
		for (int r = left + 1; r <= right; r++) {
			int v = A[r];
			int l = r - 1;
			while (l >= left && A[l] > v) {
				A[l + 1] = A[l];
				l--;
				inv++;
			}
			A[l + 1] = v;
		}
		return inv;
	}	

	public static int sort(int[] A, int l, int r) {
		if (r - l <= 100) {
			return insertionSort(A, l, r);
		}
		int m = (l + r) >>> 1;
		int inv = 0;
		inv += sort(A, l, m);
		inv += sort(A, m+1, r);
		inv += merge(A, l, m, r);
		return inv;
	}

}
