package ui;

import model.MonthlyFinances;
import model.MyPiggyBank;
import model.Expense;

import javax.swing.*;
import java.awt.*;
import java.time.Month;

public class SeeMonthlyTab {

    protected MyPiggyBank myPiggyBank;
    protected JTabbedPane desktop;

    private MonthlyFinances myMonthlyFinances;
    private JList<Expense> list = new JList<>();
    private DefaultListModel<Expense> model = new DefaultListModel<>();

    private JLabel label = new JLabel();
    private JPanel panel = new JPanel();
    private JSplitPane seeMonthlyTab = new JSplitPane();

// https://www.youtube.com/watch?v=KOI1WbkKUpQ helpful tutorial and citation for this class

    public SeeMonthlyTab(MyPiggyBank myPiggyBank, JTabbedPane desktop) {
        this.myPiggyBank = myPiggyBank;
        this.myMonthlyFinances = myPiggyBank.getMyMonthlyFinances();
        this.desktop = desktop;
        this.seeMonthlyTab.setVisible(true);
    }

    public void designSeeMonthlyTab() {
        list.setModel(model);
        seeMonthlyTab.setLeftComponent(new JScrollPane(list));
        panel.add(label);
        seeMonthlyTab.setRightComponent(panel);
    }

    public JSplitPane getSeeMonthlyTab() {
        return this.seeMonthlyTab;
    }

}
