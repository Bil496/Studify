package demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import demo.model.Location;
import demo.model.SubTopic;
import demo.model.Team;
import demo.model.Topic;
import demo.model.User;

public class Reader {
    
    private static String path = "demoDB2.json";
    
    public static void setPath(String path) {
	Reader.path = path;
    }

    private static JSONObject readFileToJSONObject() throws JSONException {
	String content = null;
	try {
	    content = new String(Files.readAllBytes(Paths.get(path)));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return new JSONObject(content);
    }

    public static void createStash() throws JSONException {
	JSONObject root = readFileToJSONObject();
	Stash stash = Stash.getInstance();

	// locations array
	JSONArray locations = root.getJSONArray("locations");
	for (int i = 0; i < locations.length(); i++) {
	    Location location = new Location();
	    location.setTitle(locations.getJSONObject(i).getString("title"));
	    stash.addLocation(location);
	}

	// topics array
	JSONArray topics = root.getJSONArray("topics");
	for (int i = 0; i < topics.length(); i++) {
	    JSONObject topicObj = topics.getJSONObject(i);

	    Topic topic = new Topic();
	    topic.setTitle(topicObj.getString("title"));

	    Integer locationId = topicObj.getInt("locationId");
	    Location location = stash.getLocation(locationId);
	    topic.setLocation(location);

	    stash.addTopicToLocation(locationId, topic);
	}

	// users array
	JSONArray users = root.getJSONArray("users");
	for (int i = 0; i < users.length(); i++) {
	    JSONObject userObj = users.getJSONObject(i);

	    User user = new User();
	    user.setName(userObj.getString("name"));
	    user.setUserName(userObj.getString("username"));
	    user.setProfilePic(userObj.getString("profile_pic"));

	    Integer locationId = userObj.getInt("locationId");
	    Location location = stash.getLocation(locationId);
	    user.setCurrentLocation(location);

	    Integer topicId = userObj.getInt("topicId");
	    Topic topic = stash.getTopic(topicId);
	    user.setCurrentTopic(topic);

	    stash.addUser(user);
	}

	// subtopics array
	JSONArray subtopics = root.getJSONArray("subtopics");
	for (int i = 0; i < subtopics.length(); i++) {
	    JSONObject subtopicObj = subtopics.getJSONObject(i);
	    Integer topicId = subtopicObj.getInt("topicId");
	    SubTopic subtopic = new SubTopic();
	    subtopic.setTitle(subtopicObj.getString("title"));
	    stash.addSubTopicToTopic(topicId, subtopic);
	}

	// talent levels array
	JSONArray talents = root.getJSONArray("talentLevels");
	for (int i = 0; i < talents.length(); i++) {
	    JSONObject talentObj = talents.getJSONObject(i);

	    Integer userId = talentObj.getInt("userId");
	    User user = stash.getUser(userId);

	    Integer subTopicId = talentObj.getInt("subtopicId");
	    SubTopic subTopic = stash.getSubTopic(subTopicId);

	    user.setTalentLevelOfSubTopic(subTopic, talentObj.getInt("talentLevel"));
	}

	// teams array
	JSONArray teams = root.getJSONArray("teams");
	for (int i = 0; i < teams.length(); i++) {
	    JSONObject teamObj = teams.getJSONObject(i);

	    Team team = new Team();
	    team.setName(teamObj.getString("name"));

	    Integer topicId = teamObj.getInt("topicId");
	    Topic topic = stash.getTopic(topicId);
	    team.setTopic(topic);

	    stash.addTeamToTopic(topicId, team);
	}

	// user_team array
	JSONArray user_team = root.getJSONArray("user_team");
	for (int i = 0; i < user_team.length(); i++) {
	    JSONObject userTeamObj = user_team.getJSONObject(i);
	    Integer userId = userTeamObj.getInt("userId");
	    Integer teamId = userTeamObj.getInt("teamId");
	    Team team = stash.getTeam(teamId);
	    User user = stash.getUser(userId);
	    team.addMember(user);
	}

    }
}
