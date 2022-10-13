package model;

// Represents a list of expenses that are to be paid every month
import java.util.ArrayList;

public class MonthlyFinances {
    protected static ArrayList<Expense> monthlyFinances;
    protected double monthlyIncome;
    protected int percentToSave; //percent of monthly income that should be put into savings
    protected int percentToSaveRoughMonth;

    public MonthlyFinances() {
        monthlyFinances = new ArrayList<>();
        monthlyIncome = 0;
    }

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

    // EFFECTS: Setter for MonthlyIncome
    public void setMonthlyIncome(double income) {
        this.monthlyIncome = income;
    }
}
