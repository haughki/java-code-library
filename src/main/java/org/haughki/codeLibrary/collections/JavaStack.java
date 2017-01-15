package org.haughki.codeLibrary.collections;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class JavaStack {
    // Deque is the interface java recommends for Stack impls. In particular, ArrayDeque
    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(10);
        stack.push(9);
        stack.push(8);
        
        stack.forEach(System.out::println);
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
        
        System.out.println();
        
        
        Deque<Integer> d = new ArrayDeque<>();
        d.offerFirst(1);
        d.offerFirst(2);
        d.offerFirst(3);
        System.out.println("d");
        d.stream().forEach(System.out::println);
        System.out.println();
        
        Deque<Integer> d2 = new ArrayDeque<>();
        d2.addFirst(1);
        d2.addFirst(2);
        d2.addFirst(3);
        System.out.println("d2");
        d2.stream().forEach(System.out::println);
        System.out.println();

        Deque<Integer> d3 = new ArrayDeque<>();
        d3.offerLast(1);
        d3.offerLast(2);
        d3.offerLast(3);
        System.out.println("d3");
        while (d3.size() > 0)
            System.out.println(d3.pollLast());

        System.out.println();
        
        
        Deque<Integer> ll = new LinkedList<>();
        ll.offerFirst(4);
        ll.offerFirst(5);
        ll.offerFirst(6);
        System.out.println("ll");
        ll.stream().forEach(System.out::println);
        System.out.println();
        
        while (ll.size() > 0)
            System.out.println(ll.pollFirst());
    }
    
}
