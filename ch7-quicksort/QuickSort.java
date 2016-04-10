import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class QuickSort {

	public static int partition(int[] A, int l, int r) {
		int m = (l + r) >>> 1;
		int v = A[m];
		swap(A, r, m);
		int e = l - 1;
		for (int i = l; i < r; i++) {
			if (A[i] <= v) {
				swap(A, i, ++e);
			}
		}
		swap(A, ++e, r);
		return e;
	}

	public static void sort(int[] A, int l, int r) {
		if (l < r) {
			int p = partition(A, l, r);
			sort(A, l, p - 1);
			sort(A, p + 1, r);
		}
	}

	static void swap(int[] A, int i, int j) {
		int t = A[i];
		A[i] = A[j];
		A[j] = t;
	}

}
