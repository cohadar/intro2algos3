import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class StringMatching {

	public static int[] toArray(List<Integer> L) {
		int[] R = new int[L.size()];
		for (int i = 0; i < R.length; i++) {
			R[i] = L.get(i);
		}
		return R;
	}

	public static int[] match(String text, String pattern) {
		ArrayList<Integer> L = new ArrayList<>();
		outer:
		for (int i = 0; i <= text.length() - pattern.length(); i++) {
			for (int j = 0; j < pattern.length(); j++) {
				if (text.charAt(i + j) != pattern.charAt(j)) {
					continue outer;
				}
			}
			L.add(i);
		}
		return toArray(L);
	}

}
