package org.haughki.codeLibrary.collections;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyStackTest {
    @Test
    public void test() throws Exception {
        MyStack<String> s = new MyStack<>();
        s.push("one");
        s.push("two");
        s.push("three");
        s.push("four");
        s.push("five");

        while (s.top != null){
            System.out.println(s.pop());
        }
    }

}