package fr.lukam.labresolver.model;

import fr.lukam.labresolver.State;

import java.awt.Point;

class StartSearcher {

    private static State[][] cases;
    private static Point start;

    static Point search(State[][] cases) {

        StartSearcher.cases = cases;

        searchInLine(0);

        return start;
    }

    private static void searchInLine(int lineIndex) {

        if (cases.length <= lineIndex) return;

        searchInColumn(lineIndex, 0);

        searchInLine(++lineIndex);
    }

    private static void searchInColumn(int lineIndex, int columnIndex) {

        if (cases[lineIndex].length <= columnIndex) return;

        if (cases[lineIndex][columnIndex] == State.EXIT)
            start = new Point(lineIndex, columnIndex);

        searchInColumn(lineIndex, ++columnIndex);

    }

}
