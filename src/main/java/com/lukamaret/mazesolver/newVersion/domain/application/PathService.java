package com.lukamaret.mazesolver.newVersion.domain.application;

import com.lukamaret.mazesolver.newVersion.domain.model.Cell;
import com.lukamaret.mazesolver.newVersion.domain.model.Path;
import com.lukamaret.mazesolver.newVersion.infrastructure.CellRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathService {

    private final CellRepository cellRepository;

    @Inject
    public PathService(CellRepository cellRepository) {
        this.cellRepository = cellRepository;
    }

    public Path computeShortestPath() {

        Cell start = cellRepository.getStartCell();
        Cell end = cellRepository.getEndCell();

        List<Cell> path = new ArrayList<>();

        Cell current = end;
        path.add(current);
        while (!start.equals(current = current.getPrevious())) {
            path.add(current);
        }

        path.add(start);
        Collections.reverse(path);
        path.forEach(Cell::flag);

        return new Path(path);
    }

}
