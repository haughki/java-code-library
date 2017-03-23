package org.haughki.codeLibrary.functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class PlayingWithStreams {
    public static void main(String[] args) {
        
        // find unique chars in an array of strings
        List<String> words = new ArrayList<>(Arrays.asList("hey", "why", "am", "I", "doing", "this", "again"));
        //List<?> l = words.stream().map(w -> w.split("")).flatMap(Arrays::stream).distinct().collect(toList());
        System.out.println(Stream.of("hey", "why", "am", "I", "doing", "this", "again").flatMapToInt(String::chars).distinct()
                .mapToObj(i -> Character.toString((char)i)).collect(Collectors.joining()));
        
        System.out.println();
        // UnaryOperator - specialization of Function<T,R> which always returns same type (i.e., Function<T,T>).
        words.replaceAll(String::toUpperCase);
        words.forEach(System.out::print);

        System.out.println();
        // removeIf
        words.removeIf(w -> w.endsWith("Y"));
        words.forEach(System.out::print);

        System.out.println();
        
        // flatmap:  find the pairs of the two sets
        List<Integer> nums1 = Arrays.asList(1, 2, 3);
        List<Integer> nums2 = Arrays.asList(3, 4);
        
        nums1.stream().flatMap(i -> nums2.stream().map(j -> new int[]{i, j})).forEach(k -> System.out.println(k[0] + ": " + k[1]));

        System.out.println();
        
        // find the pairs of the sets whose sum is divisible by 3
        nums1.stream().flatMap(i -> nums2.stream().filter(j -> (j + i) % 3 == 0).map(j -> new int[]{i, j}))
                .forEach(k -> System.out.println(k[0] + ": " + k[1]));

        System.out.println();

        // generate the "fibonacci tuples" series
        Stream.iterate(new int[]{0,1}, a -> new int[]{a[1],a[0] + a[1]})
                .limit(20)
                .forEach(a -> System.out.println(a[0] + ":" + a[1]));

        System.out.println();
        
        // for i with stream
        String[] strings = {"leg", "noggin", "elbow"};
        Stream.iterate(0, i -> i + 1)
                .limit(strings.length)
                .forEach(i -> System.out.println(strings[i]));

        System.out.println();

        // reduce string with replaceAll... (just a weird idea)
        System.out.println(Stream.of("make", "something", "nice", "please", "friend")
                .reduce("hey", (s1, s2) -> s1.replaceAll("e", s2)));
    }
}
