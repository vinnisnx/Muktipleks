package Model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private Room room;
    private Movie movie;
    private int modifier;
    private LocalDateTime startTime;
    private Seat[][] seats;
    private float basePrice;
    private int numberOfSeats;
    private boolean is3D;
    private boolean isVIP;
    private boolean thisSession3D;
    private int id;
    private List<Reservation> reservations = new ArrayList<>();

    public Session(int id, Room room, Movie movie, int modifier, LocalDateTime startTime) {
        this.id = id;
        this.seats = copySeatGrid(room.getSeats());
        this.basePrice = room.getBasePrice() * ((float)modifier/100);
        this.numberOfSeats = room.getNumberOfSeats();
        this.is3D = room.isIs3D();
        this.thisSession3D = room.isIs3D();
        this.isVIP = room.isVIP();
        this.movie = movie;
        this.modifier = modifier;
        this.startTime = startTime;
        this.room = room;
    }

    private Seat[][] copySeatGrid(Seat[][] toCopy) {
        Seat[][] seats = new Seat[toCopy.length][];
        for (int i = 0; i < toCopy.length; i++) {
            seats[i] = new Seat[toCopy[i].length];
            for (int j = 0; j < toCopy[i].length; j++) {
                seats[i][j] = new Seat(toCopy[i][j]);
            }
        }
        return seats;
    }

    public int getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public void freeSeat(Reservation reservation) {
        for (String seat : reservation.getReservedSeats()) {
            int x = seat.charAt(0) - 65;
            int y = Integer.parseInt((seat.length() > 2) ? "" + seat.charAt(1) + seat.charAt(2) : "" + seat.charAt(1)) - 1;
            this.seats[x][y].setReservation(false);
            numberOfSeats += 1;
        }
        reservations.remove(reservation);
    }


    public float reserveSeat(String... seats) {
        float sum = 0;
        for (String seat : seats) {
            int x = seat.charAt(0) - 65;
            int y = Integer.parseInt((seat.length() > 2) ? "" + seat.charAt(1) + seat.charAt(2) : "" + seat.charAt(1)) - 1;
            if (this.seats[x][y].isReservation()) throw new IllegalArgumentException("Seat already reserved");
            this.seats[x][y].setReservation(true);
            numberOfSeats -= 1;
            sum += basePrice * ((float) this.seats[x][y].getModifier() /100);
        }
        return sum;
    }

    public void makeReservation(Account user, String... seats) {
        Reservation reservation = new Reservation(user, ((reservations.isEmpty()) ? 1 : reservations.getLast().getId() + 1), this, seats, reserveSeat(seats));
        user.addReservation(reservation);
        reservations.add(reservation);
    }

    public void makeReservation(String... seats) {
        Reservation reservation = new Reservation(-1, this, seats, reserveSeat(seats));
        reservations.add(reservation);
    }

    public void deleteCascade() {
        for (Reservation reservation : reservations) {
            reservation.delete();
        }
        room.getCinema().getMaster().getAllSessions().remove(this);
        reservations.clear();
    }

    public void printSeatGrid() {
        Printer.printSeatGrid(seats, basePrice);
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Seat[][] getSeats() {
        return seats;
    }

    public void setSeats(Seat[][] seats) {
        this.seats = seats;
    }

    public float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public boolean isThisSession3D() {
        return thisSession3D;
    }

    public void setThisSession3D(boolean thisSession3D) {
        if (thisSession3D) {
            if (this.is3D) this.thisSession3D = true;
        } else {
            this.thisSession3D = false;
        }

    }

    public boolean isVIP() {
        return isVIP;
    }

    public void setVIP(boolean VIP) {
        isVIP = VIP;
    }
}
