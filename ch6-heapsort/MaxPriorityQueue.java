import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class MaxPriorityQueue<E> {

	private int n = 0;
	private final int capacity;
	private final Comparator<E> comparator;
	private List<E> heap;

	public MaxPriorityQueue(int capacity, Comparator<E> comparator) {
		this.capacity = capacity;
		this.comparator = comparator;
		this.heap = new ArrayList<>();
		this.heap.add(null); // heap starts from 1
	}

	public boolean add(E e) {
		if (e == null) {
			throw new NullPointerException("cannot insert null elements");
		}
		if (heap.add(e)) {
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
		return heap.get(1);
	} 

	public E poll() {
		if (n == 0) {
			return null;
		}
		E ret = heap.get(1);
		swap(1, n--);
		sinkDown(1);
		return ret;
	} 

	// this is the method that java's PriorityQueue lacks.
	public E set(int index, E e) {
		E ret = heap.get(index);
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
		while (p <= n / 2) {
			int l = 2 * p;
			int r = l + 1;
			int imax = p;
			if (l <= n && comparator.compare(heap.get(imax), heap.get(l)) < 0) {
				imax = l;
			}
			if (r <= n && comparator.compare(heap.get(imax), heap.get(r)) < 0) {
				imax = r;
			}			
			if (imax == p) {
				break;
			} else {
				swap(p, imax);
				p = imax;
			}
		}
	}

	private void floatUp(int c) {
		while (c > 1) {
			int p = c / 2;
			if (comparator.compare(heap.get(p), heap.get(c)) < 0) {
				swap(p, c);
				c = p;
			} else {
				break;
			}
		}
	}

	private void swap(int i, int j) {
		E t = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, t);
	}
}
