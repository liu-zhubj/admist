package com.xiaozl.initialwork1.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiaozl.initialwork1.entity.User;
import com.xiaozl.initialwork1.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaozl
 * @date 2017/11/20
 */
@Controller
@RequestMapping(value = "")
public class IndexController {

    @Resource
    private UserService userService;
    private static List<User> userList;
    public IndexController(){
        this.userList=new ArrayList<User>();
    }
    @RequestMapping(value = {"", "/", "login"}, method = RequestMethod.GET)
    public String toLogin(HttpServletRequest request)
    {
        //If session have attribute "user", jump to index page, else jump to login page.
        if (request.getSession().getAttribute("user") != null){
            return "index";
        }
        else {
            return "login";
        }
    }

    //Login
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(User user, Model model, HttpServletRequest request) throws Exception{
        try {
            //If pass, set attribute to session, then redirect to index page.
            if (userService.checkLogin(user)) {
                request.getSession().setAttribute("user", user);
                return "index";
            }
            //If not pass, send error attribute.
            else{
                model.addAttribute("login_err", "登录失败!");
                return "login";
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showReguster(HttpServletRequest request,HttpServletResponse response){
        ModelAndView mav=new ModelAndView("register");
        mav.addObject("User",new User());
        return mav;
    }
   /* public String toRegister(HttpServletRequest request) throws Exception {
        //If session have attribute "user", jump to index page, else jump to login page.
        if (request.getSession().getAttribute("user") != null) {
            String password = request.getParameter("password");
            String userName = request.getParameter("userNmae");
            userService.addUser(userName, password);

        }
        return  "register";
    }*/
    @RequestMapping(value = {"", "/", "register"}, method = RequestMethod.POST)
    public ModelAndView addUser(HttpServletRequest request,HttpServletResponse response, @ModelAttribute("user")User user) throws Exception {
        userService.newUser(user);
        return new ModelAndView("index","entity",user.getUserName());

    }

   /* public String register(@RequestParam("userName") String userName,

                           @RequestParam("password") String password,HttpServletRequest request) throws Exception {

      //    Map<Long, User> users = new ConcurrentHashMap<Long, User>();

        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        //存储user信息
        userService.checkLogin(user);
        userList.add(user);
        //跳转页面到登录页面
        return "login";
    }*/
    /*    @RequestMapping(value = "", method = RequestMethod.POST)
*/
}
