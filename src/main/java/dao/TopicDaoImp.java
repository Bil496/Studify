package dao;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Topic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class TopicDaoImp implements TopicDao {

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
}
