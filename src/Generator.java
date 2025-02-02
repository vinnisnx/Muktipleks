import Layouts.*;
import Model.Cinema;
import Model.Master;
import Model.Movie;

import java.time.LocalDateTime;

public class Generator {
    public static void generate(Master master) {
        master.addMovie(new Movie("Inside Out 2", "Animation", 105));
        master.addMovie(new Movie("Deadpool & Wolverine", "Action", 130));
        master.addMovie(new Movie("Moana 2", "Adventure", 115));
        master.addMovie(new Movie("Despicable Me 4", "Comedy", 90));
        master.addMovie(new Movie("Wicked", "Musical", 150));
        master.addMovie(new Movie("Dune: Part Two", "Sci-Fi", 155));
        master.addMovie(new Movie("Mufasa: The Lion King", "Animation", 120));
        master.addMovie(new Movie("Godzilla x Kong: The New Empire", "Action", 140));
        master.addMovie(new Movie("Kung Fu Panda 4", "Animation", 100));
        master.addMovie(new Movie("YOLO", "Drama", 110));

        Cinema curr_cinema = master.getCinema(1);
        curr_cinema.createRoom("Small", SmallRoom.buildGrid(), false, false, 50);
        curr_cinema.createRoom("Green", GreenRoom.buildGrid(), false, false, 60);
        curr_cinema.createRoom("Blue", BlueRoom.buildGrid(), false, false, 60);
        curr_cinema.createRoom("Private", PrivateRoom.buildGrid(), false, true, 200);

        curr_cinema.getRoom("Small").addSession(master.getMovie("Dune"), 100);
        for (Movie movie : master.getAvailableMovies()) {
            curr_cinema.getRoom("Green").addSession(movie, 100);
        }
        curr_cinema.getRoom("Blue").addSession((master.getMovie("YOLO")), 110, LocalDateTime.of(2025, 1, 31, 14, 30));
        curr_cinema.getRoom("Blue").addSession((master.getMovie("Mufasa")), 110, LocalDateTime.of(2025, 1, 31, 16, 51));
        curr_cinema.getRoom("Private").addSession((master.getMovie("Panda")), 100);

        curr_cinema = master.getCinema(2);
        curr_cinema.createRoom("Sala 1", SmallRoom.buildGrid(), false, false, 55);
        curr_cinema.createRoom("Sala 2", GreenRoom.buildGrid(), false, false, 65);
        curr_cinema.createRoom("Sala 3", BlueRoom.buildGrid(), true, false, 75);
        curr_cinema.createRoom("Amphitheater", Amphitheater.buildGrid(), true, false, 90);
        curr_cinema.createRoom("Sala VIP", PrivateRoom.buildGrid(), true, true, 250);

        curr_cinema.getRoom("Sala VIP").addSession(master.getMovie("YOLO"), 100);
        for (Movie movie : master.getAvailableMovies()) {
            curr_cinema.getRoom("Amphitheater").addSession(movie, 100);
        }
    }
}
