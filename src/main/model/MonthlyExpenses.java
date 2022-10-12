package model;

// Represents a list of expenses that are to be paid every month
import java.util.ArrayList;
import model.Expense;

public class MonthlyExpenses {
    protected static ArrayList<Expense> monthlyExpenses;

    public MonthlyExpenses() {
        monthlyExpenses = new ArrayList<>();

    }

    public static void addExpense(Expense e) {
        monthlyExpenses.add(e);
    }

    public void removeExpense(Expense e) {
        monthlyExpenses.remove(e);
    }

    public void isMonthlyExpense(Expense e) {
        if (e.getStatus()) {
            MonthlyExpenses.addExpense(e);
        }
    }
}
