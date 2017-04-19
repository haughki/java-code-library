package org.haughki.codeLibrary.collections.tree;


import javax.naming.OperationNotSupportedException;

// from: http://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
public class BTreePrinterTest {

    private static GenericNode<Integer> test1() {
        GenericNode<Integer> root = new GenericNode<>(2);
        GenericNode<Integer> n11 = new GenericNode<>(7);
        GenericNode<Integer> n12 = new GenericNode<>(5);
        GenericNode<Integer> n21 = new GenericNode<>(2);
        GenericNode<Integer> n22 = new GenericNode<>(6);
        GenericNode<Integer> n23 = new GenericNode<>(3);
        GenericNode<Integer> n24 = new GenericNode<>(6);
        GenericNode<Integer> n31 = new GenericNode<>(5);
        GenericNode<Integer> n32 = new GenericNode<>(8);
        GenericNode<Integer> n33 = new GenericNode<>(4);
        GenericNode<Integer> n34 = new GenericNode<>(5);
        GenericNode<Integer> n35 = new GenericNode<>(8);
        GenericNode<Integer> n36 = new GenericNode<>(4);
        GenericNode<Integer> n37 = new GenericNode<>(5);
        GenericNode<Integer> n38 = new GenericNode<>(8);

        root.setLeft(n11);
        root.setRight(n12);

        n11.setLeft(n21);
        n11.setRight(n22);
        n12.setLeft(n23);
        n12.setRight(n24);

        n21.setLeft(n31);
        n21.setRight(n32);
        n22.setLeft(n33);
        n22.setRight(n34);
        n23.setLeft(n35);
        n23.setRight(n36);
        n24.setLeft(n37);
        n24.setRight(n38);

        return root;
    }

    private static GenericNode<Integer> test2() {
        GenericNode<Integer> root = new GenericNode<>(2);
        GenericNode<Integer> n11 = new GenericNode<>(7);
        GenericNode<Integer> n12 = new GenericNode<>(5);
        GenericNode<Integer> n21 = new GenericNode<>(2);
        GenericNode<Integer> n22 = new GenericNode<>(6);
        GenericNode<Integer> n23 = new GenericNode<>(9);
        GenericNode<Integer> n31 = new GenericNode<>(5);
        GenericNode<Integer> n32 = new GenericNode<>(8);
        GenericNode<Integer> n33 = new GenericNode<>(4);

        root.setLeft(n11);
        root.setRight(n12);

        n11.setLeft(n21);
        n11.setRight(n22);

        n12.setRight(n23);
        n22.setLeft(n31);
        n22.setRight(n32);

        n23.setLeft(n33);

        return root;
    }

    public static void main(String[] args) {

        BTreePrinter.printNode(test1());
        //BTreePrinter.printNode(test2());

    }
}

interface Node <T> {
    Node<T> left();
    Node<T> right();
    void setLeft(Node<T> left);
    void setRight(Node<T> right);
    T data();
    void setData(T index);
}

class GenericNode<T extends Comparable<?>> implements Node<T> {
    private Node<T> left, right;
    private T data;

    public GenericNode(T data) {
        this.data = data;
    }

    @Override
    public Node<T> left() {
        return left;
    }

    @Override
    public Node<T> right() {
        return right;
    }

    @Override
    public void setLeft(Node<T> left) {
        this.left = left;
    }

    @Override
    public void setRight(Node<T> right) {
        this.right = right;
    }

    @Override
    public T data() {
        return data;
    }

    @Override
    public void setData(T index) {
        this.data = index;
    }
}