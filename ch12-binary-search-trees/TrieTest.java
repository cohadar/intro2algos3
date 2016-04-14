import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.function.*;

/**
  * @author Mighty Cohadar 
  */
public class TrieTest {

	static String key(double d) {
		return String.format("%.17g", d);
	}

	static Trie.Alphabet doubleAlphabet = new Trie.Alphabet('+', '9') {
		@Override
		public int toIndex(char c) {
			if (c == 'e') {
				return '/' - first;
			}
			return c - first;
		}
		@Override
		public char toChar(int index) {
			if (index == '/' - first) {
				return 'e';
			}
			return (char) (index + first);
		}		
	};

	@Test
	public void testPutGet() {
		double[] D = new double[1000];
		for (int i = 0; i < D.length; i++) {
			D[i] = Math.random();
		}
		Trie<Double> T = new Trie<Double>(doubleAlphabet);
		for (int i = 0; i < D.length; i++) {
			assertNull(T.get(key(D[i])));
			T.put(key(D[i]), D[i]);
			for (int j = 0; j <= i; j++) {
				assertEquals((Double)D[i], (Double)T.get(key(D[i])));	
			}
		}
	}

	static String hexKey(int d) {
		return String.format("CDEF%X", d);
	}	

	static Trie.Alphabet hexAlphabet = new Trie.Alphabet('0', '?') {
		@Override
		public int toIndex(char c) {
			if (c >= 'A') {
				return c - 'A' + 10;
			}
			return c - first;
		}
		@Override
		public char toChar(int index) {
			if (index >= 10) {
				return (char)('A' + index - 10);
			}
			return (char) (index + first);
		}			
	};

	@Test 
	public void testForEach() {
		Set<String> S = new HashSet<>();
		Trie<Integer> T = new Trie<Integer>(hexAlphabet);
		for (int i = 0; i < 1000; i++) {
			int v = nextInt(0, 0xFFFFFF);
			String key = hexKey(v);
			T.put(key, v);
			S.add(key);
		}
		assertTrue(S.size() > 900);
		T.forEach(new BiConsumer<String, Integer> () {
			@Override
			public void accept(String key, Integer value) {
				String key2 = hexKey(value);
				assertEquals(key2, key);
				assertTrue(S.remove(key));
			}
		});
		assertTrue(S.isEmpty());
	}	

	@Test 
	public void testSubTrie() {
		Set<String> S = new HashSet<>();
		Trie<Integer> T = new Trie<Integer>(hexAlphabet);
		for (int i = 0; i < 1000; i++) {
			int v = nextInt(0, 0xFFFFFF);
			String key = hexKey(v);
			T.put(key, v);
			S.add(key);
		}
		assertTrue(S.size() > 900);
		final String prefix = "CD";
		final int count[] = {0};
		for (String s : S) {
			if (s.indexOf(prefix) == 0) {
				count[0]++;
			}
		}
		assertTrue(count[0] > 0);
		T.subTrie(prefix).forEach(new BiConsumer<String, Integer> () {
			@Override
			public void accept(String key, Integer value) {
				String key2 = hexKey(value);
				assertEquals(key2, key);
				assertTrue(S.remove(key));
				count[0]--;
			}
		});
		assertEquals(0, count[0]);
	}

	@Test 
	public void testSubSubTrie() {
		Set<String> S = new HashSet<>();
		Trie<Integer> T = new Trie<Integer>(hexAlphabet);
		for (int i = 0; i < 1000; i++) {
			int v = nextInt(0, 0xFFFFFF);
			String key = hexKey(v);
			T.put(key, v);
			S.add(key);
		}
		assertTrue(S.size() > 900);
		final String prefix = "CDEFA";
		final int count[] = {0};
		for (String s : S) {
			if (s.indexOf(prefix) == 0) {
				count[0]++;
			}
		}
		assertTrue(count[0] > 0);
		T.subTrie("CDE").subTrie("FA").forEach(new BiConsumer<String, Integer> () {
			@Override
			public void accept(String key, Integer value) {
				String key2 = hexKey(value);
				assertEquals(key2, key);
				assertTrue(S.remove(key));
				count[0]--;
			}
		});
		assertEquals(0, count[0]);
	}	

	public static Random random = new Random();
	
	public static int nextInt(int low, int high) {
		return low + random.nextInt(high - low + 1);
	}

}
