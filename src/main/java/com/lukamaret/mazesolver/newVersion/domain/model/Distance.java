package com.lukamaret.mazesolver.newVersion.domain.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public record Distance(int distance, Cell previous, boolean visited) {

    private static final List<Color> COLORS;

    static {
        COLORS = new ArrayList<>();
        int red = 0;
        int green = 0;
        int blue = 255;

        // RGB : 0, 0, 255

        while (green < 255) {
            COLORS.add(new Color(red, green, blue));
            green += 5;
        }

        // RGB : 0, 255, 255

        while (0 < blue) {
            COLORS.add(new Color(red, green, blue));
            blue -= 5;
        }

        // RGB : 0, 255, 0

        while (red < 255) {
            COLORS.add(new Color(red, green, blue));
            red += 5;
        }

        // RGB : 255, 255, 0

        while (0 < green) {
            COLORS.add(new Color(red, green, blue));
            green -= 5;
        }

        // RGB : 255, 0, 0

        while (blue < 255) {
            COLORS.add(new Color(red, green, blue));
            blue += 5;
        }

        // RGB : 255, 0, 255

        while (0 < red) {
            COLORS.add(new Color(red, green, blue));
            red -= 5;
        }

        // RGB : 0, 0, 255

    }

    public static Distance defaultDistance() {
        return new Distance(1_000_000, null, false);
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

    public Color getColor() {
        return COLORS.get(distance % COLORS.size());
    }

}
