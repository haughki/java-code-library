package org.haughki.codeLibrary.programmingProblems.arraysAndStrings;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/*
2017.05.04
This is a problem I got on my Credit Karma interview.

- start with an array of strings
- take strings from the array in pairs
- for each pair, concat the two strings and generate a cryptographic hash of the concatenated strings
- the result of the hash will be a new string.  Each of these new strings, taken altogether, creates a new array
  of strings.  For this new array, do the same thing you did to the first.
- keep doing this until you get an array of length one -- return and print that string.
 */
public class MD5Concat_InfiniHash {
    public static void main(String[] args) throws NoSuchAlgorithmException {

        String[] a = new String[5];
        a[0] = "one";
        a[1] = "two";
        a[2] = "three";
        a[3] = "four";
        a[4] = "five";

        String infiniHash = recurse(a, a.length);
        System.out.println(infiniHash);
    }

    private static String recurse(String[] a, int bound) throws NoSuchAlgorithmException {
        if (bound == 1)
            return a[0];

        int j = 0;
        for (int i=0; i < bound; i += 2)  {
            String s1 = a[i];
            String s2;
            if (i == a.length - 1)
                s2 = a[i];
            else
                s2 = a[i + 1];

            a[j] = getMd5(s1 + s2);

            System.out.println(a[j]);
            j++;
            System.out.println();
        }

        System.out.println(j);
        return recurse(a, j);
    }

    private static String getMd5(String s) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return DatatypeConverter.printHexBinary(md.digest(s.getBytes()));
    }

}
