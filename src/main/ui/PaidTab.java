package ui;

import model.Expense;
import model.MyPiggyBank;
import model.ThisMonthsFinances;

import javax.swing.*;
import java.util.ArrayList;

public class PaidTab {

    protected MyPiggyBank myPiggyBank;
    protected JTabbedPane desktop;
    protected MainMenuWindow main;

    private ThisMonthsFinances thisMonthsFinances;
    private JList<Expense> list = new JList<>();
    private DefaultListModel model = new DefaultListModel();
    private JLabel label = new JLabel();
    private JPanel panel = new JPanel();
    private JSplitPane paidTab = new JSplitPane();
    private JScrollPane scroller = new JScrollPane(list);

    public PaidTab(MyPiggyBank myPiggyBank, JTabbedPane desktop, MainMenuWindow main) {
        this.myPiggyBank = myPiggyBank;
        this.desktop = desktop;
        this.main = main;
        this.thisMonthsFinances = myPiggyBank.getThisMonthsFinances();
        this.paidTab.setVisible(true);
    }

    public void designPaidTab() {
        paidTab.remove(paidTab.getLeftComponent());
        list.setModel(model);
        paidTab.setLeftComponent(scroller);
        panel.add(label);
        paidTab.setRightComponent(panel);
        model.removeAllElements();
        ArrayList<Expense> expenseList1 = this.thisMonthsFinances.getNonMonthlyPaid();
        for (Expense e : expenseList1) {
            model.addElement(e);
        }
        ArrayList<Expense> expenseList2 = this.thisMonthsFinances.getPaidThisMonth();
        for (Expense e : expenseList2) {
            model.addElement(e);
        }
        getItem();
        paidTab.setVisible(true);
        paidTab.getLeftComponent().repaint();
        paidTab.getLeftComponent().revalidate();
    }

    public void getItem() {
        list.getSelectionModel().addListSelectionListener(e -> {
            Expense expense = list.getSelectedValue();
            if (expense.getStatus()) {
                label.setText("Title: " + expense.getTitle() + ", Amount: $" + expense.getExpenseAmount()
                        + ", Category: " + expense.getCategory() + ", Monthly Payment");
            } else {
                label.setText("Title: " + expense.getTitle() + ", Amount: $" + expense.getExpenseAmount()
                        + ", Category: " + expense.getCategory() + ", One-Time Payment");
            }
        });
    }

    public JSplitPane getPaidTab() {
        return this.paidTab;
    }
}
