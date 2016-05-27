import java.util.*;
import java.util.function.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class RandomGraph {

	static Random random = new Random();

	static int[] randomPermutation(int n) {
		int[] P = new int[n];
		for (int i = 0; i < P.length; i++) {
			P[i] = i;
		}
		for (int i = 0; i < P.length-1; i++) {
			int j = i + random.nextInt(P.length - i);
			int temp = P[i];
			P[i] = P[j];
			P[j] = temp;
		}
		return P;
	}

	public static List<Set<Integer>> undirectedTree(int nv) {
		int[] P1 = randomPermutation(nv);
		int[] P2 = randomPermutation(nv);
		NavigableSet<Integer> S1 = new TreeSet<>(new Comparator<Integer> () {
			public int compare(Integer a, Integer b) {
				return Integer.compare(P1[a], P2[b]);
			}
		});
		NavigableSet<Integer> S2 = new TreeSet<>((a, b) -> Integer.compare(P2[a], P2[b]));
		for (int i = 0; i < nv; i++) {
			S2.add(i);
		}
		S1.add(S2.pollFirst());
		List<Set<Integer>> G = new ArrayList<>();
		for (int i = 0; i < nv; i++) {
			G.add(new HashSet<Integer>());
		}
		// generate spanning tree
		for (int b : S2) {
			int a = S1.first();
			G.get(a).add(b);
			G.get(b).add(a);
			S1.add(b);
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


}
