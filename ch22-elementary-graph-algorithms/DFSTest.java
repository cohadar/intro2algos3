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
	int[] TA;
	int[] TB;
	int[] P;

	void dfs(List<Set<Integer>> G) {
		this.nv = G.size();
		this.C = new byte[nv];
		this.TA = new int[nv];
		this.TB = new int[nv];
		this.P = new int[nv];
		Arrays.fill(P, -1);
		for (int i = 0; i < C.length; i++) {
			if (C[i] == WHITE) {
				dfs_rec(G, i);
				TB[i] = time++; // easy to forget this one
			}
		}
	}

	void dfs_rec(List<Set<Integer>> G, int a) {
		assert C[a] == WHITE;
		C[a] = GRAY;
		TA[a] = time++;
		for (int b : G.get(a)) {
			if (C[b] == WHITE) {
				P[b] = a;
				dfs_rec(G, b);
				TB[b] = time++;
			}
		}
		C[a] = BLACK;
	}

	@Test
	public void testDFS() {
		dfs(RandomGraph.undirected(10));
		debug(TA);
		debug(TB);
	}

	static void debug(Object...os) {
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
