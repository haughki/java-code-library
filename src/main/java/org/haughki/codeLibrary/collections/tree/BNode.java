package org.haughki.codeLibrary.collections.tree;

class BNode {
    BNode(){}
    BNode(int val) {
        this.val = val;
    }
    int val;
    BNode left;
    BNode right;
    int depth;
    
    int getDepth() { return depth; } 
    void visit() {
        System.out.print(this.toString());
    }


    @Override
    public String toString() {
        return String.valueOf(val) + ":" + String.valueOf(depth) + ", ";
    }
}
