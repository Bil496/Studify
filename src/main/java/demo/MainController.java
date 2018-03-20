package demo;

import demo.model.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import util.RandomStringGenerator;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

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

    @GetMapping("/team")
    ResponseEntity<?> getTeam(@RequestHeader int userId) {
        Stash stash = Stash.getInstance();
        try {
            User user = stash.getUser(userId);
            Team team = user.getCurrentTeam();
            return ResponseEntity.ok().body(team.toJSONObject().toString());
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

                    int diff = totalUtility1.compareTo(totalUtility2);
                    if (diff == 0)
                        return t1.getId().compareTo(t2.getId());
                    return diff;
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
            Topic topic = new Topic();

            JSONObject root = new JSONObject(body);
            topic.setTitle(root.getString("title"));

            Location location = stash.getLocation(locationId);
            topic.setLocation(location);

            Integer topicId = stash.addTopicToLocation(locationId, topic);

            JSONArray subtopics = root.getJSONArray("subTopics");
            for (int i = 0; i < subtopics.length(); i++) {
                JSONObject subtopicObj = subtopics.getJSONObject(i);
                SubTopic subtopic = new SubTopic();
                subtopic.setTitle(subtopicObj.getString("title"));
                stash.addSubTopicToTopic(topicId, subtopic);
            }

            return ResponseEntity.ok().body(topicId);
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
            Topic topic = stash.getTopic(topicId);
            user.quitCurrentTopic();
            user.setCurrentTopic(topic);

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

            if (!team.getMembers().contains(kickedUser)) {
                return ResponseEntity.badRequest().body("User is not already in team!");
            }

            team.removeMember(kickedUser);

            if (!user.equals(kickedUser)) {
                String title = "You are kicked!";
                String message = "You are kicked from " + team.getName() + ", by " + user.getUsername() + "!";
                Notification notification = new Notification(title, message);
                Payload payload = new Payload(Payload.Type.KICKED, team.toJSONObject("members"));

                NotificationSender.sendNotification(kickedUser, notification, payload);
            }

            return ResponseEntity.ok().body(1);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new APIError(401, e.getMessage()));
        }
    }

    @PostMapping("/firebase_token")
    ResponseEntity<?> postFirebaseToken(@RequestHeader int userId, @RequestBody String token) throws JSONException {
        Stash stash = Stash.getInstance();
        try {
            User user = stash.getUser(userId);
            String trimedToken = token.substring(1, token.length() - 1);
            user.setToken(trimedToken);
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
            Integer requestId = stash.addRequest(request);

            String title = "Join Request!";
            String message = user.getUsername() + " wants to join to your study group!";
            Notification notification = new Notification(title, message);
            Payload payload = new Payload(Payload.Type.JOIN_REQUEST,
                    //user.toJSONObject("currentTeam", "currentTopic", "currentLocation"));
                    request.toJSONObject());
            NotificationSender.sendNotification(team.getMembers(), notification, payload);

            return ResponseEntity.ok().body(requestId);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new APIError(401, e.getMessage()));
        }
    }

    @PostMapping("requests/{requestId}/accept")
    ResponseEntity<?> postAcceptRequest(@RequestHeader int userId, @PathVariable("requestId") int requestId) {
        Stash stash = Stash.getInstance();
        try {
            User user = stash.getUser(userId);
            Request request = stash.getRequest(requestId);

            User requester = request.getRequester();
            Team requested = request.getRequested();

            if (!user.getCurrentTeam().equals(requested)) {
                return ResponseEntity.badRequest()
                        .body(new APIError(401, "This user does not have permission to accept this request!"));
            }
            request.accept();

            String title = "Your request is accepted!";
            String message = "Your request to study with " + requested.getName() + " is accepted by " + user.getName()
                    + "!";
            Notification notification = new Notification(title, message);
            Payload payload = new Payload(Payload.Type.ACCEPTED, requested.toJSONObject());
            NotificationSender.sendNotification(requester, notification, payload);

            return ResponseEntity.ok().body(1);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new APIError(401, e.getMessage()));
        }
    }

    @PostMapping("requests/{requestId}/deny")
    ResponseEntity<?> postDenyRequest(@RequestHeader int userId, @PathVariable("requestId") int requestId) {
        Stash stash = Stash.getInstance();
        try {
            User user = stash.getUser(userId);
            Request request = stash.getRequest(requestId);

            User requester = request.getRequester();
            Team requested = request.getRequested();

            if (!user.getCurrentTeam().equals(requested)) {
                return ResponseEntity.badRequest()
                        .body(new APIError(401, "This user does not have permission to deny this request!"));
            }
            request.deny();

            String title = "Your request is denied!";
            String message = "Your request to study with " + requested.getName() + " is denied by "
                    + user.getName() + "!";
            Notification notification = new Notification(title, message);
            Payload payload = new Payload(Payload.Type.DENIED, null);
            NotificationSender.sendNotification(requester, notification, payload);

            return ResponseEntity.ok().body(1);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new APIError(401, e.getMessage()));
        }
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

    @PostMapping("/sendMessage")
    ResponseEntity<?> sendMessage(@RequestHeader int userId, @RequestBody String body) throws JSONException {
        Stash stash = Stash.getInstance();
        try {
            User user = stash.getUser(userId);
            Team team = user.getCurrentTeam();

            String title = "New message!";
            String message = body.substring(1, body.length() - 1);
            Notification notification = new Notification(title, message);

            JSONObject data = new JSONObject();
            data.put("chatMessage", message);
            data.put("senderName", user.getName());
            data.put("senderImage", user.getProfilePic());
            Payload payload = new Payload(Payload.Type.CHAT_MESSAGE, data);

            NotificationSender.sendNotification(team.getMembers(), notification, payload, user);

            return ResponseEntity.ok().body(1);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new APIError(401, e.getMessage()));
        }
    }

}
