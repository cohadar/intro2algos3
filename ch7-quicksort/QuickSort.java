import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class QuickSort {

	public static int hoare(int[] A, int l, int r) {
		int x = A[(l + r) >>> 1];
		while (true) {
			while (A[r] > x) {
				r--;
			};
			while (A[l] < x) {
				l++;
			};
			if (l < r) {
				swap(A, l++, r--);
			} else {
				assert l == r || l == r + 1;
				return r;
			}
		}
	}

	public static void sort(int[] A, int l, int r) {
		if (l < r) {
			int p = hoare(A, l, r);
			assert l <= p && p < r;
			sort(A, l, p);
			sort(A, p + 1, r);
		}
	}

	static void swap(int[] A, int i, int j) {
		int t = A[i];
		A[i] = A[j];
		A[j] = t;
	}

}
