package service;

import java.util.List;

import model.Team;

public interface TeamService {

    List<Team> teamsOfUser(long userId);

    long save(Team team);

    Team get(long id);
}
