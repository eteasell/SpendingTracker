package model;

import model.Expense;
import model.MonthlyExpenses;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExpenseTest {

    public Expense testExpenseCar;
    public Expense testExpenseNails;
    public MonthlyExpenses testMonthlyExpenses;

    @BeforeEach
    public void setup() {
        testExpenseCar = new Expense("Car", 500, true);
        testExpenseNails =  new Expense("Nails", 50, false);
        testMonthlyExpenses = new MonthlyExpenses();
    }

//    @Test
//    public void testIsMonthlyExpenseTrue () {
//        testExpenseCar.isMonthlyExpense();
//        ArrayList.size(testMonthlyExpenses);
//    }

}
