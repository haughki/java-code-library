package org.haughki.codeLibrary.programmingProblems;

/*
Invert a binary tree.

     4
   /   \
  2     7
 / \   / \
1   3 6   9
to
     4
   /   \
  7     2
 / \   / \
9   6 3   1
 
*/

public class InvertBinaryTree {
    public static void main(String[] args) {
        InvTreeNode root = new InvTreeNode(4);
        root.left = new InvTreeNode(2);
        root.left.left = new InvTreeNode(1);
        root.left.right = new InvTreeNode(3);
        root.right = new InvTreeNode(7);
        root.right.left = new InvTreeNode(6);
        root.right.right = new InvTreeNode(9);
        
        Inverter i = new Inverter();
        InvTreeNode nr = i.invertTree(root);
        walk(nr);
    }
    
    private static void walk(InvTreeNode node) {
        if (node == null)
            return;
        walk(node.left);
        System.out.println(node.val);
        walk(node.right);
    }
}

class InvTreeNode {
    int val;
    InvTreeNode left;
    InvTreeNode right;
    InvTreeNode(int x) { val = x; }
}

class Inverter {

    public InvTreeNode invertTree(InvTreeNode root) {
        preOrder(root);
        return root;
    }
    
    private void preOrder(InvTreeNode node) {
        if (node == null)
            return;
        InvTreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        preOrder(node.left);
        preOrder(node.right);
    }
}
