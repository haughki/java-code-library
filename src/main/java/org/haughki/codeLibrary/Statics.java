package org.haughki.codeLibrary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statics {

    // How to use a static initializer.
    // May not want to initialize the following inline.  Best,
    // for many reasons, not to init in ctor (it's static, not instance-specific).
    public final static Map<Double, String> labels = new HashMap<>();
    public final static List<String> notes = new ArrayList<>();

    static {
        labels.put(5.5, "five and a half");
        labels.put(7.1, "seven point 1");

        notes.add("Go to shop");
        notes.add("buy wig");
    }

    public static void main(String[] args) {

    }
}
