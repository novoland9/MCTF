package com.example.demo.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Team {
    @Id
    String teamName;
    User teamLeader;
    List<User> teamMember;
    Integer totalPoint;

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

    public List<User> getTeamMember() {
        return teamMember;
    }

    public void setTeamMember(List<User> teamMember) {
        this.teamMember = teamMember;
    }

    public Integer getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(Integer totalPoint) {
        this.totalPoint = totalPoint;
    }
}
