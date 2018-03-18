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

public class Topic implements Serializable {
    
    private Integer id;
    
    private String title;
    private Location location;
    private Set<SubTopic> subTopics = new HashSet<>();
    @JsonIgnore
    private Set<Team> teams = new HashSet<>();
    
    private Integer userCount = 0;
    
    public Topic() {
	
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
    
    public Location getLocation() {
	return location;
    }

    public void setLocation(Location location) {
	this.location = location;
    }

    public Set<SubTopic> getSubTopics() {
        return subTopics;
    }

    public void addSubTopic(SubTopic subTopic) {
	subTopics.add(subTopic);
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }
    
    public void addTeam(Team team) {
	if (!team.getTopic().equals(this)) {
	    throw new RuntimeException("Topic of team does not match!");
	}
	teams.add(team);
    }
    
    public void removeTeam(Team team) {
	teams.remove(team);
    }
       
    public Integer getUserCount() {
        return userCount;
    }

    public void incrementUserCount() {
        userCount++;
    }
    
    public void decrementUserCount() {
        userCount--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Topic topic = (Topic) o;

        return id.equals(topic.id);

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
	if (!ignoreList.contains("location")) map.put("location", getLocation().toJSONObject("topics"));
	
	if (!ignoreList.contains("teams")) {
	    List<JSONObject> teamsAsJSONObjects = new ArrayList<>();
	    for (Team team: getTeams()) {
		teamsAsJSONObjects.add(team.toJSONObject("topic", "members", "requests"));
	    }
	    map.put("teams", teamsAsJSONObjects);
	}
	
	if (!ignoreList.contains("subTopics")) {
	    List<JSONObject> subTopicsAsJSONObjects = new ArrayList<>();
	    for (SubTopic subTopic: getSubTopics()) {
		subTopicsAsJSONObjects.add(subTopic.toJSONObject());
	    }
	    map.put("subTopics", subTopicsAsJSONObjects);
	}
	
	return new JSONObject(map);
    }
    
    private static final long serialVersionUID = 1L;
    
}
