package service;

import dao.TopicDao;
import model.Topic;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task.CreateTeamsTask;

import java.util.*;
import java.util.concurrent.ScheduledFuture;

@Service
@Transactional
public class TopicServiceImpl implements TopicService {

    TopicDao topicDao;

    TaskScheduler taskScheduler;

    ScheduledFuture<?> scheduledFuture;

    UserService userService;

    @Override
    public List<Topic> list() {
        return topicDao.list();
    }

    @Override
    public long save(Topic topic) {
        topic.setNextGroupingTime(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)));
        long topicId = topicDao.save(topic);
        scheduledFuture = taskScheduler.scheduleAtFixedRate(new CreateTeamsTask(topicId), (1000 * 60 * 60 * 24));
        return topicId;
    }

    @Override
    public Topic get(long id) {
        return topicDao.get(id);
    }

    @Override
    public void enroll(Topic topic, User user) {
        user.setTopics(new HashSet<>(Arrays.asList(topic)));
        topicDao.enroll(topic, user);
    }

    @Override
    public List<User> getUsersWithoutTeam(Topic topic) {
        return topicDao.getUsersWithoutTeam(topic);
    }

}
