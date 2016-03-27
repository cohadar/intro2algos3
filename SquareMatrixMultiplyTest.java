import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class SquareMatrixMultiplyTest {

	@Test
	public void test() {
		int[][] A = randomMatrix(10, -10, 10);
		int[][] B = randomMatrix(10, -10, 10);
		int[][] C = SquareMatrixMultiply.multiplyN3(A, B);
	}

	public static Random random = new Random();
	
	public static int[] randomArray(int n, int min, int max) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = min + random.nextInt(max - min + 1);
		}
		return A;
	}

	public static int[][] randomMatrix(int n, int min, int max) {
		int[][] D = new int[n][];
		for (int y = 0; y < n; y++) {
			D[y] = randomArray(n, min, max);
		}
		return D;
	}

}
