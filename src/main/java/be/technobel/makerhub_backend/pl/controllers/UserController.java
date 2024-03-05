package be.technobel.makerhub_backend.pl.controllers;

import be.technobel.makerhub_backend.bll.services.UserService;
import be.technobel.makerhub_backend.pl.models.dtos.AuthDto;
import be.technobel.makerhub_backend.pl.models.forms.LoginForm;
import be.technobel.makerhub_backend.pl.models.forms.UserForm;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/login")
    public AuthDto login(@RequestBody @Valid LoginForm form){
        return userService.login(form);
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/signup")
    public void signUp(@RequestBody UserForm form){
        userService.signUp(form);
    }
}
