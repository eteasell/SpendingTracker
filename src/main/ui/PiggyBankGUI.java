package ui;

import model.MonthlyFinances;
import model.MyPiggyBank;
import model.MySpending;
import model.ThisMonthsFinances;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Main class for running project with GUI
// Code based off of examples from EdX/StackOverflow, LabelChanger, and AlarmSystem Project
public class PiggyBankGUI extends JFrame {

    private JLabel label;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private JDesktopPane desktop;
    private JInternalFrame controlPanel;
    private JInternalFrame welcome;

    private static final String JSON_STORE = "./data/MyPiggyBankAccount.json"; //*
    protected MyPiggyBank myPiggyBank;
    private MySpending mySpending;
    private MonthlyFinances myMonthlyFinances;
    private ThisMonthsFinances thisMonthsFinances;
    private JsonReader jsonReader = new JsonReader(JSON_STORE);
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);

    public PiggyBankGUI() { // frame setup fromJava Tutorials example
        super("The College Student's Piggy Bank");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());

        desktop = new JDesktopPane();
        addMainMenu();
        addWelcomeMessage();

        setContentPane(desktop);
        pack();
        setVisible(true);
        desktop.setVisible(true);
    }

    public void addWelcomeMessage() {
        welcome = new JInternalFrame("Welcome!", true, false, false, false);
        JPanel panel = new JPanel();
        welcome.add(panel);
        panel.setLayout(new FlowLayout());
        setContentPane(desktop);

        JButton b1 = new JButton("Load account");
        JButton b2 = new JButton("Create a new account");
        b1.addActionListener(new Button1Listener());
        b1.setActionCommand("myButton1");
        b2.addActionListener(new Button2Listener());
        b2.setActionCommand("myButton2");
        panel.add(b1);
        panel.add(b2);

        welcome.pack();
        welcome.setVisible(true);
        desktop.add(welcome);
    }

    public void addMainMenu() {
        controlPanel = new JInternalFrame("Main Menu", true, false, false, false);
        controlPanel.setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        controlPanel.pack();
        controlPanel.setVisible(true);
        desktop.add(controlPanel);
    }



    public static void main(String[] args) {
        new PiggyBankGUI();
    }

    public class Button1Listener implements ActionListener {

        public Button1Listener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("myButton1")) {
                rootPane.getContentPane().removeAll();
                // loadPiggyBank();
                new MainMenuWindow();
            }
        }
    }

    public class Button2Listener implements ActionListener {

        public Button2Listener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand());
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the saved PiggyBank accounts
    public void loadPiggyBank() { //*
        try {
            myPiggyBank = jsonReader.read();
            JOptionPane.showMessageDialog(null, "Loaded " + myPiggyBank.getOwner() + "'s from "
                    + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Cannot read from " + JSON_STORE,
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
