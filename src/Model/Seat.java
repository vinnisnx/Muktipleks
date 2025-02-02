package Model;

public class Seat {
    private String tag;
    private int modifier;
    private boolean reservation;

    public Seat(String tag, int modifier) {
        this.tag = tag;
        this.modifier = modifier;
        this.reservation = false;
    }

    public Seat(Seat copy) {
        this.tag = copy.getTag();
        this.modifier = copy.getModifier();
        this.reservation = false;
    }

    public boolean isReservation() {
        return reservation;
    }

    public void setReservation(boolean reservation) {
        this.reservation = reservation;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
    }
}
