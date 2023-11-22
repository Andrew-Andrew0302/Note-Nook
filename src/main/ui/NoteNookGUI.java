package ui;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//
public class NoteNookGUI extends JFrame implements ActionListener {
    private JPanel mainMenu;
    private JLabel library;

    private JTextField filePathField;

    private JButton addButton;
    private JButton removeButton;
    private JButton randomButton;
    private JButton loopButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton viewButton;

    private JFileChooser fileChooser;


    public NoteNookGUI() {
        super("Note Nook");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(900, 800));
        initializeMenu();


        JLabel welcomeLabel = new JLabel("Welcome to NoteNook!");
        JLabel pictureImage = new JLabel();
        addLabel(welcomeLabel);
        addImageToLabel(pictureImage);

        initializeMenuButtons();
        addButtons(viewButton, addButton, removeButton, randomButton, loopButton, saveButton, loadButton);
        addActionToButtons();


        fileChooser = new JFileChooser(".");
        fileChooser.setFileFilter(new FileNameExtensionFilter("WAV Files", "wav"));

        setLocationRelativeTo(null);
        mainMenu.setVisible(true);
    }

    // EFFECTS: Makes the main menu panel and changes the background color
    public void initializeMenu() {
        mainMenu = new JPanel();
        mainMenu.setBackground(Color.lightGray);
        library = new JLabel();
        library.setText("Available Songs");
        add(mainMenu);
    }

    // MODIFIES: this
    // EFFECTS: adds buttons to mainMenu
    public void addButton(JButton button, JPanel panel) {
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(Color.white);
        panel.add(button);

        button.setMargin(new Insets(8, 8, 8, 8));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: Calls the addButton method for each argument
    public void addButtons(JButton viewButton, JButton playButton, JButton pauseButton, JButton chooseButton,
                           JButton loopButton, JButton saveButton, JButton loadButton) {

        JPanel buttonPanel = new JPanel(new GridLayout(0, 2));
        buttonPanel.setBackground(Color.lightGray);

        // Add some spacing between the image and buttons
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        addButton(viewButton, buttonPanel);
        addButton(playButton, buttonPanel);
        addButton(pauseButton, buttonPanel);
        addButton(chooseButton, buttonPanel);
        addButton(loopButton, buttonPanel);
        addButton(saveButton, buttonPanel);
        addButton(loadButton, buttonPanel);

        mainMenu.add(buttonPanel);
        setVisible(true);

    }

    // EFFECTS: Initializes main menu buttons and gives them labels
    public void initializeMenuButtons() {
        viewButton = new JButton("View My Playlist");
        addButton = new JButton("Add Song");
        removeButton = new JButton("Remove Song");
        randomButton = new JButton("Random Song Of The Day");
        loopButton = new JButton("Loop");
        saveButton = new JButton("Save Current Songs");
        loadButton = new JButton("Load Previous Songs");
    }

    // EFFECTS: Creates the welcome text label and adds it to the main menu panel
    public void addLabel(JLabel j1) {
        j1.setFont(new Font("ComicSans", Font.BOLD, 50));
        mainMenu.add(j1);
    }

    // EFFECTS: Creates the image on the main menu and set the size
    public void addImageToLabel(JLabel j1) {
        ImageIcon icon = new ImageIcon("./data/NoteNook.png");
        j1.setIcon(icon);
        j1.setMinimumSize(new Dimension(20, 20));


        mainMenu.setLayout(new FlowLayout(FlowLayout.LEFT));
        mainMenu.add(j1);
        mainMenu.add(Box.createVerticalStrut(10));
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Sets each button to their respective action
    public void addActionToButtons() {
        viewButton.addActionListener(this);
        addButton.setActionCommand("View My Playlist");
        addButton.addActionListener(this);
        addButton.setActionCommand("Add Song");
        removeButton.addActionListener(this);
        removeButton.setActionCommand("Remove Song");
        randomButton.addActionListener(this);
        randomButton.setActionCommand("Random Song Of The Day");
        loopButton.addActionListener(this);
        loopButton.setActionCommand("Loop");
        saveButton.addActionListener(this);
        saveButton.setActionCommand("Save Current Songs");
        loadButton.addActionListener(this);
        loadButton.setActionCommand("Load Previous Songs");
    }


    // EFFECTS: calls the given methods when a certain button is clicked on
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("View My Playlist")) {
            new MyPlaylistGUI();
        }
    }




    public static void main(String[] args) {
        new NoteNookGUI();
    }

}
