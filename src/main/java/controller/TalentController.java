package controller;

import model.Talent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import service.TalentService;

import java.util.List;

@RestController
public class TalentController {
    @Autowired
    private TalentService talentService;

    @GetMapping("/talent")
    public ResponseEntity<List<Talent>> getTalentsByTopicId(@RequestHeader long userId, @RequestHeader long topicId) {
        List<Talent> talents = talentService.getTalentsByTopicId(userId, topicId);
        return ResponseEntity.ok().body(talents);
    }
}
