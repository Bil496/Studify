package dao;

import model.Talent;
import model.Topic;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TopicDaoImp implements TopicDao {

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
        NativeQuery query = session.createNativeQuery(String.format("insert into User_Topic (user_id, topic_id) values (%d, %d)", user.getId(), topic.getId()));
        query.executeUpdate();
        topic.setEnrolledNumber(topic.getEnrolledNumber() + 1);
        topic.setWaitingToGrouped(topic.getWaitingToGrouped() + 1);
        session.update(topic);
        for (Talent talent : user.getTalents()) {
            session.save(talent);
        }
    }

    @Override
    public List<User> getUsersWithoutTeam(Topic topic) {
        List<User> users = new ArrayList<>();
        Session session = sessionFactory.getCurrentSession();

        NativeQuery query = session.createNativeQuery("select user_id from User_Topic t where t.topic_id = :topic_id and t.user_id not in (select u.user_id from Team tt, User_Team u where tt.topic_id = :topic_id and tt.id = u.team_id)")
                .addScalar("user_id", new LongType());
        List<Long> rows = query.setParameter("topic_id", topic.getId()).list();
        for (Long userId : rows) {
            User user = new User();
            user.setId(userId);
            users.add(user);
        }
        return users;
    }
}
