import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class CountingSort {

	public static int[] sort(int[] A, int k) {
		int[] B = new int[A.length];
		int[] C = new int[1 + k];
		for (int i = 0; i < A.length; i++) {
			C[A[i]]++;
		}
		for (int i = 1; i < C.length; i++) {
			C[i] += C[i - 1];
		}
		for (int i = A.length - 1; i >= 0; i--) {
			B[--C[A[i]]] = A[i];
		}
		return B;
	}

}
