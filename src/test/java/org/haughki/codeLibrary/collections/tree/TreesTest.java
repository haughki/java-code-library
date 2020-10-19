package org.haughki.codeLibrary.collections.tree;

import org.junit.Before;
import org.junit.Test;

public class TreesTest {

    BinNode searchTreeRoot;
    
    @Before
    public void setUp() throws Exception {
        
        // binary search tree
        searchTreeRoot = new BinNode(18);
        searchTreeRoot.left = new BinNode(10);
        searchTreeRoot.left.left = new BinNode(4);
        searchTreeRoot.left.left.left = new BinNode(1);
        searchTreeRoot.left.left.right = new BinNode(6);
        searchTreeRoot.left.right = new BinNode(13);
        searchTreeRoot.right = new BinNode(32);
        searchTreeRoot.right.left = new BinNode(24);
        searchTreeRoot.right.right = new BinNode(43);
        searchTreeRoot.right.left.left = new BinNode(20);
        searchTreeRoot.right.right.left = new BinNode(36);
        searchTreeRoot.right.right.right = new BinNode(50);
    }
    
    @Test
    public void inOrderTraversal() throws Exception {
//        LevelSort levelSort = new LevelSort();
//        levelSort.buildList(searchTreeRoot);
//        levelSort.printTree();
        
        Trees.inOrderTraversal(searchTreeRoot);
        System.out.println();
        Trees.inOrderTraversalDescending(searchTreeRoot);
        System.out.println();
        Trees.preOrderTraversal(searchTreeRoot);
        System.out.println();
        Trees.postOrderTraversal(searchTreeRoot);
    }


}