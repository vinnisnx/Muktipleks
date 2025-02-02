package Layouts;

import Model.Seat;

public class PrivateRoom {
    public static Seat[][] buildGrid() {
        Seat[][] grid = new Seat[6][8];
        for (int i = 0; i < 6; i ++) {
            grid[i] = traverse(8, "VIP+", 100);
        }
        return grid;
    }

    private static Seat[] traverse(int end, String tag, int modifier) {
        Seat[] row = new Seat[end];
        for (int i = 0; i < end; i++) {
            row[i] = new Seat(tag, modifier);
        }
        return row;
    }
}
