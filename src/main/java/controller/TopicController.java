package controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.SubTopic;
import model.Talent;
import model.Topic;
import model.User;
import model.UserSubTopicId;

import org.hibernate.exception.ConstraintViolationException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<Topic>> list() {
        return ResponseEntity.ok().body(topicService.list());
    }

    @PostMapping("/topic")
    public ResponseEntity<?> save(@RequestBody Topic topic) {
        Long userId = 1l; // TODO get userId from token
        topic.setCreator(userService.get(userId));
        for (SubTopic subTopic : topic.getSubTopics()) {
            subTopic.setTopic(topic);
        }
        long id = topicService.save(topic);
        return ResponseEntity.ok().body("New topic has been saved with ID:" + id);
    }

    @GetMapping("/topic/{id}")
    public ResponseEntity<Topic> get(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(topicService.get(id));
    }

    @PostMapping("/topic/{id}")
    public ResponseEntity<?> enroll(@PathVariable("id") long topicId, @RequestBody String strTalents, @RequestHeader("X-Auth") String token) {
        // throw new UnsupportedOperationException("Not supported yet.");
        Long userId = 1l; // TODO get userId from token
        User user = userService.get(userId);
        Topic topic = topicService.get(topicId);
        Map<Long, SubTopic> subTopicsMap = new HashMap<>();
        for (SubTopic subTopic : topic.getSubTopics()) {
            subTopicsMap.put(subTopic.getId(), subTopic);
        }
        JSONObject rootObj = new JSONObject(strTalents);
        JSONArray arrTalents = rootObj.getJSONArray("talents");
        for (int i = 0; i < arrTalents.length(); i++) {
            JSONObject talentObj = arrTalents.getJSONObject(i);
            Talent talent = new Talent();
            talent.setScore(talentObj.getInt("score"));
            UserSubTopicId userSubTopicId = new UserSubTopicId(user, subTopicsMap.get(talentObj.getLong("subTopic"))); // TODO: After SubTopic Service is ready, fix it
            talent.setUserSubTopicId(userSubTopicId);
            user.getTalents().add(talent);
        }
        try {
            topicService.enroll(topic, user);
            return ResponseEntity.ok().body("User has enrolled to the topic successfully.");
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User has already enrolled.");
        }
    }
}
