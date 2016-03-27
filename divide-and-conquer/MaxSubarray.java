import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class MaxSubarray {

	public static class Ret {
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
		int ll = left;
		int rr = left;
		long ss = A[left];
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
		int rr = m;
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
		if (retl.sum < 0 && retr.sum < 0) {
			return (retl.sum > retr.sum) ? retl : retr;
		}
		if (retl.sum < 0) {
			return retr;
		}
		if (retr.sum < 0) {
			return retl;
		}
		return new Ret(retl.l, retr.r, retl.sum + retr.sum);
	}

	public static Ret maxSubarrayNLogN(int[] A, int l, int r) {
		if (r - l <= 10) {
			return maxSubarraySquare(A, l, r);	
		}
		int m = (l + r) >>> 1;
		Ret r1 = maxSubarrayNLogN(A, l, m);
		Ret r2 = maxSubarrayNLogN(A, m + 1, r);
		Ret r3 = maxSubarrayMid(A, l, m, r);
		if (r1.sum >= r2.sum && r1.sum >= r3.sum) {
			return r1;
		}
		if (r2.sum >= r1.sum && r2.sum >= r3.sum) {
			return r2;
		}
		return r3;
	}

	public static Ret maxSubarrayKadane(int[] A) {
		long bestSum = A[0];
		long currSum = A[0];
		int bestLeft = 0;
		int bestRight = 0;
		int currLeft = 0;
		int currRight = 0;		
		for (int i = 1; i < A.length; i++) {
			currSum += A[i];
			currRight = i;
			if (currSum < A[i]) {
				currSum = A[i];
				currLeft = i;
			}
			if (bestSum < currSum) {
				bestSum = currSum;
				bestLeft = currLeft;
				bestRight = currRight;
			}
		} 
		return new Ret(bestLeft, bestRight, bestSum);
	}

}
