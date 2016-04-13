import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class StringMatchingTest {

	public int[] toArray(List<Integer> L) {
		int[] R = new int[L.size()];
		for (int i = 0; i < R.length; i++) {
			R[i] = L.get(i);
		}
		return R;
	}

	private int[] match(String text, String pattern) {
		ArrayList<Integer> L = new ArrayList<>();
		int offset = 0;
		while (true) {
			int index = text.indexOf(pattern, offset);
			if (index == -1) {
				break;
			}
			L.add(index);
			offset = index + 1;
		}
		return toArray(L);
	}

	private String randomString(int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append((char)('a' + random.nextInt(4)));
		}
		return sb.toString();
	}

	@Test
	public void test() {
		String text = randomString(10000);
		String pattern = randomString(4);
		int[] A = StringMatching.match(text, pattern);
		int[] B = match(text, pattern);
		assertArrayEquals(A, B);
	}

	public static Random random = new Random();

}
