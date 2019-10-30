package fr.lukam.labresolver.file;

import fr.lukam.labresolver.State;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MazeLoader {

    private static State[][] cases;
    private static BufferedReader bufferedReader;

    public static State[][] loadCases(File file) {

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            cases = new State[(int) bufferedReader.lines().count()][];

            bufferedReader = new BufferedReader(new FileReader(file));
            readLine(0);

            bufferedReader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return cases;
    }

    private static void readLine(int lineIndex) throws IOException {

        String fileLine = bufferedReader.readLine();
        if (fileLine == null) return;

        char[] lineArray = fileLine.toCharArray();
        cases[lineIndex] = new State[lineArray.length];

        readColumn(lineArray, new Indexes(lineIndex, 0));
        readLine(++lineIndex);
    }

    private static void readColumn(char[] lineArray, Indexes indexes) {

        if (lineArray.length <= indexes.columnIndex) return;

        cases[indexes.lineIndex][indexes.columnIndex] = State.of(lineArray[indexes.columnIndex]);

        readColumn(lineArray, new Indexes(indexes.lineIndex, indexes.columnIndex + 1));
    }

    private static class Indexes {

        private final int lineIndex;
        private final int columnIndex;

        Indexes(int lineIndex, int columnIndex) {
            this.lineIndex = lineIndex;
            this.columnIndex = columnIndex;
        }

    }

}
