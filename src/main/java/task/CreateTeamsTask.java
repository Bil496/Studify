package task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import algorithm.BasicAlgorithm;
import algorithm.TeamCreateAlgorithm;
import model.Talent;
import model.Team;
import model.Topic;
import model.User;
import service.TalentService;
import service.TopicService;
import service.UserService;

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
        for (User user : usersWithoutTeam) {
            List<Talent> talents; 
            talents = new ArrayList<>(talentService.getTalentsByTopicId(user.getId(), topic.getId()));
            Collections.sort(talents);
            user.setTalents(talents);
        }
        List<Team> teams = algorithm.createTeams(topic, usersWithoutTeam);
        topic.setWaitingToGrouped(usersWithoutTeam.size() - getTotalUsersInTeams(teams));
        topic.setTotalGroupNumber(topic.getTotalGroupNumber() + teams.size());
        topic.setNextGroupingTime(new Date(topic.getNextGroupingTime().getTime() + (1000 * 60 * 60 * 24)));
        // TODO: call services for teams and topic
        /*
            usersWithoutTeam has users
            WARN: users only have ID and talents field, other fields are empty.
            In case their other fields are needed, feel free to call userService.get(user.getId()) method for each user.
         */
        // Implement Studfy Algorithm to algorithm package with implementing TeamCreateAlgorithm interface.
    }

    private int getTotalUsersInTeams(List<Team> teams){
        int total = 0;
        for (Team team: teams){
            total += team.getSize();
        }
        return total;
    }

}
