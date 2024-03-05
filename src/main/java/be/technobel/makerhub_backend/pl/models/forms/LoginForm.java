package be.technobel.makerhub_backend.pl.models.forms;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class LoginForm {
    private String username;
    private String password;
}
