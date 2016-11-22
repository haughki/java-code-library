package org.haughki.codeLibrary.collections.tree;

import java.util.Arrays;

public class TreeBuilder {
    void buildTree(BNode node, final int[] fromArray) {
        if (node == null)
            throw new IllegalArgumentException("Null node.");
        if (fromArray.length == 1) {
            node.val = fromArray[0];
            return;
        }
        int middle = getMiddle(fromArray);
        node.val = fromArray[middle];
        int[] leftArray = Arrays.copyOfRange(fromArray, 0, middle);
        node.left = new BNode();
        buildTree(node.left, leftArray);
        int[] rightArray = Arrays.copyOfRange(fromArray, middle + 1, fromArray.length);
        if (rightArray.length < 1)
            return;
        node.right = new BNode();
        buildTree(node.right, rightArray);
    }

    private int getMiddle(int[] fromArray) {
        int middle;
        if ((fromArray.length % 2) == 1)
            middle = (fromArray.length - 1) / 2;
        else
            middle = fromArray.length / 2;
        
        return middle;
    }
}
