import Layouts.*;
import Model.Account;
import Model.Master;
import Model.Cinema;
import Model.Movie;

import java.time.LocalDateTime;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Master master = new Master();
        master.addCinema(new Cinema(1, "SuperStefan", "ul. Stefana 55, 30-175 Krakow", master));
        master.addCinema(new Cinema(2, "Capital", "ul. Centralna 34, 40-570 Warsaw", master));
        master.addAccount(new Account((master.getAllAccounts().isEmpty()) ? 1 : master.getAllAccounts().getLast().getId() + 1, "User1", "1234"));
        master.addAccount(new Account((master.getAllAccounts().isEmpty()) ? 1 : master.getAllAccounts().getLast().getId() + 1, "User2", "4321"));
        Generator.generate(master);
        Cinema cinema1 = master.getCinema(1);
        Cinema cinema2 = master.getCinema(2);
        Account user1 = master.getAccount(1);
        Account user2 = master.getAccount(2);

        cinema1.getRoom("Small").getSessions().getFirst().makeReservation(user1, "A1", "A2", "A3");
        cinema1.getRoom("Small").getSessions().getFirst().makeReservation(user2, "C4", "C5", "C6");
        user1.cancelReservation(1);
        cinema1.getRoom("Small").getSessions().getFirst().makeReservation(user1, "D4", "D5", "D6");
        user1.payForReservation(3);
        user1.printReservations();
        user2.printReservations();
        cinema1.getRoom("Small").getSessions().getFirst().printSeatGrid();

        master.removeSession(master.getSession(6));
        master.removeSession(master.getSession(1));
        master.printSessionsByMovie();
        user1.printReservations();
        user2.printReservations();


        cinema2.getRoom("Sala VIP").getSessions().getFirst().makeReservation(user2, "C5", "C4");
        cinema2.getRoom("Sala VIP").getSessions().getFirst().makeReservation("D5", "D6");
        user2.printReservations();
        cinema2.getRoom("Sala VIP").getSessions().getFirst().printSeatGrid();
        master.removeCinema(cinema2);
        user2.printReservations();

        for (Cinema cinema : master.getAllCinemas()) {
            cinema.printDetails();
        }

        master.printSessionsByMovie();
        master.makeReservation(10, user1, "A1", "A2", "A3");
        master.makeReservation(10, user1, "B1", "B2", "B3");
        master.makeReservation(10, user2, "B4");
        master.makeReservation(10, "E6", "E11", "E7", "E8", "E9", "E10");
        master.makeReservation(14, user2, "B1", "B2", "B3");
        cinema1.getRoom("Green").getSession(10).printSeatGrid();
        cinema1.getRoom("Private").getSession(14).printSeatGrid();
        user1.printReservations();
        user2.printReservations();
        user1.cancelReservation(1);
        master.removeSession(master.getSession(14));
        cinema1.getRoom("Green").getSession(10).printSeatGrid();
        user1.printReservations();
        user2.printReservations();
        master.printSessionsByMovie();
        cinema1.removeRoom(cinema1.getRoom("Green"));
        user1.printReservations();
        user2.printReservations();
        master.printSessionsByMovie();


    }
}