package ui;

import model.MyPiggyBank;
import model.ThisMonthsFinances;
import persistance.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Class representing the pop-up welcome message and new account frame
public class WelcomeMessage extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private MyPiggyBank myPiggyBank;
    private ThisMonthsFinances thisMonthsFinances;
    private JTextField name;
    private JTextField amount;
    private JTextField income;

    private JTabbedPane desktop;
    private JDialog welcome;
    private JPanel panel;
    private JInternalFrame newAccountPanel;
    private MainMenuWindow mainMenu;

    private static final String JSON_STORE = "./data/MyPiggyBankAccount.json"; //*
    private final JsonReader jsonReader = new JsonReader(JSON_STORE);

    // MODIFIES: this
    // EFFECTS: constructs a new WelcomeMessage
    public WelcomeMessage(JTabbedPane desktop) {
        this.desktop = desktop;
        welcome = new JDialog();
        welcome.setLocationRelativeTo(desktop);
        panel = new JPanel();
        welcome.add(panel);
        panel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Welcome!");
        JButton b1 = new JButton("Load account");
        JButton b2 = new JButton("Create a new account");
        b1.addActionListener(new Button1Listener());
        b1.setActionCommand("myButton1");
        b2.addActionListener(new Button2Listener());
        b2.setActionCommand("myButton2");
        panel.add(label);
        panel.add(b1);
        panel.add(b2);

        welcome.pack();
        welcome.setVisible(true);
    }

    // Getter for this.welcome
    public JDialog getWelcome() {
        return this.welcome;
    }

    // MODIFIES: this, PiggyBankGUI
    // EFFECTS: constructs a panel which allows user in input name, account balance and monthly income
    public void addNewAccount() {
        newAccountPanel = new JInternalFrame("Please enter your information", true, false,
                false, false);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        newAccountPanel.add(panel);
        newAccountPanel.setSize(WIDTH, HEIGHT);

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

    // Hidden Button Listener class for LOAD ACCOUNT button
    public class Button1Listener implements ActionListener {

        public Button1Listener() {
        }

        // MODIFIES: this, MyPiggyBank, PiggyBankGUI, MainMenuWindow
        // EFFECTS: responds to user command and loads previously saved account. Brings user to main menu.
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("myButton1")) {
                welcome.setVisible(false);
                loadPiggyBank();
                desktop.remove(welcome);
                mainMenu = new MainMenuWindow(myPiggyBank, desktop);
                mainMenu.initializeMainMenu();
            }
        }
    }

    // Hidden Button Listener class for CREATE NEW ACCOUNT button
    public class Button2Listener implements ActionListener {

        public Button2Listener() {
        }

        // MODIFIES: this, PiggyBankGUI
        // EFFECTS: responds to user input and calls to construct New Account panel
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("myButton2")) {
                welcome.setVisible(false);
                addNewAccount();
            }
        }
    }

    // Hidden Button Listener class to initialize a new PiggyBank account from user input
    public class Button3Listener implements ActionListener {

        public Button3Listener() {
        }

        // MODIFIES: this, MainMenuWindow, PiggyBankGUI
        // EFFECTS: Responds to user text input by constructing a new MyPiggyBank account with
        // given name, balance, and income
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
                    double accNum = Double.parseDouble(accountNum);
                    String accountIncome = income.getText();
                    double accIncome = Double.parseDouble(accountIncome);
                    myPiggyBank = new MyPiggyBank(owner, accNum);
                    thisMonthsFinances = myPiggyBank.getThisMonthsFinances();
                    JOptionPane.showMessageDialog(null, "Created new account for " + owner);
                    desktop.remove(newAccountPanel);
                    MainMenuWindow mainMenu = new MainMenuWindow(myPiggyBank, desktop);
                    mainMenu.initializeMainMenu();
                }
            }
        }
    }

    // MODIFIES: MyPiggyBank
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

    // EFFECTS: send this myPiggyBank to PiggyBankGUI to it can be accessed to save later
    public MyPiggyBank sendPiggyBankToGUI() {
        return this.myPiggyBank;
    }


}
