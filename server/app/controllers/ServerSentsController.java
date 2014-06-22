package controllers;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

import play.Logger;
import play.cache.Cache;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import common.BaseEvent;
import common.EventSource;

/**
 * SSE API controller
 * 
 * @author spamoc
 */
public class ServerSentsController extends Controller {

    /**
     * get event API
     * @return event
     */
    public static Result userStream() {
        Chunks<String> chunk = new EventSource() {
            @Override
            public void onConnected() {
                Logger.debug("connected");
                Object obj = Cache.get("sample");
                if (obj != null) {
                    Logger.debug("write");
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        JsonNode node = mapper.readTree(obj.toString());
                        BaseEvent event = new BaseEvent((ObjectNode)node);
                        sendMessage(event);
                    } catch (IOException e) {
                        Logger.error("cache json parse error, "+ e);
                    }
                }
                close();
            }
        };
        return ok(chunk);
    }
    
    /**
     * set event API
     * @return set value
     */
    public static Result setStream(){
        ObjectNode result = Json.newObject();
        ObjectNode node = Json.newObject();
        node.put("field", Math.random());
        Cache.set("sample", node);
        result.put("set_data", node);
        return ok(result);
    }
}