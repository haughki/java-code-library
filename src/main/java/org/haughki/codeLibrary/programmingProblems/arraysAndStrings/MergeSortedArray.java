package org.haughki.codeLibrary.programmingProblems.arraysAndStrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSortedArray {
    public static void main(String[] args) {
        int[] nums1 = new int[7];
        nums1[0] = 1;
        nums1[1] = 3;
        nums1[2] = 5;
        int[] nums2 = new int[] {2, 2, 3, 4};
        
        MergeSorted m = new MergeSorted();
        m.merge(nums1, 3, nums2, nums2.length);
        for (int i = 0; i < nums1.length; i++) {
            System.out.print(nums1[i] + " ");
        }

        System.out.println();
        
        nums1 = new int[1];
        nums1[0] = 1;
        nums2 = new int[0];

        m.merge(nums1, 1, nums2, nums2.length);
        for (int i = 0; i < nums1.length; i++) {
            System.out.print(nums1[i] + " ");
        }

        System.out.println();

        nums1 = new int[1];
        nums2 = new int[] {1};
        

        m.merge(nums1, 0, nums2, nums2.length);
        for (int i = 0; i < nums1.length; i++) {
            System.out.print(nums1[i] + " ");
        }

    }
}

class MergeSorted {

    // https://discuss.leetcode.com/topic/11919/share-my-accepted-java-solution
    // Clean Java solution from leetcode.  Just start from the ends of the arrays...
    public void merge(int A[], int m, int B[], int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while(i >= 0 && j >= 0) {
            A[k--] = A[i] > B[j] ? A[i--] : B[j--];
        }
        while(j >= 0) {
            A[k--] = B[j--];
        }
    }
    
    // My best effort. WAS accepted.  Sadly not good at all :(
    public void mergeYUCK(int[] nums1, int m, int[] nums2, int n) {
        
        int[] copy1 = new int[m];
        for (int i = 0; i < m; i++) {
            copy1[i] = nums1[i];
        }

        int i =0;
        int j = 0;
        int numsi = 0;

        while (i < copy1.length && j < nums2.length) {
            if (copy1[i] < nums2[j]) {
                nums1[numsi] = copy1[i];
                numsi++;
                i++;
            }
            else {
                nums1[numsi] = nums2[j];
                numsi++;
                j++;
            }
        }

        for (int k = i; k < copy1.length; k++) {
            nums1[numsi] = copy1[k];
            numsi++;
        }

        for (int k = j; k < nums2.length; k++) {
            nums1[numsi] = nums2[k];
            numsi++;
        }
    }
 }
