package com.lukamaret.mazesolver.newVersion.view.javafx;

import javafx.application.Application;
import javafx.stage.Stage;

public class JAvaFxFrame extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Maze Solver");
        primaryStage.setWidth(1000);
        primaryStage.setHeight(900);
        primaryStage.show();
    }

}
