import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class BinarySearch {

	public static int binarySearch(int[] A, int v, int l, int r) {
		while (l <= r) {
			int m = (l + r) >>> 1;
			if (A[m] < v) {
				l = m + 1;
			} else if (A[m] > v) {
				r = m - 1;
			} else {
				return m;
			}
		}
		return ~l;
	}

}
