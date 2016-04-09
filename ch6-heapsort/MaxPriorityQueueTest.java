import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class MaxPriorityQueueTest {

	static class Pair implements Comparable<Pair> {
		final double x;
		final double y;
		Pair(double x, double y) {
			this.x = x;
			this.y = y;
		}
		public int compareTo(Pair that) {
			if (this.x == that.x) {
				return Double.compare(this.y, that.y);
			} else {
				return Double.compare(this.x, that.x);
			}
		}
	}

	private Pair randomPair() {
		return new Pair(Math.random(), Math.random());
	}

	@Test
	public void testRemove() {
		MaxPriorityQueue<Pair> Q = new MaxPriorityQueue<>(1000, new Comparator<Pair> () {
			public int compare(Pair a, Pair b) {
				return a.compareTo(b);
			}
		});
		Pair[] D = new Pair[1000];
		for (int i = 0; i < 1000; i++) {
			Q.add(randomPair());
			Q.verifyHeap();
			Q.add(D[i] = randomPair());
			Q.verifyHeap();
			assertEquals(Q.size(), (i + 1) * 2);
		}
		for (int i = 0; i < 1000; i++) {
			assertEquals(Q.size(), 2000 - i);
			boolean ok = Q.remove(D[i]);
			assert ok : "not found: " + D[i];
			Q.verifyHeap();
		}		
		Pair prev = new Pair(1.0, 1.0);
		for (int i = 0; i < 1000; i++) {
			Pair head = Q.poll();
			assertTrue("sorted", prev.compareTo(head) >= 0);
			prev = head;
		}
		assertNull("empty at the end", Q.poll());		
	}


	@Test
	public void testSet() {
		MaxPriorityQueue<Double> Q = new MaxPriorityQueue<>(1000, new Comparator<Double> () {
			public int compare(Double a, Double b) {
				return Double.compare(a, b);
			}
		});
		for (int i = 0; i < 1000; i++) {
			Q.add(Math.random());			
			assertEquals(Q.size(), i + 1);
		}
		for (int i = 0; i < 3000; i++) {
			Q.set(1 + random.nextInt(1000), Math.random());
			assertEquals(Q.size(), 1000);
		}		
		double prev = 1.0;
		for (int i = 0; i < 1000; i++) {
			double head = Q.poll();
			assertTrue("sorted", prev >= head);
			prev = head;
		}
		assertNull("empty at the end", Q.poll());		
	}

	@Test
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
			assertEquals(Q.size(), (i + 1));
		}
		double prev = 1.0;
		for (int i = 0; i < 1000; i++) {
			double head = Q.poll();
			assertTrue("sorted", prev >= head);
			prev = head;
		}
		assertNull("empty at the end", Q.poll());
	}

	@Test
	public void testPoll() {
		MaxPriorityQueue<Double> Q = new MaxPriorityQueue<>(1000, new Comparator<Double> () {
			public int compare(Double a, Double b) {
				return Double.compare(a, b);
			}
		});
		for (int i = 0; i < 1000; i++) {
			Q.add(Math.random());
			assertEquals(Q.size(), i + 1);
		}
		double prev = 1.0;
		for (int i = 0; i < 1000; i++) {
			assertEquals(Q.size(), 1000 - i);
			double head = Q.poll();
			assertTrue("sorted", prev >= head);
			prev = head;
		}
		assertNull("empty at the end", Q.poll());
		assertEquals(Q.size(), 0);
	}


	@Test
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
			assertEquals(Q.size(), i + 1);
		}
		assertEquals((int)Q.peek(), max);
	}

	public static Random random = new Random();

}
