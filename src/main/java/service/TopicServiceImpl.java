package service;

import dao.TopicDao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import model.Topic;
import model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicDao topicDao;
    @Autowired
    UserService userService;

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
    public void enroll(Topic topic, User user) {
        Set<Topic> topics = user.getTopics();
        topics.add(topic);
        user.setTopics(topics);
        topicDao.enroll(topic, user);
    }
}
