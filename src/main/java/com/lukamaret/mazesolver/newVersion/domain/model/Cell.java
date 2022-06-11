package com.lukamaret.mazesolver.newVersion.domain.model;

public class Cell {

    private final CellType type;
    private Distance distance;

    public Cell(CellType type) {
        this.type = type;
        this.distance = new Distance(Integer.MAX_VALUE, new CellPosition(-1, -1));
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return type.toString();
    }

}
