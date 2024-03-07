package be.technobel.makerhub_backend.bll.services;

import be.technobel.makerhub_backend.dal.models.entities.UserEntity;
import be.technobel.makerhub_backend.pl.models.dtos.AuthDto;
import be.technobel.makerhub_backend.pl.models.forms.LoginForm;
import be.technobel.makerhub_backend.pl.models.forms.NewsletterSubscriptionForm;
import be.technobel.makerhub_backend.pl.models.forms.UpdateUserForm;
import be.technobel.makerhub_backend.pl.models.forms.UserForm;

import java.util.Optional;

public interface UserService {
    AuthDto login(LoginForm form);
    void signUp(UserForm form);
    void forgotPassword(NewsletterSubscriptionForm form);
    Optional<UserEntity> editAccount(UpdateUserForm form);
    Optional<UserEntity> getUserByUsername(String username);
    void deactivateAccount(String username);
    void blockAccount(String username);
}
