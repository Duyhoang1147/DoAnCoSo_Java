package com.example.DACS.Controller;

import com.example.DACS.Another.ErrorValuePage;
import com.example.DACS.Model.User;
import com.example.DACS.Service.RoleService;
import com.example.DACS.Service.UserService;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@EnableWebSecurity
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
        model.addAttribute("roles", roleService.getAllRoles());
        return "users/add_user";
    }

    @PostMapping("/add")
    public String saveUser(@Valid @ModelAttribute("user") User user,
                           BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            model.addAttribute("user", user);
            model.addAttribute("roles", roleService.getAllRoles());
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
        model.addAttribute("roles", roleService.getAllRoles());
        return "/users/edit_user";
    }

    @PostMapping("/edit/{userName}")
    public String updateUser(@PathVariable("userName") String userName,
                             @Valid @ModelAttribute("user") User user,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("roles", roleService.getAllRoles());
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
    //Đăng nhập đăng ký
    @GetMapping("/login")
    public String login() {
        return "users/login";
    }
    @OneToMany()
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User()); // Thêm một đối tượng User mới vào model
        return "users/register";
    }
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, // Validate đối tượng User
                           BindingResult bindingResult, // Kết quả của quá trình validate
                           Model model) {
        if (bindingResult.hasErrors()) { // Kiểm tra nếu có lỗi validate
            var errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toArray(String[]::new);
            model.addAttribute("errors", errors);
            return "users/register"; // Trả về lại view "register" nếu có lỗi
        }
        user.setStatus(true);
        userService.save(user); // Lưu người dùng vào cơ sở dữ liệu
        userService.setDefaultRole(user.getUsername()); // Gán vai trò mặc định cho người dùng
        return "redirect:/users/login"; // Chuyển hướng người dùng tới trang "login"
    }
}

