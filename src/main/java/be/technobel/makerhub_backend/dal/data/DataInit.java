package be.technobel.makerhub_backend.dal.data;

import be.technobel.makerhub_backend.dal.models.entities.UserEntity;
import be.technobel.makerhub_backend.dal.models.enums.UserRole;
import be.technobel.makerhub_backend.dal.repositories.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements InitializingBean {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //ADMIN - Main
        UserEntity hybridvision = new UserEntity();
        hybridvision.setId(1L);
        hybridvision.setUsername("hybridvision");
        hybridvision.setFirstName("Hybrid");
        hybridvision.setLastName("Vision");
        hybridvision.setEmail("hybridvisionmusic@gmail.com");
        hybridvision.setActive(true);
        hybridvision.setBlocked(false);
        hybridvision.setRole(UserRole.ADMIN);
        hybridvision.setPassword(passwordEncoder.encode("Test1234="));
        userRepository.save(hybridvision);

        //ADMIN - Ismail
        UserEntity ismail = new UserEntity();
        ismail.setId(2L);
        ismail.setUsername("ismailjacoby");
        ismail.setFirstName("Ismail");
        ismail.setLastName("Jacoby");
        ismail.setEmail("ismailjacoby@gmail.com");
        ismail.setActive(true);
        ismail.setBlocked(false);
        ismail.setRole(UserRole.ADMIN);
        ismail.setPassword(passwordEncoder.encode("Test1234="));
        userRepository.save(ismail);

        //ADMIN - Ismail
        UserEntity francisco = new UserEntity();
        francisco.setId(3L);
        francisco.setUsername("franciscolopesmorais");
        francisco.setFirstName("Francisco");
        francisco.setLastName("Lopes Morais");
        francisco.setEmail("n1djfable@gmail.com");
        francisco.setActive(true);
        francisco.setBlocked(false);
        francisco.setRole(UserRole.ADMIN);
        francisco.setPassword(passwordEncoder.encode("Test1234="));
        userRepository.save(francisco);

        //CLIENT - string
        UserEntity user1 = new UserEntity();
        user1.setId(4L);
        user1.setUsername("string");
        user1.setFirstName("String");
        user1.setLastName("String");
        user1.setEmail("string@gmail.com");
        user1.setActive(true);
        user1.setBlocked(false);
        user1.setRole(UserRole.CLIENT);
        user1.setPassword(passwordEncoder.encode("Test1234="));
        userRepository.save(user1);

        //CLIENT - string
        UserEntity user2 = new UserEntity();
        user2.setId(5L);
        user2.setUsername("test");
        user2.setFirstName("test");
        user2.setLastName("test");
        user2.setEmail("test@gmail.com");
        user2.setActive(true);
        user2.setBlocked(false);
        user2.setRole(UserRole.CLIENT);
        user2.setPassword(passwordEncoder.encode("Test1234="));
        userRepository.save(user2);
    }
}
