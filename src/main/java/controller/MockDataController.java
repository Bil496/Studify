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

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@RestController
public class MockDataController {
    @Autowired
    private TopicService topicService;
    @Autowired
    private UserService userService;

    @GetMapping("/init")
    public ResponseEntity<String> getTalentsByTopicId() {
        Topic topicWithEnrolledUsers = CreateUser.getTopicWithEnrolledUsers();
        topicService.save(topicWithEnrolledUsers);
        Set<User> users = new TreeSet<>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });
        users.addAll(topicWithEnrolledUsers.getUsers());
        for(User user : users){
            userService.save(user);
            topicService.enroll(topicWithEnrolledUsers, user);
        }
        return ResponseEntity.ok().body("ok");
    }
}
