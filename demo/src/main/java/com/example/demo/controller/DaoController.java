package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class DaoController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DaoController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

//    @GetMapping
//    public String usersList(Model model) {
//        model.addAttribute("users",userService.usersList());
//        return "/admin";
//    }
    @GetMapping
    public String usersList(Model model, @AuthenticationPrincipal User user) {
        List<Role> allRole = roleService.roleList();
        List<User> allUsers = userService.usersList();
        model.addAttribute("allRoles", allRole);
        model.addAttribute("allUs", allUsers);
        model.addAttribute("user", user);
        return "admin";
}

    @PostMapping("/new")
    public String create(@ModelAttribute User user, @RequestParam(value = "nameRole") String[] nameRole) {
        user.setRoles(roleService.getSetOfRoles(nameRole));
        userService.addUser(user);
        return "redirect:/admin";
    }

//    @GetMapping("/new")
//    public String newUser(Model model) {
//        model.addAttribute("user", new User());
//        model.addAttribute("roles", roleService.roleList());
//        return "/info";
//    }

//
//    @GetMapping("/edit/{id}")
//    public String edit(@PathVariable("id") int id, ModelMap model) {
//        model.addAttribute("user", userService.getUserById(id));
//        model.addAttribute("roles", roleService.roleList());
//        return "/edit";
//    }
    @PostMapping(value = "/edit/{id}")
    public String update(@ModelAttribute User user, @RequestParam(value = "nameRole") String[] nameRole) {
        user.setRoles(roleService.getSetOfRoles(nameRole));
        userService.updateUser(user);
        return "redirect:/admin";
    }
    @DeleteMapping("/remove/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }
}
