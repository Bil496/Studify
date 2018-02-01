package dao;

import java.util.List;
import model.Topic;

public interface TopicDao {

    List<Topic> list();

    long save(Topic topic);

    Topic get(long id);

    public void enroll(long topicID, long userId);
}
