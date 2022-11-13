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
    private final JDesktopPane desktop;
    private JInternalFrame controlPanel;
    private JInternalFrame welcome;
    private JInternalFrame newAccountPanel;
    private JTextField name;
    private JTextField amount;
    private JTextField income;

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

    // Todo: fix the layout and functionality of these input fields
    public void addNewAccount() {
        newAccountPanel = new JInternalFrame("Please enter your information", true, false,
                false, false);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        newAccountPanel.add(panel);
        setSize(WIDTH, HEIGHT);
        setContentPane(desktop);

        panel.add(new JLabel("Account Owner:"));
        name = new JTextField();
        panel.add(name, LEFT_ALIGNMENT);
        panel.add(new JLabel("Current Account Balance:"));
        amount = new JTextField();
        panel.add(amount, RIGHT_ALIGNMENT);
        panel.add(new JLabel("Monthly Income:"));
        income = new JTextField();
        panel.add(income);
        JButton b3 = new JButton("Ok");
        b3.addActionListener(new Button3Listener());
        b3.setActionCommand("myButton3");
        panel.add(b3);
        newAccountPanel.pack();
        newAccountPanel.setVisible(true);
        desktop.add(newAccountPanel);
    }


    public class Button1Listener implements ActionListener {

        public Button1Listener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("myButton1")) {
                desktop.remove(welcome);
                loadPiggyBank();
                mySpending = myPiggyBank.getMySpending();
                myMonthlyFinances = myPiggyBank.getMyMonthlyFinances();
                thisMonthsFinances = myPiggyBank.getThisMonthsFinances();
                addMainMenu();
            }
        }
    }

    public class Button2Listener implements ActionListener {

        public Button2Listener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("myButton2")) {
                desktop.remove(welcome);
                addNewAccount();
            }
        }
    }

    public class Button3Listener implements ActionListener {

        public Button3Listener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("myButton3")) {
                if (name == null || amount == null || income == null) {
                    JOptionPane.showMessageDialog(null, "Please input your information!");
                    desktop.remove(newAccountPanel);
                    addNewAccount();
                } else {
                    String owner = name.getText();
                    String accountNum = amount.getText();
                    Double accNum = Double.parseDouble(accountNum);
                    String accountIncome = income.getText();
                    Double accIncome = Double.parseDouble(accountIncome);
                    myPiggyBank = new MyPiggyBank(owner, accNum);
                    mySpending = myPiggyBank.getMySpending();
                    myMonthlyFinances = myPiggyBank.getMyMonthlyFinances();
                    thisMonthsFinances = myPiggyBank.getThisMonthsFinances();
                    myMonthlyFinances.setMonthlyIncome(accIncome);
                    JOptionPane.showMessageDialog(null, "Created new account for " + owner);
                    desktop.remove(newAccountPanel);
                    addMainMenu();
                }
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: loads the saved PiggyBank accounts
    public void loadPiggyBank() { //*
        try {
            myPiggyBank = jsonReader.read();
            JOptionPane.showMessageDialog(null, "Loaded " + myPiggyBank.getOwner() + "'s "
                    + "account from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Cannot read from " + JSON_STORE,
                    JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        new PiggyBankGUI();
    }

}
