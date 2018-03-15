package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Team;
import model.User;
@Repository
public class TeamDaoImpl implements TeamDao{

    private SessionFactory sessionFactory;

    @Override
    public List<Team> teamsOfUser(long userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long save(Team team) {
        Session session = sessionFactory.getCurrentSession();
        session.save(team);
        for(User user : team.getUsers()) {
            NativeQuery query = session.createNativeQuery(String.format("insert into user_team (user_id, team_id) values (%d, %d)", user.getId(), team.getId()));
            query.executeUpdate();   
        }
        return team.getId();
    }

    @Override
    public Team get(long id) {
        // TODO Auto-generated method stub
        return null;
    }

}
