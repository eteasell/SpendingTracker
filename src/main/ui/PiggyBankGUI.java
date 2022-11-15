package ui;

import model.MonthlyFinances;
import model.MyPiggyBank;
import model.MySpending;
import model.ThisMonthsFinances;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Main class for running project with GUI
// Code based off of examples from EdX/StackOverflow, LabelChanger, and AlarmSystem Project
public class PiggyBankGUI extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private JFrame frame;
    protected JTabbedPane desktop;

    private WelcomeMessage welcome;
    private JInternalFrame newAccountPanel;
    private MainMenuWindow mainMenu;
    private JTextField name;
    private JTextField amount;
    private JTextField income;

    private MyPiggyBank myPiggyBank;
    private MySpending mySpending;
    private MonthlyFinances myMonthlyFinances;
    private ThisMonthsFinances thisMonthsFinances;

    // move frames into different class and pass piggy bank through constructor!!!!

    public PiggyBankGUI() { // frame setup fromJava Tutorials example
        this.frame = new JFrame();
        this.frame.setTitle("The College Student's Piggy Bank");
        this.frame.setLayout(new BorderLayout());
        this.frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);

        desktop = new JTabbedPane();
        desktop.setVisible(true);
        this.frame.add(desktop);
        this.frame.setContentPane(desktop);
        pack();

        WelcomeMessage welcome = new WelcomeMessage(myPiggyBank, desktop);
        welcome.getWelcome().setVisible(true);
        //getContentPane().add(welcome.getWelcome());
    }

    public static void main(String[] args) {
        new PiggyBankGUI();
    }

}
