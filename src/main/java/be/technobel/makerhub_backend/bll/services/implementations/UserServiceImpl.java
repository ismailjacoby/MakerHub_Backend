package be.technobel.makerhub_backend.bll.services.implementations;

import be.technobel.makerhub_backend.bll.exceptions.DuplicateUserException;
import be.technobel.makerhub_backend.bll.exceptions.NotFoundException;
import be.technobel.makerhub_backend.bll.mailing.EmailSenderService;
import be.technobel.makerhub_backend.bll.services.UserService;
import be.technobel.makerhub_backend.dal.models.entities.NewsletterEmail;
import be.technobel.makerhub_backend.dal.models.entities.UserEntity;
import be.technobel.makerhub_backend.dal.models.enums.UserRole;
import be.technobel.makerhub_backend.dal.repositories.NewsletterEmailRepository;
import be.technobel.makerhub_backend.dal.repositories.UserRepository;
import be.technobel.makerhub_backend.pl.config.security.JWTProvider;
import be.technobel.makerhub_backend.pl.models.dtos.AuthDto;
import be.technobel.makerhub_backend.pl.models.forms.LoginForm;
import be.technobel.makerhub_backend.pl.models.forms.NewsletterSubscriptionForm;
import be.technobel.makerhub_backend.pl.models.forms.UserForm;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JWTProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final NewsletterEmailRepository newsletterEmailRepository;
    private final EmailSenderService emailSenderService;

    public UserServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           JWTProvider jwtProvider, PasswordEncoder passwordEncoder, NewsletterEmailRepository newsletterEmailRepository, EmailSenderService emailSenderService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.newsletterEmailRepository = newsletterEmailRepository;
        this.emailSenderService = emailSenderService;
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

        if(userRepository.existsByEmail(form.getEmail())){
            throw new DuplicateUserException("Email already exists.");
        }

        if(userRepository.existsByUsername(form.getUsername())){
            throw new DuplicateUserException("Username is already taken.");
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

    @Override
    public void forgotPassword(NewsletterSubscriptionForm form) {
        if(form == null){
            throw new IllegalArgumentException("Form can't be null.");
        }

        String email = form.getEmail();

        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent()){
            UserEntity user = userOptional.get();
            String username = user.getUsername();
            String password = generateRandomPassword();
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);

            emailSenderService.forgotPasswordEmail(email, username, password);
        } else{
            throw new NotFoundException("Email not found.");
        }
    }

    private String generateRandomPassword(){
        return RandomStringUtils.randomAlphanumeric(10);
    }
}
