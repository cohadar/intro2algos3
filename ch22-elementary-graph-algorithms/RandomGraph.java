import java.util.*;
import java.util.function.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class RandomGraph {

	static Random random = new Random();

	public static List<List<Integer>> undirectedTree(int nv) {
		// new graph
		List<Set<Integer>> G = new ArrayList<>();
		for (int i = 0; i < nv; i++) {			
			G.add(new TreeSet<Integer>());
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
		// convert
		return set2List(G);
	}

	public static List<List<Integer>> undirectedConnected(int nv) {
		List<Set<Integer>> G = list2Set(undirectedTree(nv));
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
		// convert
		return set2List(G);
	}

	public static List<List<Integer>> undirected(int nv) {
		// new graph
		List<Set<Integer>> G = new ArrayList<>();
		for (int i = 0; i < nv; i++) {
			G.add(new TreeSet<Integer>());
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
		// convert
		return set2List(G);
	}

	public static List<Set<Integer>> list2Set(List<List<Integer>> G) {
		List<Set<Integer>> R = new ArrayList<>();
		for (List<Integer> L : G) {
			R.add(new TreeSet<Integer>(L));
		}
		return R;
	}

	public static List<List<Integer>> set2List(List<Set<Integer>> G) {
		List<List<Integer>> R = new ArrayList<>();
		for (Set<Integer> S : G) {
			R.add(new ArrayList<Integer>(S));
		}
		return R;
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
