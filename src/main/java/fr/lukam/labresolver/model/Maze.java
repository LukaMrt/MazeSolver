package fr.lukam.labresolver.model;

import fr.lukam.labresolver.State;

import java.awt.Point;
import java.io.File;
import java.util.List;

public class Maze {

    private State[][] cases;
    private Point start;

    public Maze(File file) {
        this.cases = MazeLoader.loadCases(file);
        this.start = StartSearcher.search(this.cases);
    }

    public Point getStart() {
        return this.start;
    }

    State[] getLine(int line) {
        return this.cases.length > line ? this.cases[line] : null;
    }

    State getState(Point point) {
        return this.cases[point.x][point.y];
    }

    boolean isSet(Point point) {
        return 0 <= point.x && point.x < cases.length
                && 0 <= point.y && point.y < cases[point.x].length;
    }

    void replaceToSolution(List<Point> solution, int index) {

        if (solution.size() <= index) return;

        Point point = solution.get(index);
        if (this.getState(point) != State.EXIT)
            this.cases[point.x][point.y] = State.SOLUTION;

        replaceToSolution(solution, ++index);
    }

}
