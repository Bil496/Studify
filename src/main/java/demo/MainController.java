package demo;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import demo.model.Location;
import demo.model.Request;
import demo.model.SubTopic;
import demo.model.Team;
import demo.model.Topic;
import demo.model.User;
import util.RandomStringGenerator;

@Controller
public class MainController {

    @GetMapping("/users/{username}")
    ResponseEntity<?> getUser(@PathVariable("username") String username) {
	try {
	    return ResponseEntity.ok().body(Stash.getInstance().findUserByUsername(username));
	} catch (RuntimeException e) {
	    return ResponseEntity.badRequest().body(new APIError(401, e.getMessage()));
	}
    }

    @GetMapping("/locations")
    ResponseEntity<Collection<Location>> getLocations() {
	return ResponseEntity.ok().body(Stash.getInstance().getLocations());
    }

    @GetMapping("/locations/{locationId}/topics")
    ResponseEntity<?> getTopics(@RequestHeader int userId, @PathVariable("locationId") int locationId) {
	Stash stash = Stash.getInstance();
	try {
	    Location location = stash.getLocation(locationId);

	    // Burak made me do this...
	    // (so that he doesn't bother to set locations of users...)
	    User user = stash.getUser(userId);
	    user.setCurrentLocation(location);

	    return ResponseEntity.ok().body(location.getTopics());
	} catch (RuntimeException e) {
	    return ResponseEntity.badRequest().body(new APIError(401, e.getMessage()));
	}
    }

    @GetMapping("/topics/{topicId}/teams")
    ResponseEntity<?> getTeams(@RequestHeader int userId, @PathVariable("topicId") int topicId) {
	Stash stash = Stash.getInstance();
	try {
	    final User user = stash.getUser(userId);
	    Topic topic = stash.getTopic(topicId);

	    Set<Team> teams = topic.getTeams();
	    TreeSet<Team> treeSet = new TreeSet<>(new Comparator<Team>() {
		@Override
		public int compare(Team t1, Team t2) {
		    Integer jointUtility1 = t1.hypotheticalJointUtility(user);
		    Integer juintUtility2 = t2.hypotheticalJointUtility(user);

		    if (!jointUtility1.equals(juintUtility2)) {
			// greater joint utility is favorable
			return -jointUtility1.compareTo(juintUtility2);
		    }

		    Integer totalUtility1 = t1.hypotheticalTotalUtility(user);
		    Integer totalUtility2 = t2.hypotheticalTotalUtility(user);

		    // less total utility is favorable
		    return totalUtility1.compareTo(totalUtility2);
		}
	    });
	    treeSet.addAll(teams);
	    return ResponseEntity.ok().body(treeSet);
	} catch (RuntimeException e) {
	    return ResponseEntity.badRequest().body(new APIError(401, e.getMessage()));
	}
    }

    @PostMapping("/locations/{locationId}/topics")
    ResponseEntity<?> postTopic(@PathVariable("locationId") int locationId, @RequestBody String body)
	    throws JSONException {
	Stash stash = Stash.getInstance();
	try {
	    JSONObject root = new JSONObject(body);

	    Topic topic = new Topic();
	    topic.setTitle(root.getString("title"));
	    Integer topicId = stash.addTopicToLocation(locationId, topic);

	    JSONArray subtopics = root.getJSONArray("subtopics");
	    for (int i = 0; i < subtopics.length(); i++) {
		JSONObject subtopicObj = subtopics.getJSONObject(i);
		SubTopic subtopic = new SubTopic();
		subtopic.setTitle(subtopicObj.getString("title"));
		stash.addSubTopicToTopic(topicId, subtopic);
	    }

	    return ResponseEntity.ok().body(stash.getTopic(topicId));
	} catch (RuntimeException e) {
	    return ResponseEntity.badRequest().body(new APIError(401, e.getMessage()));
	}
    }

    @PostMapping("/topics/{topicId}/talentLevels")
    ResponseEntity<?> postTalentLevels(@RequestHeader int userId, @PathVariable("topicId") int topicId,
	    @RequestBody String body) throws JSONException {
	Stash stash = Stash.getInstance();
	try {
	    User user = stash.getUser(userId);

	    if (user.getCurrentTeam() != null) {
		return ResponseEntity.badRequest().body(new APIError(31, "User is in a team in another topic!"));
	    }

	    JSONArray root = new JSONArray(body);
	    for (int i = 0; i < root.length(); i++) {
		JSONObject talentObj = root.getJSONObject(i);
		SubTopic subTopic = stash.getSubTopic(talentObj.getInt("subtopicId"));
		user.setTalentLevelOfSubTopic(subTopic, talentObj.getInt("talentLevel"));
	    }
	    return ResponseEntity.ok().body(1);
	} catch (RuntimeException e) {
	    return ResponseEntity.badRequest().body(new APIError(401, e.getMessage()));
	}
    }

    @PostMapping("/topics/{topicId}/teams")
    ResponseEntity<?> postTeam(@RequestHeader int userId, @PathVariable("topicId") int topicId) {
	Stash stash = Stash.getInstance();
	try {
	    User user = stash.getUser(userId);
	    Topic topic = stash.getTopic(topicId);

	    Team team = new Team();
	    team.setTopic(topic);
	    team.setName(RandomStringGenerator.getSentence(2));

	    team.addMember(user);

	    Integer teamId = stash.addTeamToTopic(topicId, team);
	    return ResponseEntity.ok().body(teamId);
	} catch (RuntimeException e) {
	    return ResponseEntity.badRequest().body(new APIError(401, e.getMessage()));
	}
    }

    @PostMapping("/teams/{teamId}/lock")
    ResponseEntity<?> postLock(@RequestHeader int userId, @PathVariable("teamId") int teamId) {
	Stash stash = Stash.getInstance();
	try {
	    User user = stash.getUser(userId);
	    Team team = stash.getTeam(teamId);

	    if (!team.getMembers().contains(user)) {
		return ResponseEntity.badRequest().body("Only members can lock team!");
	    }
	    if (team.isLocked()) {
		return ResponseEntity.badRequest().body("Team is already locked!");
	    }

	    team.lock();
	    return ResponseEntity.ok().body(1);
	} catch (RuntimeException e) {
	    return ResponseEntity.badRequest().body(new APIError(401, e.getMessage()));
	}
    }

    @PostMapping("/teams/{teamId}/unlock")
    ResponseEntity<?> postUnlock(@PathVariable("teamId") int teamId, @RequestHeader int userId) {
	Stash stash = Stash.getInstance();
	try {
	    User user = stash.getUser(userId);
	    Team team = stash.getTeam(teamId);

	    if (!team.getMembers().contains(user)) {
		return ResponseEntity.badRequest().body("Only members can unlock team!");
	    }
	    if (!team.isLocked()) {
		return ResponseEntity.badRequest().body("Team is already unlocked!");
	    }

	    team.unlock();
	    return ResponseEntity.ok().body(1);
	} catch (RuntimeException e) {
	    return ResponseEntity.badRequest().body(new APIError(401, e.getMessage()));
	}
    }

    @PostMapping("/teams/{teamId}/kick/{kickedUserId}")
    ResponseEntity<?> postKickUserFromTeam(@RequestHeader int userId, @PathVariable("teamId") int teamId,
	    @PathVariable("kickedUserId") int kickedUserId) {
	Stash stash = Stash.getInstance();
	try {
	    User user = stash.getUser(userId);
	    Team team = stash.getTeam(teamId);
	    User kickedUser = stash.getUser(kickedUserId);

	    if (!team.getMembers().contains(user)) {
		return ResponseEntity.badRequest().body("Only members can kick members!");
	    }

	    team.removeMember(kickedUser);
	    return ResponseEntity.ok().body(1);
	} catch (RuntimeException e) {
	    return ResponseEntity.badRequest().body(new APIError(401, e.getMessage()));
	}
    }
    
    @PostMapping("/firebase_token")
    ResponseEntity<?> postFirebaseToken(@RequestHeader int userId, @RequestBody String token) {
	Stash stash = Stash.getInstance();
	try {
	    User user = stash.getUser(userId);
	    user.setToken(token);
	} catch (RuntimeException e) {
	    return ResponseEntity.badRequest().body(new APIError(401, e.getMessage()));
	}
	return ResponseEntity.ok().body(1);
    }

    @PostMapping("/teams/{teamId}")
    ResponseEntity<?> postJoinRequest(@RequestHeader int userId, @PathVariable("teamId") int teamId) {
	Stash stash = Stash.getInstance();
	try {
	    User user = stash.getUser(userId);
	    Team team = stash.getTeam(teamId);
	    Request request = new Request(user, team);
	    stash.addRequest(request);
	} catch (RuntimeException e) {
	    return ResponseEntity.badRequest().body(new APIError(401, e.getMessage()));
	}
	return null;
    }

    @PostMapping("/teams/{teamId}/accept/{requesterId}")
    ResponseEntity<?> postAcceptRequest(@RequestHeader int userId, @PathVariable("teamId") int teamId,
	    @PathVariable("requesterId") int requesterId, @RequestBody String message) {
	// TODO send notification to accepted user only if user has no team
	return null;
    }

    @PostMapping("/teams/{teamId}/deny/{requesterId}")
    ResponseEntity<?> postDenyRequest(@RequestHeader int userId, @PathVariable("teamId") int teamId,
	    @PathVariable("requesterId") int requesterId) {
	// TODO send notification to denied user only if user has no team
	return null;
    }
    
    @PostMapping("/reset/{databaseId}")
    ResponseEntity<?> resetDemoDatabase(@PathVariable("id") int databaseId) {
	Stash stash = Stash.getInstance();
	stash.clear();
	try {
	    Reader.setPath("demoDB" + databaseId + ".json");
	    Reader.createStash();
	} catch (JSONException e) {
	    return ResponseEntity.badRequest().body(new APIError(401, e.getMessage()));
	}
	return ResponseEntity.ok().body(1);
    }

}
