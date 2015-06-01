package org.haughki.codeLibrary.aacommon.hierarchy;

public class AquaticMammal extends AquaticAnimal {

    @Override
    public final boolean CanHoldBreath() {
        return true;
    }

    @Override
    public final boolean CanDive() {
        return true;
    }

    public void HoldBreath() {
        System.out.println("Holding my breath gives me peace.");
    }

    public void Dive() {
        System.out.println("DIVE! DIVE! DIVE!");
    }
}

