package fr.lukam.labresolver;

public class Main {

    public static void main(String[] args) {

        MazeSolver mazeSolver = new MazeSolver("61x61.txt");

        if (!mazeSolver.canSolve()) {
            System.out.println("Failed to load the maze.");
            return;
        }

        double timeToSolve = mazeSolver.solve();

        System.out.println();
        System.out.println(timeToSolve / 1_000_000_000 + " seconds");
        System.out.println(timeToSolve / 1_000_000 + " ms");

    }

}
