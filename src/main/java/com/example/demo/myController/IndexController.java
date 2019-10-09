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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


// ---------------------------------------------------------------------
//
//   index主页、登陆、登出、注册页面
//
// ---------------------------------------------------------------------

@Controller
public class IndexController {

    @Autowired
    UserService userService;

    @Autowired
    TeamService teamService;

    // 主页
    // ------------------------------------------------------------------
    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("username","未登录");
        return "index";
    }

    // 注册
    // -------------------------------------------------------------------
    @GetMapping("/register")
    public String register(){
        return "regist";
    }

    @RequestMapping("doRegister")
    public String doRegister(User user, Model model){
        User user1 = userService.findByUsername(user.getUsername());
        if (user1 != null) {
            model.addAttribute("registerState","用户名已存在");
            return "regist";
        }
        User user2 = userService.findByEmail(user.getEmail());
        if (user2 != null) {
            model.addAttribute("registerState", "邮箱已被注册");
            return "regist";
        }

        user.setInTeam(false);
        user.setIsTeamLeader(false);
        userService.insertUser(user);
        return "login";
    }


    // 登录
    // ------------------------------------------------------------------
    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("state","");
        return "login";
    }

    @RequestMapping("/doLogin")
    public String doLogin(User user, Model model, HttpServletRequest request){

        User user1 = userService.getUser(user.getEmail(), user.getPassword());
        System.out.println(user1);

        if (user1 == null) {
            model.addAttribute("state","用户名或密码错误");
            return "login";
        }
        else {
            request.getSession().setAttribute(UserUtil.USER_SESSION_KEY, user1);
            if (user1.getInTeam())
                request.getSession().setAttribute(TeamUtil.TEAM_SESSION_KEY, user1.getTeam());
            else
                request.getSession().setAttribute(TeamUtil.TEAM_SESSION_KEY, null);
            return "index";
        }
    }

    // 登出
    // ------------------------------------------------------------------
    @RequestMapping("/doLogout")
    public String doLogout(User user, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("USER");
        session.removeAttribute("TEAM");
        session.invalidate();
        return "login";
    }
}


