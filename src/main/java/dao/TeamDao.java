package dao;

import java.util.List;

import model.Team;

public interface TeamDao {
    List<Team> teamsOfUser(long userId);

    long save(Team team);

    Team get(long id);
}
