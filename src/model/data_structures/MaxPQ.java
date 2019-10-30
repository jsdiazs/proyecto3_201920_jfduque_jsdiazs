package model.data_structures;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class MaxPQ<T extends DinamicArray>
{ 
	    private T[] pq;                   
	    private int n;       
	    private int max;
	    private Comparator<T> comparator;

	    /**
	     * Initializes an empty priority queue with the given initial capacity.
	     *
	     * @param  initCapacity the initial capacity of this priority queue
	     */
	    public MaxPQ() 
	    {
	        pq = (T[]) new Comparable[max + 1];
	        n = 0;
	        max=2;
	    }


	    public MaxPQ(T[] keys) {
	        n = keys.length;
	        pq = (T[]) new Comparable[max + 1];
	        for (int i = 0; i < n; i++)
	            pq[i+1] = keys[i];
	        for (int k = n/2; k >= 1; k--)
	            sink(k);
	        assert isMaxHeap();
	    }
	      

	    public boolean isEmpty() {
	        return n == 0;
	    }

	    public int size() {
	        return n;
	    }

	   
	    public T max() 
	    {
	        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
	        return pq[1];
	    }

	  
	    private void resize(int capacity) {
	        assert capacity > n;
	        T[] temp = (T[]) new Comparable[capacity];
	        for (int i = 1; i <= n; i++) {
	            temp[i] = pq[i];
	        }
	        pq = temp;
	    }


	    public void insert(T x) {

	        
	        if (n == pq.length - 1) resize(2 * pq.length);

	        pq[++n] = x;
	        swim(n);
	        assert isMaxHeap();
	    }

	    public T delMax() {
	        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
	        T max = pq[1];
	        exch(1, n--);
	        sink(1);
	        pq[n+1] = null;     
	        if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
	        assert isMaxHeap();
	        return max;
	    }


	    private void swim(int k) {
	        while (k > 1 && less(k/2, k)) {
	            exch(k, k/2);
	            k = k/2;
	        }
	    }

	    private void sink(int k) {
	        while (2*k <= n) {
	            int j = 2*k;
	            if (j < n && less(j, j+1)) j++;
	            if (!less(k, j)) break;
	            exch(k, j);
	            k = j;
	        }
	    }

	   
	    private boolean less(int i, int j) {
	        if (comparator == null) {
	            return ((Comparable<T>) pq[i]).compareTo(pq[j]) < 0;
	        }
	        else {
	            return comparator.compare(pq[i], pq[j]) < 0;
	        }
	    }

	    private void exch(int i, int j) {
	        T swap = pq[i];
	        pq[i] = pq[j];
	        pq[j] = swap;
	    }

	    private boolean isMaxHeap() {
	        for (int i = 1; i <= n; i++) {
	            if (pq[i] == null) return false;
	        }
	        for (int i = n+1; i < pq.length; i++) {
	            if (pq[i] != null) return false;
	        }
	        if (pq[0] != null) return false;
	        return isMaxHeapOrdered(1);
	    }

	
	    private boolean isMaxHeapOrdered(int k) {
	        if (k > n) return true;
	        int left = 2*k;
	        int right = 2*k + 1;
	        if (left  <= n && less(k, left))  return false;
	        if (right <= n && less(k, right)) return false;
	        return isMaxHeapOrdered(left) && isMaxHeapOrdered(right);
	    }

	}

	/******************************************************************************
	 *  Copyright 2002-2018, Robert Sedgewick and Kevin Wayne.
	 *
	 *  This file is part of algs4.jar, which accompanies the textbook
	 *
	 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
	 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
	 *      http://algs4.cs.princeton.edu
	 *
	 *
	 *  algs4.jar is free software: you can redistribute it and/or modify
	 *  it under the terms of the GNU General Public License as published by
	 *  the Free Software Foundation, either version 3 of the License, or
	 *  (at your option) any later version.
	 *
	 *  algs4.jar is distributed in the hope that it will be useful,
	 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
	 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	 *  GNU General Public License for more details.
	 *
	 *  You should have received a copy of the GNU General Public License
	 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
	 ******************************************************************************/

