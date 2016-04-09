import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class MaxPriorityQueue<E> {

	private int n = 0;
	private final int capacity;
	private final Comparator<E> comparator;
	private List<E> list;

	public MaxPriorityQueue(int capacity, Comparator<E> comparator) {
		this.capacity = capacity;
		this.comparator = comparator;
		this.list = new ArrayList<>();
		this.list.add(null); // list starts from 1
	}

	public boolean add(E e) {
		if (e == null) {
			throw new NullPointerException("cannot insert null elements");
		}
		if (list.add(e)) {
			n++;
			floatUp(n);
			return true;
		}
		return false;
	}

	public E peek() {
		if (n == 0) {
			return null;
		}
		return list.get(1);
	} 

	public E poll() {
		if (n == 0) {
			return null;
		}
		E ret = list.get(1);
		list.set(1, list.get(n--));
		sinkDown(1);
		return ret;
	} 

	// this is the method that java's PriorityQueue lacks.
	public E set(int index, E e) {
		E ret = list.get(index);
		int comp = comparator.compare(e, ret);
		if (comp < 0) {
			sinkDown(index);
		} else if (comp > 0) {
			floatUp(index);	
		}
		return ret;
	}

	public int size() {
		return n;
	}

	private void sinkDown(int p) {
		E t = list.get(p);
		while (p <= n / 2) {
			int l = 2 * p;
			int r = l + 1;
			int c = -1;
			if (l <= n && comparator.compare(t, list.get(l)) < 0) {
				c = l;
			}
			if (r <= n && comparator.compare((c == -1)?t:list.get(c), list.get(r)) < 0) {
				c = r;
			}			
			if (c == -1) {
				break;
			} else {
				list.set(p, list.get(c));
				p = c;
			}
		}
		list.set(p, t);
	}

	private void floatUp(int c) {
		E t = list.get(c);
		while (c > 1) {
			int p = c / 2;
			if (comparator.compare(list.get(p), t) < 0) {
				list.set(c, list.get(p));
				c = p;
			} else {
				break;
			}
		}
		list.set(c, t);
	}

}
