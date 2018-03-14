package demo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import demo.model.Location;
import demo.model.SubTopic;
import demo.model.Team;
import demo.model.Topic;
import demo.model.User;

public class Stash {

    private static final Stash STASH = new Stash();

    private Map<Integer, User> users = new HashMap<>();
    private Map<Integer, Team> teams = new HashMap<>();
    private Map<Integer, Topic> topics = new HashMap<>();
    private Map<Integer, SubTopic> subTopics = new HashMap<>();
    private Map<Integer, Location> locations = new HashMap<>();
        
    private Stash() {
	
    }

    public static Stash getInstance() {
	return STASH;
    }
        
    // GET METHODS

    public User getUser(Integer userId) {
	return users.get(userId);
    }
    
    public Team getTeam(Integer teamId) {
	return teams.get(teamId);
    }

    public Topic getTopic(Integer topicId) {
	return topics.get(topicId);
    }
    
    public SubTopic getSubTopic(Integer subTopicId) {
	return subTopics.get(subTopicId);
    }
    
    public Location getLocation(Integer locationId) {
	return locations.get(locationId);
    }
    
    // GET COLLECTION METHODS
    
    public Collection<User> getUsers() {
	return users.values();
    }
    
    public Collection<Team> getTeams() {
	return teams.values();
    }
    
    public Collection<Topic> getTopics() {
	return topics.values();
    }
    
    public Collection<SubTopic> getSubTopics() {
	return subTopics.values();
    }

    public Collection<Location> getLocations() {
	return locations.values();
    }
    
    // ADD METHODS

    public Integer addUser(User user) {
	Integer index = users.size();
	user.setId(index);
	users.put(index, user);
	return index;
    }

    public Integer addLocation(Location location) {
	Integer index = locations.size();
	location.setId(index);
	locations.put(index, location);
	return index;
    }
    
    // ADD TO METHODS

    public Integer addTopicToLocation(Integer locationId, Topic topic) {
	Integer index = topics.size();
	topic.setId(index);
	topics.put(index, topic);
	
	Location location = getLocation(locationId);
	location.addTopic(topic);

	return index;
    }
    
    public Integer addSubTopicToTopic(Integer topicId, SubTopic subTopic) {
	Integer index = subTopics.size();
	subTopic.setId(index);
	subTopics.put(index, subTopic);
	
	Topic topic = getTopic(topicId);
	topic.addSubTopic(subTopic);
	
	return index;
    }
    
    public Integer addTeamToTopic(Integer topicId, Team team) {
	Integer index = teams.size();
	team.setId(index);
	teams.put(index, team);
	
	Topic topic = getTopic(topicId);
	//team.setTopic(topic);
	topic.addTeam(team);

	return index;
    }
    
    // FIND BY METHODS

    public User findUserByUsername(String username) {
	for (User user : users.values()) {
	    if (user.getUserName().equals(username))
		return user;
	}
	return null;
    }

}
