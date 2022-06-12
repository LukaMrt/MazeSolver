package com.lukamaret.mazesolver.newVersion.domain.model;

public record Distance(int distance, Cell previous, boolean visited) {

    public static Distance defaultDistance() {
        return new Distance(1_000, null, false);
    }

    public Distance visit() {
        return new Distance(distance, previous, true);
    }

    public Distance update(int distance, Cell previous) {
        return new Distance(distance, previous, visited);
    }

    public Distance add(int distanceToAdd) {
        return new Distance(distance + distanceToAdd, previous, visited);
    }

    public boolean isCloser(Distance other) {
        return distance < other.distance;
    }

}
