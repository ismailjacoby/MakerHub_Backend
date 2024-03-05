package be.technobel.makerhub_backend.pl.models.forms;

public record UserForm(
        String username,
        String firstName,
        String lastName,
        String email,
        String password
) {
}
