package com.example.demo.myController;


import com.example.demo.entities.Team;
import com.example.demo.entities.User;
import com.example.demo.service.TeamService;
import com.example.demo.service.UserService;
import com.example.demo.util.TeamUtil;
import com.example.demo.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TeamController {
    @Autowired
    UserService userService;

    @Autowired
    TeamService teamService;

    @RequestMapping("/doCreateTeam")
    public String createTeam(Team team, HttpServletRequest request, Model model){

        User user = (User)request.getSession().getAttribute("USER");

        Team teamByName = teamService.findByTeamName(team.getTeamName());
        if (teamByName != null)
            model.addAttribute("crateTeamState", "团队名已被使用。");
        else if (user.getInTeam())
            model.addAttribute("crateTeamState", "你已经在其它团队中");
        else if (user.getIsTeamLeader())
            model.addAttribute("crateTeamState", "你已经创建了团队");
        else{
            team.setTotalPoint(0);
            team.setTeamLeader(user);
            teamService.save(team);
            teamService.insertTeam(team);
            model.addAttribute("crateTeamState", "成功创建团队");
            request.getSession().setAttribute(TeamUtil.TEAM_SESSION_KEY, team);
            return "index";
        }
        model.addAttribute("crateTeamState", "出现了预期之外的错误");
        return "index";
    }

    @RequestMapping("/doDismissTeam")
    public String dismissTeam(HttpServletRequest request, Model model){

        User user = (User)request.getSession().getAttribute("USER");
        Team team = (Team)request.getSession().getAttribute("TEAM");

        if (!user.getInTeam())
            model.addAttribute("dismissTeamState", "宁还没有入团队");
        else if (!user.getIsTeamLeader())
            model.addAttribute("dismissTeamState", "宁不是队长，无权解散团队");
        else {
            teamService.dismissTeam(team);
            model.addAttribute("dismissTeamState","解散团队成功");
        }
        model.addAttribute("dismissTeamState", "出现了预期之外的错误");
        return "index";
    }

    @RequestMapping("/doJoinTeam")
    public void joinTeam(Team team, Model model, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("USER");
        Team sessionTeam = (Team)request.getSession().getAttribute("TEAM");

        if (sessionTeam!=null || user.getInTeam()==true){
            model.addAttribute("joinTeamState","你已经加入了队伍");
        }
        if (sessionTeam==null && user.getInTeam()==false){
            Team teamToJoin = teamService.findByTeamName(team.getTeamName());
            if (teamToJoin == null){
                model.addAttribute("joinTeamState", "不存在该队伍");
            }
            else {
                userService.joinTeam(user, teamToJoin);
                model.addAttribute("joinTeamState","加入队伍成功");
            }
        }
        model.addAttribute("joinTeamState","出现了预期之外的错误");
    }

    @RequestMapping("/doLeaveTeam")
    public void leaveTeam(Model model, HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("USER");
        Team sessionTeam = (Team)request.getSession().getAttribute("TEAM");

        if (user.getInTeam()==false || sessionTeam==null){
            model.addAttribute("leaveTeamState", "你还没有加入队伍");
        }

    }
}


