package service;

import dao.TopicDao;
import java.util.List;
import model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicDao topicDao;

    @Override
    public List<Topic> list() {
        return topicDao.list();
    }

    @Override
    public long save(Topic topic) {
        return topicDao.save(topic);
    }

    @Override
    public Topic get(long id) {
        return topicDao.get(id);
    }

    @Override
    public void enroll(long topicId, long userId) {
        topicDao.enroll(topicId, userId);
    }
}
