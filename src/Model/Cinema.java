package Model;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private int id;
    private String name;
    private String address;
    private List<Room> rooms;
    private Master master;

    public Cinema(int id, String name, String address, Master master) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rooms = new ArrayList<>();
        this.master = master;
    }

    public void createRoom(String name, Seat[][] seats, boolean is3D, boolean isVIP, int basePrice) {
        Room room = new Room(this, (rooms.isEmpty() ? 1 : rooms.getLast().getId()+1), name, seats,
                is3D, isVIP, basePrice);
        rooms.add(room);
    }

    public Room getRoom(String name) {
        for (Room room: rooms) {
            if (room.getName().contains(name)) return room;
        }
        throw new IllegalArgumentException("No room found");
    }

    public Master getMaster() {
        return master;
    }

    public void removeRoom(Room room) {
        room.deleteCascade();
        rooms.remove(room);
    }

    public void deleteCascade() {
        for (Room room : rooms) {
            room.deleteCascade();
        }
        rooms.clear();
    }

    public void printDetails() {
        Printer.printCinemaDetails(this);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Room> getRooms() {
        return rooms;
    }

}
