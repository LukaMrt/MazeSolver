package fr.lukam.labresolver.model;

import fr.lukam.labresolver.State;

import java.awt.Point;
import java.util.List;

public class MazeWriter {

    private static Maze maze;

    public static void write(Maze maze, List<Point> solution) {

        MazeWriter.maze = maze;

        writeOriginMaze();
        writeSolution(solution);

    }

    private static void writeOriginMaze() {
        System.out.println("Labyrinthe d'origine :");
        writeLine(0);
    }

    private static void writeSolution(List<Point> solution) {
        if (solution == null) {
            System.out.println("Il n'y a aucun chemin allant du départ à l'arrivée.");
            return;
        }

        maze.replaceToSolution(solution, 0);

        System.out.println("\nSolution :");
        writeLine(0);
    }

    private static void writeLine(int lineIndex) {

        if (maze.getLine(lineIndex) == null) return;

        writeColumn(lineIndex, 0);

        System.out.println();
        writeLine(++lineIndex);
    }

    private static void writeColumn(int lineIndex, int columnIndex) {

        if (maze.getLine(lineIndex).length <= columnIndex) return;
        State state = maze.getLine(lineIndex)[columnIndex];
        if (state == null) return;

        System.out.print(state);

        writeColumn(lineIndex, ++columnIndex);
    }

}
