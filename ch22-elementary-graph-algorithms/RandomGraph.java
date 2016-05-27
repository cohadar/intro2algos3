import java.util.*;
import java.util.function.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class RandomGraph {

	static Random random = new Random();

	public static List<Set<Integer>> undirectedTree(int nv) {
		// new graph
		List<Set<Integer>> G = new ArrayList<>();
		for (int i = 0; i < nv; i++) {
			G.add(new HashSet<Integer>());
		}
		// totally connected undirected graph with random weights
		PriorityQueue<Edge> Q = new PriorityQueue<>((a, b) -> Double.compare(a.w, b.w));
		for (int y = 0; y < nv; y++) {
			for (int x = y + 1; x < nv; x++) {
				Q.add(new Edge(x, y, Math.random()));
			}
		}
		// Prim's for minimum spanning tree
		Set<Integer> S = new HashSet<>();
		S.add(random.nextInt(nv));
		while (!Q.isEmpty()) {
			Edge e = Q.poll();
			boolean ca = S.contains(e.a);
			boolean cb = S.contains(e.b);
			if (ca != cb) {
				G.get(e.a).add(e.b);
				G.get(e.b).add(e.a);
				S.add((ca) ? e.b : e.a);
			}
		}
		return G;
	}

	public static List<Set<Integer>> undirectedConnected(int nv) {
		List<Set<Integer>> G = undirectedTree(nv);
		// some more random edges
		int more = (int)Math.sqrt(nv) * nv;
		for (int i = 0; i < more; i++) {
			int a = random.nextInt(nv);
			int b = random.nextInt(nv);
			if (a != b) {
				G.get(a).add(b);
				G.get(b).add(a);
			}
		}
		return G;
	}

	public static List<Set<Integer>> undirected(int nv) {
		// new graph
		List<Set<Integer>> G = new ArrayList<>();
		for (int i = 0; i < nv; i++) {
			G.add(new HashSet<Integer>());
		}
		// random edges
		int more = (int)Math.sqrt(nv) * nv;
		for (int i = 0; i < more; i++) {
			int a = random.nextInt(nv);
			int b = random.nextInt(nv);
			if (a != b) {
				G.get(a).add(b);
				G.get(b).add(a);
			}
		}
		return G;
	}

}

class Edge {
	final int a;
	final int b;
	final double w;
	Edge(int a, int b, double w) {
		this.a = a;
		this.b = b;
		this.w = w;
	}
	public String toString() {
		return String.format("(a=%d, b=%d, w=%f)", a, b, w);
	}	
}
