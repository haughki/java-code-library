package org.haughki.codeLibrary.algorithm;

public class TimeComplexity {
    public static void main(String[] args) {
        logn(50);
        logn(1000);
        logn(87254);

        System.out.println();

        recursive_logn(50);
        recursive_logn(1000);
        recursive_logn(87254);

        System.out.println();
                
        nlogn(50);
        nlogn(1000);
        nlogn(87254);

        System.out.println();

        nlogn_reverse(50);
        nlogn_reverse(1000);
        nlogn_reverse(87254);

        System.out.println();

        recursive_nlogn(50);
        recursive_nlogn(1000);
        recursive_nlogn(87254);
    }

    private static void logn(int n) {
        int count = 0;
        for (int j = n; j > 0; j=j/2) {
            count++;
        }
        // change of base formula says that log(b)n == log(x)n/log(x)b.  There's no builtin for log base 2, so we use
        // base 10.
        System.out.println(Math.log10(n) / Math.log10(2));
        System.out.println(count);
    }

    private static void recursive_logn(int n) {
        // change of base formula says that log(b)n == log(x)n/log(x)b.  There's no builtin for log base 2, so we use
        // base 10.
        System.out.println(Math.log10(n) / Math.log10(2));
        System.out.println(recursiveLognHelper(n));
    }

    private static int recursiveLognHelper(int n) {
        if (n < 1)
            return 0;
        return 1 + recursiveLognHelper(n / 2);
    }
    
    private static void nlogn(int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = n; j > 0; j=j/2) {
                count++;
            }
        }
        // change of base formula says that log(b)n == log(x)n/log(x)b.  There's no builtin for log base 2, so we use
        // base 10.
        // I don't know why count and the calculation are so different...
        System.out.println(n * (Math.log10(n) / Math.log10(2)));
        System.out.println(count);
    }

    private static void nlogn_reverse(int n) {
        int count = 0;
        for (int j = n; j > 0; j=j/2) {
            for (int i = 0; i < n; i++) {
                count++;
            }
        }
        // change of base formula says that log(b)n == log(x)n/log(x)b.  There's no builtin for log base 2, so we use
        // base 10.
        // I don't know why count and the calculation are so different...
        System.out.println(n * (Math.log10(n) / Math.log10(2)));
        System.out.println(count);
    }

    private static void recursive_nlogn(int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            count += recursiveNlognHelper(n);
        }

        // change of base formula says that log(b)n == log(x)n/log(x)b.  There's no builtin for log base 2, so we use
        // base 10.
        // I don't know why count and the calculation are so different...
        System.out.println(n * (Math.log10(n) / Math.log10(2)));
        System.out.println(count);
    }
    
    private static int recursiveNlognHelper(int n) {
        if (n < 1)
            return 0;
        else
            return 1 + recursiveNlognHelper(n / 2);
    }
    

}
