package org.haughki.codeLibrary.collections.tree;

class BinNode {
    BinNode(){}
    BinNode(int val) {
        this.val = val;
    }
    int val;
    BinNode left;
    BinNode right;
    int depth;
    
    int getDepth() { return depth; } 
    void visit() {
        System.out.print(this.toString());
    }


    @Override
    public String toString() {
        //return String.valueOf(val) + ":" + String.valueOf(depth) + ", ";
        return String.valueOf(val) + ", ";
    }
}
