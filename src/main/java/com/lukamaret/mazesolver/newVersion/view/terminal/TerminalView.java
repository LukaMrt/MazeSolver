package com.lukamaret.mazesolver.newVersion.view.terminal;

public class TerminalView {

    public void printMaze(String[][] cells) {

        for (String[] row : cells) {
            for (String cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }

    }

}
