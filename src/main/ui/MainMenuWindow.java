package ui;

import model.MyPiggyBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainMenuWindow {

    private final JTabbedPane desktop;
    private JPanel mainTab;
    private AddExpenseTab addExpenseTab;
    private JPanel seeMonthlyTab;
    private MyPiggyBank myPiggyBank;

    public MainMenuWindow(MyPiggyBank myPiggyBank, JTabbedPane desktop) {
        this.myPiggyBank = myPiggyBank;
        this.desktop = desktop;
        mainTab = new JPanel();
        addExpenseTab = new AddExpenseTab(myPiggyBank, desktop);
        seeMonthlyTab = new JPanel();
    }

    public void initializeMainMenu() {
        mainTab.setVisible(true);
        seeMonthlyTab.setVisible(true);
        designMainTab();
        addExpenseTab.designAddExpenseTab();
        desktop.addTab("Main", mainTab);
        desktop.addTab("Add An Expense", addExpenseTab.getAddExpenseTab());
        desktop.addTab("See Monthly", seeMonthlyTab);
    }


    public void designMainTab() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JLabel("Account Owner: " + myPiggyBank.getOwner()), BorderLayout.PAGE_START);
        panel.add(new JLabel("Current Balance: $"
                + (double)Math.round(myPiggyBank.getCurrentBalance() * 100) / 100), BorderLayout.LINE_START);
        panel.setVisible(true);
        mainTab.add(panel);
    }


    private class AddExpenseAction extends AbstractAction {

        private AddExpenseAction() {
            super("Add Expense");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // lead to new pop up to add expense
        }
    }

    private class SeeMonthlyAction extends AbstractAction {

        private SeeMonthlyAction() {
            super("See Monthly");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // lead to new window which shows List of monthly expense, expenses to be paid this month, overdue expenses,
            // this month's saving, and this month's spending
        }
    }
}
