package model;

import java.util.ArrayList;

// Represents a list of expenses that are to be paid every month
public class MonthlyFinances {
    protected static ArrayList<Expense> monthlyFinances;
    protected double monthlyIncome;
    protected int percentToSave; //percent of monthly income that should be put into savings
    protected int percentToSaveRoughMonth; // percent of monthly income to put into savings when income is low

    public MonthlyFinances() {
        monthlyFinances = new ArrayList<>();
        monthlyIncome = 0;
    }

    // REQUIRES: e is not already in MonthlyFinances
    // MODIFIES: this
    // EFFECTS: adds e to MonthlyFinances if e is to be paid monthly
    public static void addExpense(Expense e) {
        if (e.getStatus()) {
            monthlyFinances.add(e);
        }
    }

    // REQUIRES: e is in MonthlyFinances
    // MODIFIES: this
    // EFFECTS: removes e from MonthlyFinances
    public void removeExpense(Expense e) {
        monthlyFinances.remove(e);
    }

    // EFFECTS: Getter for MonthlyFinances
    public ArrayList<Expense> getMonthlyFinances() {
        return this.monthlyFinances;
    }

    // EFFECTS: Getter for MonthlyIncome
    public double getMonthlyIncome() {
        return this.monthlyIncome;
    }

    // EFFECTS: Setter for MonthlyIncome
    public void setMonthlyIncome(double income) {
        this.monthlyIncome = income;
    }

    // EFFECTS: Getter for percentToSave
    public int getPercentToSave() {
        return this.percentToSave;
    }

    // EFFECTS: Getter for percentToSaveRoughMonth
    public int getPercentToSaveRoughMonth() {
        return this.percentToSaveRoughMonth;
    }
}
