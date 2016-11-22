package org.haughki.codeLibrary.collections.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class MyGraphTest {
    @Test
    public void test() {
        MyGraph<String> mg = new MyGraph<>();
        MyGraph<String>.Node start = mg.createNode("start");
        MyGraph<String>.Node _0 = mg.createNode("0");
        MyGraph<String>.Node _1 = mg.createNode("1");
        MyGraph<String>.Node _2 = mg.createNode("2");
        MyGraph<String>.Node _0_0 = mg.createNode("0.0");
        MyGraph<String>.Node _0_1 = mg.createNode("0.1");
        MyGraph<String>.Node _0_0_0 = mg.createNode("0.0.0");
        MyGraph<String>.Node _0_0_1 = mg.createNode("0.0.1");
        MyGraph<String>.Node _1_0 = mg.createNode("1.0");
        MyGraph<String>.Node _1_1 = mg.createNode("1.1");
        
        start.addAdjacent(new ArrayList<>(Arrays.asList(_0, _1, _2)));
        _0.addAdjacent(new ArrayList<>(Arrays.asList(_0_0, _0_1)));
        _2.addAdjacent(new ArrayList<>(Arrays.asList(_0_0_0)));
        _1.addAdjacent(new ArrayList<>(Arrays.asList(_1_0, _1_1)));

        _0_0.addAdjacent(new ArrayList<>(Arrays.asList(_0_0_0, _0_0_1)));
        _0_1.addAdjacent(new ArrayList<>(Arrays.asList(_0_0_1)));
        
        mg.breadthSearch(start, "");

        System.out.println();
        
        //mg.breadthSearch(_0_0_1, "");
    }

}