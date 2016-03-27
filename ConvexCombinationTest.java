import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class ConvexCombinationTest {

	@Test
	public void test() {
		double[] A = new double[1000];
		for (int i = 0; i < A.length; i++) {
			A[i] = Math.atan2(Math.random() * 1000.0 - 500.0, Math.random() * 1000.0 - 500.0);
		}
		Arrays.sort(A);
		debug(A);
	}

	static void debug(Object...os) {
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
