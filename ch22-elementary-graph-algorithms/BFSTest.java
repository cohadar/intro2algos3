import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class BFSTest {

	static final int INF = Integer.MAX_VALUE / 2;
	public static final int WHITE = 0;
	public static final int GRAY = 1;
	public static final int BLACK = 2;

	public static void bfs(List<List<Integer>> G, int sa) {
		byte[] V = new byte[G.size()];
		int[] D = new int[G.size()];
		int[] P = new int[G.size()];
		Deque<Integer> Q = new ArrayDeque<>();
		Arrays.fill(D, INF);
		Arrays.fill(P, -1);
		D[sa] = 0;
		Q.add(sa);
		V[sa] = GRAY;
		while (!Q.isEmpty()) {
			int a = Q.remove();
			assert V[a] == GRAY;
			for (int b : G.get(a)) {
				if (V[b] == WHITE) {
					V[b] = GRAY;
					Q.add(b);
					D[b] = D[a] + 1;
					P[b] = a;
				}
			}
			V[a] = BLACK;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < G.size(); i++) {
			sb.append(String.format("(%d p=%d d=%d) ", i, P[i], D[i]));
		}
		System.out.println(sb);
		for (int i = 0; i < D.length; i++) {
			assertTrue(D[i] >= 0);
		}
	}

	@Test
	public void testBFS() {
		bfs(RandomGraph.undirectedConnected(10), 0);
	}

	static void debug(Object...os) {
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
