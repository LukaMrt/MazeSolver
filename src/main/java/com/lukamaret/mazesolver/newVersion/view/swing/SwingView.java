package com.lukamaret.mazesolver.newVersion.view.swing;

import com.lukamaret.mazesolver.newVersion.domain.model.CellView;

import javax.inject.Inject;
import javax.swing.*;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import static com.lukamaret.mazesolver.newVersion.view.swing.components.LabelBuilder.aLabel;
import static com.lukamaret.mazesolver.newVersion.view.swing.components.PanelBuilder.aPanel;

public class SwingView {

    private final MazeSolverFrame frame;
    private CellView[][] cells;

    @Inject
    public SwingView(MazeSolverFrame frame) {
        this.frame = frame;
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                printMaze(cells);
            }
        });
    }

    public void printMaze(CellView[][] cells) {

        this.cells = cells;

        int height = Double.valueOf(frame.getHeight() * 0.86).intValue();
        int width = Double.valueOf(frame.getWidth() * 0.99).intValue();

        frame.setContentPane(aPanel()
                .withYBoxLayout()
                .add(aPanel()
                        .withYBoxLayout()
                        .withSize(frame.getWidth(), Double.valueOf(frame.getHeight() * 0.1).intValue())
                        .addVerticalGlue()
                        .add(aLabel().withText("Maze Solver").isBigTitle().isXCentered().build())
                        .addVerticalGlue()
                        .build())
                .add(aPanel()
                        .withXBoxLayout()
                        .addHorizontalGlue()
                        .add(new MazePanel(height, width, cells))
                        .addHorizontalGlue()
                        .build())
                .build());

        ((JPanel) this.frame.getContentPane()).updateUI();
    }

}
