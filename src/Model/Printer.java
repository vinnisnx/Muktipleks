package Model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Printer {
    public static void universalSeparator() {
        System.out.println();
    }

    public static void printCinemaDetails(Cinema cinema) {
        System.out.println("Cinema id: " + cinema.getId());
        System.out.println("Name:" + cinema.getName());
        System.out.println("Address: " + cinema.getAddress());
        System.out.println("Has following rooms: ");
        for (Room room : cinema.getRooms()) {
            printRoomDetails(room);
        }
        universalSeparator();
    }

    public static void printRoomDetails(Room room) {
        String space = "    ";
        System.out.println(space + "Room id: " + room.getId());
        System.out.println(space + "Name: " + room.getName());
        System.out.println(space + "Total seats: " + room.getNumberOfSeats());
        if (room.isVIP()) System.out.println(space + "Has VIP status");
        if (room.isIs3D()) System.out.println(space + "Has 3D screen");
        if (!room.getSessions().isEmpty()) {
            System.out.println(space + "Has following sessions");
            for (Session session : room.getSessions()) {
                printSessionsInRoom(session);
            }
        }
        System.out.println(space + "########################");
        universalSeparator();
    }

    public static void printSessionsInRoom(Session session) {
        String space = "        ";
        System.out.println(space + "Title: " + session.getMovie().getTitle());
        System.out.println(space + "Genre: " + session.getMovie().getGenre());
        System.out.println(space + "Starts: " + session.getStartTime().toString());
        System.out.println(space + "Movie length in minuter: " + session.getMovie().getLength());
        System.out.println(space + "Available seats: " + session.getNumberOfSeats());
        System.out.println(space + "*****************");
        universalSeparator();

    }

    public static void printSeatGrid(Seat[][] seats, float basePrice) {
        int max_length = 0;
        for (Seat[] seat : seats) {
            if (seat.length > max_length) max_length = seat.length;
        }
        for (int i = 0; i < seats.length; i++) {
            StringBuilder printCounter = new StringBuilder();
            StringBuilder row = new StringBuilder();
            int counter = 1;
            for (int j = 0; j < seats[i].length; j++) {
                if (counter < 10) {
                    printCounter.append("   ").append(counter).append("   ");
                } else {
                    printCounter.append("   ").append(counter).append("     ");
                }

                counter += 1;
                if (seats[i][j].isReservation()) {
                    row.append("[XX.XX]");
                } else {
                    float temp = basePrice * ((float)seats[i][j].getModifier()/100);
                    row.append("[").append(String.format((temp >= 100) ? "%.1f" : "%.2f", temp)).append("]");
                }

            }
            String space = "       ".repeat((max_length - seats[i].length)/2);
            System.out.println("   " + space + printCounter);
            System.out.println((char)(i+65) + ": " + space + row);
        }
        universalSeparator();
    }

    public static void printCustomerReservations(Account user) {
        System.out.println(user.getUserName() + " has following reservations: ");
        for (Reservation reservation : user.getReservations()) {
            printReservationDetails(reservation);
        }
        universalSeparator();
    }

    public static void printReservationDetails(Reservation reservation) {
        String space = "   ";
        System.out.println(space + "Reservation id: " + reservation.getId());
        System.out.println(space + "Session id: " + reservation.getSession().getId());
        System.out.println(space + "Cinema: " + reservation.getSession().getRoom().getCinema().getName());
        System.out.println(space + "Location: " + reservation.getSession().getRoom().getCinema().getAddress());
        System.out.println(space + "Room: " + reservation.getSession().getRoom().getName());
        System.out.println(space + "Seats: " + String.join(", ", reservation.getReservedSeats()));
        System.out.println(space + "Movie: " + reservation.getSession().getMovie().getTitle());
        System.out.println(space + "Start time: " + reservation.getSession().getStartTime());
        System.out.println(space + "Total cost: " + reservation.getTotalCost());
        System.out.println(space + "Payment status: " + (reservation.isPaid() ? "Paid" : "Not paid"));
        universalSeparator();
    }


    public static void printTitle(String title, String genre) {
        System.out.println(title + ", " + genre + ":");
    }

    public static void printSessionByMovie(Session session) {
        System.out.println("    " + "Cinema " + session.getRoom().getCinema().getName() +
                " room " + session.getRoom().getName() + " at " + session.getStartTime() + ". Available seats: " +
                session.getNumberOfSeats() + ". Reference id: " + session.getId());
    }
}
