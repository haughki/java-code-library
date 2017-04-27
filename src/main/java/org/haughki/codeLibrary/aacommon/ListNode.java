package org.haughki.codeLibrary.aacommon;

/*
Simple programming problems definition from Leetcode -- used in a few of the programming problems.
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) {
        val = x;
    }
    
    public static ListNode buildList(int... vals) {
        if (vals.length < 1)
            return null;
        ListNode start = new ListNode(vals[0]);
        ListNode curr = start;
        for (int i = 1; i < vals.length; i++) {
            curr.next = new ListNode(vals[i]);
            curr = curr.next;
        }
        return start;
    }
}
