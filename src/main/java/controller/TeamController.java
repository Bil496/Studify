package controller;

import model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.TeamService;

/**
 * Created by ask on 20.02.2018
 */
@RestController
public class TeamController {
    @Autowired
    private TeamService teamService;

    /*---Add new team---*/
    @PostMapping("/team")
    public ResponseEntity<?> save(@RequestBody Team team) {
        long id = teamService.save(team);
        return ResponseEntity.ok().body("New Team has been saved with ID:" + id);
    }

    /*---Get a team by id---*/
    @GetMapping("/team/{id}")
    public ResponseEntity<Team> get(@PathVariable("id") long id) {
        Team team = teamService.get(id);
        return ResponseEntity.ok().body(team);
    }
}
