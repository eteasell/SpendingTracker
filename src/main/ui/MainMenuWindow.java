package ui;

import model.MyPiggyBank;

import javax.swing.*;
import java.awt.*;

// Class representing the main window on desktop
public class MainMenuWindow {

    private AddExpenseTab addExpenseTab;
    private MyPiggyBank myPiggyBank;
    private SeeMonthlyTab seeMonthlyTab;
    private PaidTab paidTab;

    private final JTabbedPane desktop;
    private JPanel mainTab;
    private ImageIcon image;

    // MODIFIES: this
    // EFFECTS: Constructs new MainMenuWindow
    public MainMenuWindow(MyPiggyBank myPiggyBank, JTabbedPane desktop) {
        this.myPiggyBank = myPiggyBank;
        this.desktop = desktop;
        mainTab = new JPanel();
        seeMonthlyTab = new SeeMonthlyTab(myPiggyBank, desktop, this);
        addExpenseTab = new AddExpenseTab(myPiggyBank, desktop, this, seeMonthlyTab);
        paidTab = new PaidTab(myPiggyBank, desktop, this);
    }

    // MODIFIES: this, PiggyBankGUI, AddExpenseTab, SeeMonthlyTab, PaidTab
    // EFFECTS: sets up the desktop by adding all tabs
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


    // Citation: code relating to images is from https://www.youtube.com/watch?v=ntirmRhy6Fw
    // MODIFIES: this
    // EFFECTS: constructs the mainTab
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
            imgLabel.setVisible(true);
            panel.add(imgLabel, BorderLayout.SOUTH);
        } catch (Exception e) {
            System.out.println("Image not found");
        }
        panel.setVisible(true);
        mainTab.add(panel);
    }

    // Getter for this.paidTab
    public PaidTab getPaidTab() {
        return this.paidTab;
    }


}
