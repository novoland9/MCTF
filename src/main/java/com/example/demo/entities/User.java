package com.example.demo.entities;

import javax.persistence.*;

@Entity
public class User {
    @Id
    String username;
    String password;
    String email;
    Integer point = 0;
    @GeneratedValue
    Integer userId;


    @ManyToOne
    @JoinColumn(name = "teamId")
    Team team;

    Boolean inTeam;
    Boolean isTeamLeader;

    public Boolean getInTeam() {
        return inTeam;
    }

    public void setInTeam(Boolean inTeam) {
        this.inTeam = inTeam;
    }

    public Boolean getIsTeamLeader() {
        return isTeamLeader;
    }

    public void setIsTeamLeader(Boolean teamLeader) {
        isTeamLeader = teamLeader;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}