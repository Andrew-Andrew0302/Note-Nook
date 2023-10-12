package ui;

import model.Song;
import model.SongLibrary;

import java.util.Scanner;

// run NoteNook app
public class NoteNook {
    private SongLibrary songLibrary;
    private Scanner input;
    private Song song;

    // EFFECTS: runs the NoteNook application
    public NoteNook() {
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
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: views the list of songs in the album
    public void doViewSong() {
        System.out.println("Songs in the library: ");
        for (Song s : songLibrary.viewSong()) {
            System.out.println(s.getName());
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


    public void doRemoveSong() {
        System.out.println("Please enter the following details:\nTitle of Song:");
        String song = input.next();

        songLibrary.removeSong(song);
    }

    public void doPlayRandomSong() {
        Song ran = songLibrary.getRandomSong();
        String name = ran.getName();
        System.out.println("Random Song: " + name);
    }

    public void doPlayFirstSong() {
        System.out.println("Song Title: " + songLibrary.play());
    }

}
