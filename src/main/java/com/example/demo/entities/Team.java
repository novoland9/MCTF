package com.example.demo.entities;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Team {
    @Id
    String teamName;
    User teamLeader;
    Integer totalPoint;
    @GeneratedValue
    Integer teamId;


    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    Set<User> users;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public User getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(User teamLeader) {
        this.teamLeader = teamLeader;
    }

    public Integer getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(Integer totalPoint) {
        this.totalPoint = totalPoint;
    }
}
