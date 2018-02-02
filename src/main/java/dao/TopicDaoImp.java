package dao;

import java.util.List;
import java.util.Set;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Topic;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import service.UserService;

@Repository
public class TopicDaoImp implements TopicDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Topic> list() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Topic> cq = cb.createQuery(Topic.class);
        Root<Topic> root = cq.from(Topic.class);
        cq.select(root);
        Query<Topic> query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public long save(Topic topic) {
        sessionFactory.getCurrentSession().save(topic);
        return topic.getId();
    }

    @Override
    public Topic get(long id) {
        return sessionFactory.getCurrentSession().get(Topic.class, id);
    }

    @Override
    public void enroll(Topic topic, User user) {
        Session session = sessionFactory.getCurrentSession();
        NativeQuery query = session.createNativeQuery(String.format("insert into user_topic (user_id, topic_id) values (%d, %d)", user.getId(), topic.getId()));
        query.executeUpdate();
        topic.setEnrolledNumber(topic.getEnrolledNumber() + 1);
        topic.setWaitingToGrouped(topic.getWaitingToGrouped() + 1);
        session.update(topic);
    }
}
