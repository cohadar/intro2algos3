import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class MaxPriorityQueue<E> {

	private int n = 0;
	private final int capacity;
	private final Comparator<E> comparator;
	private Object[] H;

	public MaxPriorityQueue(int capacity, Comparator<E> comparator) {
		this.capacity = capacity;
		this.comparator = comparator;
		this.H = new Object[1 + capacity];
	}


	public void insert(E e) {
		H[++n] = e;
		floatUp(n);
	}

	@SuppressWarnings("unchecked")
	private void floatUp(int c) {
		while (c > 1) {
			int p = c / 2;
			if (comparator.compare((E)H[p], (E)H[c]) < 0) {
				swap(H, p, c);
				c = p;
			} else {
				break;
			}
		}
	}

	private static void swap(Object[] H, int i, int j) {
		Object t = H[i];
		H[i] = H[j];
		H[j] = t;
	}
}
