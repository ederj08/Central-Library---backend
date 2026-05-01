package com.bootcamp.login;

import org.springframework.security.access.prepost.PreAuthorize;
import  org.springframework.security.core.Authentication;
import  org.springframework.security.core.GrantedAuthority;
import  org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/v1/login")
@CrossOrigin(origins = "http://localhost:4200")

public class LoginController {

    @GetMapping
    public Collection<? extends GrantedAuthority>
    login(Authentication auth) {
        return auth.getAuthorities();
    }
}
