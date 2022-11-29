package ui;

import model.Event;
import model.Expense;
import model.MyPiggyBank;
import model.ThisMonthsFinances;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Class representing tab for adding new expenses
public class AddExpenseTab {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private JSeparator sep = new JSeparator();

    protected MyPiggyBank myPiggyBank;
    protected ThisMonthsFinances thisMonthsFinances;
    private JTabbedPane desktop;
    private MainMenuWindow main;
    private SeeMonthlyTab monthly;

    private JPanel addExpenseTab;
    private JPanel panel = new JPanel();
    private static JTextField title;
    private static JTextField amount;
    private static JRadioButton yes;
    private static JRadioButton no;
    private static JRadioButton needs;
    private static JRadioButton fun;
    private static JRadioButton food;
    private static JRadioButton shopping;
    private JButton add;

    // MODIFIES: this
    // EFFECTS: constructs a new AddExpenseTab
    public AddExpenseTab(MyPiggyBank myPiggyBank, JTabbedPane desktop, MainMenuWindow main, SeeMonthlyTab monthly) {
        this.myPiggyBank = myPiggyBank;
        this.thisMonthsFinances = myPiggyBank.getThisMonthsFinances();
        this.desktop = desktop;
        this.main = main;
        this.monthly = monthly;
        this.addExpenseTab = new JPanel();
        this.addExpenseTab.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: designs the AddExpenseTab
    public void designAddExpenseTab() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setSize(WIDTH, HEIGHT);

        generateInputForm();

        panel.add(Box.createVerticalGlue()); //* From StackOverflow
        panel.add(Box.createRigidArea(new Dimension(20, 20))); //* From StackOverflow
        panel.setBackground(new Color(247, 209, 248));
        panel.setSize(WIDTH, HEIGHT);
        panel.setVisible(true);
        addExpenseTab.add(panel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: produces the panel for AddExpenseTab which allows the user to input expense information
    public void generateInputForm() {
        panelAdder();
        labelAdder();
        add = new JButton("ADD");
        panel.add(add);
        add.addActionListener(new AddButtonListener());
        add.setActionCommand("add");
    }

    // MODIFIES: this
    // EFFECTS: adds the text fields and radio buttons onto panel for user input
    public void panelAdder() {
        panel.add(new JLabel("Enter the information below to add a new expense..."));
        panel.add(sep);
        panel.add(new JLabel("Expense title:"));
        title = new JTextField();
        panel.add(title);
        panel.add(new JLabel("Amount to be paid: (Do not type in the '$' sign)"));
        amount = new JTextField();
        panel.add(amount);
        panel.add(new JLabel("Is this expense to be paid monthly? Choose one option only."));
        yes = new JRadioButton("Yes");
        panel.add(yes);
        no = new JRadioButton("No");
        panel.add(no);
        panel.add(new JLabel("Choose a category that best fits this expense. Choose one option only."));
        needs = new JRadioButton("Needs");
        panel.add(needs);
        fun = new JRadioButton("Fun");
        panel.add(fun);
        food = new JRadioButton("Food");
        panel.add(food);
        shopping = new JRadioButton("Shopping");
        panel.add(shopping);
    }

    // MODIFIES: this
    // EFFECTS: adds the instructions to the bottom of the AddExpense panel
    public void labelAdder() {
        panel.add(new JLabel("If this expense is not to be paid monthly, clicking the 'ADD' button will pay"
                + " the expense right now."));
        panel.add(new JLabel("If this expense is to be paid monthly, you will need to select it from"
                + " your 'See Monthly' list to pay it."));
    }

    // Getter for AddExpenseTab
    public JPanel getAddExpenseTab() {
        return this.addExpenseTab;
    }

    // Hidden class for the button listener corresponding to the ADD button on the AddExpenseTab
    public class AddButtonListener implements ActionListener {

        private String expTitle;
        private String expAmnt;
        private double expAmount;
        private boolean paidMonthly = false;
        private String category = null;

        // Hidden class constructor
        public AddButtonListener() {
        }

        // REQUIRES: Title contains a string. Amount contains a String of double form. One and only one of
        // needs, fun, food, shopping is selected. One and only one of yes, no is selected.
        // MODIFIES: this, expense, thisMonthsFinances, seeMonthlyTab, paidTab, main
        // EFFECTS: Turns user input into a new Expense.
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("add")) {
                textBoxResponse();
                if (needs.isSelected()) {
                    category = "Needs";
                }
                if (fun.isSelected()) {
                    category = "Fun";
                }
                if (food.isSelected()) {
                    category = "Food";
                }
                if (shopping.isSelected()) {
                    category = "Shopping";
                }
                if (yes.isSelected()) {
                    yesOption();
                } else if (no.isSelected()) {
                    noOption();
                }
            }
        }

        // REQUIRES: title, amount non-null. Amount string in double form.
        // MODIFIES: this
        // EFFECTS: read text user input
        public void textBoxResponse() {
            expTitle = title.getText();
            expAmnt = amount.getText();
            expAmount = Double.parseDouble(expAmnt);
        }

        // MODIFIES: expense, thisMonthsFinances, seeMonthlyTab, paidTab
        // EFFECTS: constructs appropriate monthly expense, sends to thisMonthsExpenses, and updates seeMonthly
        // and paid tabs.
        public void yesOption() {
            paidMonthly = true;
            Expense expense = new Expense(expTitle, expAmount, true, category);
            thisMonthsFinances.addToThisMonthsExpense(expense);
            monthly.designSeeMonthlyTab();
            main.getPaidTab().designPaidTab();
        }

        // MODIFIES: expense, myPiggyBank, main, paidTab
        // EFFECTS: constructs appropriate one-time expense, sends to myPiggyBank, and updates main and paid tabs.
        public void noOption() {
            Expense expense = new Expense(expTitle, expAmount, false, category);
            myPiggyBank.pay(expense);
            main.designMainTab();
            try {
                main.getPaidTab().designPaidTab();
            } catch (Exception exception) {
                System.out.println("list changed again");
            }
        }
    }
}
