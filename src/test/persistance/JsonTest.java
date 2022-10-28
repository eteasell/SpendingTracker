package persistance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import model.Expense;

// Test class for Json. Code Taken from JsonSerializationDemo
public class JsonTest {
    protected void checkExpense(String title, double amount,  boolean dueMonthly, String category, Expense e) {
        assertEquals(title, e.getTitle());
        assertEquals(amount, e.getExpenseAmount());
        assertEquals(dueMonthly, e.getStatus());
        assertEquals(category, e.getCategory());
    }
}
