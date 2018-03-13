package demo.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Team implements Serializable {
    
    private Integer id;
    
    private String name;
    private Topic topic;
    
    private Integer jointUtility;
    private Integer totalUtility;
    
    private Set<User> members = new HashSet<>();

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
    }

    public Integer getJointUtility() {
        return jointUtility;
    }

    public void setJointUtility(Integer jointUtility) {
        this.jointUtility = jointUtility;
    }
    
    public Integer getTotalUtility() {
	return totalUtility;
    }

    public void setTotalUtility(Integer totalUtility) {
	this.totalUtility = totalUtility;
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

	members.add(user);
	
	if (user.getCurrentTeam() == null) {
	    user.setCurrentTeam(this);
	}
    }
    
    public void removeMember(User user) {
	if (!getMembers().contains(user)) {
	    return;
	}
	members.remove(user);
	user.quitCurrentTeam();
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
    
    private static final long serialVersionUID = 1L;

}
