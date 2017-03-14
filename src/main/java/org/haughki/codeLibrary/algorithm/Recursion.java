package org.haughki.codeLibrary.algorithm;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Recursion {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("my", "little", "buddy", "is", "you");
        printBeforeRecurse(strings.iterator());
        System.out.println();
        printAfterRecurse(strings.iterator());
    }
    
    private static void printBeforeRecurse(Iterator<String> strings){
        if (strings.hasNext()) {
            System.out.println(strings.next());
            printBeforeRecurse(strings);
        }
    }
    
    private static void printAfterRecurse(Iterator<String> strings) {
        if (strings.hasNext()) {
            String next = strings.next();
            printAfterRecurse(strings);
            System.out.println(next);
        }
    }
}

