package ui;

import persistence.JsonReader;
import persistence.JsonWriter;
import model.Song;
import model.SongLibrary;

import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;
import java.io.IOException;

// run NoteNook app
public class NoteNook {
    private static final String JSON_STORE = "./data/SongLibrary.json";
    private SongLibrary songLibrary;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the NoteNook application
    public NoteNook() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runNoteNook();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runNoteNook() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // displays option menu to user
    public void displayMenu() {
        System.out.println("Welcome to NoteNook! " + songLibrary.getAlbum() + " Song library!");
        System.out.println("Select From:");
        System.out.println("\t1 -> View Songs");
        System.out.println("\t2 -> Add Song");
        System.out.println("\t3 -> Remove Song");
        System.out.println("\t4 -> Play Random Song");
        System.out.println("\t5 -> Play First Song");
        System.out.println("\ts -> save Song Library to file");
        System.out.println("\tl -> load Song Library from file");
        System.out.println("\tq -> Quit");
    }

    private void init() {
        songLibrary = new SongLibrary("Ripley");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            doViewSong();
        } else if (command.equals("2")) {
            doAddSong();
        } else if (command.equals("3")) {
            doRemoveSong();
        } else if (command.equals("4")) {
            doPlayRandomSong();
        } else if (command.equals("5")) {
            doPlayFirstSong();
        } else if (command.equals("s")) {
            saveSongLibrary();
        } else if (command.equals("l")) {
            loadSongLibrary();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: views the list of songs in the album
    public void doViewSong() {
        if (songLibrary.getSongsSize() == 0) {
            System.out.println("No Songs in the Library!");
        } else {
            System.out.println("Songs in the library: ");
            for (Song s : songLibrary.viewSong()) {
                System.out.println(s.getName());
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new song by asking for user input,
    //          then adds it to the songLibrary
    private void doAddSong() {

        int id;
        String name;
        String artist;
        String genre;
        int duration;

        System.out.println("Please enter the following details:\nSong id:");

        id = input.nextInt();
        System.out.println("Title of Song:");
        name = input.next();
        System.out.println("Artist Of Song:");
        artist = input.next();
        System.out.println("Song Genre:");
        genre = input.next();
        System.out.println("Duration of Song:");
        duration = input.nextInt();

        Song song = new Song(id, name, artist, genre, duration);

        songLibrary.addSong(song);
    }


    // MODIFIES: This
    // EFFECTS: Remove a song from the song library, user needs to input the Song name.
    public void doRemoveSong() {

        System.out.println("Please enter the following details:\nTitle of Song:");
        String song = input.next();
        for (Song s : songLibrary.viewSong()) {
            if (Objects.equals(s.getName(), song)) {
                songLibrary.removeSong(song);
                System.out.println("Song removed!");
                return;
            }
        }

        System.out.println("Song Not Found in Song Library!");
    }

    // EFFECTS: Play a random song from the songLibrary
    public void doPlayRandomSong() {
        Song ran = songLibrary.getRandomSong();
        String name = ran.getName();
        System.out.println("Random Song: " + name);
    }

    // EFFECTS: Play the first song in the songLibrary
    public void doPlayFirstSong() {
        System.out.println("Song Title: " + songLibrary.play());
    }

    // EFFECTS: saves the SongLibrary to file
    private void saveSongLibrary() {
        try {
            jsonWriter.open();
            jsonWriter.write(songLibrary);
            jsonWriter.close();
            System.out.println("Saved " + songLibrary.getAlbum() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads SongLibrary from file
    private void loadSongLibrary() {
        try {
            songLibrary = jsonReader.read();
            System.out.println("Loaded " + songLibrary.getAlbum() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
