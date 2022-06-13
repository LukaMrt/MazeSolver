package com.lukamaret.mazesolver.newVersion.view;

import com.lukamaret.mazesolver.newVersion.domain.model.CellView;

import javax.inject.Inject;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static com.lukamaret.mazesolver.newVersion.view.components.LabelBuilder.aLabel;
import static com.lukamaret.mazesolver.newVersion.view.components.PanelBuilder.aPanel;

public class SwingView {

    private final MazeSolverFrame frame;

    @Inject
    public SwingView(MazeSolverFrame frame) {
        this.frame = frame;
    }

    public void printMaze(CellView[][] cells) {

        List<JPanel> panels = new ArrayList<>();

        for (CellView[] cellRow : cells) {
            for (CellView cellView : cellRow) {
                panels.add(aPanel()
                        .withBackground(cellView.color())
                        .build());
            }
        }

        JPanel panel = aPanel()
                .withGridLayout(cells.length, cells[0].length)
                .addAll(panels)
                .build();

        frame.setContentPane(aPanel()
                .withYBoxLayout()
                .addRigidArea(0, 10)
                .add(aLabel().withText("Maze Solver").isBigTitle().isXCentered().build())
                .addRigidArea(0, 10)
                .add(aPanel()
                        .withXBoxLayout()
                        .addRigidArea(10, 0)
                        .add(panel)
                        .addRigidArea(10, 0)
                        .build())
                .addRigidArea(0, 10)
                .build());

        ((JPanel) this.frame.getContentPane()).updateUI();
    }

}
