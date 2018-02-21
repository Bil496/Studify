package controller;

import model.Talent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.TalentService;

import java.util.List;

@RestController
public class TalentController {
    @Autowired
    private TalentService talentService;

    @PostMapping("/talent")
    public ResponseEntity<List<Talent>> getTalentsByTopicId(@RequestBody long userId, @RequestBody long topicId) {
        List<Talent> talents = talentService.getTalentsByTopicId(userId, topicId);
        return ResponseEntity.ok().body(talents);
    }
}
