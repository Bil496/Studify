package service;

import dao.TeamDao;
import dao.UserDao;
import model.Team;
import model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    TeamDao teamDao;

    UserDao userDao;

    @Override
    public List<Team> teamsOfUser(long userId) {
        User user = userDao.get(userId);
        return new ArrayList<>(user.getTeams());
    }

    @Override
    public long save(Team team) {
        return teamDao.save(team);
    }

    @Override
    public Team get(long id) {
        return teamDao.get(id);
    }

}
