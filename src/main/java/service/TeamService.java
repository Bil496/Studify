package service;

import model.Team;

import java.util.List;

public interface TeamService {

    List<Team> teamsOfUser(long userId);

    long save(Team team);

    Team get(long id);
}
