package com.example.demo.dao;

import com.example.demo.entities.Team;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamDao extends JpaRepository<Team, Integer> {
    public Team getByTeamName(String name);
    public Team findByTeamName(String name);
    public Team findByTeamLeader(User teamLeader);
    public List<User> findByTeamId(Integer teamId);
    public void deleteTeamByTeamLeader(User teamLeader);
}
