package dao;

import model.Talent;
import model.UserSubTopicId;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public class TalentDaoImp implements TalentDao {
    private SessionFactory sessionFactory;

    @Override
    public List<Talent> getTalentsByTopicId(long userId, long topicId) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Talent> cq = cb.createQuery(Talent.class);
        Root<Talent> root = cq.from(Talent.class);
        cq.select(root);
        Query<Talent> query = session.createQuery(cq);
        List<Talent> talents = new ArrayList<>();
        for (Talent talent : query.getResultList()) {
            UserSubTopicId userSubTopicId = talent.getUserSubTopicId();
            if (userSubTopicId.getSubTopic().getTopic().getId() == topicId) {
                if (userSubTopicId.getUser().getId() == userId) {
                    talents.add(talent);
                }
            }
        }
        return talents;
    }

    @Override
    public void save(Talent talent) {
        sessionFactory.getCurrentSession().save(talent);
    }
}
