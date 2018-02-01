package controller;

import java.util.List;
import model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import service.TopicService;

@RestController
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping("/topic")
    public ResponseEntity<List<Topic>> list() {
        return ResponseEntity.ok().body(topicService.list());
    }

    @PostMapping("/topic")
    public ResponseEntity<?> save(@RequestBody Topic topic) {
        long id = topicService.save(topic);
        return ResponseEntity.ok().body("New topic has been saved with ID:" + id);
    }

    @GetMapping("/topic/{id}")
    public ResponseEntity<Topic> get(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(topicService.get(id));
    }

    @PostMapping("/topic/{topicId}")
    public ResponseEntity<?> enroll(@PathVariable("") long topicID, @RequestHeader("X-Auth") String token) {
        throw new UnsupportedOperationException("Not supported yet.");
//        long userId = 0;
//        topicService.enroll(topicID, userId);
//        return ResponseEntity.ok().body("User has enrolled to the topic successfully.");
    }
}
