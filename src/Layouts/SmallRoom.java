package Layouts;
import Model.Seat;

public class SmallRoom {
    public static Seat[][] buildGrid() {
        Seat[][] grid = new Seat[7][10];
        for (int i = 0; i < 2; i ++) {
            grid[i] = traverse(8, "Close", 70);
        }
        for (int i = 2; i < 6; i++) {
            grid[i] = traverse(10, "Standard", 100);
        }
        grid[6] = traverse(10, "Sofa", 120);
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
