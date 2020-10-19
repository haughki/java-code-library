package org.haughki.codeLibrary.collections.tree;

import org.junit.Test;

public class TreeBuilderTest {
    @Test
    public void buildTree() throws Exception {
        int[] source = {4, 9, 15, 22};
        TreeBuilder tb = new TreeBuilder();
        BinNode root = new BinNode();
        tb.buildTree(root, source);
        
        Trees.inOrderTraversal(root);

        System.out.println();
        
        LevelSort ls = new LevelSort();
        ls.buildList(root);
        ls.printTree();

        System.out.println();
    }

    @Test
    public void buildTree2() throws Exception {
        int[] source = {4, 8, 16, 19, 25, 29, 38, 40, 47, 59, 85, 90, 99};
        TreeBuilder tb = new TreeBuilder();
        BinNode root = new BinNode();
        tb.buildTree(root, source);

        Trees.inOrderTraversal(root);

        System.out.println();

        LevelSort ls = new LevelSort();
        ls.buildList(root);
        ls.printTree();

        System.out.println();
    }

}