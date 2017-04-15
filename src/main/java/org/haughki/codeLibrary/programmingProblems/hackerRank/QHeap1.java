package org.haughki.codeLibrary.programmingProblems.hackerRank;

import java.util.Scanner;

//https://www.hackerrank.com/challenges/qheap1
public class QHeap1 {
    public static void main(String[] args) {
        String input = "5\n" +
                "1 4\n" +
                "1 9\n" +
                "3\n" +
                "2 4\n" +
                "3";
        Scanner in = new Scanner(input);

        //Scanner in = new Scanner(System.in);
        int queries = in.nextInt();
        MinHeap minHeap = new MinHeap();
        for (int i = 0; i < queries; i++) {
            int qType = in.nextInt();
            switch (qType) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        }
    }
}

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