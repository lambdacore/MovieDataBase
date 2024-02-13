// Jonathan Harrington - Assignment 3 - 06,02,2022

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class MovieDatabase {
    // Hashtable to store RedBlackTrees for each searchable movie attribute
    private HashMap<String, RedBlackTree<?, Movie>> treeTable;
    // RedBlackTrees for each searchable movie attribute
    private RedBlackTree<Integer, Movie> yearTree;
    private RedBlackTree<Double, Movie> scoreTree;
    private RedBlackTree<String, Movie> languageTree;
    private RedBlackTree<String, Movie> ratingTree;

    // Constructor initializes the RedBlackTrees
    public MovieDatabase() {
        treeTable = new HashMap<>();
        yearTree = new RedBlackTree<>();
        scoreTree = new RedBlackTree<>();
        languageTree = new RedBlackTree<>();
        ratingTree = new RedBlackTree<>();
    }

    // Method to add multiple movies to the database
    public void addMovies(ArrayList<Movie> movies) {
        for (Movie movie : movies) {
            putMovieInTrees(movie);
        }
    }

    // Helper method to add a movie to the appropriate RedBlackTrees
    private void putMovieInTrees(Movie movie) {
        RedBlackTree<Integer, Movie> yearTree = getOrCreateTree("year");
        yearTree.put(movie.getYear(), movie);

        RedBlackTree<Double, Movie> scoreTree = getOrCreateTree("score");
        scoreTree.put(movie.getImdbScore(), movie);

        RedBlackTree<String, Movie> languageTree = getOrCreateTree("language");
        languageTree.put(movie.getLanguage(), movie);

        RedBlackTree<String, Movie> ratingTree = getOrCreateTree("rating");
        ratingTree.put(movie.getContentRating(), movie);
    }

    // Helper method to retrieve the RedBlackTree associated with the given attribute or create a new one if it doesn't exist
    private <K extends Comparable<K>> RedBlackTree<K, Movie> getOrCreateTree(String attributeName) {
        RedBlackTree<?, Movie> tree = treeTable.get(attributeName);
        if (tree == null) {
            tree = new RedBlackTree<>();
            treeTable.put(attributeName, tree);
        }
        return (RedBlackTree<K, Movie>) tree;
    }

    public HashSet<Movie> getMoviesByAttribute(String attributeName, Object value) {
        RedBlackTree<?, Movie> tree = treeTable.get(attributeName);
        if (tree != null) {
            if (value instanceof Integer) {
                RedBlackTree<Integer, Movie> intTree = (RedBlackTree<Integer, Movie>) tree;
                return intTree.get((Integer) value);
            } else if (value instanceof Double) {
                RedBlackTree<Double, Movie> doubleTree = (RedBlackTree<Double, Movie>) tree;
                return doubleTree.get((Double) value);
            } else if (value instanceof String) {
                RedBlackTree<String, Movie> stringTree = (RedBlackTree<String, Movie>) tree;
                return stringTree.get((String) value);
            }
        }
        return new HashSet<>();
    }

    // Method to get movies by year
    public HashSet<Movie> getMoviesByYear(int year) {
        return yearTree.get(year);
    }

    // Method to get movies by score
    public HashSet<Movie> getMoviesByScore(double score) {
        return scoreTree.get(score);
    }

    // Method to get movies by language
    public HashSet<Movie> getMoviesByLanguage(String language) {
        return languageTree.get(language);
    }

    // Method to get movies by rating
    public HashSet<Movie> getMoviesByRating(String rating) {
        return ratingTree.get(rating);
    }
}
