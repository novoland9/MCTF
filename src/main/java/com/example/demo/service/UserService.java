package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface{
    @Autowired
    UserDao userDao;

    public User getUser(String email, String password) {
        return userDao.getByPasswordAndEmail(email,password);
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
}
