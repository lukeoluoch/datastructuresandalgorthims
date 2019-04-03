package proj3;

import java.util.*;

public class CustomPriorityQueue {
	public Node[] pq;
	public int n;
	public Comparator comparator;

	public CustomPriorityQueue(int initCapacity) {
		pq = new Node[initCapacity + 1];
		n = 0;

	}

	public CustomPriorityQueue() {
		this(1);
	}

	public CustomPriorityQueue(int initCapacity, Comparator<Node> comparator) {
		this.comparator = comparator;
		pq = new Node[initCapacity + 1];
		n = 0;
	}

	public CustomPriorityQueue(Comparator<Node> comparator) {
		this(1, comparator);
	}

	public CustomPriorityQueue(Node[] keys) {
		n = keys.length;
		pq = new Node[keys.length + 1];
		for (int i = 0; i < n; i++)
			pq[i + 1] = keys[i];
		for (int k = n / 2; k >= 1; k--)
			sink(k);
		assert isMinHeap();
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public int size() {
		return n;
	}

	public Node min() {
		if (isEmpty())
			throw new NoSuchElementException("Priority queue underflow");
		return pq[1];
	}

	public void resize(int capacity) {
		assert capacity > n;
		Node[] temp = new Node[capacity];
		for (int i = 1; i <= n; i++) {
			temp[i] = pq[i];
		}
		pq = temp;
	}

	public void add(Node x) {

		if (n == pq.length - 1)
			resize(2 * pq.length);

		pq[++n] = x;
		swim(n);
		assert isMinHeap();
	}

	public Node Minimization() {
		if (isEmpty())
			throw new NoSuchElementException("Priority queue underflow");
		Node min = pq[1];
		exchange(1, n--);
		sink(1);
		pq[n + 1] = null; 
		if ((n > 0) && (n == (pq.length - 1) / 4))
			resize(pq.length / 2);
		assert isMinHeap();
		return (Node) min;
	}

	private void swim(int k) {
		while (k > 1 && greater(k / 2, k)) {
			exchange(k, k / 2);
			k = k / 2;
		}
	}

	private void sink(int k) {
		while (2 * k <= n) {
			int j = 2 * k;
			if (j < n && greater(j, j + 1))
				j++;
			if (!greater(k, j))
				break;
			exchange(k, j);
			k = j;
		}
	}
	private boolean greater(int i, int j) {
        if (comparator == null) {
        	
            return ((Comparable) pq[i].character).compareTo(pq[j].character) > 0;
        }
        else {
            return comparator.compare(pq[i].character, pq[j].character) > 0;
        }
    }

    private void exchange(int i, int j) {
        Node swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    private boolean isMinHeap() {
        return isMinHeap(1);
    }
    private boolean isMinHeap(int k) {
        if (k > n) return true;
        int left = 2*k;
        int right = 2*k + 1;
        if (left  <= n && greater(k, left))  return false;
        if (right <= n && greater(k, right)) return false;
        return isMinHeap(left) && isMinHeap(right);
    }
    public Iterator<Node> iterator() {
        return new HeapIterator();
    }
    private class HeapIterator implements Iterator<Node> {

        private CustomPriorityQueue copy;
        public HeapIterator() {
            if (comparator == null) copy = new CustomPriorityQueue(size());
            else                    copy = new CustomPriorityQueue(size(), comparator);
            for (int i = 1; i <= n; i++)
                copy.add(pq[i]);
        }

        public boolean hasNext()  { return !copy.isEmpty();                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Node next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.Minimization();
        }
    }
}
