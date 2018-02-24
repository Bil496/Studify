package controller;

import data.CreateUser;
import model.Topic;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import service.TopicService;
import service.UserService;

@RestController
public class MockDataController {
    @Autowired
    private TopicService topicService;
    @Autowired
    private UserService userService;

    @GetMapping("/init")
    public ResponseEntity<String> getTalentsByTopicId() {
        Topic topicWithEnrolledUsers = CreateUser.getTopicWithEnrolledUsers();
        for (User user : topicWithEnrolledUsers.getUsers()) {
            userService.save(user);
        }
        topicService.save(topicWithEnrolledUsers);
        for (User user : topicWithEnrolledUsers.getUsers()) {
            topicService.enroll(topicWithEnrolledUsers, user);
        }
        return ResponseEntity.ok().body("ok");
    }
}
