package com.seu.studentblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    // Spring Security intercepts /admin/login for the POST; this GET just renders the form.
    @GetMapping("/admin/login")
    public String loginPage() {
        return "admin/login";
    }
}
