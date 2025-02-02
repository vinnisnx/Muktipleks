package Model;

public class Reservation {
    private int id;
    private Session session;
    private String[] reservedSeats;
    private float totalCost;
    private boolean paid;
    private Account user;

    public Reservation(Account user, int id, Session session, String[] reservedSeats, float totalCost) {
        this.user = user;
        this.id = id;
        this.session = session;
        this.reservedSeats = reservedSeats;
        this.totalCost = totalCost;
        this.paid = false;
    }

    public Reservation(int id, Session session, String[] reservedSeats, float totalCost) {
        this(null, id, session, reservedSeats, totalCost);
    }

    public int getId() {
        return id;
    }

    public Account getUser() {
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }

    public void delete() {
        if (user != null) {
            user.getReservations().remove(this);
        }
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String[] getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(String[] reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
