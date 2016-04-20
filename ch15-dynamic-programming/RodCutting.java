import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class RodCutting {

	public static int[] nn(Map<Integer, Integer> P, int n) {
		int[] D = new int[n + 1];
		for (Map.Entry<Integer, Integer> e : P.entrySet()) {
			assert e.getKey() > 0;
			if (e.getKey() <= n) {
				D[e.getKey()] = e.getValue();	
			}
		}
		for (int l = 1; l <= n; l++) {
			for (int i = 1; i <= l / 2; i++) {
				D[l] = Math.max(D[l], D[i] + D[l - i]);
			}
		}
		return D;
	}

	public static int[] np(Map<Integer, Integer> P, int n) {
		int[] D = new int[n + 1];
		for (int l = 1; l <= n; l++) {
			for (Map.Entry<Integer, Integer> e : P.entrySet()) { 
				assert e.getKey() > 0;
				if (e.getKey() <= l) {
					D[l] = Math.max(D[l], e.getValue() + D[l - e.getKey()]);
				}				
			}
		}
		return D;
	}

}
