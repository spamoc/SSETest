package common;

import org.codehaus.jackson.node.ObjectNode;

/**
 * SSE basic event
 * @author spamoc
 */
public class BaseEvent extends Event {

    public BaseEvent(String field, String message) {
        super.getNode().put(field, message);
    }
    
    public BaseEvent(){
        
    }
    
    public BaseEvent(ObjectNode node){
        super.setNode(node);
    }

}
