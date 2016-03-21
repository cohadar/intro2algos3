import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class MaxSubarray {

	public static Random random = new Random();
	
	public static int[] randomArray(int n, int min, int max) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = min + random.nextInt(max - min + 1);
		}
		return A;
	}

	static class Ret {
		final int l;
		final int r;
		final long sum;
		Ret(int l, int r, long sum) {
			this.l = l;
			this.r = r;
			this.sum = sum;
		}
		public String toString() {
			return String.format("(l=%d, r=%d, sum=%d)", l, r, sum);
		}	
	}

	public static Ret maxSubarraySquare(int[] A, int left, int right) {
		int ll = 0;
		int rr = 0;
		long ss = 0;
		for (int l = left; l <= right; l++) {
			long sum = 0;
			for (int r = l; r <= right; r++) {
				sum += A[r];
				if (ss < sum) {
					ss = sum;
					ll = l;
					rr = r;
				}
			}
		}
		return new Ret(ll, rr, ss);
	}

	private static Ret maxSubarrayToLeft(int[] A, int l, int m) {
		int ll = m;
		int rr = m;
		long ss = A[m];
		long sum = A[m];
		for (int i = m - 1; i >= l; i--) {
			sum += A[i];
			if (ss < sum) {
				ss = sum;
				ll = i;
			}
		}
		return new Ret(ll, rr, ss);
	}

	private static Ret maxSubarrayToRight(int[] A, int m, int r) {
		int ll = m;
		int rr = r;
		long ss = A[m];
		long sum = A[m];
		for (int i = m + 1; i <= r; i++) {
			sum += A[i];
			if (ss < sum) {
				ss = sum;
				rr = i;
			}
		}
		return new Ret(ll, rr, ss);
	}

	private static Ret maxSubarrayMid(int[] A, int l, int m, int r) {
		Ret retl = maxSubarrayToLeft(A, l, m);
		Ret retr = maxSubarrayToRight(A, m + 1, r);
		return new Ret(retl.l, retr.r, retl.sum + retr.sum);
	}

	public static Ret maxSubarray(int[] A, int l, int r) {
		if (r - l <= 10) {
			return maxSubarraySquare(A, l, r);	
		}
		int m = (l + r) >>> 1;
		Ret r1 = maxSubarray(A, l, m);
		Ret r2 = maxSubarray(A, m + 1, r);
		Ret r3 = maxSubarrayMid(A, l, m, r);
		if (r1.sum > r2.sum && r1.sum > r3.sum) {
			return r1;
		}
		if (r2.sum > r1.sum && r2.sum > r3.sum) {
			return r2;
		}
		return r3;
	}

	private static int minSumLeft(int[] A) {
		int rr = 0;
		long min = 0;
		long sum = 0;
		for (int i = 0; i < A.length; i++) {
			sum += A[i];
			if (min > sum) {
				min = sum;
				rr = i;
			}
		}
		return rr;
	}

	public static Ret maxSubarrayLinear(int[] A) {
		int ll = 0;
		int rr = 0;
		long max = 0;
		long suma = 0;
		long sumb = 0;
		int al = 0;
		int bl = -1;
		for (int r = 0; r < A.length; r++) {
			if (A[r] >= 0) {
				if (bl == -1) {
					bl = r;
				}
				sumb += A[r];
				if (max < sumb) {
					max = sumb;
					ll = bl;
					rr = r;
				}				
			} else {
				if (suma < sumb) {
					suma = sumb;
					al = bl;
				}
				sumb = 0;
				bl = -1;
			}
			suma += A[r];
			if (max < suma) {
				max = suma;
				ll = al;
				rr = r;
			}			
		}
		return new Ret(ll, rr, max);
	}

	public static void main(String[] args) {
		int[] A = randomArray(30000, -1_000_000, 1_000_000);
		Ret r1 = maxSubarraySquare(A, 0, A.length - 1);
		Ret r2 = maxSubarray(A, 0, A.length - 1);
		Ret r3 = maxSubarrayLinear(A);
		debug(r1);
		debug(r2);
		debug(r3);	
		assert r1.sum == r2.sum;
		assert r1.sum == r3.sum;
	}

	static void debug(Object...os) {
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
