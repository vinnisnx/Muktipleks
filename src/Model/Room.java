package Model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Room {
    private Cinema cinema;
    private int id;
    private String name;
    private Seat[][] seats;
    private int numberOfSeats;
    private boolean is3D;
    private boolean isVIP;
    private int basePrice;
    private List<Session> sessions;

    public Room(Cinema cinema, int id, String name, Seat[][] seats, boolean is3D, boolean isVIP, int basePrice) {
        this.cinema = cinema;
        this.id = id;
        this.name = name;
        this.seats = seats;
        this.is3D = is3D;
        this.isVIP = isVIP;
        this.basePrice = basePrice;
        this.sessions = new ArrayList<>();
        calculateSeats();
    }

    public void addSession(Movie movie, int modifier, LocalDateTime date) {
        if (!sessions.isEmpty() && !sessions.getLast().getStartTime().plusMinutes(sessions.getLast().getMovie().getLength() + 30).isBefore(date)) {
            throw new IllegalArgumentException("Overlapping time");
        } else {
            adderSession(movie, modifier, date);
        }
    }

    public void addSession(Movie movie, int modifier) {
        LocalDateTime date;
        if (sessions.isEmpty()) {
            date = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        } else {
            date = sessions.getLast().getStartTime().plusMinutes(sessions.getLast().getMovie().getLength()+30);
        }
        if (date.getHour() >= 20) date = date.plusDays(1).withHour(9).withMinute(0);
        adderSession(movie, modifier, date);
    }

    private void adderSession(Movie movie, int modifier, LocalDateTime date) {
        Session session = new Session((cinema.getMaster().getAllSessions().isEmpty()) ? 1 : cinema.getMaster().getAllSessions().getLast().getId() + 1, this, movie, modifier, date);
        sessions.add(session);
        cinema.getMaster().addSession(session);
    }

    public void removeSession(Session session) {
        session.deleteCascade();
        sessions.remove(session);
    }

    public void deleteCascade() {
        for (Session session : sessions) {
            session.deleteCascade();
        }
        sessions.clear();
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public Session getSession(int id) {
        for (Session session : sessions) {
            if (session.getId() == id) {
                return session;
            }
        }
        throw new IllegalArgumentException("Reservation with ID " + id + " not found.");
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void printRoomDetails() {
        Printer.printRoomDetails(this);
    }

    private void calculateSeats() {
        for (Seat[] seat : seats) {
            for (Seat value : seat) {
                if (value != null) this.numberOfSeats += 1;
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Seat[][] getSeats() {
        return seats;
    }

    public void setSeats(Seat[][] seats) {
        this.seats = seats;
        calculateSeats();
    }

    public boolean isIs3D() {
        return is3D;
    }

    public void setIs3D(boolean is3D) {
        this.is3D = is3D;
    }

    public boolean isVIP() {
        return isVIP;
    }

    public void setVIP(boolean VIP) {
        isVIP = VIP;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }
}
