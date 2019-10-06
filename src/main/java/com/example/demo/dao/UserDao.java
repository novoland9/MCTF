package com.example.demo.dao;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    public User getByPasswordAndEmail(String email, String password);
    public User findByEmail(String email);
    public User findByUsername(String username);
}
