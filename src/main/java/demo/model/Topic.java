package demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    public Topic(Integer id, String title, Integer userCount, Set<SubTopic> subTopics, Set<Team> teams) {
        this.id = id;
        this.title = title;
        this.userCount = userCount;
        this.subTopics = subTopics;
        this.teams = teams;
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
    
    private static final long serialVersionUID = 1L;
    
}
