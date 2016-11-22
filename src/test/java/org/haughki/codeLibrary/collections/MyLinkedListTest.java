package org.haughki.codeLibrary.collections;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyLinkedListTest {
    @Test
    public void test() throws Exception {
        MyLinkedList<String> l = new MyLinkedList<>();
        l.add("one");
        l.add("two");
        l.add("three");
        l.add("four");
        l.add("five");
        
        while (l.head != null){
            System.out.println(l.poll());
        }
            
    }

}