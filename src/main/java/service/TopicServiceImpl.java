package service;

import dao.TopicDao;

import java.util.Date;
import java.util.List;
import model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicDao topicDao;

    @Override
    public List<Topic> list() {
        return topicDao.list();
    }

    @Override
    public long save(Topic topic) {
        topic.setNextGroupingTime(new Date(System.currentTimeMillis() +  (1000 * 60 * 60 * 24)));
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
