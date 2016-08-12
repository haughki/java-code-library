
package org.haughki.codeLibrary.generics;

import java.util.Arrays;
import java.util.Comparator;

public class Super {
    public static void main(String[] args) {
        // Note: see also functional.MaxWithComparator

        // signature of sort():  public static <T> void sort(T[] a, Comparator<? super T> c)
        // This seems confusing at first:  how can the Comparator guarantee that it can compare T's or parents of T?
        // What if T has a method, which we use in the definition of the compare method, that a parent doesn't have?
        // The key here is to remember that sort will _only_ sort T's.  This means that the comparator will only 
        // _compare_ T's.  BUT, if the comparator is written to compare parents of T, then T's will automatically be 
        // up-cast to the parent.  And of course, T will always have all of it's methods _and_ 
        // all of it's parents methods:
        String[] strings = {"nog", "log", "gog"};
        // Note that Object is a parent of String; i.e., a super of String
        Arrays.sort(strings, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                // nonsense impl
                if (o1.equals(o2))
                    return 0;
                return 1;
            }
        });
        
    }
}
