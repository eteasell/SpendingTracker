package persistance;

import model.Event;
import model.EventLog;
import model.MyPiggyBank;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer to convert MyPiggyBank into a JSON file
public class JsonWriter {

    // Code in this class is taken from JsonSerializationDemo program provided in the course material

    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of MyPiggyBank to file
    public void write(MyPiggyBank account) {
        JSONObject json = account.toJson();
        saveToFile(json.toString(TAB));
        EventLog.getInstance().logEvent(new Event("Account saved to : " + this.destination));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
