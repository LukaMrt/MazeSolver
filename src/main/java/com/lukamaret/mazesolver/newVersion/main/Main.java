package com.lukamaret.mazesolver.newVersion.main;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.lukamaret.mazesolver.newVersion.domain.application.DistanceService;
import com.lukamaret.mazesolver.newVersion.domain.application.MazeService;
import com.lukamaret.mazesolver.newVersion.domain.application.PathService;
import com.lukamaret.mazesolver.newVersion.infrastructure.MazeDimension;
import com.lukamaret.mazesolver.newVersion.main.guice.DefaultInjector;
import com.lukamaret.mazesolver.newVersion.view.TerminalView;

public class Main {

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new DefaultInjector(new MazeDimension(61, 61)));
        MazeService mazeService = injector.getInstance(MazeService.class);
        DistanceService distanceService = injector.getInstance(DistanceService.class);
        TerminalView view = injector.getInstance(TerminalView.class);
        PathService pathService = injector.getInstance(PathService.class);

        mazeService.loadMaze();

        while (!distanceService.computeStep()) {
        }

        pathService.computeShortestPath();
        view.printMaze(mazeService.getCellsDescriptions());

    }

}
