package ui;

import model.MonthlyFinances;
import model.MyPiggyBank;
import model.MySpending;
import model.ThisMonthsFinances;
import persistance.JsonReader;
import persistance.JsonWriter;
import ui.MyPiggyBankApp;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

// Main class for running project with GUI
// Code based off of example from EdX/StackOverflow, LabelChanger, and AlarmSystem Project
public class PiggyBankGUI extends JFrame implements ActionListener {

    private JLabel label;

    private static final String JSON_STORE = "./data/MyPiggyBankAccount.json"; //*
    private MyPiggyBank myPiggyBank;
    private MySpending mySpending;
    private MonthlyFinances myMonthlyFinances;
    private ThisMonthsFinances thisMonthsFinances;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public PiggyBankGUI() {
        super("The College Student's Piggy Bank");
        paneSetUp();
        JButton b1 = new JButton("Load account");
        JButton b2 = new JButton("Create a new account");
        b1.setActionCommand("myButton1");
        b1.addActionListener(this);
        b2.setActionCommand("myButton2");
        b2.addActionListener(this);
        label = new JLabel("Welcome!");
        add(label);
        add(b1);
        add(b2);
    }

    public void paneSetUp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(900, 700));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        setVisible(true);
    }

    public static void main(String[] args) {
        new PiggyBankGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("myButton1")) {
            loadPiggyBank();
            this.dispose();
            new MainMenu();
        } else if (e.getActionCommand().equals("myButton2")) {
            // go to setup screen
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the saved PiggyBank accounts
    public void loadPiggyBank() { //*
        try {
            myPiggyBank = jsonReader.read();
            System.out.println("Loaded " + myPiggyBank.getOwner() + "'s from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
