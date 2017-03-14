package org.haughki.codeLibrary.aacommon;

import java.util.Objects;

// Theoretical coordinate in a 2d array (2d array is just the model for this coordinate system, not actually implemented).
public class Coordinate {
    public final int row;
    public final int col;
    public final int leftRight;
    public final int rightLeft;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
        this.leftRight = row - col;
        this.rightLeft = row + col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.row, this.col);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coordinate))
            return false;
        Coordinate test = (Coordinate)o;
        if (this.row != test.row)
            return false;
        if (this.col != test.col)
            return false;
        return true;
    }
}
