package ui;

import model.Expense;
import model.MyPiggyBank;
import model.ThisMonthsFinances;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PaidTab {

    protected MyPiggyBank myPiggyBank;
    protected JTabbedPane desktop;
    protected MainMenuWindow main;

    private ThisMonthsFinances thisMonthsFinances;
    private JList<Expense> list1;
    private DefaultListModel model1;
    private JList<Expense> list2;
    private DefaultListModel model2;
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JSplitPane paidTab = new JSplitPane();

    public PaidTab(MyPiggyBank myPiggyBank, JTabbedPane desktop, MainMenuWindow main) {
        this.myPiggyBank = myPiggyBank;
        this.desktop = desktop;
        this.main = main;
        this.thisMonthsFinances = myPiggyBank.getThisMonthsFinances();
    }

    public void designPaidTab() {
        paidTab.removeAll();
        list1 = new JList<>();
        model1 = new DefaultListModel();
        list1.setModel(model1);
        list2 = new JList<>();
        model2 = new DefaultListModel();
        list2.setModel(model2);

        panel1.setLayout(new FlowLayout());
        panel2.setLayout(new FlowLayout());
        paidTab.setLeftComponent(panel1);
        paidTab.setRightComponent(panel2);

        panel1.add(new JLabel("Non Monthly Expenses"));
        panel1.add(new JScrollPane(list1));
        panel2.add(new JLabel("Monthly Expenses"));
        panel2.add(new JScrollPane(list2));

        ArrayList<Expense> expenseList1 = this.thisMonthsFinances.getNonMonthlyPaid();
        for (Expense e : expenseList1) {
            model1.addElement(e);
        }
        ArrayList<Expense> expenseList2 = this.thisMonthsFinances.getPaidThisMonth();
        for (Expense e : expenseList2) {
            model2.addElement(e);
        }
    }

    public JSplitPane getPaidTab() {
        return this.paidTab;
    }
}
