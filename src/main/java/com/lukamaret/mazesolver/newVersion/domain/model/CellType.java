package com.lukamaret.mazesolver.newVersion.domain.model;

import java.awt.*;
import java.util.Arrays;

public enum CellType {

    EMPTY('0', Color.WHITE),
    WALL('1', Color.BLACK),
    START('S', Color.GREEN),
    END('E', Color.RED),
    PATH('X', Color.WHITE);

    private final char aChar;
    private final Color color;

    CellType(char aChar, Color color) {
        this.aChar = aChar;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return String.valueOf(aChar);
    }

    public static CellType of(char aChar) {
        return Arrays.stream(values())
                .filter(cellType -> cellType.aChar == aChar)
                .findAny()
                .orElse(WALL);
    }

}
