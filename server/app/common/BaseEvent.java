package common;

import org.codehaus.jackson.node.ObjectNode;

import play.libs.Json;

/**
 * SSE basic event
 * @author spamoc
 */
public class BaseEvent extends Event {
    private ObjectNode node = Json.newObject();
    public BaseEvent(ObjectNode node) {
        super(node.toString());
    }
    public BaseEvent(){
        super("");
    }
    
    /**
     * not used
     * @param field
     * @param value
     */
    public void put(String field, String value){
        node.put(field, value);
    }
}
