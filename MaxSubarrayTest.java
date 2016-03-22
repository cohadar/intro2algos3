import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class MaxSubarrayTest {

	@Test
	public void testAllNegative() {
		for (int i = 0; i < 100; i++) {
			test(randomArray(3000, -2000, -1000));
		}
	}

	@Test
	public void testRandom() {
		for (int i = 0; i < 100; i++) {
			test(randomArray(3000, -1000, 1000));			
		}
	}	

	private static long sum(int[] A, int l, int r) {
		long s = 0;
		for (int i = l; i <= r; i++) {
			s += A[i];
		}
		return s;
	}

	private static void assertSum(String name, int[] A, MaxSubarray.Ret ret) {
		assertEquals(name, ret.sum, sum(A, ret.l, ret.r));
	}

	private static void test(int[] A) {
		MaxSubarray.Ret r1 = MaxSubarray.maxSubarraySquare(A, 0, A.length - 1);
		MaxSubarray.Ret r2 = MaxSubarray.maxSubarrayNLogN(A, 0, A.length - 1);
		MaxSubarray.Ret r4 = MaxSubarray.maxSubarrayKadane(A);
		assert r1.sum == r2.sum;
		assert r1.sum == r4.sum;
		assertSum("'square sum'", A, r1);
		assertSum("nLogN sum", A, r2);
		assertSum("'Kadane sum'", A, r4);
	}

	public static Random random = new Random();
	
	public static int[] randomArray(int n, int min, int max) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = min + random.nextInt(max - min + 1);
		}
		return A;
	}	

}
