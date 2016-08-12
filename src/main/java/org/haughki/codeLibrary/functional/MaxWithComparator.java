package org.haughki.codeLibrary.functional;

import java.util.*;

public class MaxWithComparator {
    public static void main(String[] args) {
        List<String> s = new ArrayList<>(Arrays.asList("aaa", "bbb", "ccc"));

        // anonymous Comparator for finding the "max" of the list.  Arbitrary
        // impl. makes anything starting with "c" the max.
        // How to do this with lambda?
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
        System.out.println(max.get());

        // Make the comparator more general -- it now compares types of Object
        Optional<String> max1 = s.stream().max(new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1.equals(o2))
                    return 0;
                if (o1.toString().equals("c"))
                    return 1;
                return -1;
            }
        });
        System.out.println(max1.get());

        // Object IS a <? super String>
        Comparator<? super String> comparator = new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1.equals(o2))
                    return 0;
                if (o1.toString().equals("c"))
                    return 1;
                return -1;
            }
        };
        System.out.println(s.stream().max(comparator).get());
        
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
