import java.util.*;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
  * @author Mighty Cohadar 
  */
public class MaxPriorityQueueTest {

	@Test(invocationCount = 100)
	public void testSet() {
		MaxPriorityQueue<Double> Q = new MaxPriorityQueue<>(1000, new Comparator<Double> () {
			public int compare(Double a, Double b) {
				return Double.compare(a, b);
			}
		});
		for (int i = 0; i < 1000; i++) {
			Q.add(Math.random());
		}
		for (int i = 0; i < 3000; i++) {
			Q.set(1 + random.nextInt(1000), Math.random());
		}		
		double prev = 1.0;
		for (int i = 0; i < 1000; i++) {
			double head = Q.poll();
			assertTrue(prev >= head, "sorted");
			prev = head;
		}
		assertNull(Q.poll(), "empty at the end");		
	}

	@Test(invocationCount = 100)
	public void testAddAndPoll() {
		MaxPriorityQueue<Double> Q = new MaxPriorityQueue<>(1000, new Comparator<Double> () {
			public int compare(Double a, Double b) {
				return Double.compare(a, b);
			}
		});
		for (int i = 0; i < 1000; i++) {
			Q.add(Math.random());
			Q.add(Math.random());
			Q.poll();
		}
		double prev = 1.0;
		for (int i = 0; i < 1000; i++) {
			double head = Q.poll();
			assertTrue(prev >= head, "sorted");
			prev = head;
		}
		assertNull(Q.poll(), "empty at the end");
	}

	@Test(invocationCount = 100)
	public void testPoll() {
		MaxPriorityQueue<Double> Q = new MaxPriorityQueue<>(1000, new Comparator<Double> () {
			public int compare(Double a, Double b) {
				return Double.compare(a, b);
			}
		});
		for (int i = 0; i < 1000; i++) {
			Q.add(Math.random());
		}
		double prev = 1.0;
		for (int i = 0; i < 1000; i++) {
			double head = Q.poll();
			assertTrue(prev >= head, "sorted");
			prev = head;
		}
		assertNull(Q.poll(), "empty at the end");
	}


	@Test(invocationCount = 100)
	public void testPeek() {
		MaxPriorityQueue<Integer> Q = new MaxPriorityQueue<>(1000, new Comparator<Integer> () {
			public int compare(Integer a, Integer b) {
				return Integer.compare(a, b);
			}
		});
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < 1000; i++) {
			int v = random.nextInt();
			max = Math.max(max, v);
			Q.add(v);
		}
		assertEquals((int)Q.peek(), max);
	}

	public static Random random = new Random();

}
