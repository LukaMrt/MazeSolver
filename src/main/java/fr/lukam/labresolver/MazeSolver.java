package fr.lukam.labresolver;

import fr.lukam.labresolver.model.Maze;
import fr.lukam.labresolver.model.MazePath;
import fr.lukam.labresolver.model.MazeWriter;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class MazeSolver {

    private Maze maze;

    MazeSolver(String mazePath) {

        URL mazeUrl = this.getClass().getClassLoader().getResource(mazePath);

        if (mazeUrl != null) {
            this.maze = new Maze(new File(mazeUrl.getFile()));
        }

    }

    boolean canSolve() {
        return this.maze != null;
    }

    void solve() {

        double start = System.nanoTime();
        List<Point> solution = new MazePath(maze, maze.getStart(), new ArrayList<>()).computePoint();
        double end = System.nanoTime();

        System.out.println();

        MazeWriter.write(maze, solution);

        System.out.println();
        System.out.println((end - start) / 1_000_000_000 + " seconds");
        System.out.println((end - start) / 1_000_000 + " ms");

    }

}
