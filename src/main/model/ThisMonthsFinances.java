package model;


import java.util.ArrayList;
import java.util.Calendar;

//TODO: This class needs some cleaning up

//Represents the monthly expenses for the current month that have been or are yet to be paid
public class ThisMonthsFinances extends MonthlyFinances {

    private ArrayList<Expense> overdueExpenses;
    private Calendar rightNow = Calendar.getInstance();
    private ArrayList<Expense> thisMonthsExpenses;
    private double thisMonthsSaving;
    private double thisMonthsSpending;

    public ThisMonthsFinances() {
        thisMonthsExpenses = new ArrayList<>();
        overdueExpenses = new ArrayList<>();
    }

    //check if this month's income is more or less than usual.
    //divide into spending and saving categories
    public void thisMonthsIncome(double income) {
        if (income >= monthlyIncome) {
            thisMonthsSaving = income * percentToSave;
        } else {
            thisMonthsSaving = income * percentToSaveRoughMonth;
        }
        thisMonthsSpending = income - thisMonthsSaving;
    }


    // REQUIRES: Expense e is in ThisMonthsExpenses
    // This method should remove the given expense from ThisMonthsExpenses but NOT from Monthly
    // Expenses, and subtract the expense amount from MyPiggyBank
    public void payExpense(Expense e) {
        MyPiggyBank.myAccount.pay(e);
        thisMonthsExpenses.remove(e);
    }

    // This method should recognize when the first of the month hits, store any unpaid expenses
    // as a new list 'unpaid', and reset ThisMonthsExpenses to match MonthlyExpenses again.
    public void resetMonth() {
        if ((rightNow.DAY_OF_MONTH) == 1) {
            if (thisMonthsExpenses.size() > 0) {
                for (Expense e : thisMonthsExpenses) {
                    overdueExpenses.add(e);
                    thisMonthsExpenses.remove(e);
                }
            }
            for (Expense e : MonthlyFinances.monthlyFinances) {
                thisMonthsExpenses.add(e);
            }
        }
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
        return thisMonthsSaving;
    }

    //Getter for thisMonthsSpending
    public double getThisMonthsSpending() {
        return thisMonthsSpending;
    }
}

