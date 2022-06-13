package com.lukamaret.mazesolver.newVersion.domain.model;

import java.awt.*;
import java.util.Vector;

public class Cell {

    private final CellPosition position;
    private CellType type;
    private Distance distance;

    public Cell(CellPosition position, CellType type) {
        this.position = position;
        this.type = type;
        this.distance = Distance.defaultDistance();
    }

    public static Cell defaultCell() {
        return new Cell(CellPosition.empty(), CellType.WALL);
    }

    public boolean is(CellType type) {
        return this.type == type;
    }

    public boolean isNotVisited() {
        return !distance.visited();
    }

    public int getDistance() {
        return distance.distance();
    }

    public void visit() {
        distance = distance.visit();
    }

    public CellPosition addVectorToPosition(Vector<Integer> direction) {
        return position.addVector(direction);
    }

    public void updateDistance(int distance, Cell previous) {
        this.distance = this.distance.update(distance, previous);
    }

    public boolean isCloser(Cell cell) {
        return this.distance.isCloser(cell.distance.add(1));
    }

    public Cell getPrevious() {
        return distance.previous();
    }

    public String printPosition() {
        return position.toString();
    }

    public void flag() {
        type = CellType.PATH;
    }

    @Override
    public String toString() {
        return type.toString();
    }

    public String toExtendedString() {
        String previous = distance.previous() != null ? String.format("%1$5s", distance.previous().position.toString()) : "";
        return "[" + type.toString() + "(" + String.format("%1$3s", distance.distance()) + ", " + previous + ")" + "]";
    }

    public Color getColor() {
        return type.getColor();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return position.equals(cell.position);
    }

}
