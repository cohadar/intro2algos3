import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class InsertionSort {

	public static Random random = new Random();
	
	public static int[] randomArray(int n, int min, int max) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = min + random.nextInt(max - min + 1);
		}
		return A;
	}

	public static void insertionSort(int[] A) {
		for (int r = 1; r < A.length; r++) {
			int v = A[r];
			int l = r - 1;
			while (l >= 0 && A[l] > v) {
				A[l+1] = A[l];
				l--;
			}
			A[l+1] = v;
		}
	}

	public static void main(String[] args) {
		int[] A = randomArray(20, -20, 20);
		insertionSort(A);
		debug(A);
		for (int i = 1; i < A.length; i++) {
			assert A[i-1] <= A[i];
		}
	}

	static void debug(Object...os) {
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
