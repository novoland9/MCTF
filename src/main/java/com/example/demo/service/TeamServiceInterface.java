package com.example.demo.service;

import com.example.demo.entities.Team;
import com.example.demo.entities.User;

import java.util.List;

public interface TeamServiceInterface {
    Team save(Team team);
    Team findByTeamName(String teamName);
    Team findByTeamLeader(User teamLeader);
    Boolean checkTeamByTeamName(String teamName);
    List<User> findUserByTeamId(Integer teamId);
    void dismissTeam(Team team);
}
