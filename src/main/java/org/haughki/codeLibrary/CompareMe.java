package org.haughki.codeLibrary;

public class CompareMe implements Comparable<CompareMe> {
    private final int id;
    CompareMe(final int id) {
        this.id = id;
    }
    @Override
    public int compareTo(CompareMe o) {
        //really:  return Integer.compare(this.id, o.id);
        if (this.id == o.id)
            return 0;
        else if (this.id < o.id)
            return -1;
        else
            return 1;
    }
}
