package com.lukamaret.mazesolver.newVersion.domain.application;

import com.lukamaret.mazesolver.newVersion.domain.model.Cell;
import com.lukamaret.mazesolver.newVersion.domain.model.CellPosition;
import com.lukamaret.mazesolver.newVersion.domain.model.CellType;
import com.lukamaret.mazesolver.newVersion.domain.model.CellView;
import com.lukamaret.mazesolver.newVersion.infrastructure.CellRepository;
import com.lukamaret.mazesolver.newVersion.infrastructure.MazeLoader;

import javax.inject.Inject;
import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MazeService {

    private static final Random RANDOM = new Random();

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

    public void setUpCreation(int width, int height) {
        width = formatBound(width);
        height = formatBound(height);

        cellRepository.setLineCount(height);
        cellRepository.setColumnCount(width);

        for (int i = 0; i < width; i++) {
            CellPosition position = new CellPosition(0, i);
            cellRepository.set(position, new Cell(position, CellType.WALL));
            position = new CellPosition(height - 1, i);
            cellRepository.set(position, new Cell(position, CellType.WALL));
        }

        for (int i = 1; i < height; i++) {
            for (int j = 0; j < width; j++) {
                CellPosition position = new CellPosition(i, j);
                CellType type = i % 2 == 1 && j % 2 == 1 ? CellType.EMPTY : CellType.WALL;
                cellRepository.set(position, new Cell(position, type));
            }
        }

        CellPosition startPosition = new CellPosition(1, 0);
        cellRepository.set(startPosition, new Cell(startPosition, CellType.START));
        Cell start = cellRepository.get(startPosition);
        start.updateDistance(0, start);

        CellPosition endPosition = new CellPosition(height - 2, width - 1);
        cellRepository.set(endPosition, new Cell(endPosition, CellType.END));

        cellRepository.getAllCellsAsList().stream()
                .filter(cell -> cell.is(CellType.EMPTY))
                .forEach(cell -> cell.affectGroup(new Color(RANDOM.nextInt(0xFFFFFF))));
    }

    private int formatBound(int bound) {
        return Math.max(3, bound % 2 == 0 ? bound + 1 : bound);
    }

    public boolean creationStep(boolean utils) {

        Cell wall = cellRepository.getAllCellsAsList().stream()
                .filter(cell -> cell.is(CellType.WALL))
                .filter(cell -> cell.getPosition().isNotBorder(cellRepository.getLineCount(), cellRepository.getColumnCount()))
                .collect(toEagerShuffledStream())
                .findAny()
                .orElse(null);

        if (wall == null) {
            return false;
        }

        CellPosition wallPosition = wall.getPosition();

        Cell cell1 = cellRepository.get(wallPosition.addVector(new Vector<>(List.of(-1, 0))));
        Cell cell2 = cellRepository.get(wallPosition.addVector(new Vector<>(List.of(1, 0))));
        Cell cell3 = cellRepository.get(wallPosition.addVector(new Vector<>(List.of(0, -1))));
        Cell cell4 = cellRepository.get(wallPosition.addVector(new Vector<>(List.of(0, 1))));

        List<Cell> cells = Stream.of(cell1, cell2, cell3, cell4)
                .filter(cell -> cell.is(CellType.EMPTY))
                .toList();
        Cell reference = cells
                .stream()
                .findAny()
                .orElse(null);

        if (reference == null) {
            return false;
        }

        if (!utils) {
            wall.updateType(CellType.EMPTY);
            wall.affectGroup(reference);
            return true;
        }


        if (cells.stream().allMatch(reference::isInSameGroup)) {
            return false;
        }

        wall.updateType(CellType.EMPTY);
        wall.affectGroup(reference);

        cellRepository.getAllCellsAsList().stream()
                .filter(cell -> cells.stream().anyMatch(cell::isInSameGroup))
                .toList()
                .forEach(cell -> cell.affectGroup(reference));

        return cellRepository.getAllCellsAsList().stream()
                .filter(cell -> cell.is(CellType.EMPTY))
                .allMatch(cell -> cell.isInSameGroup(reference));
    }

    public static <T> Collector<T, ?, Stream<T>> toEagerShuffledStream() {
        return Collectors.collectingAndThen(
                toList(),
                list -> {
                    Collections.shuffle(list);
                    return list.stream();
                });
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

    public CellView[][] getCellsView() {

        Cell[][] cells = cellRepository.getAllCells();

        CellView[][] extendedCells = new CellView[cells.length][cells[0].length];

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                Cell cell = cells[i][j];
                Color color = cell.getColor();
                if (cell.is(CellType.EMPTY) && cell.getDistance() < 1_000_000) {
                    color = cell.getDistanceColor();
                }
                extendedCells[i][j] = new CellView(cell.getPosition(), cell.toString().charAt(0), color);
            }
        }

        return extendedCells;
    }

    public void setEmptyColors(Color color) {
        cellRepository.getAllCellsAsList().stream()
                .filter(cell -> cell.is(CellType.EMPTY))
                .forEach(cell -> cell.affectGroup(color));
    }

}
