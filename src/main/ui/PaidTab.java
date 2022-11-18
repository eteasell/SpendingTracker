package ui;

import model.Expense;
import model.MyPiggyBank;
import model.ThisMonthsFinances;

import javax.swing.*;
import java.util.ArrayList;

// Class representing the Paid Tab on desktop.
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

    // MODIFIES: this
    // EFFECTS: constructs a new PaidTab
    public PaidTab(MyPiggyBank myPiggyBank, JTabbedPane desktop, MainMenuWindow main) {
        this.myPiggyBank = myPiggyBank;
        this.desktop = desktop;
        this.main = main;
        this.thisMonthsFinances = myPiggyBank.getThisMonthsFinances();
        this.paidTab.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: constructs a JList of all previously paid expenses, and allows user to inspect each one
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void designPaidTab() {
        try {
            paidTab.remove(paidTab.getLeftComponent());
        } catch (Exception e) {
            System.out.println("ok don't do that");
        }
        list.setModel(model);
        paidTab.setLeftComponent(scroller);
        panel.add(label);
        paidTab.setRightComponent(panel);
        model.removeAllElements();
        ArrayList<Expense> expenseList1 = this.thisMonthsFinances.getNonMonthlyPaid();
        for (Expense e : expenseList1) {
            if (!model.contains(e)) {
                model.addElement(e);
            }
        }
        ArrayList<Expense> expenseList2 = this.thisMonthsFinances.getPaidThisMonth();
        for (Expense e : expenseList2) {
            if (!model.contains(e)) {
                model.addElement(e);
            }
        }
        getItem();
        paidTab.setVisible(true);
        paidTab.getLeftComponent().repaint();
        paidTab.getLeftComponent().revalidate();
    }

    // MODIFIES: this
    // EFFECTS: displays expense information for user-selected expense in JList
    public void getItem() {
        if (!model.isEmpty()) {
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
    }

    // Getter for this.paidTab
    public JSplitPane getPaidTab() {
        return this.paidTab;
    }
}
