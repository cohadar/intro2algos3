import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class UpperBoundTest {

	public int upperBoundLinear(int[] A, int v, int l, int r) {
		for (int i = 0; i < A.length; i++) {
			if (A[i] > v) {
				return i;
			}
		}
		return A.length;
	}

	private void test1() {
		int[] A = randomArray(1000, -1000, 1000);
		Arrays.sort(A);
		int v = nextInt(-1100, 1000);
		int a = upperBoundLinear(A, v, 0, A.length - 1);
		int b = UpperBound.upperBound(A, v, 0, A.length - 1);
		assertEquals(a, b);
	}

	@Test
	public void test() {
		for (int i = 0; i < 100; i++) {
			test1();
		}
	}

	public static Random random = new Random();
	
	public static int[] randomArray(int n, int min, int max) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = min + random.nextInt(max - min + 1);
		}
		return A;
	}

	public static int nextInt(int low, int high) {
		return low + random.nextInt(high - low + 1);
	}

}
