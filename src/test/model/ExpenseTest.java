package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseTest {

    private Expense testExpenseHair;

    @BeforeEach
    public void setup() {
        testExpenseHair = new Expense("Hair", 20, false, "Shopping");
    }

    @Test
    public void testToString() {
        String string = testExpenseHair.toString();
        assertEquals("Hair", string);
    }
}
