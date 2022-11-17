package ui;

import model.MonthlyFinances;
import model.MyPiggyBank;
import model.Expense;
import model.ThisMonthsFinances;

import javax.swing.*;
import java.awt.*;
import java.time.Month;
import java.util.ArrayList;

public class SeeMonthlyTab {

    protected MyPiggyBank myPiggyBank;
    protected JTabbedPane desktop;

    private ThisMonthsFinances thisMonthsFinances;
    private JList<Expense> list;
    private DefaultListModel<Expense> model;

    private JLabel label = new JLabel();
    private JPanel panel = new JPanel();
    private JSplitPane seeMonthlyTab = new JSplitPane();

// https://www.youtube.com/watch?v=KOI1WbkKUpQ helpful tutorial and citation for this class

    public SeeMonthlyTab(MyPiggyBank myPiggyBank, JTabbedPane desktop) {
        this.myPiggyBank = myPiggyBank;
        this.thisMonthsFinances = myPiggyBank.getThisMonthsFinances();
        this.desktop = desktop;
        this.seeMonthlyTab.setVisible(true);
    }

    public void designSeeMonthlyTab() {
        list = new JList<>();
        model = new DefaultListModel<>();
        list.setModel(model);
        seeMonthlyTab.setLeftComponent(new JScrollPane(list));
        panel.add(label);
        seeMonthlyTab.setRightComponent(panel);
        ArrayList<Expense> expenseList = this.thisMonthsFinances.getThisMonthsExpenses();
        for (Expense e : expenseList) {
            model.addElement(e);
        }
    }

    public JSplitPane getSeeMonthlyTab() {
        return this.seeMonthlyTab;
    }

}
