package com.lukamaret.mazesolver.newVersion.infrastructure;

import com.lukamaret.mazesolver.newVersion.domain.model.Cell;
import com.lukamaret.mazesolver.newVersion.domain.model.CellPosition;

import java.util.HashMap;

public class CellRepository {

    private final HashMap<CellPosition, Cell> cells = new HashMap<>();

    public void set(CellPosition position, Cell cell) {
        cells.put(position, cell);
    }

    public Cell get(CellPosition position) {
        return cells.get(position);
    }

}
