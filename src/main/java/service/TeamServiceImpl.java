package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.TeamDao;
import model.Team;
@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamDao teamDao;
    @Override
    public List<Team> teamsOfUser(long userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long save(Team team) {
        return teamDao.save(team);
    }

    @Override
    public Team get(long id) {
        // TODO Auto-generated method stub
        return null;
    }

}
