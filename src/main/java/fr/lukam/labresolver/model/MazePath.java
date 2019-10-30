package fr.lukam.labresolver.model;

import fr.lukam.labresolver.State;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class MazePath {

    private static final int[][] NEXT_CASES_OPERATIONS
            = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    private final Maze maze;
    private final Point point;
    private final List<Point> parents;

    MazePath(Maze maze, Point point, List<Point> parents) {
        this.maze = maze;
        this.point = point;
        this.parents = parents;
        this.parents.add(this.point);
    }

    public List<Point> computePoint() {

        if (this.point == null) return null;

        boolean isEnd = this.maze.getState(this.point) == State.EXIT
                && this.parents.size() != 1;

        if (isEnd)
            return this.parents;
        else
            return this.continueWay(0);

    }

    private List<Point> continueWay(int index) {

        if (index >= NEXT_CASES_OPERATIONS.length) return null;

        Point nextPoint = new Point(
                this.point.x + NEXT_CASES_OPERATIONS[index][0],
                this.point.y + NEXT_CASES_OPERATIONS[index][1]);

        List<Point> list1 = null;
        List<Point> list2 = this.continueWay(++index);

        boolean canGo = this.maze.isSet(nextPoint)
                && this.maze.getState(point) != State.WALL
                && !this.parents.contains(nextPoint);

        if (canGo)
            list1 = new MazePath(this.maze, nextPoint, new ArrayList<>(this.parents)).computePoint();

        return list1 != null
                && (list2 == null || list1.size() < list2.size())
                ? list1
                : list2;

    }

}
