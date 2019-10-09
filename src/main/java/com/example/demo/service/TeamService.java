package com.example.demo.service;

import com.example.demo.dao.TeamDao;
import com.example.demo.entities.Team;
import com.example.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TeamService implements TeamServiceInterface{
    @Autowired
    TeamDao teamDao;
    @Autowired
    UserService userService;

    public void insertTeam(Team team){
        teamDao.save(team);
    }

    public Team getTeam(String teamName){
        return teamDao.getByTeamName(teamName);
    }

    @Override
    public Team save(Team team) {
        return teamDao.save(team);
    }

    @Override
    public Team findByTeamName(String teamName) {
        return teamDao.findByTeamName(teamName);
    }

    @Override
    public Team findByTeamLeader(User teamLeader) {
        return teamDao.findByTeamLeader(teamLeader);
    }

    @Override
    public Boolean checkTeamByTeamName(String teamName) {
        Team newTeam = findByTeamName(teamName);
        if (newTeam == null)
            return false;
        else
            return true;
    }

    @Override
    public List<User> findUserByTeamId(Integer teamId) {
        return teamDao.findByTeamId(teamId);
    }

    @Override
    public void dismissTeam(Team team) {
        List<User> userList = findUserByTeamId(team.getTeamId());
        for(int i = 0; i < userList.size(); i++){
            User user = userList.get(i);
            userService.leaveTeam(user);
        }
        User leader = team.getTeamLeader();
        userService.leaveTeam(leader);
        teamDao.deleteTeamByTeamLeader(leader);
    }

}
