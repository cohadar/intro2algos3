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
	StringBuilder sb;

	int[] dfs1(List<List<Integer>> G) {
		time = 0;
		sb = new StringBuilder();
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
		debug(sb);
		return TR;
	}

	int[] dfs2(List<List<Integer>> G) {
		time = 0;
		sb = new StringBuilder();
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
		debug(sb);
		return TR;
	}

	void dfs_rec(List<List<Integer>> G, int a) {
		assert C[a] == WHITE;
		C[a] = GRAY;
		TL[a] = ++time;
		sb.append(String.format("%d(%d) ", a, TL[a]));
		for (int b : G.get(a)) {
			if (C[b] == WHITE) {
				P[b] = a;
				dfs_rec(G, b);
			}
		}
		C[a] = BLACK;
		TR[a] = ++time;
	}

	// this version is very bad on graphs with large node arity
	// will do proper trampoline later
	void dfs_iter(List<List<Integer>> G, int sa) {
		Deque<Integer> S = new ArrayDeque<>();
		S.push(~sa);
		S.push(-1);
		S.push(sa);
		while (!S.isEmpty()) {
			int a = S.pop();
			if (a < 0) {
				C[~a] = BLACK;
				TR[~a] = ++time;
			} else if (C[a] == WHITE) {
				P[a] = S.pop();
				C[a] = GRAY;
				TL[a] = ++time;
				sb.append(String.format("%d(%d) ", a, TL[a]));
				ListIterator<Integer> i = G.get(a).listIterator(G.get(a).size());
				while (i.hasPrevious()) {
					int b = i.previous();
					S.push(~b);
					S.push(a);
					S.push(b);
				}
			} else {
				// junk
				S.pop();
				S.pop();
			}
		}
	}

	@Test
	public void testDFS() {
		List<List<Integer>> G = RandomGraph.undirected(10);
		int[] P1 = dfs1(G);
		int[] P2 = dfs2(G);
		assertArrayEquals(P1, P2);
	}

	static void debug(Object...os) {
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
