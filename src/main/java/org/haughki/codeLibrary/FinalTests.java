package org.haughki.codeLibrary;

import org.haughki.codeLibrary.aacommon.Person;

public class FinalTests {
    public static void main(String[] args) {
        final Person p = new Person();
        p.comment = "durgeon";
        System.out.println(p.comment);
    }
}
