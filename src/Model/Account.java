package Model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private int id;
    private String userName;
    private String password;
    private List<Reservation> reservations = new ArrayList<>();

    public Account(int id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public int getId() {
        return id;
    }

    public void printReservations() {
        Printer.printCustomerReservations(this);
    }

    public void payForReservation(Reservation reservation) {
        if (reservation.isPaid()) throw new IllegalArgumentException("Reservation has been paid already");
        reservation.setPaid(true);
    }

    public void payForReservation(int id) {
        payForReservation(findReservationById(id));
    }

    public void cancelReservation(Reservation reservation) {
        if (reservation.isPaid()) throw new IllegalArgumentException("Paid reservation cannot be cancelled.");
        reservation.getSession().freeSeat(reservation);
        reservations.remove(reservation);
    }

    public void cancelReservation(int id) {
        cancelReservation(findReservationById(id));
    }

    private Reservation findReservationById(int id) {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == id) {
                return reservation;
            }
        }
        throw new IllegalArgumentException("Reservation with ID " + id + " not found.");
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
