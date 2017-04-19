package org.haughki.codeLibrary.programmingProblems.hackerRank;

import org.haughki.codeLibrary.collections.tree.MinHeap;

import java.util.Scanner;

//https://www.hackerrank.com/challenges/qheap1
public class QHeap1 {
    public static void main(String[] args) {
//        String input = "5\n" +
//                "1 4\n" +
//                "1 9\n" +
//                "3\n" +
//                "2 4\n" +
//                "3";

//        String input = "18\n" +
//                "1 5\n" +
//                "1 3\n" +
//                "1 17\n" +
//                "1 10\n" +
//                "3\n" +  // 3
//                "1 84\n" +
//                "1 19\n" +
//                "1 6\n" +
//                "1 2\n" +
//                "1 22\n" +
//                "1 9\n" +
//                "3\n" +  // 2
//                "2 6\n" +
//                "3\n" +  // 2
//                "2 2\n" +
//                "3\n" +  // 3
//                "2 22\n" +
//                "3";     // 3

        String input = "12\n" +
                "1 10\n" +
                "1 9\n" +
                "3\n" +   // 9
                "1 3\n" +
                "3\n" +   // 3
                "2 9\n" +
                "3\n" +   // 3
                "2 3\n" +
                "3\n" +   // 10
                "1 5\n" +
                "1 2\n" +
                "3";      // 2
        
        Scanner in = new Scanner(input);

        //Scanner in = new Scanner(System.in);
        int queries = in.nextInt();
        MinHeap minHeap = new MinHeap();
        for (int i = 0; i < queries; i++) {
            int qType = in.nextInt();
            switch (qType) {
                case 1:
                    minHeap.insert(in.nextInt());
                    break;
                case 2:
                    minHeap.delete(in.nextInt());
                    break;
                case 3:
                    minHeap.print();
                    break;
            }
        }
    }
}


/* Attempted to implement as a binary tree structure -- turns out, though heaps are modeled as trees, they are usually (?)
implemented on top of an array.  See \collections\tree\MinHeap.java

class HeapNode {
    int val;
    HeapNode parent;
    HeapNode left;
    HeapNode right;
    HeapNode(int val) {
        this.val = val; 
    }
}

class MinHeap {
    HeapNode last;
    HeapNode min;
    
    void add(int val) {
        HeapNode in = new HeapNode(val);
        if (last == null) {  // adding first node
            last = in;
            min = in;
            return;
        }
        // put in 
        in.parent = last;
        if (last.left != null)
            last.left = in;
        else
            last.right = in;
        
        if (in.val > last.val) {
            last = in;
            return;
        }
            
        
        while (in.parent != null && in.val < in.parent.val) {
            int tVal = in.parent.val;
            in.parent.val = in.val;
            in.val = tVal;
            in = in.parent;
        }
        
        if (in.parent == null)
            min = in;
    }
    
    void delete(int val) {
        
    }
    void print() {
        if (min != null)
            System.out.println(min.val);
        else
            System.out.println("");
    }
        
}

*/