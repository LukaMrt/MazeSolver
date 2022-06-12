package com.lukamaret.mazesolver.newVersion.domain.model;

import java.util.Arrays;

public enum CellType {

    EMPTY('0'),
    WALL('1'),
    START('S'),
    END('E'),
    PATH('X');

    private final char aChar;

    CellType(char aChar) {
        this.aChar = aChar;
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
