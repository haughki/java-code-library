package org.haughki.codeLibrary.collections.tree;

import java.util.*;

public class MyGraph<T> {
    class GNode {
        class SearchState {
            boolean reset = false;
            boolean visited = false;
            List<GNode> path = new ArrayList<>();
        }
        T val;
        List<GNode> adjacent = new ArrayList<>();
        SearchState searchState;
        
        GNode(T val) {
            this.val = val;
        }
        
        void addAdjacent(List<GNode> toAdd) {
            toAdd.forEach(n -> this.adjacent.add(n));
            this.adjacent.forEach(n -> n.adjacent.add(this));
        }
    }
    
    private Queue<GNode> searchQueue = new ArrayDeque<>();
    private Set<GNode> visited = new HashSet<>();
    private boolean depthSearchFinished = false;
    
    GNode createNode(T val) {
        return new GNode(val);
    }

    void findPath(GNode node1, GNode node2) {

    }

    void breadthSearch(GNode startNode, T searchTarget) {
        System.out.println("Searching for: " + searchTarget);
        visited.add(startNode);
        searchQueue.add(startNode);
        while(!searchQueue.isEmpty()) {
            GNode current = searchQueue.remove();
            System.out.println(current.val);
            if (current.val.equals(searchTarget)) {
                System.out.println("Found target: " + current.val);
                break;
            }
            current.adjacent.forEach(n -> {
                if (!visited.contains(n)) {
                    visited.add(n);
                    searchQueue.add(n);
                }
            });
        }
        // reset tracker
        visited = new HashSet<>();
    }
    
//    void depthSearch(Node node, final T searchTarget) {
//        if(!visited.contains(node)){
//            visited.add(node);
//            System.out.println(node.val);
//            if (node.val.equals(searchTarget)) {
//                System.out.println("Found target: " + node.val);
//                depthSearchFinished = true;
//                return;
//            }
//            for (Node n: node.adjacent) {
//                depthSearch(n, searchTarget);
//                if (depthSearchFinished)
//                    return;
//            }
//        }
//    }

    T depthSearch(GNode node, final T searchTarget) {
        if (node == null) return null;
        visited.add(node);
        System.out.println(node.val);
        if (node.val.equals(searchTarget))
            return node.val;
        for(GNode n : node.adjacent) {
            T ret;
            if (!visited.contains(n)) {
                ret = depthSearch(n, searchTarget);
                if (ret != null)
                    return ret;
            }
        }
        return null;
    }
}




