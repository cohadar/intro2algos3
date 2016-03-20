import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class SelectionSort {

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

	public static void selectionSort(int[] A) {
		for (int l = 0; l < A.length - 1; l++) {
			int m = l;
			for (int r = l + 1; r < A.length; r++) {
				if (A[m] > A[r]) {
					m = r;
				}
			}
			swap(A, l, m);
		}
	}

	public static void main(String[] args) {
		int[] A = randomArray(20, -20, 20);
		selectionSort(A);
		debug(A);
		for (int i = 1; i < A.length; i++) {
			assert A[i-1] <= A[i];
		}
	}

	static void debug(Object...os) {
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
