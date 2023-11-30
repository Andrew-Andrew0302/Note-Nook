package ui;

import model.Song;
import model.SongLibrary;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;


public class MyPlaylistGUI extends JFrame implements ActionListener {

    private static final String songLibraryFile = "./data/SongLibraryFile.json";

    private Song song1;
    private Song song2;
    private Song song3;

    private SongLibrary songLibrary;
    private DefaultTableModel tableModel;
    private JTable songTable;

    private JButton addButton;
    private JButton removeButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton randomButton;
    private JButton returnButton;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // Makes A new JTable and Add buttons below it
    public MyPlaylistGUI() {
        super("My Playlist");
        songLibrary = new SongLibrary("Ripley's Song Library");
        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Artist", "Genre", "Duration"}, 0);
        songTable = new JTable(tableModel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);

        jsonWriter = new JsonWriter(songLibraryFile);
        jsonReader = new JsonReader(songLibraryFile);


        setLayout(new BorderLayout());
        add(new JScrollPane(songTable), BorderLayout.CENTER);

        initializeSongs();

        initializeButtons();
        addButtons(addButton, removeButton, saveButton, loadButton, randomButton, returnButton);
        addActionToButtons();


        setLocationRelativeTo(null);
        setVisible(true);


    }

    // MODIFIES: this
    // EFFECTS: Initialize 3 songs onto the Song Library
    public void initializeSongs() {
        song1 = new Song(1, "Less of You", "Keshi", "Pop", 360);
        song2 = new Song(2, "Strawberry and Wine", "Jaylon Ashaun", "Pop", 200);
        song3 = new Song(3, "Girl", "Alexander 23", "Pop", 300);

        songLibrary.addSong(song1);
        songLibrary.addSong(song2);
        songLibrary.addSong(song3);
        updateTable();
    }

    // MODIFIES: this
    // EFFECTS: adds buttons to mainMenu
    public void addButton(JButton button, JPanel panel) {
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(Color.white);
        panel.add(button);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: Initializes buttons and gives them labels
    public void initializeButtons() {
        addButton = new JButton("Add Song");
        removeButton = new JButton("Remove Song");
        saveButton = new JButton("Save Current Songs");
        loadButton = new JButton("Load Previous Songs");
        randomButton = new JButton("Get Song of the day");
        returnButton = new JButton("Main Menu");
    }

    // MODIFIES: this
    // EFFECTS: Sets each button to their respective action
    public void addActionToButtons() {
        addButton.addActionListener(this);
        addButton.setActionCommand("Add Song");
        removeButton.addActionListener(this);
        removeButton.setActionCommand("Remove Song");
        saveButton.addActionListener(this);
        saveButton.setActionCommand("Save Current Songs");
        loadButton.addActionListener(this);
        loadButton.setActionCommand("Load Previous Songs");
        randomButton.addActionListener(this);
        randomButton.setActionCommand("Get Song of the day");
        returnButton.addActionListener(this);
        returnButton.setActionCommand("Main Menu");

    }

    // EFFECTS: Calls the addButton method for each argument and make the buttons at the Bottom of the JTable
    public void addButtons(JButton addButton, JButton removeButton, JButton saveButton, JButton loadButton,
                           JButton randomButton, JButton returnButton) {
        JPanel buttonPanel = new JPanel(new GridLayout(0, 2));
        buttonPanel.setBackground(Color.lightGray);


        // Add some spacing between the image and buttons
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        addButton(addButton, buttonPanel);
        addButton(removeButton, buttonPanel);
        addButton(saveButton, buttonPanel);
        addButton(loadButton, buttonPanel);
        addButton(randomButton, buttonPanel);
        addButton(returnButton, buttonPanel);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // EFFECTS: calls the given methods when a certain button is clicked on
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add Song")) {
            addSong();
        } else if (e.getActionCommand().equals("Remove Song")) {
            removeSong();
        } else if (e.getActionCommand().equals("Save Current Songs")) {
            saveSongLibrary();
        } else if (e.getActionCommand().equals("Load Previous Songs")) {
            loadSongLibrary();
        } else if (e.getActionCommand().equals("Main Menu")) {
            returnToMainMenu();
        } else if (e.getActionCommand().equals("Get Song of the day")) {
            randomSong();
        }
    }

    // Modifies: this
    // EFFECTS: Adds a Song onto the Song Library by user input
    public void addSong() {
        String name = JOptionPane.showInputDialog("Enter song name:");
        String artist = JOptionPane.showInputDialog("Enter artist:");
        String genre = JOptionPane.showInputDialog("Enter genre:");
        String durationStr = JOptionPane.showInputDialog("Enter duration in seconds:");

        try {
            int duration = Integer.parseInt(durationStr);
            Song song = new Song(songLibrary.getSongsSize() + 1, name, artist, genre, duration);
            songLibrary.addSong(song);
            updateTable();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid duration. Please enter a number.");
        }

    }

    // Modifies: this
    // EFFECTS: Removes a Song onto the Song Library by clicking on which Song to Remove
    public void removeSong() {
        int selectedRow = songTable.getSelectedRow();
        if (selectedRow != -1) {
            int selectedId = (int) tableModel.getValueAt(selectedRow, 0);
            String songName = (String) tableModel.getValueAt(selectedRow, 1);
            songLibrary.removeSong(songName);
            updateTable();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a song to remove.");
        }
    }

    // EFFECTS: saves the SongLibrary to file
    public void saveSongLibrary() {
        try {
            jsonWriter.open();
            jsonWriter.write(songLibrary);
            jsonWriter.close();
            updateTable();
            JOptionPane.showMessageDialog(this, "Saved the songs to " + songLibraryFile);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to write to file: " + songLibraryFile);
        }
    }

    // EFFECTS: Loads the SongLibrary to file
    public void loadSongLibrary() {
        try {
            songLibrary = jsonReader.read();
            updateTable();
            JOptionPane.showMessageDialog(this, "Loaded Songs from : " + songLibraryFile);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to read from file: " + songLibraryFile);
        }
    }

    // EFFECTS: Shows a random Song to the user
    public void randomSong() {
        if (songLibrary.getSongsSize() > 0) {
            Song random = songLibrary.getRandomSong();
            JOptionPane.showMessageDialog(this, "Song of the day is : " + random.getName()
                    + " by " + random.getArtist());
        } else {
            JOptionPane.showMessageDialog(this, "No Songs currently in the Playlist.");
        }
    }

    // EFFECTS: Return to Main Menu Page from our Playlist JTable
    public void returnToMainMenu() {
        new NoteNookGUI();
    }

    // EFFECTS: Updates the table every time a change happens in the code so the user can view the changes
    // they made in the playlist.
    public void updateTable() {
        tableModel.setRowCount(0);
        for (Song song : songLibrary.viewSong()) {
            tableModel.addRow(new Object[]{song.getId(), song.getName(), song.getArtist(),
                    song.getGenre(), song.getDuration()});
        }
    }





}
