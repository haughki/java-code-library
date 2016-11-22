package org.haughki.codeLibrary.collections;


public class MyStack<T> {
    class Node {
        T value;
        Node next;

        Node(T value){
            this.value = value;
        }
    }
    
    Node top;

    void push(T newValue) {
        Node newNode = new Node(newValue);
        newNode.next = top;
        top = newNode;
    }
    
    T pop() {
        if (top == null)
            return null;
        T ret = top.value;
        top = top.next;
        return ret;
    }
}
