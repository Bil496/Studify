package demo.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Team implements Serializable {
    
    private Integer id;
    
    @JsonIgnore
    private Topic topic;
    private String name;
    
    private Integer jointUtility;
    private Integer totalUtility;
    
    private Set<User> members = new HashSet<>();
    
    private Boolean isLocked = false;
    
    @JsonIgnore
    private Map<SubTopic, Integer> jointUtilityMap;

    public Team() {
	
    }

    public Team(Integer id, String name, Set<User> members) {
        this.id = id;
        this.name = name;
        this.members = members;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Topic getTopic() {
	return topic;
    }

    public void setTopic(Topic topic) {
	this.topic = topic;
	
	jointUtilityMap = new HashMap<>();
	for (SubTopic subTopic : topic.getSubTopics()) {
	    jointUtilityMap.put(subTopic, 0);
	}
	
	jointUtility = 0;
	totalUtility = 0;
    }

    public Integer getJointUtility() {
        return jointUtility;
    }
    
    public Integer getTotalUtility() {
	return totalUtility;
    }

    public Set<User> getMembers() {
        return members;
    }
    
    public void addMember(User user) {
	if (!user.getCurrentTopic().equals(getTopic())) {
	    throw new RuntimeException("Topic of user and team does not match!");
	}
	if (user.getCurrentTeam() != null && !user.getCurrentTeam().equals(this)) {
	    throw new RuntimeException("User is already in another team!");
	}
	if (isLocked()) {
	    throw new RuntimeException("Team does not accept any more member!");
	}

	members.add(user);
	
	// update utilities
	for (SubTopic subTopic : topic.getSubTopics()) {
	    Integer userTalentLevel = user.getTalentLevel(subTopic);
	    totalUtility += userTalentLevel;
	    
	    Integer teamTalentLevel = jointUtilityMap.get(subTopic);
	    if (userTalentLevel > teamTalentLevel) {
		jointUtilityMap.put(subTopic, userTalentLevel);
		jointUtility += (userTalentLevel - teamTalentLevel);
	    }
	}
	
	// TODO send notitification to other members
	
	if (user.getCurrentTeam() == null) {
	    user.setCurrentTeam(this);
	}
    }
    
    public void removeMember(User user) {
	if (!getMembers().contains(user)) {
	    return;
	}
	
	members.remove(user);
	
	// update utilities
	jointUtility = 0;
	for (SubTopic subTopic : topic.getSubTopics()) {
	    jointUtilityMap.put(subTopic, 0);
	    totalUtility -= user.getTalentLevel(subTopic);
	    for (User member : getMembers()) {
		Integer memberTalentLevel = member.getTalentLevel(subTopic);
		Integer teamTalentLevel = jointUtilityMap.get(subTopic);
		if (memberTalentLevel > teamTalentLevel) {
		    jointUtilityMap.put(subTopic, memberTalentLevel);	 
		}
	    }
	    jointUtility += jointUtilityMap.get(subTopic);
	}
	
	// TODO if no member left delete team
	// TODO else send notitification to other members

	user.quitCurrentTeam();
    }
    
    public Integer getSize() {
	return getMembers().size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        return id.equals(team.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
    
    public Boolean isLocked() {
	return isLocked;
    }

    public void lock() {
	isLocked = true;
    }
    
    public void unlock() {
	isLocked = false;
    }

    private static final long serialVersionUID = 1L;

}
