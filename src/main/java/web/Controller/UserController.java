package web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = {"/"})
    public String Users(Model model) {
        List<User> list = userService.getAllUsers();
        model.addAttribute("list", list);
        return "user";
    }

    @RequestMapping(value = "/remove/{id}")
    public String removeUser(@PathVariable(value = "id") long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @GetMapping(value = {"/add"})
    public String addNewUserGet(@ModelAttribute("newUser") User user) {
        return "addUser";
    }

    @PostMapping("/add")
    public String addNewUserPost(@ModelAttribute(value = "newUser") User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping(value = {"/edit/{id}"})
    public String editNewUserGet(Model model, @PathVariable(value = "id") long id) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String editNewUserPost(@ModelAttribute(value = "user") User user) {
        userService.editUser(user);
        return "redirect:/";
    }

}
