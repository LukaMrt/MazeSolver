package com.lukamaret.mazesolver.newVersion.domain.application;

import com.lukamaret.mazesolver.newVersion.domain.model.Cell;
import com.lukamaret.mazesolver.newVersion.infrastructure.CellRepository;
import com.lukamaret.mazesolver.newVersion.infrastructure.MazeLoader;

import javax.inject.Inject;

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

    public String[][] getCellsDescriptions() {

        Cell[][] cells = cellRepository.getAllCells();

        String[][] extendedCells = new String[cells.length][cells[0].length];

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                extendedCells[i][j] = cells[i][j].toString();
            }
        }

        return extendedCells;
    }

}
