package org.haughki.codeLibrary.algorithm;

import java.util.ArrayList;
import java.util.List;

public class MinHeap {

    private List<Integer> a = new ArrayList<>();  // note that heap size and array size may differ
    private int last;

    public MinHeap() {
        last = 0;
        a.add(Integer.MIN_VALUE);
    }

    public int last() {
        return last;
    }

    public boolean heapEmpty() {
        return last() == 0;
    }
    
    public int min() {
        if (heapEmpty())
            throw new IllegalStateException("The heap is empty.");
        else
            return a.get(1);
    }
    
    public void insert(Integer e) {
        last++;
        if (last < a.size() - 1)
            a.set(last, e);
        else
            a.add(e);
        
        upHeapBubble();
    }

    public int removeMin() {
        if (heapEmpty())
            throw new IllegalStateException("The heap is empty.");
        else {
            int min = min();
            a.set(1, a.get(last)); // remove last?
            last--;
            downHeapBubble();
            return min;
        }
    }

    // Happens after insert(). Insert puts the new value in the last position in the heap (last spot in array).
    // Compare this new value with parent, swapping if the new value is less.  Continue up the tree like this until
    // you find the proper location of the newly added value.
    private void upHeapBubble(){
        int index = last();
        while (index > 1){
            int parent = index / 2;
            //break if the index is greater or equal to the parent
            if (a.get(index) >= a.get(parent))
                break;
            swap(index,parent);
            index = parent;
        }
    }

    // Happens after removeMin().  That method moved the last item in the array to the first.  Now, move this value
    // (was the last value) down through the tree, swapping when it is greater than either of the children; and, always
    // swapping the the min of the two children.
    private void downHeapBubble(){
        int index = 1;
        // The children of a given "node" at index v, are v * 2 (left) and v * 2 + 1.  This is the basic organizing
        // property of an array-based heap.
        while (true){
            int child = index * 2; // left child
            if (child > last())  // no children -- do nothing
                break;
            if (child + 1 <= last()){  // right child 
                //if there are two children -> take the smallest or if they are equal take the left one
                child = findMin(child, child + 1);
            }
            if (a.get(index) <= a.get(child))
                break;
            swap(index,child);
            index = child;
        }
    }
    
    private void swap(int i, int j) {
        Integer temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    // return min of left and right child, if they are equal return the left
    private int findMin(int leftChild, int rightChild) {
        if (a.get(leftChild) <= a.get(rightChild))
            return leftChild;
        else
            return rightChild;
    }

    public String toString() {
        String s = "[";
        for (int i = 1; i <= last(); i++) {
            s += a.get(i);
            if (i != last)
                s += ",";
        }
        return s + "]";
    }
}