package ui;

import model.MyPiggyBank;
import model.MySpending;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuWindow {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    //private JTabbedPane mainMenu;
    private JPanel mainTab;
    private JPanel seeMonthlyTab;
    private JInternalFrame mainFrame;
    private MyPiggyBank myPiggyBank;

    public MainMenuWindow(MyPiggyBank myPiggyBank, JTabbedPane desktop) {
        this.myPiggyBank = myPiggyBank;
        //mainFrame = new JInternalFrame("Main Menu", true, false, false, false);
        //mainMenu = new JTabbedPane(JTabbedPane.BOTTOM);
        //mainMenu.setSize(WIDTH, HEIGHT);
        //mainFrame.add(mainMenu);
        mainTab = new JPanel();
        seeMonthlyTab = new JPanel();
        mainTab.setVisible(true);
        seeMonthlyTab.setVisible(true);
        designMainTab();
        //mainMenu.addTab("Main", mainTab);
        //mainMenu.addTab("See Monthly", seeMonthlyTab);
        //mainFrame.setSize(WIDTH, HEIGHT);
        //mainMenu.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        //mainMenu.setVisible(true);
        //mainFrame.setVisible(true);
        //mainFrame.pack();
        //mainFrame.add(mainMenu);
        // desktop.add(mainFrame);
        // desktop.setVisible(true);
        desktop.addTab("Main", mainTab);
        desktop.addTab("See Monthly", seeMonthlyTab);
    }


    // TODO: figure out how to properly round current balance
    // TODO: figure out how to format BorderLayout properly
    public void designMainTab() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JLabel("Account Owner: " + myPiggyBank.getOwner()), BorderLayout.PAGE_START);
        panel.add(new JLabel("Current Balance: $"
                + (double)Math.round(myPiggyBank.getCurrentBalance() * 100) / 100), BorderLayout.LINE_START);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());;
        panel2.setBackground(new Color(149, 167, 236));
        panel2.setSize(WIDTH, HEIGHT);
        panel2.add(new JButton("Testing"));
        panel2.setVisible(true);
        panel.add(panel2, BorderLayout.SOUTH);
        panel.setVisible(true);
        mainTab.add(panel);
    }

    public JPanel deliverMainTab() {
        return this.mainTab;
    }

    public JPanel deliverSeeMonthlyTab() {
        return this.seeMonthlyTab;
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
