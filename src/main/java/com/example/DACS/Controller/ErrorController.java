package com.example.DACS.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {
    @GetMapping("/notfound/{mess}")
    public String NotFoundElement(@PathVariable String mess,
                                  Model model){
        model.addAttribute("errorMessage", mess);
        return "error/404";
    }
}
