package controller;

import data.CreateUser;
import model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import service.TopicService;

@RestController
public class MockDataController {
    @Autowired
    private TopicService topicService;

    @GetMapping("/init")
    public ResponseEntity<String> getTalentsByTopicId() {
        Topic topicWithEnrolledUsers = CreateUser.getTopicWithEnrolledUsers();
        topicService.save(topicWithEnrolledUsers);
        return ResponseEntity.ok().body("ok");
    }
}
