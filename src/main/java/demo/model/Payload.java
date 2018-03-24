package demo.model;

import org.json.JSONObject;

public class Payload {

    public enum Type {
        JOIN_REQUEST, ACCEPTED, DENIED, 
        MERGE_REQUEST, MERGE_ACCEPTED, MERGE_DENIED,
        KICKED, 
        CHAT_MESSAGE,
        NOTIFY_GROUP_FOR_QUITING, NOTIFY_GROUP_FOR_JOINING, TEAM_LOCKED, TEAM_UNLOCKED, NOTIFY_GROUP_FOR_KICKING
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
