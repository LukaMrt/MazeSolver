package com.lukamaret.mazesolver.newVersion.infrastructure;

import com.lukamaret.mazesolver.newVersion.domain.model.Cell;
import com.lukamaret.mazesolver.newVersion.domain.model.CellPosition;
import com.lukamaret.mazesolver.newVersion.domain.model.CellType;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

@Singleton
public class MazeLoader {

    private final CellRepository cellRepository;
    private final FilePath filePath;

    @Inject
    public MazeLoader(CellRepository cellRepository, FilePath filePath) {
        this.cellRepository = cellRepository;
        this.filePath = filePath;
    }

    public void load() {

        URL resource = this.getClass().getResource(filePath.filePath());

        if (resource == null) {
            return;
        }

        File file = new File(resource.getFile());

        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void load(BufferedReader reader) throws IOException {
        int row = 0;
        String line;

        while ((line = reader.readLine()) != null) {
            loadRow(row++, line);
        }
    }

    private void loadRow(int row, String line) {
        for (int column = 0; column < line.length(); column++) {
            CellPosition position = new CellPosition(row, column);
            cellRepository.set(position, new Cell(position, CellType.of(line.charAt(column))));
        }
    }

}
