package com.lukamaret.mazesolver.newVersion.infrastructure;

import com.lukamaret.mazesolver.newVersion.domain.model.Cell;
import com.lukamaret.mazesolver.newVersion.domain.model.CellPosition;
import com.lukamaret.mazesolver.newVersion.domain.model.CellType;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.List;

@Singleton
public class CellRepository {

    private final Cell[][] cells;

    @Inject
    public CellRepository(MazeDimension dimension) {
        cells = new Cell[dimension.linesCount()][dimension.columnsCount()];
    }

    public void set(CellPosition position, Cell cell) {
        cells[position.line()][position.column()] = cell;
    }

    public Cell get(CellPosition position) {
        if (position.line() < 0 || cells.length <= position.line() || position.column() < 0 || cells[0].length <= position.column()) {
            return null;
        }
        return cells[position.line()][position.column()];
    }

    public List<Cell> getAllCellsAsList() {
        return Arrays.stream(cells)
                .flatMap(Arrays::stream)
                .toList();
    }

    public Cell[][] getAllCells() {
        return cells;
    }

    public Cell getStartCell() {
        return getAllCellsAsList().stream()
                .filter(cell -> cell.is(CellType.START))
                .findFirst()
                .orElse(Cell.defaultCell());
    }

    public Cell getEndCell() {
        return getAllCellsAsList().stream()
                .filter(entry -> entry.is(CellType.END))
                .findFirst()
                .orElse(Cell.defaultCell());
    }

}
