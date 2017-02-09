package org.haughki.codeLibrary.programmingProblems;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

/*
Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 */
public class MaxDepthBinaryTree {
    public int maxDepth(TreeNode root) {
        return walk(root, 0);
    }

    private int walk(TreeNode node, int depth) {
        if (node == null)
            return depth;
        depth++;
        int ldepth = walk(node.left, depth);
        int rdepth = walk(node.right, depth);
        if (ldepth >= rdepth)
            return ldepth;
        return rdepth;
    }
}
