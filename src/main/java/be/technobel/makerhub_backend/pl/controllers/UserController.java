package be.technobel.makerhub_backend.pl.controllers;

import be.technobel.makerhub_backend.bll.services.UserService;
import be.technobel.makerhub_backend.pl.models.dtos.AuthDto;
import be.technobel.makerhub_backend.pl.models.forms.LoginForm;
import be.technobel.makerhub_backend.pl.models.forms.NewsletterSubscriptionForm;
import be.technobel.makerhub_backend.pl.models.forms.UserForm;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

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
    public void signUp(@RequestBody @Valid UserForm form){
        userService.signUp(form);
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/forgotpassword")
    public void forgotPassword(@RequestBody @Valid NewsletterSubscriptionForm form) {
        userService.forgotPassword(form);
    }
}
