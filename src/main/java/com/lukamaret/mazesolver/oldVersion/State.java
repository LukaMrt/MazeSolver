package com.lukamaret.mazesolver.oldVersion;

import java.util.Arrays;

public enum State {

    EMPTY("0"),
    WALL("1"),
    EXIT("X"),
    SOLUTION(".");

    private final String value;

    State(String value){
        this.value = value;
    }

    public static State of(char value) {
        return Arrays.stream(values())
                .filter(state -> String.valueOf(value).equalsIgnoreCase(state.value))
                .findAny()
                .orElse(State.WALL);
    }

    @Override
    public String toString(){
        return value;
    }

}
