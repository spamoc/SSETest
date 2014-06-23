package common;

import java.util.HashMap;
import java.util.Map;

/**
 * SSE abstract event(must extend this)
 * 
 * @author spamoc
 */
public abstract class Event {
    private String message;
    private final Map<String, String> fields = new HashMap<String, String>();

    /**
     * Create an event
     * 
     * @param message
     *            The message to send
     */
    public Event(String message) {
        this.message = message;
    }

    /**
     * Set the id for the event
     */
    public Event withId(String id) {
        if (id != null) {
            fields.put("id", id);
        }
        return this;
    }

    /**
     * Set the name for the event
     */
    public Event withName(String name) {
        if (name != null) {
            fields.put("name", name);
        }
        return this;
    }

    /**
     * Set the reconnection timeout that the client should use
     */
    public Event withRetry(int milliseconds) {
        fields.put("retry", Integer.toString(milliseconds));
        return this;
    }
    
    public String getMessage(){
        return this.message;
    }
    public Map<String, String> getFields(){
        return this.fields;
    }

}