package org.haughki.codeLibrary.collections;


public class MyLinkedList<T> {
    class Node {
        T value;
        Node next;

        Node(T value){
            this.value = value;
        }
    }
    
    Node head;
    Node tail;
    
    void add(T newValue) {
        Node newNode = new Node(newValue);
        if (tail != null) {
            tail.next = newNode;
            tail = newNode;
        } else {
            head = newNode;
            tail = head;
        }
    }
    
    T poll() {
        if (head == null)
            return null;
        T ret = head.value;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return ret;
    }
}
