package algorithm;

import model.Team;
import model.Topic;
import model.User;

import java.util.List;

/**
 * Created by burak on 2/24/2018.
 */
public interface TeamCreateAlgorithm {
    List<Team> createTeams(Topic topic, List<User> usersWithoutTeam);
}
