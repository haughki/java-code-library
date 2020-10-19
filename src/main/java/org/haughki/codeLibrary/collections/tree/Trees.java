package org.haughki.codeLibrary.collections.tree;


public class Trees {
    
    

    static int level = 0;
    static void inOrderTraversal(BinNode node) {
        level++;
        if (node != null) {
            inOrderTraversal(node.left);
            node.depth = level;
            node.visit();
            inOrderTraversal(node.right);
        }
        level--;
    }

    static void inOrderTraversalDescending(BinNode node) {
        if (node != null) {
            inOrderTraversalDescending(node.right);
            node.visit();
            inOrderTraversalDescending(node.left);
        }
    }

    static void preOrderTraversal(BinNode node) {
        if (node != null) {
            node.visit();
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    static void postOrderTraversal(BinNode node) {
        if (node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            node.visit();
        }
    }
}
