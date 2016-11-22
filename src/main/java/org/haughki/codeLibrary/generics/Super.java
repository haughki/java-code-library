
package org.haughki.codeLibrary.generics;

import java.util.Arrays;
import java.util.Comparator;

public class Super {
    public static void main(String[] args) {
        // Note: see also functional.MessingWithComparator

        // signature of sort():  public static <T> void sort(T[] a, Comparator<? super T> c)
        // This seems confusing at first:  how can the Comparator guarantee that it can compare T's or parents of T?
        // What if T has a method, which we use in the definition of the compare method, that a parent doesn't have?
        // The key here is to remember that sort will _only_ sort T's.  This means that the comparator will only 
        // _compare_ T's.  BUT, if the comparator is written to compare parents of T, then T's (which _will_ be the
        // actual type of the passed "thing to compare") will automatically be up-cast to the parent.  And of course, 
        // T will always have all of it's methods _and_ all of it's parents methods:
        String[] strings = {"nog", "gog", "log"};
        Arrays.stream(strings).forEach(System.out::println);
        System.out.println();
        
        // Note that Object is a parent of String; i.e., a super of String
        Arrays.sort(strings, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                // nonsense impl -- sort by hashcode
                if (o1.hashCode() == o2.hashCode())
                    return 0;
                else if (o1.hashCode() > o2.hashCode())
                    return 1;
                return -1;
            }
        });
        Arrays.stream(strings).forEach(System.out::println);
        System.out.println();
        
        strings[0] = "nog";
        strings[1] = "gog";
        strings[2] = "log";

        // note, you would never have to actually implement this -- string sort this way by default.  This just
        // demonstrates how to implement a comparator using a lambda
        Arrays.sort(strings, (String s, String t) -> s.compareTo(t));
        Arrays.stream(strings).forEach(System.out::println);
    }
}
