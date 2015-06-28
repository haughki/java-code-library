package org.haughki.codeLibrary;

import java.util.Collection;
import java.util.Stack;

public class TypesAndReflection {
    public static void main(String[] args) {
        Class c = TypesAndReflection.class;
        System.out.println("TypesAndReflection.class: " + c);
        Class ic = Collection.class;
        System.out.println("Collection.class: " + ic);
        Class oc = (new TypesAndReflection()).getClass();
        System.out.println("(new TypesAndReflection()).getClass(): " + oc);

        System.out.println();

        Stack s = new Stack();
        s.push("pokey");
        s.push("puppy");

        whoAmI(s);
    }

    private static void whoAmI(Collection s) {
        Class c = s.getClass();
        System.out.println("Derived type: " + c);
    }
}
