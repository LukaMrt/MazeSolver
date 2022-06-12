package com.lukamaret.mazesolver.newVersion.infrastructure;

import com.lukamaret.mazesolver.newVersion.domain.model.Cell;
import com.lukamaret.mazesolver.newVersion.domain.model.CellPosition;
import com.lukamaret.mazesolver.newVersion.domain.model.CellType;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.*;
import java.net.URL;

@Singleton
public class MazeLoader {

    private final CellRepository cellRepository;
    private final FileName fileName;

    @Inject
    public MazeLoader(CellRepository cellRepository, FileName fileName) {
        this.cellRepository = cellRepository;
        this.fileName = fileName;
    }

    public void load() {

        URL url = getClass().getClassLoader().getResource(fileName.fileName());

        if (url == null) {
            return;
        }

        File file = new File(url.getFile());

        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            cellRepository.setLineCount((int) reader.lines().count());
        } catch (IOException e) {
            e.printStackTrace();
        }


        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void load(BufferedReader reader) throws IOException {
        int row = 0;
        String line;

        while ((line = reader.readLine()) != null) {
            cellRepository.setColumnCount(line.length());
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
