import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class MinMax {

	public static int[] minMax(int[] A) {
		assert A.length > 0;
		int min = A[0];
		int max = A[0];
		int start = 2;
		if (A.length % 2 == 0) {
			if (A[0] <= A[1]) {
				min = A[0];
				max = A[1];
			} else {
				min = A[1];
				max = A[0];
			}
			start = 3;
		}
		for (int i = start; i < A.length; i += 2) {
			if (A[i - 1] <= A[i]) {
				min = Math.min(min, A[i - 1]);
				max = Math.max(max, A[i]);
			} else {
				min = Math.min(min, A[i]);
				max = Math.max(max, A[i - 1]);
			}
		}
		return new int[] { min, max };
	}

	public static int[] minMin(int[] A) {
		assert A.length > 1;
		int m1 = Math.min(A[0], A[1]);
		int m2 = Math.max(A[0], A[1]);
		for (int i = 2; i < A.length; i++) {
			if (m2 > A[i]) {
				if (m1 > A[i]) {
					m2 = m1;
					m1 = A[i];
				} else {
					m2 = A[i];
				}
			}
		}
		return new int[] { m1, m2 };
	}

	public static int partition(int[] A, int l, int r) {
		swap(A, nextInt(l, r), r);
		int v = A[r];
		int p = l - 1;
		for (int i = l; i < r; i++) {
			if (A[i] <= v) {
				swap(A, ++p, i);
			}
		}
		swap(A, ++p, r);
		return p;
	}

	public static int orderStatistic(int[] A, int l, int r, int order) {
		assert 1 <= order && order <= A.length : "out of range, order: " + order;
		int p = partition(A, l, r);
		int k = p - l + 1;
		if (k == order) {
			return A[p];
		} 
		if (order < k) {
			return orderStatistic(A, l, p - 1, order);
		} else {
			return orderStatistic(A, p + 1, r, order - k);
		}
	}

	public static Random random = new Random();

	public static int nextInt(int l, int r) {
		return l + random.nextInt(r - l + 1);
	}

	static void swap(int[] A, int i, int j) {
		int t = A[i];
		A[i] = A[j];
		A[j] = t;
	}
}
