package fr.lukam.labresolver;

import fr.lukam.labresolver.model.Maze;
import fr.lukam.labresolver.file.MazeWriter;

import java.awt.*;
import java.io.File;
import java.net.URL;
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

    double solve() {

        double start = System.nanoTime();
        List<Point> solution = this.maze.solve();
        double end = System.nanoTime();

        System.out.println();

        MazeWriter.write(maze, solution);

        return end - start;
    }

}
