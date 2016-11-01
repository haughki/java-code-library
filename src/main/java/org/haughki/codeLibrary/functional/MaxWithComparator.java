package org.haughki.codeLibrary.functional;

import java.util.*;

public class MaxWithComparator {
    public static void main(String[] args) {
        List<String> s = new ArrayList<>(Arrays.asList("bbb", "aaa", "ccc", "fff"));

        // anonymous Comparator for finding the "max" of the list.  Arbitrary
        // impl. makes anything starting with "c" the max.
        // How to do this with lambda: see below
        Optional<String> max = s.stream().max(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.equals(o2))
                    return 0;
                if (o1.startsWith("c"))
                    return 1;
                return -1;
            }
        });
        max.ifPresent(System.out::println);

        // Make the comparator more general -- it now compares types of Object
        Optional<String> max1 = s.stream().max(new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1.equals(o2))
                    return 0;
                if (o1.toString().startsWith("c"))
                    return 1;
                return -1;
            }
        });
        max1.ifPresent(System.out::println);

        // Object IS a <? super String>
        Comparator<? super String> comparator = new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1.equals(o2))
                    return 0;
                if (o1.toString().startsWith("c"))
                    return 1;
                return -1;
            }
        };
        s.stream().max(comparator).ifPresent(System.out::println);
        
        // lambda
        s.stream().max((Object o1, Object o2) -> {
            if (o1.equals(o2))
                return 0;
            if (o1.toString().startsWith("c"))
                return 1;
            return -1;
        }).ifPresent(System.out::println);
        
        // really
        s.stream().max(String::compareTo).ifPresent(System.out::println);
        
//        Optional<String> max2 = s.stream().max(new Comparator<?>() {  -- ERROR: can't instantiate a wildcard type
//            @Override
//            public int compare(Object o1, Object o2) {
//                if (o1.equals(o2))
//                    return 0;
//                if (o1.toString().equals("c"))
//                    return 1;
//                return -1;
//            }
//        });
//        System.out.println(max2.get());

    }
}
