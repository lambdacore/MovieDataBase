// Jonathan Harrington - Assignment 3 - 06,02,2022

class Movie {
    private int id;
    private String color;
    private String movie_title;
    private int duration;
    private String director_name;
    private String actor_1_name;
    private String actor_2_name;
    private String actor_3_name;
    private String movie_imdb_link;
    private String language;
    private String country;
    private String content_rating;
    private int year;
    private double imdb_score;

    // constructor
    public Movie(int id, String color, String movie_title, int duration, String director_name,
                 String actor_1_name, String actor_2_name, String actor_3_name, String movie_imdb_link,
                 String language, String country, String content_rating, int year, double imdb_score) {
        this.id = id;
        this.color = color;
        this.movie_title = movie_title;
        this.duration = duration;
        this.director_name = director_name;
        this.actor_1_name = actor_1_name;
        this.actor_2_name = actor_2_name;
        this.actor_3_name = actor_3_name;
        this.movie_imdb_link = movie_imdb_link;
        this.language = language;
        this.country = country;
        this.content_rating = content_rating;
        this.year = year;
        this.imdb_score = imdb_score;
    }

    // getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public String getContentRating() { return content_rating; }
    public void setYear(int year) { this.year = year; }
    public int getYear() { return year; }
    public double getImdbScore() { return imdb_score; }
    public void printDetails() {
        System.out.println("ID: " + id);
        System.out.println("Color: " + color);
        System.out.println("Movie Title: " + movie_title);
        System.out.println("Duration: " + duration);
        System.out.println("Director Name: " + director_name);
        System.out.println("Actor 1 Name: " + actor_1_name);
        System.out.println("Actor 2 Name: " + actor_2_name);
        System.out.println("Actor 3 Name: " + actor_3_name);
        System.out.println("IMDB Link: " + movie_imdb_link);
        System.out.println("Language: " + language);
        System.out.println("Country: " + country);
        System.out.println("Content Rating: " + content_rating);
        System.out.println("Year: " + year);
        System.out.println("IMDB Score: " + imdb_score);
    }

    //
    // getters and setter not used in program but could be used to extend its use
    //
    public String getMovieTitle() { return movie_title; }
    public void setMovieTitle(String movie_title) { this.movie_title = movie_title; }
    public String getDirectorName() { return director_name; }
    public void setDirectorName(String director_name) { this.director_name = director_name; }
    public String getActor1Name() { return actor_1_name; }
    public void setActor1Name(String actor_1_name) { this.actor_1_name = actor_1_name; }
    public String getActor2Name() { return actor_2_name; }
    public void setActor2Name(String actor_2_name) { this.actor_2_name = actor_2_name; }

    public String getActor3Name() { return actor_3_name; }
    public void setActor3Name(String actor_3_name) { this.actor_3_name = actor_3_name; }

    public String getMovieImdbLink() { return movie_imdb_link; }
    public void setMovieImdbLink(String movie_imdb_link) { this.movie_imdb_link = movie_imdb_link; }


    public void setContentRating(String content_rating) { this.content_rating = content_rating; }

    public void setImdbScore(double imdb_score) { this.imdb_score = imdb_score; }


}
