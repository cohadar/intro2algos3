import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class DFSTest {

	public static final int WHITE = 0;
	public static final int GRAY = 1;
	public static final int BLACK = 2;

	int nv;
	int time;
	byte[] C;
	int[] TL;
	int[] TR;
	int[] P;

	int[] dfs1(List<Set<Integer>> G) {
		time = 0;
		this.nv = G.size();
		this.C = new byte[nv];
		this.TL = new int[nv];
		this.TR = new int[nv];
		this.P = new int[nv];
		Arrays.fill(P, -1);
		for (int i = 0; i < C.length; i++) {
			if (C[i] == WHITE) {
				dfs_rec(G, i);
			}
		}
		return TL;
	}

	int[] dfs2(List<Set<Integer>> G) {
		time = 0;
		this.nv = G.size();
		this.C = new byte[nv];
		this.TL = new int[nv];
		this.TR = new int[nv];
		this.P = new int[nv];
		Arrays.fill(P, -1);
		for (int i = 0; i < C.length; i++) {
			if (C[i] == WHITE) {
				dfs_iter(G, i);
			}
		}
		debug(TL);
		debug(TR);
		return TL;
	}

	void dfs_rec(List<Set<Integer>> G, int a) {
		assert C[a] == WHITE;
		C[a] = GRAY;
		TL[a] = time++;
		for (int b : G.get(a)) {
			if (C[b] == WHITE) {
				P[b] = a;
				dfs_rec(G, b);
			}
		}
		C[a] = BLACK;
		TR[a] = time++;
	}

	void dfs_iter(List<Set<Integer>> G, int sa) {
		Deque<Integer> S = new ArrayDeque<>();
		TL[sa] = time++;
		S.push(sa);
		while (!S.isEmpty()) {
			int a = S.pop();
			if (C[a] == WHITE) {
				C[a] = GRAY;
				for (int b : G.get(a)) {
					TL[b] = time++;
					S.push(b);
					debug(TL);
				}
			}
		}
	}

	@Test
	public void testDFS() {
		List<Set<Integer>> G = RandomGraph.undirected(10);
		int[] P1 = dfs1(G);
		int[] P2 = dfs2(G);
		// debug(P1);
		// debug(P2);
		// assertArrayEquals(P1, P2);
	}

	static void debug(Object...os) {
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
