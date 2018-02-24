package task;

import algorithm.BasicAlgorithm;
import algorithm.TeamCreateAlgorithm;
import model.Team;
import model.Topic;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.TalentService;
import service.TopicService;
import service.UserService;

import java.util.HashSet;
import java.util.List;

@Component
public class CreateTeamsTask implements Runnable {
    private long topicId;
    @Autowired
    TopicService topicService;
    @Autowired
    UserService userService;
    @Autowired
    TalentService talentService;

    public CreateTeamsTask(long topicId) {
        this.topicId = topicId;
    }
    
    @Override
    public void run() {
        TeamCreateAlgorithm algorithm = new BasicAlgorithm();
        Topic topic = topicService.get(topicId);
        List<User> usersWithoutTeam = topicService.getUsersWithoutTeam(topic);
        for (User user : usersWithoutTeam){
            user.setTalents(new HashSet<>(talentService.getTalentsByTopicId(user.getId(), topic.getId())));
        }
        List<Team> teams = algorithm.createTeams(topic, usersWithoutTeam);
        /*
            usersWithoutTeam has users
            WARN: users only have ID and talents field, other fields are empty.
            In case their other fields are needed, feel free to call userService.get(user.getId()) method for each user.
         */
        // Implement Studfy Algorithm to algorithm package with implementing TeamCreateAlgorithm interface.
    }

}
