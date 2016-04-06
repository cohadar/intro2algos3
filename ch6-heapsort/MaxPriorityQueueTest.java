import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class MaxPriorityQueueTest {

	@Test
	public void test() {
		MaxPriorityQueue<Integer> Q = new MaxPriorityQueue<>(1000, new Comparator<Integer> () {
			public int compare(Integer a, Integer b) {
				return Integer.compare(a, b);
			}
		});
		for (int i = 0; i < 1000; i++) {
			Q.insert(random.nextInt(2000) - 1000);
		}
	}

	public static Random random = new Random();

}
