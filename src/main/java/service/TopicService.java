package service;

import java.util.List;
import model.Topic;
import model.User;

public interface TopicService {

    List<Topic> list();

    long save(Topic topic);

    Topic get(long id);

    public void enroll(Topic topic, User user);
}
