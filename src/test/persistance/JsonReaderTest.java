package persistance;

import model.MyPiggyBank;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// test class for JSONReader
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MyPiggyBank account = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }


    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmpty.json");
        try {
            MyPiggyBank account = reader.read();
            assertEquals("Test", account.getOwner());
            assertEquals(5000, account.getCurrentBalance());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralPiggyBank() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPiggyBank.json");
        try {
            MyPiggyBank account = reader.read();
            assertEquals("Test", account.getOwner());
            assertEquals(0, account.getMySpending().getCategories("Needs").size());
            assertEquals(1, account.getMySpending().getCategories("Shopping").size());
            assertEquals(1, account.getMyMonthlyFinances().getMonthlyFinances().size());
            assertEquals(4950, account.getCurrentBalance());
            checkExpense("rent", 500, true, "Needs",
                    account.getMyMonthlyFinances().getMonthlyFinances().get(0));
            checkExpense("hair", 50, false, "Shopping",
                    account.getMySpending().getCategories("Shopping").get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
