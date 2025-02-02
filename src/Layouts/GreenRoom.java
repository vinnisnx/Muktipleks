package Layouts;

import Model.Seat;

public class GreenRoom {
    public static Seat[][] buildGrid() {
        Seat[][] grid = new Seat[10][14];
        for (int i = 0; i < 3; i ++) {
            grid[i] = traverse(12, "Close", 70);
        }
        for (int i = 3; i < 9; i++) {
            grid[i] = traverse(12, "Standard", 100);
        }
        for (int i = 6; i < 8; i++ ) {
            correction(grid[i], 4, 8, "VIP", 150);
        }
        grid[9] = traverse(14, "Comfort", 120);
        return grid;
    }

    private static Seat[] traverse(int end, String tag, int modifier) {
        Seat[] row = new Seat[end];
        for (int i = 0; i < end; i++) {
            row[i] = new Seat(tag, modifier);
        }
        return row;
    }

    public static void correction(Seat[] seat, int start, int end, String tag, int modifier) {
        for (int i = start; i < end; i ++) {
            seat[i] = new Seat(tag, modifier);
        }
    }
}
