package org.haughki.codeLibrary.functional;

import java.util.*;


class Banana {
    // comparator via composition
    static Comparator<Banana> ripenessComparator;

    static {
        // could also do this as a lambda, but this also works (and shows what is really happening)
        ripenessComparator = new Comparator<Banana>() {
            @Override
            public int compare(Banana b1, Banana b2) {
                if (b1.ripeness == b2.ripeness)
                    return 0;
                else if (b1.ripeness > b2.ripeness)
                    return 1;
                else
                    return -1;
            }
        };
    }
    
    private final float length;
    private final float diameter;
    private int ripeness;
    private String color;

    Banana(final float length, final float diameter, int ripeness, String color) {
        this.length = length;
        this.diameter = diameter;
        this.ripeness = ripeness;
        this.color = color;
    }

    float getLength() {
        return this.length;
    }

    float getDiameter() {
        return diameter;
    }
    
    int getRipeness() {
        return ripeness;
    }

    String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return length + ":" + diameter + ":" + ripeness + ":" + color;
    }
}

public class MessingWithComparator {
    public static void main(String[] args) {
        
        // note that Strings are already Comparable
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

        System.out.println();

        // an absurd implementation just to show "how" you could do this; this is what compareTo already does....
        s.stream().max((s1, s2) -> {
            if (s1.equals(s2))
                return 0;
            else if (s1.compareTo(s2) < 0)
                return -1;
            else
                return 1;
        }).ifPresent(System.out::println);
        
        s.stream().max((s1, s2) -> s1.compareTo(s2)).ifPresent(System.out::println);
        
        s.stream().max(String::compareTo).ifPresent(System.out::println);

        s.stream().max(Comparator.comparing(s1 -> s1)).ifPresent(System.out::println);
        
        // the actual right way:
        s.stream().max(Comparator.naturalOrder()).ifPresent(System.out::println);

        System.out.println();
        

        /* *** BANANA *** */
        
        List<Banana> bananas = new ArrayList<>(Arrays.asList(
                new Banana(15.0f, 2.1f, -1, "green"),
                new Banana(14.3f, 2.2f, 1, "bright yellow"),
                new Banana(15.3f, 2.4f, 0, "green"),
                new Banana(14.6f, 1.6f, 1, "yellow")
        ));

        // Progression
        // Explicit Comparator definition 
        bananas.stream().sorted((b1, b2) -> {
            if (b1.getDiameter() == b2.getDiameter())
                return 0;
            else if (b1.getDiameter() > b2.getDiameter())
                return 1;
            else
                return -1;
        }).forEach(System.out::println);
        System.out.println();
        // using String's compareTo method -- this is the definition of the "natural" comparison for Strings
        bananas.stream().sorted((b1, b2) -> b1.getColor().compareTo(b2.getColor())).forEach(System.out::println);
        System.out.println();
        // using a pre-defined Comparator, stored in Banana -- nice that it's with the class def.
        bananas.stream().sorted(Banana.ripenessComparator).forEach(System.out::println);
        System.out.println();
        // Here's the "real" one:  no need to ever define a comparator -- just tell it what to use (i.e., length)
        // and, easy to reverse the order...
        bananas.stream().sorted(Comparator.comparing(Banana::getLength).reversed()).forEach(System.out::println);
    }
}
