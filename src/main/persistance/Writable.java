package persistance;

import org.json.JSONObject;

// Code taken from JsonSerializationDemo program provided in the course material

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
