package org.haughki.codeLibrary.programmingProblems;

import org.haughki.codeLibrary.aacommon.ListNode;

import java.util.HashSet;
import java.util.Set;

/*
3/21
https://leetcode.com/problems/linked-list-cycle/#/description
Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?


Insights:  1) a circular linked list has no null to terminate the list!  If you're base case is checking for null,
you'll end up in an infinite loop.  
 */
public class LinkedListCycle {
    public static void main(String[] args) {
        ListNode a = new ListNode(5);
        ListNode b = new ListNode(6);
        ListNode c = new ListNode(7);
        ListNode d = new ListNode(8);
        a.next = b;
        b.next = c;
        c.next = d;
        //d.next = b;
        HasCycle h = new HasCycle();
        System.out.println(h.hasCycle(a));
    }
    
}

class HasCycle {
    // editorial from leetcode:  https://leetcode.com/articles/linked-list-cycle/
    // Most importantly, this is O(n).  
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
    
    // first attempt:  uses a set -- works fine, O(n), but challenge wants to use no extra space.
    public boolean hasCycle0(ListNode head) {
        if (head == null)
            return false;
        Set<ListNode> nodes = new HashSet<>();
        ListNode curr = head.next;
        nodes.add(head);
        while (curr != null) {
            if (nodes.contains(curr))
                return true;
            nodes.add(curr);
            curr = curr.next;
        }
        return false;
    }

    // first attempt at challenge:  this works, but is too slow. Editorial solution above.  I _did_ get the critical
    // insight, which feels good!
    public boolean hasCycle1(ListNode head) {
        ListNode curr1 = head;
        int outer = 1;
        while (curr1 != null) {
            ListNode curr2 = curr1.next;
            int inner = outer;
            while (inner > 0) {
                if (curr2 == null)
                    return false;
                if (curr1 == curr2)
                    return true;
                curr2 = curr2.next;
                inner--;
            }
            curr1 = curr1.next;
            outer++;
        }
        return false;
    }
}