package org.haughki.codeLibrary.collections.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class MyGraphTest {
    @Test
    public void test() {
        MyGraph<String> mg = new MyGraph<>();
        MyGraph.GNode start = mg.createNode("start");
        MyGraph.GNode _0 = mg.createNode("0");
        MyGraph.GNode _1 = mg.createNode("1");
        MyGraph.GNode _2 = mg.createNode("2");
        MyGraph.GNode _0_0 = mg.createNode("0.0");
        MyGraph.GNode _0_1 = mg.createNode("0.1");
        MyGraph.GNode _0_0_0 = mg.createNode("0.0.0");
        MyGraph.GNode _0_0_1 = mg.createNode("0.0.1");
        MyGraph.GNode _1_0 = mg.createNode("1.0");
        MyGraph.GNode _1_1 = mg.createNode("1.1");
        
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