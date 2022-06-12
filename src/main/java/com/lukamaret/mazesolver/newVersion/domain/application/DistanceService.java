package com.lukamaret.mazesolver.newVersion.domain.application;

import com.lukamaret.mazesolver.newVersion.domain.model.Cell;
import com.lukamaret.mazesolver.newVersion.domain.model.CellType;
import com.lukamaret.mazesolver.newVersion.infrastructure.CellRepository;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class DistanceService {

    private static final List<Vector<Integer>> DIRECTIONS = List.of(new Vector<>(List.of(-1, 0)),
            new Vector<>(List.of(1, 0)),
            new Vector<>(List.of(0, -1)),
            new Vector<>(List.of(0, 1)));

    private final CellRepository cellRepository;

    @Inject
    public DistanceService(CellRepository cellRepository) {
        this.cellRepository = cellRepository;
    }

    public boolean computeStep() {

        return cellRepository.getAllCellsAsList()
                .stream()
                .filter(cell -> !cell.is(CellType.WALL))
                .filter(Cell::isNotVisited)
                .sorted(Comparator.comparingInt(Cell::getDistance))
                .limit(1)
                .peek(Cell::visit)
                .peek(cell -> DIRECTIONS.stream()
                        .map(cell::addVectorToPosition)
                        .map(cellRepository::get)
                        .filter(Objects::nonNull)
                        .filter(neighbor -> !neighbor.is(CellType.WALL))
                        .filter(Cell::isNotVisited)
                        .filter(cell::isCloser)
                        .forEach(neighbor -> neighbor.updateDistance(cell.getDistance() + 1, cell)))
                .findFirst()
                .isEmpty();
    }

    public void computeDijkstra() {

        boolean run = false;

        while (!run) {
            run = computeStep();
        }

    }

}
