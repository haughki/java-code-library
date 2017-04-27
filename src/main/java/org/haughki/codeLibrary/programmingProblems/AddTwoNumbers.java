package org.haughki.codeLibrary.programmingProblems;

/*
https://leetcode.com/problems/add-two-numbers/#/description
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
 */

import org.haughki.codeLibrary.aacommon.ListNode;

public class AddTwoNumbers {

    public static void main(String[] args) {
//        [2,4,3]
//        [5,6,4]

//        ListNode l1 = new ListNode(9);
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(8);
//        l1.next.next.next = new ListNode(3);
//        
//        ListNode l2 = new ListNode(5);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);

        ListNode ans = add(l1, l2);
        ListNode curr = ans;
        while (curr != null) {
            System.out.println(curr.val);
            curr = curr.next;
        }
    }

    private static ListNode add(ListNode l1, ListNode l2) {
        ListNode prev = new ListNode(-1);
        ListNode ans = prev;
        int carry = 0, currVal;
        while (l1 != null) {
            ListNode currRes;
            currVal = l1.val;
            l1 = l1.next;
            if (l2 != null) {
                currVal += l2.val;
                l2 = l2.next;
            }

            currRes = new ListNode(currVal + carry);
            if (currRes.val > 9) {
                carry = 1;
                currRes.val = currRes.val % 10;
            } else
                carry = 0;

            prev.next = currRes;
            prev = currRes;
        }
        while (l2 != null) {
            ListNode currRes = new ListNode(l2.val + carry);
            l2 = l2.next;

            if (currRes.val > 9) {
                carry = 1;
                currRes.val = currRes.val % 10;
            } else
                carry = 0;

            prev.next = currRes;
            prev = currRes;
        }

        if (carry > 0)
            prev.next = new ListNode(1);

        return ans.next;
    }

}
