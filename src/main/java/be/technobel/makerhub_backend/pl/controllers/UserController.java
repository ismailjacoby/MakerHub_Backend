package be.technobel.makerhub_backend.pl.controllers;

import be.technobel.makerhub_backend.bll.exceptions.NotFoundException;
import be.technobel.makerhub_backend.bll.services.UserService;
import be.technobel.makerhub_backend.pl.models.dtos.AuthDto;
import be.technobel.makerhub_backend.pl.models.dtos.UserDto;
import be.technobel.makerhub_backend.pl.models.dtos.UserFullDto;
import be.technobel.makerhub_backend.pl.models.forms.LoginForm;
import be.technobel.makerhub_backend.pl.models.forms.EmailForm;
import be.technobel.makerhub_backend.pl.models.forms.UpdateUserForm;
import be.technobel.makerhub_backend.pl.models.forms.UserForm;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public void forgotPassword(@RequestBody @Valid EmailForm form) {
        userService.forgotPassword(form);
    }

    @PostMapping("/editaccount")
    public void editAccount(@RequestBody @Valid UpdateUserForm form){
        userService.editAccount(form);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(UserDto.fromDto(
                userService.getUserByUsername(username)
                        .orElseThrow(()-> new NotFoundException("User not found."))));
    }

    @PatchMapping("/deactivate/{username}")
    public void deactivateAccount(@PathVariable String username) {
        userService.deactivateAccount(username);
    }
    @PatchMapping("/block/{username}")
    public void blockAccount(@PathVariable String username) {
        userService.blockAccount(username);
    }

    @GetMapping("clients/all")
    public ResponseEntity<List<UserFullDto>> getAllClients(){
        List<UserFullDto> clients = userService.getAllClients()
                .stream()
                .map(UserFullDto::fromDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clients);
    }
}
