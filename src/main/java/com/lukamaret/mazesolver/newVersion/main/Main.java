package com.lukamaret.mazesolver.newVersion.main;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.lukamaret.mazesolver.newVersion.domain.application.DistanceService;
import com.lukamaret.mazesolver.newVersion.domain.application.MazeService;
import com.lukamaret.mazesolver.newVersion.domain.application.PathService;
import com.lukamaret.mazesolver.newVersion.infrastructure.FileName;
import com.lukamaret.mazesolver.newVersion.main.guice.DefaultInjector;
import com.lukamaret.mazesolver.newVersion.view.SwingView;
import com.lukamaret.mazesolver.newVersion.view.TerminalView;

public class Main {

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new DefaultInjector(new FileName("41x41.txt")));
        MazeService mazeService = injector.getInstance(MazeService.class);
        DistanceService distanceService = injector.getInstance(DistanceService.class);
        SwingView view = injector.getInstance(SwingView.class);
        PathService pathService = injector.getInstance(PathService.class);

        mazeService.loadMaze();

        while (!distanceService.computeStep()) {
            view.printMaze(mazeService.getCellsDescriptions());
        }

        pathService.computeShortestPath();
        view.printMaze(mazeService.getCellsDescriptions());

    }

}
