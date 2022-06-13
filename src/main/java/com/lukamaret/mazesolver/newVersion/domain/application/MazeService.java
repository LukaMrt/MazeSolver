package com.lukamaret.mazesolver.newVersion.domain.application;

import com.lukamaret.mazesolver.newVersion.domain.model.Cell;
import com.lukamaret.mazesolver.newVersion.domain.model.CellType;
import com.lukamaret.mazesolver.newVersion.domain.model.CellView;
import com.lukamaret.mazesolver.newVersion.infrastructure.CellRepository;
import com.lukamaret.mazesolver.newVersion.infrastructure.MazeLoader;

import javax.inject.Inject;
import java.awt.*;

public class MazeService {

    private final CellRepository cellRepository;
    private final MazeLoader mazeLoader;

    @Inject
    public MazeService(CellRepository cellRepository, MazeLoader mazeLoader) {
        this.cellRepository = cellRepository;
        this.mazeLoader = mazeLoader;
    }

    public void loadMaze() {
        mazeLoader.load();
        Cell start = cellRepository.getStartCell();
        start.updateDistance(0, start);
    }

    public String[][] getExtendedToStringCell() {

        Cell[][] cells = cellRepository.getAllCells();

        String[][] extendedCells = new String[cells.length][cells[0].length];

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                extendedCells[i][j] = cells[i][j].toExtendedString();
            }
        }

        return extendedCells;
    }

    public CellView[][] getCellsDescriptions() {

        Cell[][] cells = cellRepository.getAllCells();

        CellView[][] extendedCells = new CellView[cells.length][cells[0].length];

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                Cell cell = cells[i][j];
                Color color = cell.getColor();
                if (cell.is(CellType.EMPTY) && cell.getDistance() < 1_000_000) {
                    color = getColor(cell);
                }
                extendedCells[i][j] = new CellView(cell.toString().charAt(0), color);
            }
        }

        return extendedCells;
    }

    private Color getColor(Cell cell) {
        int start = 100;
        int red = 0;
        int green = 0;
        int blue = Math.max(start, 255 - cell.getDistance());

        if (cell.getDistance() > 255 - start) {
            red = Math.min(cell.getDistance() - (255 - start), 255);
        }

        if (cell.getDistance() > 2 * 255 - start) {
            green = Math.min(cell.getDistance() - (2 * 255 - start), 255);
        }

        if (cell.getDistance() > 3 * 255 - start) {
            red = Math.max(0, 255 - cell.getDistance() + (3 * 255 - start));
        }

        if (cell.getDistance() > 4 * 255 - start) {
            blue = Math.min(start, start + cell.getDistance() - (4 * 255 - start));
        }

        return new Color(red, green, blue);
    }

}
