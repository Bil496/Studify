package demo.model;

import org.json.JSONObject;

public class Payload {

    public enum Type {
        JOIN_REQUEST, ACCEPTED, DENIED, KICKED,
        // not supported yet
        FOLLOWED, CHAT_MESSAGE
    }

    private Type type;
    private JSONObject data;

    public Payload(Type type, JSONObject data) {
        super();
        this.type = type;
        this.data = data;
    }

    public Type getType() {
        return type;
    }

    public JSONObject getData() {
        return data;
    }

}
