package ui;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class NoteNookGUI extends JFrame implements ActionListener {
    private JPanel mainMenu;
    private JLabel library;

    private JTextField filePathField;

    private JButton viewButton;

    private JFileChooser fileChooser;

    // Makes a New JFrame with different attributes
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
        addButtons(viewButton);
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
    public void addButtons(JButton viewButton) {

        JPanel buttonPanel = new JPanel(new GridLayout(0, 2));
        buttonPanel.setBackground(Color.lightGray);

        // Add some spacing between the image and buttons
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        addButton(viewButton, buttonPanel);

        mainMenu.add(buttonPanel);
        setVisible(true);

    }

    // EFFECTS: Initializes main menu buttons and gives them labels
    public void initializeMenuButtons() {
        viewButton = new JButton("View My Playlist");
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
        viewButton.setActionCommand("View My Playlist");
    }


    // EFFECTS: calls the given methods when a certain button is clicked on
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("View My Playlist")) {
            new MyPlaylistGUI();
        }
    }
}
