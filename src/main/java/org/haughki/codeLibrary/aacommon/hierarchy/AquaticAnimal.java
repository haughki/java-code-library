package org.haughki.codeLibrary.aacommon.hierarchy;

public abstract class AquaticAnimal extends Animal {
    public void Swim() {
        System.out.println("Swim swim swimmy, I swim and I swim.");
    }

    public abstract boolean CanHoldBreath();

    public abstract boolean CanDive();

    @Override
    public final boolean CanSwim() {
        return true;
    }
}
