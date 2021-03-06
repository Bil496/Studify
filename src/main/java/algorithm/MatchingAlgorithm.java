package algorithm;

import model.Team;
import model.Topic;
import model.User;

import java.util.List;
import java.util.Set;

public abstract class MatchingAlgorithm {

    protected Topic topic;
    protected List<User> users;

    public MatchingAlgorithm(Topic topic, List<User> users) {
        this.topic = topic;
        this.users = users;
    }

    public abstract Set<Team> getTeams();

    public abstract Set<User> getUnmatchedUsers();

    public abstract boolean leftUnmatchedUsers();

}
