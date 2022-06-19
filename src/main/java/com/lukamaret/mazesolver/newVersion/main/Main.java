package com.lukamaret.mazesolver.newVersion.main;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.lukamaret.mazesolver.newVersion.domain.application.DistanceService;
import com.lukamaret.mazesolver.newVersion.domain.application.MazeService;
import com.lukamaret.mazesolver.newVersion.domain.application.PathService;
import com.lukamaret.mazesolver.newVersion.infrastructure.FileName;
import com.lukamaret.mazesolver.newVersion.main.guice.DefaultInjector;
import com.lukamaret.mazesolver.newVersion.view.swing.SwingView;

import java.awt.*;


public class Main {

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new DefaultInjector(new FileName("61x61.txt")));
        MazeService mazeService = injector.getInstance(MazeService.class);
        DistanceService distanceService = injector.getInstance(DistanceService.class);
        SwingView view = injector.getInstance(SwingView.class);
        PathService pathService = injector.getInstance(PathService.class);

        int size = 30;

        mazeService.setUpCreation(size, size);
        view.printMaze(mazeService.getCellsView());

        boolean run;
        do {
            run = mazeService.creationStep(true);
            view.printMaze(mazeService.getCellsView());
        } while (!run);

        for (int i = 0; i < size; i++) {
            mazeService.creationStep(false);
            view.printMaze(mazeService.getCellsView());
        }

//        mazeService.loadMaze();
        view.printMaze(mazeService.getCellsView());
        mazeService.setEmptyColors(Color.WHITE);

        while (!distanceService.computeStep()) {
            view.printMaze(mazeService.getCellsView());
        }

        pathService.computeShortestPath();
        view.printMaze(mazeService.getCellsView());

    }

}
