package com.example.DACS.Controller;

import com.example.DACS.Another.ErrorValuePage;
import com.example.DACS.Model.User;
import com.example.DACS.Service.RoleService;
import com.example.DACS.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUser());
        return "users/index_user";
    }

    @GetMapping("/add")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        return "users/add_user";
    }

    @PostMapping("/add")
    public String saveUser(@Valid @ModelAttribute("user") User user,
                           BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            model.addAttribute("user", user);
            return "users/add_user";
        }
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{userName}")
    public String editUserForm(@PathVariable("userName") String userName,
                               Model model) {
        User user = userService.findByUsername(userName).isPresent() ? userService.findByUsername(userName).get() : null;
        if (user == null) {
            return ErrorValuePage.NOTFOUND.value + "UserName " + userName + " not found";
        }
        model.addAttribute("user", user);
        return "/users/edit_user";
    }

    @PostMapping("/edit/{userName}")
    public String updateUser(@PathVariable("userName") String userName,
                             @Valid @ModelAttribute("user") User user,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "/users/edit_user";
        }
        User userFind = userService.findByUsername(userName).isPresent() ? userService.findByUsername(userName).get() : null;
        if (userFind == null) {
            return ErrorValuePage.NOTFOUND.value + "UserName " + userName + " not found";
        }
        user.setId(userFind.getId());
        userService.save(user);
        return "redirect:/users";
    }
    @GetMapping("/delete/{userName}")
    public String deleteUser(@PathVariable("userName") String userName,
                             Model model) {
        User user = userService.findByUsername(userName).isPresent() ? userService.findByUsername(userName).get() : null;
        if (user == null) {
            return ErrorValuePage.NOTFOUND.value + "UserName " + userName + " not found";
        }
        userService.deleteUser(userName);
        return "redirect:/users";
    }
}

