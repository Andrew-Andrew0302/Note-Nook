package model;

public class Song {
    private int id; // song id
    private final String name; // Song Title
    private final String artist; // Song Artist
    private final String genre; // Genre Of Song
    private final int duration; // Duration Of Song in Seconds


    // Constructor
    public Song(int id, String name, String artist, String genre, int duration) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;

    }

    // Getter Methods

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getGenre() {
        return this.genre;
    }

    public int getDuration() {
        return this.duration;
    }

}
