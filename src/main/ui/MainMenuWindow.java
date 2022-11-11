package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuWindow extends PiggyBankGUI {

    protected JPanel buttonPanel;

    public MainMenuWindow() {
        addPanel();
        JLabel label = new JLabel("Testing");
        add(label);
    }

    public void addPanel() { // code based off of 'AlarmSystem' example
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,2));
        buttonPanel.add(new JButton(new AddExpenseAction()));
        buttonPanel.add(new JButton(new SeeMonthlyAction()));
        rootPane.add(buttonPanel);
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
