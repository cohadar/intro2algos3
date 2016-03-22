import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class InsertionSort {

	/**
	* both left and right are inclusive.
	*/
	public static void insertionSort(int[] A, int left, int right) {
		for (int r = left + 1; r <= right; r++) {
			int v = A[r];
			int l = r - 1;
			while (l >= left && A[l] > v) {
				A[l + 1] = A[l];
				l--;
			}
			A[l + 1] = v;
		}
	}

}
