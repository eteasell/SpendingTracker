package ui;

import model.MonthlyFinances;
import model.MyPiggyBank;
import model.Expense;
import model.ThisMonthsFinances;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SeeMonthlyTab {

    protected MyPiggyBank myPiggyBank;
    protected JTabbedPane desktop;
    protected MainMenuWindow main;

    private ThisMonthsFinances thisMonthsFinances;
    private JList<Expense> list;
    private DefaultListModel model;
    private JButton button = new JButton("Pay selected expense");
  //  private JScrollPane scrollPane = new JScrollPane(list);

    private JLabel label = new JLabel();
    private JPanel panel = new JPanel();
    private JSplitPane seeMonthlyTab = new JSplitPane();
    private MyButtonListener myListener = new MyButtonListener(null);

// https://www.youtube.com/watch?v=KOI1WbkKUpQ helpful tutorial and citation for this class

    public SeeMonthlyTab(MyPiggyBank myPiggyBank, JTabbedPane desktop, MainMenuWindow main) {
        this.myPiggyBank = myPiggyBank;
        this.thisMonthsFinances = myPiggyBank.getThisMonthsFinances();
        this.desktop = desktop;
        this.main = main;
        this.seeMonthlyTab.setVisible(true);
    }

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


    public JSplitPane getSeeMonthlyTab() {
        return this.seeMonthlyTab;
    }

    public class MyButtonListener implements ActionListener {

        private Expense expense;

        public MyButtonListener(Expense e) {
            this.expense = e;
        }

        public void setExpense(Expense e) {
            this.expense = e;
        }

        public Expense getExpense() {
            return this.expense;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("pay")) {
                try {
                    myPiggyBank.pay(expense);
                    main.designMainTab();
                    model.removeElement(expense);
                } catch (Exception ex) {
                    System.out.println("list changed");
                }
            }
        }
    }

}
