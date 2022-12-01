package ui;

import model.MyPiggyBank;
import model.Expense;
import model.ThisMonthsFinances;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Class for the tab to show this month's expenses left to pay
// Citation: https://www.youtube.com/watch?v=KOI1WbkKUpQ
public class SeeMonthlyTab {

    protected MyPiggyBank myPiggyBank;
    protected JTabbedPane desktop;
    protected MainMenuWindow main;

    private ThisMonthsFinances thisMonthsFinances;
    private JList<Expense> list;
    private DefaultListModel model;
    private JButton button = new JButton("Pay selected expense");

    private JLabel label = new JLabel();
    private JPanel panel = new JPanel();
    private JSplitPane seeMonthlyTab = new JSplitPane();
    private MyButtonListener myListener = new MyButtonListener(null);

    // MODIFIES: this
    // EFFECTS: constructs a new SeeMonthlyTab
    public SeeMonthlyTab(MyPiggyBank myPiggyBank, JTabbedPane desktop, MainMenuWindow main) {
        this.myPiggyBank = myPiggyBank;
        this.thisMonthsFinances = myPiggyBank.getThisMonthsFinances();
        this.desktop = desktop;
        this.main = main;
        this.seeMonthlyTab.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: displays tab of expenses left to be paid this month, and allows expenses to be selected and paid.
    public void designSeeMonthlyTab() {
        list = new JList<>();
        model = new DefaultListModel<>();
        list.setModel(model);

        list.getSelectionModel().addListSelectionListener(e -> {
            Expense expense = list.getSelectedValue();
            label.setText("Title: " + expense.getTitle() + ", Amount: $" + expense.getExpenseAmount()
                    + ", Category: " + expense.getCategory());
            myListener.setExpense(expense);
        });

        button.setActionCommand("pay");
        button.addActionListener(myListener);

        panel.add(button);
        panel.add(label);
        seeMonthlyTab.setRightComponent(panel);
        ArrayList<Expense> expenseList = this.thisMonthsFinances.getThisMonthsExpenses();
        seeMonthlyTab.setLeftComponent(new JScrollPane(list));
        for (Expense e : expenseList) {
            model.addElement(e);
        }

    }

    // Getter for this.seeMonthlyTab
    public JSplitPane getSeeMonthlyTab() {
        return this.seeMonthlyTab;
    }

    // Hidden Button Listener class for PAY button
    public class MyButtonListener implements ActionListener {

        private Expense expense;

        // EFFECTS: constructs a button listener custom to the selected expense
        public MyButtonListener(Expense e) {
            this.expense = e;
        }

        // EFFECTS: setter for expense
        public void setExpense(Expense e) {
            this.expense = e;
        }

        // EFFECTS: getter for expense
        public Expense getExpense() {
            return this.expense;
        }

        // MODIFIES: this, Main, MyPiggyBank, PaidTab
        // EFFECTS: pays the selected expense, and updates myPiggyBank, MainTab, and PaidTab accordingly
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("pay")) {
                try {
                    myPiggyBank.pay(expense);
                    main.designMainTab();
                    model.removeElement(expense);
                    main.getPaidTab().designPaidTab();
                } catch (Exception ex) {
                    System.out.println("");
                }
            }
        }
    }

}
