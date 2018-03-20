package dao;

import model.Team;

import java.util.List;

public interface TeamDao {
    List<Team> teamsOfUser(long userId);

    long save(Team team);

    Team get(long id);
}
