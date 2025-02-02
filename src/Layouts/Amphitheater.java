package Layouts;

import Model.Seat;

public class Amphitheater {
    public static Seat[][] buildGrid() {
        Seat[][] grid = new Seat[26][60];
        for (int i = 0; i < 5; i ++) {
            grid[i] = traverse(30, "Super Close", 40);
        }
        for (int i = 5; i < 10; i++) {
            grid[i] = traverse(46, "Close", 70);
        }

        for (int i = 10; i < 25; i++) {
            grid[i] = traverse(60, "Standard", 100);
        }

        for (int i = 20; i < 25; i++) {
            correction(grid[i], 15, 46, "Comfort", 120);
        }


        for (int i = 23; i < 25; i++ ) {
            correction(grid[i], 20, 41, "VIP", 150);
        }

        grid[25] = traverse(30, "VIP+", 230);

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

