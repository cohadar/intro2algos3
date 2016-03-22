import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class UpperBound {

	public static int upperBound(int[] A, int v, int l, int r) {
		while (l <= r) {
			int m = (l + r) >>> 1;
			if (A[m] > v) {
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		return l;
	}
	
}
