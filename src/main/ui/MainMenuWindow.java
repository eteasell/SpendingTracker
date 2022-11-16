package ui;

import model.MyPiggyBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainMenuWindow {

    private final JTabbedPane desktop;
    private JPanel mainTab;
    private AddExpenseTab addExpenseTab;
    private MyPiggyBank myPiggyBank;
    private SeeMonthlyTab seeMonthlyTab;

    public MainMenuWindow(MyPiggyBank myPiggyBank, JTabbedPane desktop) {
        this.myPiggyBank = myPiggyBank;
        this.desktop = desktop;
        mainTab = new JPanel();
        addExpenseTab = new AddExpenseTab(myPiggyBank, desktop, this);
        seeMonthlyTab = new SeeMonthlyTab(myPiggyBank, desktop);
    }

    public void initializeMainMenu() {
        mainTab.setVisible(true);
        designMainTab();
        addExpenseTab.designAddExpenseTab();
        seeMonthlyTab.designSeeMonthlyTab();
        desktop.addTab("Main", mainTab);
        desktop.addTab("Add An Expense", addExpenseTab.getAddExpenseTab());
        desktop.addTab("See Monthly", seeMonthlyTab.getSeeMonthlyTab());
    }


    public void designMainTab() {
        mainTab.removeAll();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JLabel("Account Owner: " + myPiggyBank.getOwner()), BorderLayout.PAGE_START);
        double num = (double)Math.round(myPiggyBank.getCurrentBalance() * 100) / 100;
        panel.add(new JLabel("Current Balance: $" + num));
        panel.setVisible(true);
        mainTab.add(panel);
    }



}
