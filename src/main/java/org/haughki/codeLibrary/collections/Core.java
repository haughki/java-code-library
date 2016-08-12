package org.haughki.codeLibrary.collections;

import java.util.*;

public class Core {
    public static void main(String[] args) {
        List<String> l = new ArrayList<>(Arrays.asList("Bug", "nut", "mango"));
        Collection<String> c = l;

        // toArray
        String[] strings = new String[2];
        String[] strings2 = l.toArray(strings);
        Arrays.stream(strings).forEach(System.out::println);
        System.out.println(strings);
        System.out.println();
        Arrays.stream(strings2).forEach(System.out::println);
        System.out.println(strings2);

        System.out.println();
        filter(l, "nut");
        l.stream().forEach(System.out::println);
    }

    private static <T> void filter(Collection<T> coll, final T comparator) {
        // removing items from a collection
/*
        for (Iterator<T> it = coll.iterator(); it.hasNext(); )
            if (c.condition(it.next()))
                it.remove();
*/
        Iterator<T> it = coll.iterator();
        while (it.hasNext())
            if (comparator.equals(it.next()))
                it.remove();
    }
}
