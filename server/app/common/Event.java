package common;

import org.codehaus.jackson.node.ObjectNode;

import play.libs.Json;

/**
 * SSE abstract event(must extend this)
 * @author spamoc
 */
public abstract class Event {
    private ObjectNode node = Json.newObject();
    
    
    public ObjectNode getNode(){
        return this.node;
    }
    
    public void setNode(ObjectNode node){
        this.node = node;
    }
    @Override
    public String toString(){
        return this.node.toString();
    }
}
