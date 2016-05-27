import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class QuickSort {

	public static Random random = new Random();
	
	public static int partition(int[] A, int l, int r) {
		swap(A, r, l + random.nextInt(r - l + 1));
		int x = A[r];
		int e = l - 1;
		for (int i = l; i < r; i++) {
			if (A[i] <= x) {
				swap(A, ++e, i);
			}
		}
		swap(A, ++e, r);
		return e;
	}

	public static int hoare(int[] A, int l, int r) {
		int x = A[(l + r) >>> 1];
		while (true) {
			while (A[l] < x) {
				l++;
			}
			while (A[r] > x) {
				r--;
			}
			if (l < r) {
				swap(A, l++, r--);
			} else {
				return r;
			}
		}
	}

	public static int[] dutch(int[] A, int l, int r) {
		swap(A, l, l + random.nextInt(r - l + 1));
		int x = A[l];
		int a = l - 1;
		int b = l;
		for (int i = l + 1; i <= r; i++) {
			if (A[i] == x) {
				swap(A, ++b, i);
			} else if (A[i] < x) {
				swap(A, ++a, i);
				swap(A, ++b, i);
			}
		}
		return new int[] { a + 1, b };
	}

	public static void sort_with_random(int[] A, int l, int r) {
		if (l < r) {
			int p = partition(A, l, r);
			assert l <= p && p <= r;
			sort_with_random(A, l, p - 1);
			sort_with_random(A, p + 1, r);
		}
	}

	public static void sort_with_hoare(int[] A, int l, int r) {
		if (l < r) {
			int p = hoare(A, l, r);
			assert l <= p && p < r;
			sort_with_hoare(A, l, p);
			sort_with_hoare(A, p + 1, r);
		}
	}

	public static void sort_with_dutch(int[] A, int l, int r) {
		if (l < r) {
			int[] p = dutch(A, l, r);
			assert l <= p[0] && p[0] <= r;
			assert l <= p[1] && p[1] <= r;
			assert p[0] <= p[1];
			sort_with_random(A, l, p[0] - 1);
			sort_with_random(A, p[1] + 1, r);
		}
	}	

	static void swap(int[] A, int i, int j) {
		int t = A[i];
		A[i] = A[j];
		A[j] = t;
	}

}
