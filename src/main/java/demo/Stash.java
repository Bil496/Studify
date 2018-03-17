package demo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import demo.model.Location;
import demo.model.Request;
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
    private Map<Integer, Request> requests = new HashMap<>();
        
    private Stash() {
	
    }

    public static Stash getInstance() {
	return STASH;
    }
    
    public void clear() {
	users.clear();
	teams.clear();
	topics.clear();
	subTopics.clear();
	locations.clear();
    }
        
    // GET METHODS

    public User getUser(Integer userId) {
	User user = users.get(userId);
	if (user == null) {
	    throw new RuntimeException("User not found in stash!");
	}
	return user;
    }
    
    public Team getTeam(Integer teamId) {
	Team team = teams.get(teamId);
	if (team == null) {
	    throw new RuntimeException("Team not found in stash!");
	}
	return team;
    }

    public Topic getTopic(Integer topicId) {
	Topic topic = topics.get(topicId);
	if (topic == null) {
	    throw new RuntimeException("Topic not found in stash!");
	}
	return topic;
    }
    
    public SubTopic getSubTopic(Integer subTopicId) {
	SubTopic subTopic = subTopics.get(subTopicId);
	if (subTopic == null) {
	    throw new RuntimeException("Sub-Topic not found in stash!");
	}
	return subTopic;
    }
    
    public Location getLocation(Integer locationId) {
	Location location = locations.get(locationId);
	if (location == null) {
	    throw new RuntimeException("Location not found in stash!");
	}
	return location;
    }
    
    public Request getRequest(Integer requestId) {
	Request request = requests.get(requestId);
	if (request == null) {
	    throw new RuntimeException("Request not found in stash!");
	}
	return request;
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
    
    public Collection<Request> getRequests() {
	return requests.values();
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
    
    public Integer addRequest(Request request) {
	Integer index = requests.size();
	request.setId(index);
	requests.put(index, request);
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
	throw new RuntimeException("User does not exist!");
    }
    
    public Team findTeamByName(String name) {
	for (Team team : teams.values()) {
	    if (team.getName().equals(name))
		return team;
	}
	throw new RuntimeException("Team does not exist!");
    }

}
