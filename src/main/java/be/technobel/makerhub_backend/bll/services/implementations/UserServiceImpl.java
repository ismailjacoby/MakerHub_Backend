package be.technobel.makerhub_backend.bll.services.implementations;

import be.technobel.makerhub_backend.bll.services.UserService;
import be.technobel.makerhub_backend.dal.models.entities.NewsletterEmail;
import be.technobel.makerhub_backend.dal.models.entities.UserEntity;
import be.technobel.makerhub_backend.dal.models.enums.UserRole;
import be.technobel.makerhub_backend.dal.repositories.NewsletterEmailRepository;
import be.technobel.makerhub_backend.dal.repositories.UserRepository;
import be.technobel.makerhub_backend.pl.config.security.JWTProvider;
import be.technobel.makerhub_backend.pl.models.dtos.AuthDto;
import be.technobel.makerhub_backend.pl.models.forms.LoginForm;
import be.technobel.makerhub_backend.pl.models.forms.UserForm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JWTProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final NewsletterEmailRepository newsletterEmailRepository;

    public UserServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           JWTProvider jwtProvider, PasswordEncoder passwordEncoder, NewsletterEmailRepository newsletterEmailRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.newsletterEmailRepository = newsletterEmailRepository;
    }


    @Override
    public AuthDto login(LoginForm form) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(form.getUsername(),form.getPassword()));
        UserEntity user = userRepository.findByUsername(form.getUsername()).get();
        String token = jwtProvider.generateToken(user.getUsername(), user.getRole());

        return AuthDto.builder()
                .token(token)
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }

    @Override
    public void signUp(UserForm form) {
        if(form == null){
            throw new IllegalArgumentException("Form can't be null.");
        }

        //Register new user
        UserEntity client = new UserEntity();
        client.setUsername(form.getUsername());
        client.setFirstName(form.getFirstName());
        client.setLastName(form.getLastName());
        client.setEmail(form.getEmail());
        client.setPassword(passwordEncoder.encode(form.getPassword()));
        client.setActive(true);
        client.setBlocked(false);
        userRepository.save(client);

        //Automatically adds the new user to a newsletter
        NewsletterEmail newsletterEmail = new NewsletterEmail();
        newsletterEmail.setEmail(form.getEmail());
        newsletterEmailRepository.save(newsletterEmail);
    }
}
