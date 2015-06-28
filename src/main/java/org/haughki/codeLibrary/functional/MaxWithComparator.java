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
    }
}
