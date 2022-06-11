package fr.lukam.labresolver.model;

import fr.lukam.labresolver.State;
import fr.lukam.labresolver.file.MazeLoader;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Maze {

    private State[][] cases;

    public Maze(File file) {
        this.cases = MazeLoader.loadCases(file);
    }

    public List<Point> solve() {
        return new MazePath(this, StartSearcher.search(this.cases), new ArrayList<>()).computePoint();
    }

    public State[] getLine(int line) {
        return this.cases.length > line ? this.cases[line] : null;
    }

    public State getState(Point point) {
        return this.cases[point.x][point.y];
    }

    public boolean isSet(Point point) {
        return 0 <= point.x && point.x < cases.length
                && 0 <= point.y && point.y < cases[point.x].length;
    }

    public void replaceToSolution(List<Point> solution, int index) {

        if (solution.size() <= index) return;

        Point point = solution.get(index);
        if (this.getState(point) != State.EXIT)
            this.cases[point.x][point.y] = State.SOLUTION;

        replaceToSolution(solution, ++index);
    }

}
