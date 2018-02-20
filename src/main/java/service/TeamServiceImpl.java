package service;

import java.util.ArrayList;
import java.util.List;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.TeamDao;
import dao.UserDao;
import model.Team;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamDao teamDao;

    @Autowired
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
