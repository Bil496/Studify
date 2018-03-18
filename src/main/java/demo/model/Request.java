package demo.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class Request implements Serializable {
    
    private Integer id;
    
    private User requester;
    private Team requested;
    
    private boolean accepted = false;
    private boolean denied = false;

    public Request(User requester, Team requested) {
	this.requester = requester;
	this.requested = requested;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public User getRequester() {
	return requester;
    }
    
    public Team getRequested() {
	return requested;
    }
    
    public void accept() {
	if (accepted) {
	    throw new RuntimeException("This request is already accepted!");
	}
	if (denied) {
	    throw new RuntimeException("This request is denied earlier!");
	}
	accepted = true;
	requester.removeRequest(this);
	requested.removeRequest(this);
	requester.setCurrentTeam(requested);
    }
    
    public void deny() {
	if (accepted) {
	    throw new RuntimeException("This request is already accepted!");
	}
	if (denied) {
	    throw new RuntimeException("This request is denied earlier!");
	}
	accepted = true;
	requester.removeRequest(this);
	requested.removeRequest(this);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        return id.equals(request.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
    
    public JSONObject toJSONObject(String... ignore) {
	List<String> ignoreList = Arrays.asList(ignore);
	
	Map<String, Object> map = new HashMap<>();
	if (!ignoreList.contains("id")) map.put("id", getId());
	if (!ignoreList.contains("requester")) map.put("requester", getRequester().toJSONObject());
	if (!ignoreList.contains("requested")) map.put("requested", getRequested().toJSONObject("members"));
	
	return new JSONObject(map);
    }
    
    private static final long serialVersionUID = 1L;

}
