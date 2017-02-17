package org.haughki.codeLibrary.programmingProblems;


import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.

Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.

Critical insight:  it seems like, to know the min value for the current stack, we need to store all of the values, sorted.
Then, when we pop, we need to remove the correct value from the sorted list.  But, we don't need to do that.  All we need
to know is:  for any head of the stack, what is the min value for that stack.  So, for each node in the stack, we just
need to store the min value _up to that point_. I.e., we don't care about anything other than the min value, so we can
lose the rest of the values.  So, every time we add a new node, compare it to the min value of the current head (which is
the min value _up to that point_). If it's less, the new node's value become the new head min value; else, use the previous
head's min value.
 */

public class MinStackProblem {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
}


class MinStack {

    private class StackNode {
        int val;
        int min;
        StackNode next;
        StackNode(int x, StackNode next) { 
            val = x;
            if (next == null)
                this.min = x;
            else
                this.min = Math.min(x, next.min);  // here's the trick: store the min up-till-now in the head.
            this.next = next;
        }
    }

    private StackNode head;
    
    
    public MinStack() {
    }

    public void push(int x) {
        StackNode newNode = new StackNode(x, head);
        head = newNode;
    }

    public void pop() {
        if (head != null)
            head = head.next;
    }

    public int top() {
        if (head != null)
            return head.val;
        throw new IllegalStateException();
    }

    public int getMin() {
        if (head != null)
            return head.min;
        throw new IllegalStateException();
    }
}