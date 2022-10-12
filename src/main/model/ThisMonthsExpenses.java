package model;


import java.util.ArrayList;
import java.util.Calendar;
import model.MyPiggyBank;

//Represents the monthly expenses for the current month that have been or are yet to be paid
public class ThisMonthsExpenses extends MonthlyExpenses {

    private ArrayList<Expense> overdueExpenses;
    private Calendar rightNow = Calendar.getInstance();
    private ArrayList<Expense> thisMonthsExpenses;

    public ThisMonthsExpenses() {
        thisMonthsExpenses = new ArrayList<>();
        overdueExpenses = new ArrayList<>();
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
            for (Expense e : MonthlyExpenses.monthlyExpenses) {
                thisMonthsExpenses.add(e);
            }
        }
    }
}

