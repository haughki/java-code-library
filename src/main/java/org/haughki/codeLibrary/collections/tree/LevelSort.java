package org.haughki.codeLibrary.collections.tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LevelSort {

    int depth = 0;
    int maxDepth = 0;
    List<BinNode> nodes = new ArrayList<>();
    void buildList(BinNode node) {
        depth++;
        if (depth > maxDepth)
            maxDepth = depth;
        if (node != null) {
            buildList(node.left);
            node.depth = depth;
            nodes.add(node);
            buildList(node.right);
        } else {
            BinNode nullNode = new BinNode(-1);
            nullNode.depth = depth;
            nodes.add(nullNode);
        }
        depth--;
    }
    
    void printTree() {
        int printDepth = 1;
        int numInRow = 1;
        System.out.println("depth: " + maxDepth);
        int totalNodes = (int)Math.pow(2, maxDepth) - 1;
        System.out.println("num nodes: " + totalNodes);
        int bottomNodes = (totalNodes + 1) / 2;
        int bottomWidth = bottomNodes + ((bottomNodes / 2) * 3) + ((bottomNodes / 2) -1);
        //System.out.println(bottomNodes);

        //nodes.stream().sorted(Comparator.comparing(BNode::getDepth))
        nodes.sort(Comparator.comparing(BinNode::getDepth));
        
        for (BinNode node: nodes) {
            if (node.depth == printDepth) {
                System.out.print(toPrint(node.val, bottomWidth, numInRow));
            }
            else {
                System.out.println();
                printDepth++;
                numInRow *= 2;
                System.out.print(toPrint(node.val, bottomWidth, numInRow));
            }
        }
    }

    private String toPrint(int val, int bottomWidth, int numInRow) {
        //boolean wideVal = val > 9;
        String printVal = val != -1 ? String.valueOf(val) : "x";
        int numSpaces = (bottomWidth + 3) / (numInRow + 1);
        //numSpaces = wideVal ? numSpaces - 1 : numSpaces;
        return buildSpaces(numSpaces) + printVal;
    }
    
    private String buildSpaces(int numSpaces) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numSpaces; i++)
            sb.append(" ");
        return sb.toString();
    }
}
