package service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.TopicDao;
import model.Topic;
import model.User;
import task.CreateTeamsTask;

@Service
@Transactional
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicDao topicDao;
    
    @Autowired
    TaskScheduler taskScheduler;
    
    ScheduledFuture<?> scheduledFuture;
    
    
    @Autowired
    UserService userService;

    @Override
    public List<Topic> list() {
        return topicDao.list();
    }

    @Override
    public long save(Topic topic) {
        topic.setNextGroupingTime(new Date(System.currentTimeMillis() +  (1000 * 60 * 60 * 24)));
        scheduledFuture = taskScheduler.scheduleAtFixedRate(new CreateTeamsTask(topic.getId()), (1000 * 60 * 60 * 24));
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
    
    @Override
    public Set<User> getUsersWithoutTeam(Topic topic){
        return topicDao.getUsersWithoutTeam(topic);
    }
    
}
