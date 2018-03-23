package demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.*;

public class Team implements Serializable {

    private Integer id;

    @JsonIgnore
    private Topic topic;
    private String name;

    private Integer jointUtility;
    private Integer totalUtility;

    @JsonIgnore
    private Map<SubTopic, Integer> jointUtilityMap;

    @JsonIgnoreProperties({"currentTopic", "currentTeam", "currentLocation"})
    private Set<User> members = new HashSet<>();

    @JsonIgnore
    private Set<Request> requests = new HashSet<>();
    @JsonIgnore
    private Set<MergeRequest> mergeRequests = new HashSet<>();

    private Boolean locked = false;

    public Team() {

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

    public Integer hypotheticalJointUtility(User hypotheticalMember) {
        Integer hypotheticalJointUtility = getJointUtility();
        for (SubTopic subTopic : topic.getSubTopics()) {
            Integer userTalentLevel = hypotheticalMember.getTalentLevel(subTopic);

            Integer teamTalentLevel = jointUtilityMap.get(subTopic);
            if (userTalentLevel > teamTalentLevel) {
                hypotheticalJointUtility += (userTalentLevel - teamTalentLevel);
            }
        }
        return hypotheticalJointUtility;
    }

    public Integer hypotheticalTotalUtility(User hypotheticalMember) {
        Integer hypotheticalTotalUtility = getTotalUtility();
        for (SubTopic subTopic : topic.getSubTopics()) {
            Integer userTalentLevel = hypotheticalMember.getTalentLevel(subTopic);
            hypotheticalTotalUtility += userTalentLevel;
        }
        return hypotheticalTotalUtility;
    }

    public Integer getSize() {
        return getMembers().size();
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
            throw new RuntimeException("Team is closed to new members!");
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

        // if no member left delete team
        if (getSize() == 0) {
            getTopic().removeTeam(this);
            topic = null;
        }

        user.quitCurrentTeam();
    }

    public void addRequest(Request request) {
        if (!request.getRequested().equals(this)) {
            throw new RuntimeException("This request does not belong to this team!");
        }
        requests.add(request);
    }

    public void removeRequest(Request request) {
        requests.remove(request);
    }

    public Set<Request> getRequests() {
        return requests;
    }
    
    
    public void addMergeRequest(MergeRequest mergeRequest) {
        if (!mergeRequest.getRequester().equals(this) ||
        	!mergeRequest.getRequested().equals(this)) {
            throw new RuntimeException("This request does not belong to this team!");
        }
        mergeRequests.add(mergeRequest);
    }

    public void removeMergeRequest(MergeRequest mergeRequest) {
        mergeRequests.remove(mergeRequest);
    }
    
    public Set<MergeRequest> getMergeRequests() {
	return mergeRequests;
    }
    
    
    public void merge(Team team) {
	for (User member: team.getMembers()) {
	    member.quitCurrentTeam();
	    member.setCurrentTeam(this);
	}
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Team team = (Team) o;

        return id.equals(team.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Boolean isLocked() {
        return locked;
    }

    public void lock() {
        locked = true;
    }

    public void unlock() {
        locked = false;
    }

    public JSONObject toJSONObject(String... ignore) {
        List<String> ignoreList = Arrays.asList(ignore);

        Map<String, Object> map = new HashMap<>();
        if (!ignoreList.contains("id"))
            map.put("id", getId() != null ? getId() : JSONObject.NULL);

        if (!ignoreList.contains("name"))
            map.put("name", getName() != null ? getName() : JSONObject.NULL);

        if (!ignoreList.contains("jointUtility"))
            map.put("jointUtility", getJointUtility() != null ? getJointUtility() : JSONObject.NULL);

        if (!ignoreList.contains("totalUtility"))
            map.put("totalUtility", getTotalUtility() != null ? getTotalUtility() : JSONObject.NULL);

        if (!ignoreList.contains("locked"))
            map.put("locked", isLocked() != null ? isLocked() : JSONObject.NULL);

        if (!ignoreList.contains("topic"))
            map.put("topic", getTopic() != null ? getTopic().toJSONObject("teams") : JSONObject.NULL);

        if (!ignoreList.contains("members")) {
            if (getMembers() == null) {
                map.put("members", JSONObject.NULL);
            } else {
                List<JSONObject> membersAsJSONObjects = new ArrayList<>();
                for (User member : getMembers()) {
                    membersAsJSONObjects
                            .add(member.toJSONObject("currentTopic", "currentTeam", "currentLocation", "requests"));
                }
                map.put("members", membersAsJSONObjects);
            }
        }

        if (!ignoreList.contains("requests")) {
            if (getRequests() == null) {
                map.put("requests", JSONObject.NULL);
            } else {
                List<JSONObject> requestsAsJSONObjects = new ArrayList<>();
                for (Request request : getRequests()) {
                    requestsAsJSONObjects.add(request.toJSONObject("requested"));
                }
                map.put("requests", requestsAsJSONObjects);
            }
        }

        return new JSONObject(map);
    }

    private static final long serialVersionUID = 1L;

}
