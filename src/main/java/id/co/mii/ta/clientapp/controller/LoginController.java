/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.controller;

import id.co.mii.ta.clientapp.model.dto.request.LoginRequest;
import id.co.mii.ta.clientapp.service.LoginService;
import id.co.mii.ta.clientapp.service.UserService;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Fathullah
 */
@Controller
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    private LoginService loginService;
    private UserService userService;
    public static Integer empId;
//    public static String empName;

    @GetMapping
    public String login(LoginRequest loginRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || auth instanceof AnonymousAuthenticationToken) {
            return "auth/login";
        }

        return "redirect:/";
    }

    @PostMapping
    public String checkLogin(LoginRequest loginRequest) {

        if (!loginService.login(loginRequest)) {
            System.out.println("Gagal");
            return "redirect:login?error=true";
        }
        System.out.println("Berhasil");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String a = "redirect:/"; 
        Set<String> roles = auth.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet());
        empId = userService.getDetailByUsername(auth.getName()).getId();
        System.out.println(empId);
//        empName = loginRequest.getUsername();
//        System.out.println(empName);

        return a;
    }

}
