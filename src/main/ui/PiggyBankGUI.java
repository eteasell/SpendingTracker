package ui;

import model.MyPiggyBank;
import ui.MyPiggyBankApp;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Main class for running project with GUI
// Code based off of example from EdX/StackOverflow, LabelChanger, and AlarmSystem Project
public class PiggyBankGUI extends JFrame implements ActionListener {

    private MyPiggyBankApp myApp = new MyPiggyBankApp();
    private JLabel label;

    public PiggyBankGUI() {
        super("The College Student's Piggy Bank");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(900, 700));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
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
        setVisible(true);
    }

    public static void main(String[] args) {
        new PiggyBankGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("myButton1")) {
            myApp.loadPiggyBank();
            // go to menu screen
        } else if (e.getActionCommand().equals("myButton2")) {
            // go to setup screen
        }
    }
}
