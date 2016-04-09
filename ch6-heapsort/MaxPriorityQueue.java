import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class MaxPriorityQueue<E> {

	private int n = 0;
	private final Comparator<E> comparator;
	private E[] data;

	@SuppressWarnings("unchecked")
	public MaxPriorityQueue(int minCapacity, Comparator<E> comparator) {
		assert minCapacity >= 0;
		this.data = (E[]) new Object[1 + minCapacity]; // data is stored from index 1
		this.comparator = comparator;
	}

	void verifyHeap() {
		for (int p = n / 2; p >= 1; p--) {
			int l = p * 2;
			int r = l + 1;
			if (l <= n) {
				assert comparator.compare(data[l], data[p]) <= 0 : "invalid heap";
			}
			if (r <= n) {
				assert comparator.compare(data[r], data[p]) <= 0 : "invalid heap";
			}
		}
	}

	public boolean add(E e) {
		if (e == null) {
			throw new NullPointerException("cannot insert null elements");
		}
		n++;
		ensureCapacity(n);
		data[n] = e;
		floatUp(n);
		return true;
	}

	public boolean remove(E e) {
		for (int i = 1; i <= n; i++) {
			if (e == data[i]) {
				data[i] = data[n];
				data[n] = null;
				n--;
				if (i <= n) {
					sinkDown(i);
					floatUp(i);
				}
				return true;
			}
		}
		return false;
	}

	public E peek() {
		if (n == 0) {
			return null;
		}
		return data[1];
	} 

	public E poll() {
		if (n == 0) {
			return null;
		}
		E ret = data[1];
		data[1] = data[n];
		data[n] = null;
		n--;
		sinkDown(1);
		return ret;
	} 

	// this is the method that java's PriorityQueue lacks.
	public E set(int index, E e) {
		E ret = data[index];
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
		E t = data[p];
		while (p <= n / 2) {
			int l = 2 * p;
			int r = l + 1;
			int c = -1;
			if (l <= n && comparator.compare(t, data[l]) < 0) {
				c = l;
			}
			if (r <= n && comparator.compare((c == -1)?t:data[c], data[r]) < 0) {
				c = r;
			}			
			if (c == -1) {
				break;
			} else {
				data[p] = data[c];
				p = c;
			}
		}
		data[p] = t;
	}

	private void floatUp(int c) {
		E t = data[c];
		while (c > 1) {
			int p = c / 2;
			if (comparator.compare(data[p], t) < 0) {
				data[c] = data[p];
				c = p;
			} else {
				break;
			}
		}
		data[c] = t;
	}

	public void ensureCapacity(int minCapacity) {
		int oldCapacity = data.length - 1;
		if (minCapacity > oldCapacity) {
			int newCapacity = (oldCapacity * 3) / 2 + 1;
			if (newCapacity < minCapacity) {
				newCapacity = minCapacity;
			}
			data = Arrays.copyOf(data, newCapacity + 1);
		}
	}

}
