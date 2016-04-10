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

}
