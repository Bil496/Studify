package algorithm;

import data.CreateUser;
import model.Team;
import model.Topic;
import model.User;
import util.RandomStringGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by burak on 2/24/2018.
 */
public class BasicAlgorithm implements TeamCreateAlgorithm {
    private static final int TEAM_NAME_WORD_COUNT = 3;
    /*
        This class exist because real algorithm to find Nash Equilibria is not implemented yet.
     */
    @Override
    public List<Team> createTeams(Topic topic, List<User> usersWithoutTeam) {
        List<Team> teams = new ArrayList<>();
        for (int i = 0; i + topic.getMinSize() <= usersWithoutTeam.size(); i += topic.getMinSize()){
            Team team = new Team();
            team.setName(RandomStringGenerator.getSentence(TEAM_NAME_WORD_COUNT));
            team.setTopic(topic);
            team.setSize(topic.getMinSize());
            Set<User> usersOfTeam = new HashSet<>();
            team.setUsers(usersOfTeam);
            team.setUtility(10);
            for (int j = 0; j < topic.getMinSize(); j++){
                usersOfTeam.add(usersWithoutTeam.get(i + j));
            }
            teams.add(team);
        }
        return teams;
    }
}
