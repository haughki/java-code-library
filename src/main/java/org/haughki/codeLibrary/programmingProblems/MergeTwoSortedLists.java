package org.haughki.codeLibrary.programmingProblems;

import org.haughki.codeLibrary.aacommon.ListNode;

/*
https://leetcode.com/problems/merge-two-sorted-lists/#/description

Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes 
of the first two lists.

 */
public class MergeTwoSortedLists {
    public static void main(String[] args) {
        MergeLists m = new MergeLists();
        
        ListNode l1 = ListNode.buildList(1,3,5);
        ListNode l2 = ListNode.buildList(2,2,3,4);
        ListNode merged = m.mergeTwoLists(l1, l2);
        
        while (merged != null) {
            System.out.print(merged.val + " ");
            merged = merged.next;
        }

        System.out.println();

        l1 = ListNode.buildList(1);
        l2 = ListNode.buildList();
        merged = m.mergeTwoLists(l1, l2);

        while (merged != null) {
            System.out.print(merged.val + " ");
            merged = merged.next;
        }

        System.out.println();

        l1 = ListNode.buildList();
        l2 = ListNode.buildList(1);
        merged = m.mergeTwoLists(l1, l2);

        while (merged != null) {
            System.out.print(merged.val + " ");
            merged = merged.next;
        }

        System.out.println();

        l1 = ListNode.buildList();
        l2 = ListNode.buildList();
        merged = m.mergeTwoLists(l1, l2);

        while (merged != null) {
            System.out.print(merged.val + " ");
            merged = merged.next;
        }

    }
}


/**
 * This is the definition for a singly-linked list, the definition the solution should use.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class MergeLists {

    // https://discuss.leetcode.com/topic/5513/my-recursive-way-to-solve-this-problem-java-easy-understanding
    // top-voted java solution on leetcode
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }

        ListNode mergeHead;
        if(l1.val < l2.val){
            mergeHead = l1;
            mergeHead.next = mergeTwoLists(l1.next, l2);
        }
        else{
            mergeHead = l2;
            mergeHead.next = mergeTwoLists(l1, l2.next);
        }
        return mergeHead;
    }
    
    // My best effort.  Very clean and fast, but didn't figure out the recursive solution.
    public ListNode mergeTwoListsMYBEST(ListNode l1, ListNode l2) {
        if (l1 == null) {
            if (l2 == null)
                return null;
            return l2;
        } else if (l2 == null)
            return l1;
        
        ListNode prev; // points to the last node that was put properly in order
        
        // Init prev to the lesser of the first values of l1 and l2, and increment whichever one you use.
        if (l1.val < l2.val) {
            prev = l1;
            l1 = l1.next;
        } else {
            prev = l2;
            l2 = l2.next;
        }

        ListNode head = prev;  // save the head of the list the return
        ListNode curr;  // the current least value node we've found
        
        // while neither list is null, find the next smallest value in the list.  Hold that value (curr), and move 
        // that list to the next member.  Then point prev.next to curr (this is the critical step that orders the 
        // objects) and _set_ prev to curr for the next loop.
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr = l1;
                l1 = l1.next;
            } else {
                curr = l2;
                l2 = l2.next;
            }
            prev.next = curr;
            prev = curr;
        }
        
        if (l1 == null)
            prev.next = l2;
        else
            prev.next = l1;
        
        return head;
    }
}
