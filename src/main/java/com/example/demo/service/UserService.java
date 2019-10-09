package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.entities.Team;
import com.example.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface{
    @Autowired
    UserDao userDao;

    public User getUser(String email, String password) {
        return userDao.getByEmailAndPassword(email,password);
    }

    public void insertUser(User user) {
        userDao.save(user);
    }

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public boolean checkUserByUsername(String username) {
        User user = findByUsername(username);
        if (user == null)
            return true;
        else
            return false;
    }

    @Override
    public boolean checkUserByEmail(String email) {
        User user = findByEmail(email);
        if (user == null)
            return true;
        else
            return false;
    }

    @Override
    public void leaveTeam(User user) {
        user.setIsTeamLeader(false);
        user.setInTeam(false);
        user.setTeam(null);
    }

    @Override
    public void joinTeam(User user, Team team) {
        user.setTeam(team);
        user.setInTeam(true);
        user.setIsTeamLeader(false);
    }
}
