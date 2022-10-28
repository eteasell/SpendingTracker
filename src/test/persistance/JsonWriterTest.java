package persistance;

import model.Expense;
import model.MonthlyFinances;
import model.MyPiggyBank;
import model.MySpending;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Test class for JsonWriter
// code in this class taken from JsonSerializationDemo
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            MyPiggyBank account = new MyPiggyBank("Test", 5000);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyAccount() {
        try {
            MyPiggyBank account = new MyPiggyBank("Test", 5000);
            JsonWriter writer = new JsonWriter("./data/testWriterEmpty.json");
            writer.open();
            writer.write(account);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmpty.json");
            account = reader.read();
            assertEquals("Test", account.getOwner());
            assertEquals(5000, account.getCurrentBalance());
            assertEquals(0, account.getMyMonthlyFinances().getMonthlyFinances().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralAccount() {
        try {
            MyPiggyBank account = new MyPiggyBank("Test", 5000);
            MonthlyFinances testMonthly = account.getMyMonthlyFinances();
            Expense rent = new Expense("rent", 500, true, "Needs");
            Expense hair = new Expense("hair", 50, false, "Shopping");
            testMonthly.addExpense(rent);
            account.pay(hair);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPiggyBank.json");
            writer.open();
            writer.write(account);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPiggyBank.json");
            account = reader.read();
            assertEquals("Test", account.getOwner());
            List<Expense> spentNeeds = account.getMySpending().getCategories("Needs");
            List<Expense> spentShopping = account.getMySpending().getCategories("Shopping");
            assertEquals(0, spentNeeds.size());
            assertEquals(1, spentShopping.size());
            assertEquals(1, account.getMyMonthlyFinances().getMonthlyFinances().size());
            assertEquals(4950, account.getCurrentBalance());
            checkExpense("rent", 500, true, "Needs",
                    account.getMyMonthlyFinances().getMonthlyFinances().get(0));
            checkExpense("hair", 50, false, "Shopping", spentShopping.get(0));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
