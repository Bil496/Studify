package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import model.*;
import org.hibernate.exception.ConstraintViolationException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.TopicService;
import service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TopicController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private UserService userService;

    @GetMapping("/topic")
    public ResponseEntity<String> list() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(topicService.list());
        return ResponseEntity.ok().body(json);
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
    public ResponseEntity<?> enroll(@PathVariable("id") long topicId, @RequestHeader("userId") long userId,
                                    @RequestBody String strTalents) throws JSONException {
        User user = userService.get(userId);
        user.setTalents(new ArrayList<>());
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
            UserSubTopicId userSubTopicId = new UserSubTopicId(user, subTopicsMap.get(talentObj.getLong("subTopic")));
            talent.setUserSubTopicId(userSubTopicId);
            user.getTalents().add(talent);
            subTopicsMap.remove(talentObj.getLong("subTopic"));
        }

        if (subTopicsMap.isEmpty() == false) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not all subtopics has covered");
        }
        try {
            topicService.enroll(topic, user);
            return ResponseEntity.ok().body("Successfully enrolled to topic");
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User has already enrolled.");
        }
    }
}
