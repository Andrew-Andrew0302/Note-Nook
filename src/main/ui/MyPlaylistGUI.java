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

    private SongLibrary songLibrary;
    private DefaultTableModel tableModel;
    private JTable songTable;

    private JButton addButton;
    private JButton removeButton;
    private JButton saveButton;
    private JButton loadButton;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


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

        initializeButtons();
        addButtons(addButton, removeButton, saveButton, loadButton);
        addActionToButtons();


        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addButton(JButton button, JPanel panel) {
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(Color.white);
        panel.add(button);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void initializeButtons() {
        addButton = new JButton("Add Song");
        removeButton = new JButton("Remove Song");
        saveButton = new JButton("Save Current Songs");
        loadButton = new JButton("Load Previous Songs");
    }

    public void addActionToButtons() {
        addButton.addActionListener(this);
        addButton.setActionCommand("Add Song");
        removeButton.addActionListener(this);
        removeButton.setActionCommand("Remove Song");
        saveButton.addActionListener(this);
        saveButton.setActionCommand("Save Current Songs");
        loadButton.addActionListener(this);
        loadButton.setActionCommand("Load Previous Songs");
    }

    public void addButtons(JButton addButton, JButton removeButton, JButton saveButton, JButton loadButton) {
        JPanel buttonPanel = new JPanel(new GridLayout(2,2));
        buttonPanel.setBackground(Color.lightGray);

        // Add some spacing between the image and buttons
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        addButton(addButton, buttonPanel);
        addButton(removeButton, buttonPanel);
        addButton(saveButton, buttonPanel);
        addButton(loadButton, buttonPanel);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add Song")) {
            addSong();
        } else if (e.getActionCommand().equals("Remove Song")) {
            removeSong();
        } else if (e.getActionCommand().equals("Save Current Songs")) {
            saveSongLibrary();
        } else if (e.getActionCommand().equals("Load Previous Songs")) {
            loadSongLibrary();
        }
    }


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

    private void saveSongLibrary() {
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


    private void loadSongLibrary() {
        try {
            songLibrary = jsonReader.read();
            updateTable();
            JOptionPane.showMessageDialog(this, "Loaded Songs from : " + songLibraryFile);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to read from file: " + songLibraryFile);
        }
    }

    public void updateTable() {
        tableModel.setRowCount(0);
        for (Song song : songLibrary.viewSong()) {
            tableModel.addRow(new Object[]{song.getId(), song.getName(), song.getArtist(),
                    song.getGenre(), song.getDuration()});
        }
    }
}
