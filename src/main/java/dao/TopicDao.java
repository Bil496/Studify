package dao;

import java.util.List;
import model.Topic;
import model.User;

public interface TopicDao {

    List<Topic> list();

    long save(Topic topic);

    Topic get(long id);

    public void enroll(Topic topic, User user);
}
