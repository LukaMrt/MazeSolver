package com.lukamaret.mazesolver.newVersion.view.swing;

import com.lukamaret.mazesolver.newVersion.domain.model.CellView;

import javax.swing.*;
import java.awt.*;

public class MazePanel extends JPanel {

    private final int height;
    private final int width;
    private final CellView[][] cells;

    public MazePanel(int height, int width, CellView[][] cells) {
        this.height = height;
        this.width = width;
        this.cells = cells;
        this.setPreferredSize(new Dimension(width, height));
        this.setMinimumSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));
        this.setSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {

        for (CellView[] cellRow : cells) {
            int columnsCount = cellRow.length;
            for (CellView cellView : cellRow) {
                g.setColor(cellView.color());
                g.fillRect(cellView.position().column() * (width / columnsCount),
                        cellView.position().line() * (height / cells.length),
                        width / columnsCount + 1,
                        height / cells.length + 1);
            }
        }

    }

}
