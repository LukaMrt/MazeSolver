package com.lukamaret.mazesolver.newVersion.domain.model;

import java.util.Vector;

public record CellPosition(int line, int column) {

    public static CellPosition defaultPosition() {
        return new CellPosition(-1, -1);
    }

    public static CellPosition empty() {
        return new CellPosition(0, 0);
    }

    public CellPosition addVector(Vector<Integer> direction) {
        return new CellPosition(line + direction.get(0), column + direction.get(1));
    }

    @Override
    public String toString() {
        return line + " " + column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellPosition that = (CellPosition) o;
        return line == that.line && column == that.column;
    }

}
