package model;

import java.util.ArrayList;

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
}
