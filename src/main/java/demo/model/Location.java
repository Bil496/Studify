package demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Location implements Serializable {
    
    private Integer id;
    private String title;
    
    @JsonIgnore
    private Set<Topic> topics = new HashSet<>();

    public Location() {
	
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    public void addTopic(Topic topic) {
	if (!topic.getLocation().equals(this)) {
	    throw new RuntimeException("Location of topic does not match!");
	}
        topics.add(topic);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        return id.equals(location.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
    
    public JSONObject toJSONObject(String... ignore) {
	List<String> ignoreList = Arrays.asList(ignore);
	
	Map<String, Object> map = new HashMap<>();
	if (!ignoreList.contains("id")) map.put("id", getId());
	if (!ignoreList.contains("title")) map.put("title", getTitle());
	
	List<JSONObject> topicsAsJSONObjects = new ArrayList<>();
	for (Topic topic: getTopics()) {
	    topicsAsJSONObjects.add(topic.toJSONObject("location", "teams", "subTopics"));
	}
	if (!ignoreList.contains("topics")) map.put("topics", topicsAsJSONObjects);
	
	return new JSONObject(map);
    }
    
    private static final long serialVersionUID = 1L;

}
