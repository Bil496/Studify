package dao;

import java.util.List;
import model.Topic;

public interface TopicDao {

    long save(Topic topic);

    Topic get(long id);

    List<Topic> list();

    void update(long id, Topic topic);

    void delete(long id);
}
