package ui;

import model.Expense;
import model.MonthlyFinances;
import model.MyPiggyBank;
import model.ThisMonthsFinances;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public AddExpenseTab(MyPiggyBank myPiggyBank, JTabbedPane desktop, MainMenuWindow main, SeeMonthlyTab monthly) {
        this.myPiggyBank = myPiggyBank;
        this.thisMonthsFinances = myPiggyBank.getThisMonthsFinances();
        this.desktop = desktop;
        this.main = main;
        this.monthly = monthly;
        this.addExpenseTab = new JPanel();
        this.addExpenseTab.setVisible(true);
    }

    public void designAddExpenseTab() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setSize(WIDTH, HEIGHT);

        generateInputForm();

        panel.add(Box.createVerticalGlue()); //* Stack
        panel.add(Box.createRigidArea(new Dimension(20, 20))); //* Stack
        panel.setBackground(new Color(217, 221, 245));
        panel.setSize(WIDTH, HEIGHT);
        panel.setVisible(true);
        addExpenseTab.add(panel, BorderLayout.CENTER);
    }


    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void generateInputForm() {
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

        panel.add(new JLabel("If this expense is not to be paid monthly, clicking the 'ADD' button will pay"
                + " the expense right now."));
        panel.add(new JLabel("If this expense is to be paid monthly, you will need to select it from"
                + " your 'See Monthly' list to pay it."));
        add = new JButton("ADD");
        panel.add(add);
        add.addActionListener(new AddButtonListener());
        add.setActionCommand("add");
    }

    public JPanel getAddExpenseTab() {
        return this.addExpenseTab;
    }

    public class AddButtonListener implements ActionListener {

        private String expTitle;
        private String expAmnt;
        private double expAmount;
        private boolean paidMonthly = false;
        private String category = null;

        public AddButtonListener() {
            String expTitle = title.getText();
            String expAmnt = amount.getText();
            double expAmount;
        }

        @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("add")) {
                expTitle = title.getText();
                expAmnt = amount.getText();
                expAmount = Double.parseDouble(expAmnt);

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
                    paidMonthly = true;
                    Expense expense = new Expense(expTitle, expAmount, true, category);
                    thisMonthsFinances.getThisMonthsExpenses().add(expense);
                    monthly.designSeeMonthlyTab();
                    main.getPaidTab().designPaidTab();
                } else if (no.isSelected()) {
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
        // TODO: FIX HERE
    }
}
