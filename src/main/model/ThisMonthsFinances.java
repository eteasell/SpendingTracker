package model;

import java.util.ArrayList;
import java.util.Calendar;

//Represents the monthly expenses for the current month that are yet to be paid
public class ThisMonthsFinances extends MonthlyFinances {

    private ArrayList<Expense> overdueExpenses; // expenses from last month that were not paid
    private Calendar rightNow = Calendar.getInstance();
    private int dayOfMonth = rightNow.DAY_OF_MONTH;
    private ArrayList<Expense> thisMonthsExpenses; // expenses remaining this month to be paid
    private ArrayList<Expense> paidThisMonth;
    private ArrayList<Expense> nonMonthlyPaid;
    private double thisMonthsSaving; // amount to be put into savings this month
    private static double thisMonthsSpending; // amount left to spend this month

    public ThisMonthsFinances() {
        this.thisMonthsExpenses = new ArrayList<>();
        this.overdueExpenses = new ArrayList<>();
        this.paidThisMonth = new ArrayList<>();
        this.nonMonthlyPaid = new ArrayList<>();
    }

    // REQUIRES: requires income >= 0
    // MODIFIES: this
    // EFFECTS: checks if this month's income is more or equal than normal, divides into spending
    // and saving categories accordingly
    public void thisMonthsIncome(double income) {
        if (income >= monthlyIncome) {
            this.thisMonthsSaving = income * percentToSave;
        } else {
            this.thisMonthsSaving = income * percentToSaveRoughMonth;
        }
        thisMonthsSpending = income - this.thisMonthsSaving;
    }

    // EFFECTS: calls the correct pay method depending on expense status
    public void payExpense(Expense e) {
        thisMonthsSpending = thisMonthsSpending - e.getExpenseAmount();
        if (e.getStatus()) {
            payMonthly(e);
        } else {
            payNonMonthly(e);
        }
    }

    // MODIFIES: this
    // EFFECTS: if the expense is in thisMonthsExpenses, removes the given expense from
    // ThisMonthsExpenses but NOT from Monthly Expenses, and updates thisMonthsSpending
    public void payMonthly(Expense e) {
        this.thisMonthsExpenses.remove(e);
        this.paidThisMonth.add(e);
        EventLog.getInstance().logEvent(new Event(""));
    }

    // MODIFIES: this
    // EFFECTS: adds not monthly payment to a one time paid list
    public void payNonMonthly(Expense e) {
        this.nonMonthlyPaid.add(e);
        EventLog.getInstance().logEvent(new Event("Paid new expense titled: " + e.getTitle()));
    }


    // MODIFIES: This
    // EFFECTS: if it is the first day of the month, stores any unpaid expenses in overdueExpenses,
    // and resets ThisMonthsExpenses to match MonthlyExpenses again.
    public void resetMonth() {
        if ((dayOfMonth) == 1) {  // should this be switched to manual reset? Does the automatic work??
            if (thisMonthsExpenses.size() > 0) {
                overdueExpenses.addAll(thisMonthsExpenses);
            }
            thisMonthsExpenses.removeAll(thisMonthsExpenses);
            thisMonthsExpenses.addAll(MonthlyFinances.monthlyFinances);
        }
    }

    // EFFECTS: returns expense in list that natches name
    public Expense getSingleExpense(String name) {
        for (Expense e : thisMonthsExpenses) {
            if (name.equals(e.getTitle())) {
                return e;
            }
        }
        return null;
    }

    // MODIFIES: this, MyPiggyBank
    // EFFECTS: adds expense to thisMonthsExpenses
    public void addToThisMonthsExpense(Expense e) {
        thisMonthsExpenses.add(e);
        EventLog.getInstance().logEvent(new Event("Added new monthly expense titled: " + e.getTitle()));
    }

    //EFFECTS: adds an expense to overdue expenses
    public void addOverdueExpense(Expense e) {
        overdueExpenses.add(e);
    }

    //Getter for paidThisMonth
    public ArrayList<Expense> getPaidThisMonth() {
        return paidThisMonth;
    }

    //Getter for nonMonthlyPaid
    public ArrayList<Expense> getNonMonthlyPaid() {
        return nonMonthlyPaid;
    }

    //Getter for overdueExpenses
    public ArrayList<Expense> getOverdueExpenses() {
        return overdueExpenses;
    }

    //Getter for thisMonthsExpenses
    public ArrayList<Expense> getThisMonthsExpenses() {
        return thisMonthsExpenses;
    }

    //Getter for thisMonthsSaving
    public double getThisMonthsSaving() {
        return this.thisMonthsSaving;
    }

    //Getter for thisMonthsSpending
    public double getThisMonthsSpending() {
        return thisMonthsSpending;
    }

    //Setter for thisMonthsSpending for testing purposes
    public void setThisMonthsSpending(double amount) {
        thisMonthsSpending = amount;
    }

    //Setter for thisMonthsSpending for testing purposes
    public void setThisMonthsSaving(double amount) {
        thisMonthsSaving = amount;
    }

    //Setter for rightNow for testing purposes
    public void setDayOfMonth(int i) {
        this.dayOfMonth = i;
    }

    //Setter for thisMonthsExpenses for testing purposes
    public void addToThisMonthsExpenses(Expense e) {
        thisMonthsExpenses.add(e);
    }
}

