package be.technobel.makerhub_backend.bll.services;

import be.technobel.makerhub_backend.pl.models.dtos.AuthDto;
import be.technobel.makerhub_backend.pl.models.forms.LoginForm;
import be.technobel.makerhub_backend.pl.models.forms.UserForm;

public interface UserService {
    AuthDto login(LoginForm form);
    void signUp(UserForm form);
}
