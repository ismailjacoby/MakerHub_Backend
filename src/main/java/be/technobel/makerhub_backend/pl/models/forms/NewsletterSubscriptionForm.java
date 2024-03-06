package be.technobel.makerhub_backend.pl.models.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NewsletterSubscriptionForm {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
}
