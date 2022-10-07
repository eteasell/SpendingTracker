package model;

// Represents an expense, either to be paid one time or monthly, with an amount and a name
public class Expense {
    private String title;
    private double expenseAmount;
    private Boolean dueMonthly; // True if the expense is to be paid monthly, false otherwise

    public Expense(String title, double expenseAmount, Boolean status) {
        this.title = title;
        this.expenseAmount = expenseAmount;
        this.dueMonthly = status;
    }

    private void isMonthlyExpense() {
        //if (this.dueMonthly)
    }

    public String getTitle() {
        return this.title;
    }

    public double getExpenseAmount() {
        return this.expenseAmount;
    }

}
