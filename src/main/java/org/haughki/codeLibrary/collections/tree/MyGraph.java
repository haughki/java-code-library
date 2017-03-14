package org.haughki.codeLibrary.collections.tree;

import java.util.*;

public class MyGraph<T> {
    class Node {
        class SearchState {
            boolean reset = false;
            boolean visited = false;
            List<Node> path = new ArrayList<>();
        }
        T val;
        List<Node> adjacent = new ArrayList<>();
        SearchState searchState;
        
        Node(T val) {
            this.val = val;
        }
        
        void addAdjacent(List<Node> toAdd) {
            toAdd.forEach(n -> this.adjacent.add(n));
            this.adjacent.forEach(n -> n.adjacent.add(this));
        }
    }
    
    private Queue<Node> searchQueue = new ArrayDeque<>();
    private Set<Node> visited = new HashSet<>();
    private boolean depthSearchFinished = false;
    
    Node createNode(T val) {
        return new Node(val);
    }

    void findPath(Node node1, Node node2) {

    }

    void breadthSearch(Node startNode, T searchTarget) {
        System.out.println("Searching for: " + searchTarget);
        visited.add(startNode);
        searchQueue.add(startNode);
        while(!searchQueue.isEmpty()) {
            Node current = searchQueue.remove();
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

    T depthSearch(Node node, final T searchTarget) {
        if (node == null) return null;
        visited.add(node);
        System.out.println(node.val);
        if (node.val.equals(searchTarget))
            return node.val;
        for(Node n : node.adjacent) {
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




