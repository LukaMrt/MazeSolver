package com.lukamaret.mazesolver.newVersion.infrastructure;

import com.lukamaret.mazesolver.newVersion.domain.model.Cell;
import com.lukamaret.mazesolver.newVersion.domain.model.CellPosition;
import com.lukamaret.mazesolver.newVersion.domain.model.CellType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MazeLoader {

    private MazeLoader() {
    }

    public static void load(CellRepository cellRepository, String filePath) {

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            load(cellRepository, reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void load(CellRepository cellRepository, BufferedReader reader) throws IOException {
        int row = 0;
        String line;

        while ((line = reader.readLine()) != null) {
            loadRow(cellRepository, row++, line);
        }
    }

    private static void loadRow(CellRepository cellRepository, int row, String line) {
        for (int column = 0; column < line.length(); column++) {
            cellRepository.set(new CellPosition(row, column), new Cell(CellType.of(line.charAt(column))));
        }
    }

}
