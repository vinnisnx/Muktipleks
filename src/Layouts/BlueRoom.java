package Layouts;

import Model.Seat;

public class BlueRoom {
    public static Seat[][] buildGrid() {
        Seat[][] grid = new Seat[15][26];
        for (int i = 0; i < 5; i ++) {
            grid[i] = traverse(16, "Close", 70);
        }
        for (int i = 5; i < 13; i++) {
            grid[i] = traverse(22, "Standard", 100);
        }
        for (int i = 10; i < 12; i++ ) {
            correction(grid[i], 7, 15, "VIP", 150);
        }
        for (int i = 12; i < 14; i++) {
            grid[i] = traverse(22, "Comfort", 120);
        }
        grid[14] = traverse(26, "Sofa", 140);
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
