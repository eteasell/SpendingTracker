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
    private PaidTab paidTab;
    private ImageIcon image;

    public MainMenuWindow(MyPiggyBank myPiggyBank, JTabbedPane desktop) {
        this.myPiggyBank = myPiggyBank;
        this.desktop = desktop;
        mainTab = new JPanel();
        seeMonthlyTab = new SeeMonthlyTab(myPiggyBank, desktop, this);
        addExpenseTab = new AddExpenseTab(myPiggyBank, desktop, this, seeMonthlyTab);
        paidTab = new PaidTab(myPiggyBank, desktop, this);
    }

    public void initializeMainMenu() {
        mainTab.setVisible(true);
        designMainTab();
        addExpenseTab.designAddExpenseTab();
        seeMonthlyTab.designSeeMonthlyTab();
        paidTab.designPaidTab();
        desktop.addTab("Main", mainTab);
        desktop.addTab("Add An Expense", addExpenseTab.getAddExpenseTab());
        desktop.addTab("See Monthly", seeMonthlyTab.getSeeMonthlyTab());
        desktop.addTab("Paid", paidTab.getPaidTab());
    }


    // image code from https://www.youtube.com/watch?v=ntirmRhy6Fw
    public void designMainTab() {
        mainTab.removeAll();
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255, 0));
        panel.setLayout(new BorderLayout());
        panel.add(new JLabel("Account Owner: " + myPiggyBank.getOwner()), BorderLayout.PAGE_START);
        double num = (double)Math.round(myPiggyBank.getCurrentBalance() * 100) / 100;
        panel.add(new JLabel("Current Balance: $" + num), BorderLayout.LINE_START);
        try {
            image = new ImageIcon("./Data/pig.png");
            Image img = image.getImage();
            Image newImg = img.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
            image = new ImageIcon(newImg);
            JLabel imgLabel = new JLabel(image);
            imgLabel.setSize(imgLabel.getPreferredSize());
            //imgLabel.setSize(650, 600);
            imgLabel.setVisible(true);
            panel.add(imgLabel, BorderLayout.SOUTH);
        } catch (Exception e) {
            System.out.println("Image not found");
        }
        panel.setVisible(true);
        mainTab.add(panel);
    }

    public PaidTab getPaidTab() {
        return this.paidTab;
    }


}
