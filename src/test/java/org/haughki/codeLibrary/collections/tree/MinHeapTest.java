package org.haughki.codeLibrary.collections.tree;

import org.junit.Assert;
import org.junit.Test;

public class MinHeapTest {
    
    @Test
    public void deleteLast() throws Exception {
        MinHeap minHeap = new MinHeap();
        minHeap.insert(10);
        minHeap.insert(9);
        minHeap.insert(3);
        Assert.assertEquals("[3,10,9]", minHeap.toString());
        minHeap.delete(9);
        Assert.assertEquals("[3,10]", minHeap.toString());
    }
    
    @Test
    public void delete() throws Exception {
        MinHeap minHeap = new MinHeap();
        minHeap.insert(17);
        minHeap.insert(10);
        minHeap.insert(5);
        minHeap.insert(3);

        minHeap.delete(5);
        Assert.assertEquals("[3,17,10]", minHeap.toString());
        Assert.assertEquals(3, minHeap.min());

        minHeap.insert(84);
        minHeap.insert(19);
        minHeap.insert(6);
        minHeap.insert(22);
        minHeap.insert(9);

        Assert.assertEquals("[3,9,6,17,19,10,22,84]", minHeap.toString());
        Assert.assertEquals(3, minHeap.min());

        minHeap.delete(10);
        Assert.assertEquals("[3,9,6,17,19,84,22]", minHeap.toString());
        Assert.assertEquals(3, minHeap.min());

        minHeap.insert(2);
        minHeap.insert(5);
        minHeap.insert(8);
        minHeap.insert(12);
        minHeap.insert(24);
        minHeap.insert(38);
        minHeap.insert(92);
        minHeap.insert(71);
        minHeap.insert(7);
        minHeap.insert(14);

        Assert.assertEquals("[2,3,6,5,8,24,22,7,9,19,12,84,38,92,71,17,14]", minHeap.toString());
        Assert.assertEquals(2, minHeap.min());

        minHeap.delete(3);
        Assert.assertEquals("[2,5,6,7,8,24,22,14,9,19,12,84,38,92,71,17]", minHeap.toString());
        Assert.assertEquals(2, minHeap.min());
        
        minHeap.delete(6);
        Assert.assertEquals("[2,5,17,7,8,24,22,14,9,19,12,84,38,92,71]", minHeap.toString());
        Assert.assertEquals(2, minHeap.min());
        
//        Node<Integer> mh = new MinHeapNode<>(1, minHeap);
//        BTreePrinter.printNode(mh);

    }



    @Test
    public void arrayLengthVersusLast() throws Exception {
        // The size of the array back should always be different than the
        // number of elements in the heap.
        MinHeap minHeap = new MinHeap();
        minHeap.insert(17);
        minHeap.insert(10);
        minHeap.insert(5);
        minHeap.insert(3);
        Assert.assertEquals(4, minHeap.last());
        Assert.assertEquals(5, minHeap.size());
        
        minHeap.removeMin();
        Assert.assertEquals(3, minHeap.last());
        Assert.assertEquals(5, minHeap.size());
    }

    @Test
    public void addInReverse() throws Exception {
        MinHeap minHeap = new MinHeap();
        minHeap.insert(17);
        minHeap.insert(10);
        Assert.assertEquals("[10,17]", minHeap.toString());
        Assert.assertEquals(10, minHeap.min());
        
        minHeap.insert(5);
        Assert.assertEquals("[5,17,10]", minHeap.toString());
        Assert.assertEquals(5, minHeap.min());

        minHeap.insert(3);
        Assert.assertEquals("[3,5,10,17]", minHeap.toString());
        Assert.assertEquals(3, minHeap.min());
    }

    @Test
    public void generalHappyPath_insertRemoveMinDelete() throws Exception {
        MinHeap minHeap = new MinHeap();
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(17);
        minHeap.insert(10);
        minHeap.insert(84);
        minHeap.insert(19);
        minHeap.insert(6);
        minHeap.insert(22);
        minHeap.insert(9);

        Assert.assertEquals("[3,5,6,9,84,19,17,22,10]", minHeap.toString());
        Assert.assertEquals(3, minHeap.min());
        
        minHeap.removeMin();
        Assert.assertEquals("[5,9,6,10,84,19,17,22]", minHeap.toString());
        Assert.assertEquals(5, minHeap.min());
        
        minHeap.delete(9);
        Assert.assertEquals("[5,10,6,22,84,19,17]", minHeap.toString());
        Assert.assertEquals(5, minHeap.min());
    }

    @Test
    public void printTree() throws Exception {
        MinHeap minHeap = new MinHeap();
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(17);
        minHeap.insert(10);
        minHeap.insert(84);
        minHeap.insert(19);
        minHeap.insert(6);
        minHeap.insert(22);
        minHeap.insert(9);

        //minHeap.printTree();
        Node<Integer> mh = new MinHeapNode<>(1, minHeap);
        
        BinTreePrinter.printNode(mh);
    }

}