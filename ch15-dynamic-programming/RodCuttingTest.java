import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class RodCuttingTest {

	@Test
	public void testSmall() {
		Map<Integer, Integer> P = new HashMap<>();
		P.put(1, 1);
		P.put(2, 5);
		P.put(3, 8);
		P.put(4, 9);
		P.put(5, 10);
		P.put(6, 17);
		P.put(7, 17);
		P.put(8, 20);
		P.put(9, 24);
		P.put(10, 30);
		int[] A = RodCutting.nn(P, 5);
		int[] B = RodCutting.np(P, 5);
		assertArrayEquals(A, B);
	}

	@Test
	public void testLarge() {
		Map<Integer, Integer> P = new HashMap<>();
		P.put(1, 1);
		P.put(2, 5);
		P.put(3, 8);
		P.put(4, 9);
		P.put(5, 10);
		P.put(6, 17);
		P.put(7, 17);
		P.put(8, 20);
		P.put(9, 24);
		P.put(10, 30);
		int[] A = RodCutting.nn(P, 10000);
		int[] B = RodCutting.np(P, 10000);
		assertArrayEquals(A, B);
	}

	@Test
	public void testSparse() {
		Map<Integer, Integer> P = new HashMap<>();
		P.put(2, 5);
		P.put(3, 8);
		P.put(5, 10);
		P.put(10, 17);
		P.put(11, 30);
		int[] A = RodCutting.nn(P, 10000);
		int[] B = RodCutting.np(P, 10000);
		assertArrayEquals(A, B);
	}	

}
