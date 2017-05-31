package org.haughki.codeLibrary.algorithm;

public class BinarySearch {
    public static void main(String[] args) {
        int[] a = {2,6,7,13,22};
        //int n = 6;
        int n = 22;
        //int n = 9;
        System.out.println(findIndex(n, a, 0, a.length - 1));
    }

    private static int findIndex(int n, int[] a, int start, int end) {
        if (start == end) {
            if (n == a[start])
                return start;
            else
                return -1;
        }

        int mid = start + ((end - start) / 2);
//        System.out.println(start);
//        System.out.println(end);
//        System.out.println(mid);
//        System.out.println();
        if (a[mid] == n) {
            return mid;
        } else if (n < a[mid]) {
            return findIndex(n, a, start, mid);
        } else {
            return findIndex(n, a, mid + 1, end);
        }
    }
}
