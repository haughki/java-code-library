package org.haughki.codeLibrary.collections.tree;

import org.junit.Before;
import org.junit.Test;

public class TreesTest {

    BNode searchTreeRoot;
    
    @Before
    public void setUp() throws Exception {
        
        // binary search tree
        searchTreeRoot = new BNode(18);
        searchTreeRoot.left = new BNode(10);
        searchTreeRoot.left.left = new BNode(4);
        searchTreeRoot.left.left.left = new BNode(1);
        searchTreeRoot.left.left.right = new BNode(6);
        searchTreeRoot.left.right = new BNode(13);
        searchTreeRoot.right = new BNode(32);
        searchTreeRoot.right.left = new BNode(24);
        searchTreeRoot.right.right = new BNode(43);
        searchTreeRoot.right.left.left = new BNode(20);
        searchTreeRoot.right.right.left = new BNode(36);
        searchTreeRoot.right.right.right = new BNode(50);
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