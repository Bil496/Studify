package controller;

import java.util.Set;

import model.SubTopic;
import model.Topic;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import service.TopicService;
import service.UserService;

@RestController
public class TopicController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private UserService userService;

    @GetMapping("/topic")
    public ResponseEntity<String> list() {
        JSONObject response = new JSONObject();
        JSONArray topics = new JSONArray();
        response.put("topics", topics);
        for (Topic topic : topicService.list()) {
            JSONObject obj = new JSONObject();
            obj.put("id", topic.getId());
            obj.put("title", topic.getTitle());
            obj.put("createDate", topic.getCreateDate());
            obj.put("enrolledNumber", topic.getEnrolledNumber());
            obj.put("waitingToGrouped", topic.getWaitingToGrouped());
            obj.put("totalGroupNumber", topic.getTotalGroupNumber());
            obj.put("nextGroupingTime", topic.getNextGroupingTime());
            topics.put(obj);
        }
        return ResponseEntity.ok().body(response.toString());
    }
    
    @PostMapping("/topic")
    public ResponseEntity<?> save(@RequestBody String str) {
        Long userId = 1l; // TODO get userId from token
        JSONObject obj = new JSONObject(str);
        Topic topic = new Topic();
        topic.setTitle(obj.getString("title"));
        topic.setMinSize(obj.getInt("minSize"));
        topic.setMaxSize(obj.getInt("maxSize"));
        topic.setCreator(userService.get(userId));
        JSONArray subTopicsArr = obj.getJSONArray("subTopics");
        Set<SubTopic> subTopics = topic.getSubTopics();
        for (int i = 0; i < subTopicsArr.length(); i++) {
            SubTopic subTopic = new SubTopic();
            subTopic.setTopic(topic);
            subTopic.setTitle(subTopicsArr.getJSONObject(i).getString("title"));
            subTopics.add(subTopic);
        }
        topic.setSubTopics(subTopics);
        long id = topicService.save(topic);
        return ResponseEntity.ok().body("New topic has been saved with ID:" + id);
    }

    @GetMapping("/topic/{id}")
    public ResponseEntity<String> get(@PathVariable("id") long id) {
        Topic topic = topicService.get(id);
        JSONObject response = new JSONObject();
        response.put("id", topic.getId());
        response.put("title", topic.getTitle());
        response.put("createDate", topic.getCreateDate());
        response.put("enrolledNumber", topic.getEnrolledNumber());
        response.put("waitingToGrouped", topic.getWaitingToGrouped());
        response.put("totalGroupNumber", topic.getTotalGroupNumber());
        response.put("nextGroupingTime", topic.getNextGroupingTime());
        JSONArray subTopics = new JSONArray();
        response.put("subTopics", subTopics);
        for (SubTopic subTopic : topic.getSubTopics()) {
            JSONObject objSubTopic = new JSONObject();
            objSubTopic.put("id", subTopic.getId());
            objSubTopic.put("title", subTopic.getTitle());
            subTopics.put(objSubTopic);
        }
        return ResponseEntity.ok().body(response.toString());
    }

    @PostMapping("/topic/{topicId}")
    public ResponseEntity<?> enroll(@PathVariable("") long topicID, @RequestHeader("X-Auth") String token) {
        throw new UnsupportedOperationException("Not supported yet.");
//        long userId = 0;
//        topicService.enroll(topicID, userId);
//        return ResponseEntity.ok().body("User has enrolled to the topic successfully.");
    }
}
