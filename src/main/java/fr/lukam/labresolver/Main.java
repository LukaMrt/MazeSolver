package fr.lukam.labresolver;

public class Main {

    public static void main(String[] args) {

        MazeSolver mazeSolver = new MazeSolver("2x4.txt");

        if (!mazeSolver.canSolve()) {
            System.out.println("Failed to load the maze.");
            return;
        }

        mazeSolver.solve();

    }

}
