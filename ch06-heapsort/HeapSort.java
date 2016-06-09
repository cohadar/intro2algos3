import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class HeapSort {

	public static void swap(int[] H, int i, int j) {
		int t = H[i];
		H[i] = H[j];
		H[j] = t;
	}

	public static void maxHeapify(int[] H, int n, int p) {
		int l = p * 2;
		int r = l + 1;
		int imax = p;
		if (l <= n && H[l] > H[imax]) {
			imax = l;
		}
		if (r <= n && H[r] > H[imax]) {
			imax = r;
		}
		if (imax == p) {
			return;
		}
		swap(H, p, imax);
		maxHeapify(H, n, imax);
	}

	public static void buildMaxHeap(int[] H, int n) {
		for (int i = n / 2; i >= 1; i--) {
			maxHeapify(H, n, i);
		}
	}

	public static void heapSort(int[] H, int n) {
		buildMaxHeap(H, n);
		for (int i = n; i >= 2; i--) {
			swap(H, 1, i);
			maxHeapify(H, i - 1, 1);
		}
	}

}
