package org.haughki.codeLibrary.aacommon.hierarchy;

public abstract class Animal extends RealThing {
    public void Live() {
        System.out.println("I am ALIVE!");
    }

    public void Die() {
        System.out.println("Oh crap.");
    }

    public abstract boolean CanSwim();

    @Override
    public final boolean IsAnimate() {
        return true;
    }
}
