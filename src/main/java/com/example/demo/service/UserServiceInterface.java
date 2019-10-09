package com.example.demo.service;

import com.example.demo.entities.Team;
import com.example.demo.entities.User;

public interface UserServiceInterface {
    User save(User user);
    User findByEmail(String email);
    User findByUsername(String username);
    boolean checkUserByUsername(String username);
    boolean checkUserByEmail(String email);
    void leaveTeam(User user);
    void joinTeam(User user, Team team);
}
