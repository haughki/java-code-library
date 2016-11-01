package org.haughki.codeLibrary.generics;

import org.haughki.codeLibrary.aacommon.hierarchy.AquaticAnimal;
import org.haughki.codeLibrary.aacommon.hierarchy.AquaticMammal;
import org.haughki.codeLibrary.aacommon.hierarchy.HumpbackWhale;
import org.haughki.codeLibrary.aacommon.hierarchy.Whale;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class FunctionalSignatures {
    public static void main(String[] args) {
        Map<String, AquaticMammal> whales = new HashMap<>();
        whales.put("first", new AquaticMammal());
        whales.put("second", new Whale());
        whales.put("third", new AquaticMammal());
        
        whales.replaceAll((Object k, AquaticAnimal v) -> {
            if (k.toString().compareTo("joker") > 0) {
                System.out.println("One joker is: " + k + ": but can it hold it's breath? " + v.CanHoldBreath());
                return new HumpbackWhale();
            }
            
            if (v instanceof AquaticMammal)
                return (AquaticMammal)v;
            
            return new Whale();
        });
        
        whales.entrySet().stream().filter(e -> e.getValue() instanceof HumpbackWhale)
                .forEach(e -> {
                    System.out.println("I am: " + e.getKey() + " and I do: ");
                    ((HumpbackWhale)e.getValue()).SlapPectoralFin();
                });

        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.offer("uno");
        linkedList.offer("dos");
        linkedList.offer("tres");

        for (int i = 0; i < linkedList.size(); i++) {
            String ohDamn = linkedList.get(i);
            System.out.println(ohDamn);
        }
    }
}
