package dao;

import model.Topic;
import model.User;

import java.util.List;

public interface TopicDao {

    List<Topic> list();

    long save(Topic topic);

    Topic get(long id);

    void enroll(Topic topic, User user);

    List<User> getUsersWithoutTeam(Topic topic);
}
