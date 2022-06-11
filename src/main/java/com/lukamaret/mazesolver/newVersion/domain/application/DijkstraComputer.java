package com.lukamaret.mazesolver.newVersion.domain.application;

import com.lukamaret.mazesolver.newVersion.domain.model.Cell;
import com.lukamaret.mazesolver.newVersion.domain.model.CellPosition;
import com.lukamaret.mazesolver.newVersion.infrastructure.CellRepository;

public class DijkstraComputer {

    private final CellRepository cellRepository;

    public DijkstraComputer(CellRepository cellRepository) {
        this.cellRepository = cellRepository;
    }

}
