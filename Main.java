// Jonathan Harrington - Assignment 3 - 06,02,2022

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        // Initialize the MovieDatabase
        MovieDatabase movieDatabase = new MovieDatabase();

        List<Movie> movies = new ArrayList<>();
        String line = "";

        Pattern pattern = Pattern.compile("\"([^\"]*)\"|(?<=,|^)([^,]*)(?=,|$)");

        //
        // change file path here for testing.
        //
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/lairos/IdeaProjects/MovieDataBased/src/movies.csv"))) {
            br.readLine(); // this will read the first line to skip the header

            while ((line = br.readLine()) != null) {
                // use pattern matcher to separate fields considering quotes
                Matcher matcher = pattern.matcher(line);
                List<String> movieDetails = new ArrayList<>();
                while (matcher.find()) {
                    String value = matcher.group(1) != null ? matcher.group(1) : matcher.group(2);
                    movieDetails.add(value.trim());
                }
                // Used for testing to see which row we are looking at.
                //System.out.println(line);
                Movie movie = new Movie(
                        movieDetails.get(0).isEmpty() ? 0 : Integer.parseInt(movieDetails.get(0)),
                        movieDetails.get(1),
                        movieDetails.get(2),
                        movieDetails.get(3).isEmpty() ? 0 : Integer.parseInt(movieDetails.get(3)),
                        movieDetails.get(4),
                        movieDetails.get(5),
                        movieDetails.get(6),
                        movieDetails.get(7),
                        movieDetails.get(8),
                        movieDetails.get(9),
                        movieDetails.get(10),
                        movieDetails.get(11),
                        movieDetails.get(12).isEmpty() ? 0 : Integer.parseInt(movieDetails.get(12)),
                        movieDetails.get(13).isEmpty() ? 0.0 : Double.parseDouble(movieDetails.get(13))
                );

                movies.add(movie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add the movies to the MovieDatabase
        movieDatabase.addMovies((ArrayList<Movie>) movies);

        Scanner scanner = new Scanner(System.in);
        Integer year = null;
        Double score = null;
        String language = null;
        String rating = null;

        System.out.println("Enter year (- to ignore):");
        String yearInput = scanner.nextLine();
        if (!yearInput.equals("-")) {
            year = Integer.parseInt(yearInput);
        }

        System.out.println("Enter score (- to ignore):");
        String scoreInput = scanner.nextLine();
        if (!scoreInput.equals("-")) {
            try {
                score = Double.parseDouble(scoreInput);
            } catch (NumberFormatException e) {
                // Handle invalid score input, if needed
            }
        }

        System.out.println("Enter language (- to ignore):");
        language = scanner.nextLine();
        if (language.equals("-")) {
            language = null;
        }

        System.out.println("Enter rating (- to ignore):");
        rating = scanner.nextLine();
        if (rating.equals("-")) {
            rating = null;
        }

        // Get movies based on the selected criteria from the MovieDatabase
        HashSet<Movie> yearMovies = year != null ? movieDatabase.getMoviesByAttribute("year", year) : new HashSet<>(movies);
        HashSet<Movie> scoreMovies = score != null ? movieDatabase.getMoviesByAttribute("score", score) : new HashSet<>(movies);
        HashSet<Movie> languageMovies = language != null ? movieDatabase.getMoviesByAttribute("language", language) : new HashSet<>(movies);
        HashSet<Movie> ratingMovies = rating != null ? movieDatabase.getMoviesByAttribute("rating", rating) : new HashSet<>(movies);

        // Perform intersection of movies based on all selected criteria
        yearMovies.retainAll(scoreMovies);
        yearMovies.retainAll(languageMovies);
        yearMovies.retainAll(ratingMovies);

        // Print the filtered movies
        System.out.println("Results (Movies -> " +
                (year != null ? "year:" + year + " " : "") +
                (score != null ? "score:" + score + " " : "") +
                (language != null ? "language:" + language + " " : "") +
                (rating != null ? "rating:" + rating : "") +
                ")");
        for (Movie movie : yearMovies) {
            movie.printDetails();
            System.out.println("-----------------------------");
        }

        scanner.close();
    }
}
