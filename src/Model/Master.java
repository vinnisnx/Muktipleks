package Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Master {
    private List<Movie> availableMovies = new ArrayList<>();
    private List<Cinema> allCinemas = new ArrayList<>();
    private List<Account> allAccounts = new ArrayList<>();
    private List<Session> allSessions = new ArrayList<>();

    public List<Movie> getAvailableMovies() {
        return availableMovies;
    }

    public List<Account> getAllAccounts() {
        return allAccounts;
    }

    public Account getAccount(int id) {
        for (Account user : allAccounts) {
            if (user.getId() == id) {
                return user;
            }
        }
        throw new IllegalArgumentException("Reservation with ID " + id + " not found.");
    }

    public List<Session> getAllSessions() {
        return allSessions;
    }

    public void makeReservation(int id, Account user, String... seats) {
        getSession(id).makeReservation(user, seats);
    }

    public void makeReservation(int id, String... seats) {
        getSession(id).makeReservation(seats);
    }

    public void removeSession(Session session) {
        session.getRoom().removeSession(session);
    }

    public Session getSession(int id) {
        for (Session session : allSessions) {
            if (session.getId() == id) {
                return session;
            }
        }
        throw new IllegalArgumentException("Reservation with ID " + id + " not found.");
    }

    public void addSession(Session session) {
        allSessions.add(session);
    }

    public void addAccount(Account user) {
        allAccounts.add(user);
    }

    public Movie getMovie(String title) {
        for (Movie movie: availableMovies) {
            if (movie.getTitle().contains(title)) return movie;
        }
        throw new IllegalArgumentException("No movie found");
    }

    public void printSessionsByMovie() {
        List<Session> sortedSessions = allSessions.stream()
                .sorted(Comparator.comparing(session -> session.getMovie().getTitle()))
                .toList();
        String title = "";
        for (Session session : sortedSessions) {
            if (!title.equals(session.getMovie().getTitle())) {
                title = session.getMovie().getTitle();
                Printer.universalSeparator();
                Printer.printTitle(title, session.getMovie().getGenre());
            }
            Printer.printSessionByMovie(session);
        }
        Printer.universalSeparator();
    }

    public List<Cinema> getAllCinemas() {
        return allCinemas;
    }

    public Cinema getCinema(int i) {
        for (Cinema cinema : allCinemas) {
            if (i == cinema.getId()) return cinema;
        }
        return null;
    }

    public void addMovie(Movie movie) {
        availableMovies.add(movie);
    }

    public void addCinema(Cinema cinema) {
        allCinemas.add(cinema);
    }

    public void removeMovie(Movie movie) {
        availableMovies.remove(movie);
    }

    public void removeMovie(String name) {
        for (Movie movie : availableMovies) {
            if (Objects.equals(movie.getTitle(), name)) {
                removeMovie(movie);
                break;
            }
        }
    }


    public void removeCinema(Cinema cinema) {
        cinema.deleteCascade();
        allCinemas.remove(cinema);
    }

    public void removeCinema(String name) {
        for (Cinema cinema : allCinemas) {
            if (Objects.equals(cinema.getName(), name)) {
                removeCinema(cinema);
                break;
            }
        }

    }

    public void removeCinema(int id) {
        for (Cinema cinema : allCinemas) {
            if (cinema.getId() == id) {
                removeCinema(cinema);
                break;
            }
        }
    }
}
