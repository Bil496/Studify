package controller;

import model.Team;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.TeamService;
import service.UserService;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;

    /*---Add new user---*/
    @PostMapping("/user")
    public ResponseEntity<?> save(@RequestBody User user) {
        long id = userService.save(user);
        return ResponseEntity.ok().body("New User has been saved with ID:" + id);
    }

    /*---Get a user by id---*/
    @GetMapping("/user/{id}")
    public ResponseEntity<User> get(@PathVariable("id") long id) {
        User user = userService.get(id);
        return ResponseEntity.ok().body(user);
    }

    /*---get all books---*/
    @GetMapping("/user")
    public ResponseEntity<List<User>> list() {
        List<User> users = userService.list();
        return ResponseEntity.ok().body(users);
    }

    /*---get all teams of user---*/
    @GetMapping("/user/{id}/teams")
    public ResponseEntity<List<Team>> list(@PathVariable("id") long id) {
        List<Team> teams = teamService.teamsOfUser(id);
        return ResponseEntity.ok().body(teams);
    }

    /*---Update a user by id---*/
    @PutMapping("/user/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody User user) {
        userService.update(id, user);
        return ResponseEntity.ok().body("User has been updated successfully.");
    }

    /*---Delete a user by id---*/
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        userService.delete(id);
        return ResponseEntity.ok().body("User has been deleted successfully.");
    }
}
