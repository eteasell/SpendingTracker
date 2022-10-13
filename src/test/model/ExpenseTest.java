package model;

import org.junit.jupiter.api.BeforeEach;

public class ExpenseTest {

    public Expense testExpenseCar;
    public Expense testExpenseNails;
    public MonthlyFinances testMonthlyFinances;

    @BeforeEach
    public void setup() {
        testExpenseCar = new Expense("Car", 500, true, "Needs");
        testExpenseNails =  new Expense("Nails", 50, false, "Shopping");
        testMonthlyFinances = new MonthlyFinances();
    }

//    @Test
//    public void testIsMonthlyExpenseTrue () {
//        testExpenseCar.isMonthlyExpense();
//        ArrayList.size(testMonthlyExpenses);
//    }

}
