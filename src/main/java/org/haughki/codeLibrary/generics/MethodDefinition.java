
package org.haughki.codeLibrary.generics;

public class MethodDefinition {
    public static void main(String[] args) {
        TwoTypes("bug", 45);
    }

    // The two "T's" can be different types...
    public static <T> void TwoTypes(T typeOne, T typeTwo) {
        System.out.println(typeOne);
        System.out.println(typeTwo);
    }
}
