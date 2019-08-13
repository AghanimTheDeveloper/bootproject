package com.aghanim.bootproject.controller;

import com.aghanim.bootproject.model.User;
import com.aghanim.bootproject.service.abstraction.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/index")
    public String welcome (Model model){
        return "index";
    }

    @GetMapping(value = "/signup")
    public String registration(){
        return "signup";
    }

    @PostMapping(value = "/signup")
    public String signUp(User userForm){
        userService.addUser(userForm);
        return "redirect:/login";
    }

    @GetMapping(value = "/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping(value = "/postLogin")
    public String postLogin(Model model, HttpSession session){
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        validatePrinciple(authentication.getPrincipal());
        User loggedInUser = (User) authentication.getPrincipal();
        model.addAttribute("currentUser", loggedInUser.getName());
        session.setAttribute("user", loggedInUser);
        return "redirect:/index";
    }

    private void validatePrinciple(Object principal) {
        if (!(principal instanceof UserDetails)) {
            throw new  IllegalArgumentException("Principal can not be null!");
        }
    }

    @GetMapping(value = "/user")
    public String userPage(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping(value = "/admin")
    public String usersList(Model model){
        List<User> users = userService.getUsers();
        model.addAttribute("list", users);
        return "admin";
    }

    @GetMapping(value = "/add")
    public String userFormForAdd (Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "add";
    }

    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute("user") User user){
        userService.addUser(user);
        return "redirect:/admin";
    }


    @GetMapping(value = "/edit")
    public String userFormForUpdate(@RequestParam("id") long id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping(value = "/edit")
    public String editUser(@ModelAttribute("user") User user){
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/delete")
    public String deleteUser(@RequestParam("id") long id){
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
