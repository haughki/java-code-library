package org.haughki.codeLibrary.collections.tree;

import java.util.ArrayList;
import java.util.List;

public class MinHeap {

    private List<Integer> a = new ArrayList<>();  // note that heap size and array size may differ
    private int last;

    public MinHeap() {
        // A pointer to the current 'end' value in the array. If items are removed from the array after they've been
        // added, there will be meaningless values at the end of the array, so we need to know where the right-most meaningful
        // value is.
        last = 0;
        a.add(Integer.MIN_VALUE);  // dummy value. a[0] is never used.
    }
    
    public boolean heapEmpty() {
        return last == 0;
    }
    
    public int min() {
        if (heapEmpty())
            throw new IllegalStateException("The heap is empty.");
        else
            return a.get(1);
    }
    
    public void insert(Integer val) {
        last++;
        if (last < a.size())  // if elements of the array have been removed, there will be 'extra' space
            a.set(last, val);
        else
            a.add(val);
        
        upHeapBubble();
    }

    // Happens after insert(). Insert puts the new value in the last position in the heap (last open spot in array:
    // may not be the actual last _index_ of the array if items have been removed).
    // Compare this new value with parent, swapping if the new value is less.  Continue up the tree like this until
    // you find the proper location of the newly added value.
    private void upHeapBubble(){
        int index = last;
        while (index > 1){
            int parent = index / 2;  // O(log(n))
            //break if the index is greater or equal to the parent
            if (a.get(index) >= a.get(parent))
                break;
            swap(index,parent);
            index = parent;
        }
    }
    
    // Happens after removeMin() (or delete()). For removeMin(), e.g., things always start at index 1. downHeapBubble then
    // moves the last item in the array to the first. Then, moves the new "first value" (was the last value)
    // down through the tree, swapping when it is greater than either of the children; and, always
    // swapping the the min of the two children.
    private void downHeapBubble(int index) {
        a.set(index, a.get(last));
        last--;
        //int index = 1;
        // The children of a given "node" at index v, are v * 2 (left) and v * 2 + 1.  This is the basic organizing
        // property of an array-based heap.
        while (true){
            int child = index * 2; // left child
            if (child > last)  // no children -- do nothing
                break;
            if (child + 1 <= last) {  // right child 
                //if there are two children -> take the smallest; if they are equal take the left one
                child = findMin(child, child + 1);
            }
            if (a.get(index) <= a.get(child))
                break;
            swap(index, child);
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
    
    public void delete(Integer val) {
        if (heapEmpty())
            throw new IllegalStateException("The heap is empty.");
        else {
            int foundIndex = -1;
            for (int i = 1; i <= last; i++) {  // O(n)
                if (a.get(i).equals(val)) {
                    foundIndex = i;
                    break;
                }
            }

            if (foundIndex == -1)
                return;

            downHeapBubble(foundIndex);
        }
    }

    public void print() {
        System.out.println(min());
    }

    
    
    
    public int removeMin() {
        if (heapEmpty())
            throw new IllegalStateException("The heap is empty.");
        else {
            int min = min();
            downHeapBubble(1);
            return min;
        }
    }

    public String toString() {
        String s = "[";
        for (int i = 1; i <= last; i++) {
            s += a.get(i);
            if (i != last)
                s += ",";
        }
        return s + "]";
    }

    public List<Integer> back() {
        return a;
    }

    public int last() {
        return last;
    }

    public int size() {
        return a.size();
    }
}

class MinHeapNode<T> implements Node<T> {
    private final MinHeap minHeap;
    private int index;
    
    MinHeapNode(int index, MinHeap minHeap) {
        this.index = index;
        this.minHeap = minHeap;
    }
    
    @Override
    public Node<T> left() {
        int leftIndex = index * 2;
        if (leftIndex > minHeap.last())
            return null;
        return new MinHeapNode<>(leftIndex, minHeap);
    }

    @Override
    public Node<T> right() {
        int rightIndex = (index * 2) + 1;
        if (rightIndex > minHeap.last())
            return null;
        return new MinHeapNode<>(rightIndex, minHeap);
    }

    @Override
    public void setLeft(Node<T> left) {
        throw new IllegalStateException();
    }

    @Override
    public void setRight(Node<T> right) {
        throw new IllegalStateException();
    }

    @Override
    public T data() {
        return (T) minHeap.back().get(index);  // MinHeap not generic -- didn't want to spend the time
    }

    @Override
    public void setData(T data) {
        throw new IllegalStateException();
    }
}