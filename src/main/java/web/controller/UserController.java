package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.DAO.UserDAO;
import web.model.User;


@Controller
public class UserController {

    private final UserDAO userDAO;
    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/users/add")
    public String addUser(@ModelAttribute(value = "user") User user) {
        return "addUser";
    }
    @PostMapping(value = "/users/add")
    public String addingUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addUser";
        }
        userDAO.addUser(user);
        return "redirect:/users";
    }

    @GetMapping(value = "/user/delete")
    public String deleteUser(@RequestParam int id) {
        userDAO.deleteUser(id);
        return "redirect:/users";

    }

    @GetMapping(value = "/user/edit")
    public String updateUser(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userDAO.getUser(id));
        return "edit";
    }

    @PostMapping(value = "/user/edit")
    public String updateUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        userDAO.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping(value = "/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userDAO.getUsers());
        return "users";
    }

}
