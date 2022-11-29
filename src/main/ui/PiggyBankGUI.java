package ui;

import model.EventLog;
import model.MyPiggyBank;
import model.Event;
import persistance.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;


// Main class for running project with GUI
// Code based off of examples from EdX/StackOverflow, LabelChanger, and AlarmSystem Project
public class PiggyBankGUI extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private JFrame frame;
    protected JTabbedPane desktop;
    protected WelcomeMessage welcome;

    private static final String JSON_STORE = "./data/MyPiggyBankAccount.json"; //*
    private final JsonWriter jsonWriter = new JsonWriter(JSON_STORE);

    private EventLog eventLog = EventLog.getInstance();

    // MODIFIES: this, WelcomeMessage
    // EFFECTS: constructs the main frame for GUI
    public PiggyBankGUI() { // frame setup from Java Tutorials example
        eventLog.clear();
        this.frame = new JFrame();
        this.frame.setTitle("The College Student's Piggy Bank");
        this.frame.setLayout(new BorderLayout());
        this.frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);

        this.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                customCloser();

            }

        });

        desktop = new JTabbedPane();
        desktop.setVisible(true);
        this.frame.add(desktop);
        this.frame.setContentPane(desktop);
        pack();

        welcome = new WelcomeMessage(this.desktop);
        welcome.getWelcome().setVisible(true);
    }


    // EFFECTS: saves this myPiggyBank to JSON
    public void savePiggyBank(MyPiggyBank myPiggyBank) {
        try {
            jsonWriter.open();
            jsonWriter.write(myPiggyBank);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, "Saved " + myPiggyBank.getOwner() + "'s "
                    + "account to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Unable to write to:  "
                    + JSON_STORE, JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to save application when the close button is clicked, then closes
    // application after response
    public void customCloser() { // citation: https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
        Object[] options = {"Yes", "No"};
        int n = JOptionPane.showOptionDialog(frame, "Would you like to save before you go?", "Wait!",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (n == JOptionPane.YES_OPTION) {
            MyPiggyBank acc = welcome.sendPiggyBankToGUI();
            savePiggyBank(acc);
            frame.dispose();
        } else if (n == JOptionPane.NO_OPTION) {
            frame.dispose();
        }
        for (Event event : eventLog) {
            System.out.println(event.toString() + "\n");
        }
        System.exit(0);
    }


    // EFFECTS: runs the program
    public static void main(String[] args) {
        new PiggyBankGUI();
    }


}
