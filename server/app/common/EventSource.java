package common;

import play.libs.F.Callback0;
import play.mvc.Results.Chunks;

/**
 * A Chunked stream sending event source messages.
 */
public abstract class EventSource extends Chunks<String> {

    private Out<String> out;

    /**
     * Create a new event source socket
     */
    public EventSource() {
        super(play.core.j.JavaResults.writeString("text/event-stream", play.api.mvc.Codec.javaSupported("utf-8")));
    }

    public final void onReady(Out<String> out) {
        this.out = out;
        onConnected();
    }

    /**
     * Send an event on this socket.
     */
    public void sendMessage(String message) {
        out.write("data:" + message.replaceAll("\r?\n", "\r\ndata:") + "\r\n");
    }

    /**
     * Send an event on this socket.
     */
    public void sendMessage(Event event) {
        out.write(event.toString());
    }

    /**
     * The socket is ready, you can start sending messages.
     */
    public abstract void onConnected();

    /**
     * Add a callback to be notified when the client has disconnected.
     */
    public void onDisconnected(Callback0 callback) {
        out.onDisconnected(callback);
    }

    /**
     * Close the channel
     */
    public void close() {
        out.close();
    }
}